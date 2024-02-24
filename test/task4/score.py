import gzip
import json
import re
import subprocess
import sys
import argparse
import logging
import os.path as osp


class Test_Report:

    def __init__(self, name, score, max_score, output, output_path=None):
        self.name = name
        self.score = score
        self.max_score = max_score
        self.output = output
        self.output_path = output_path


class LeaderBoard_Report:

    def __init__(self, name, value, order, is_desc=False, suffix=None):
        self.name = name
        self.value = value
        self.order = order
        self.is_desc = is_desc
        self.suffix = suffix


class ReportsManager:

    def __init__(self):
        self.tests = []
        self.testsleaderboard = []

    def add_test_report(self,
                        name,
                        score,
                        max_score,
                        output,
                        output_path=None):
        self.tests.append(
            Test_Report(name, score, max_score, output, output_path))

    def add_test_report_instance(self, Test_Report):
        self.tests.append(Test_Report)

    def add_leaderboard_report(self,
                               name,
                               value,
                               order,
                               is_desc=False,
                               suffix=None):
        self.testsleaderboard.append(
            LeaderBoard_Report(name, value, order, is_desc, suffix))

    def add_leaderboard_report_instance(self, LeaderBoard_Report):
        self.testsleaderboard.append(LeaderBoard_Report)

    def toJson(self):
        # 返回一个 json 字符串，它有两个属性，一个是 test_reports，一个是 leaderboard_reports
        # test_reports 是一个列表，每一个元素是一个字典，包含了一个测试报告的信息
        # leaderboard_reports 是一个列表，每一个元素是一个字典，包含了一个排行榜报告的信息
        return json.dumps(self, default=lambda o: o.__dict__, sort_keys=True)

    def toTxt(self):
        # 返回一个字符串，它包含了所有的测试报告和排行榜报告的信息
        txt = ''
        for leaderboard in self.testsleaderboard:
            txt += f'{leaderboard.name}:'.rjust(10)
            txt += f'{leaderboard.value}'.ljust(10)
            if leaderboard.suffix:
                txt += f' {leaderboard.suffix}'.ljust(20)
            txt += '\n'
        for test in self.tests:
            txt += f'{test.score:.2f}'.rjust(8)
            txt += '/'
            txt += f'{test.max_score:.2f}'.ljust(8)
            txt += f'{test.name}'.ljust(20)
            txt += f'{test.output}'.rjust(20)
            txt += '\n'
        return txt


class CustomFilter(logging.Filter):

    def __init__(self,
                 name: str = "",
                 condition_dict: dict = {},
                 condition: str = None) -> None:
        super().__init__(name)
        self.condition_dict = condition_dict
        self.condition = condition

    def filter(self, record):
        return self.condition_dict[self.condition]


def check_and_get_case(task4_logger, condition_dict, task4_test_dir,
                       task4_test_weight):
    # 判断 task4_test_dir 是否存在
    task4_test_dir = osp.abspath(task4_test_dir)
    if not osp.exists(task4_test_dir):
        task4_logger.error('task4_test_dir: %s does not exist.' %
                           task4_test_dir)
        return 0, {}
    # 判断 task4_test_weight 是否存在
    if not osp.exists(task4_test_weight):
        task4_logger.error('task4_test_weight: %s does not exist.' %
                           task4_test_weight)
        return 0, {}

    task4_test_weight_dict = {}
    try:
        with open(task4_test_weight, 'r') as f:
            for line in f:
                case_line = line.strip().split()
                if len(case_line) == 2:
                    case = case_line[0]
                    weight = case_line[1]
                    task4_test_weight_dict[case] = float(weight)
                elif len(case_line) == 1:
                    case = case_line[0]
                    task4_test_weight_dict[case] = 1.0
    except Exception as e:
        task4_logger.error('task4_test_weight: %s format error.' %
                           task4_test_weight)
        task4_logger.error(e)
        return 0, {}

    return 1, task4_test_weight_dict


def make_weighted_averge(now_weighted_averge, now_weights_sum, new_value,
                         new_weight):
    new_weights_sum = now_weights_sum + new_weight
    new_weighted_averge = now_weighted_averge + (
        new_value - now_weighted_averge) * new_weight * 1.0 / new_weights_sum
    return new_weighted_averge, new_weights_sum


def get_tm(sp):
    val = -1
    matchObj = re.findall(b'TOTAL: (\\d*)H-(\\d*)M-(\\d*)S-(\\d*)us',
                          sp.stderr)
    if len(matchObj) == 0:
        return val
    matchObj = matchObj[-1]
    val = int(matchObj[0])
    val = val * 60 + int(matchObj[1])
    val = val * 60 + int(matchObj[2])
    val = val * 1000000 + int(matchObj[3])
    return val


def score_one_case(task4_logger, condition_dict, manager, task4_test_log_level,
                   task4_test_dir, case, task4_test_clang,
                   task4_test_rtlib_path, task4_test_timeout):
    # 为每一个算例单独生成一个日志文件
    case_abs_path = osp.join(task4_test_dir, case)

    # 创建过滤器
    one_case_file_filter = CustomFilter(name='one_case_file_filter',
                                        condition_dict=condition_dict,
                                        condition='one_case_file')

    # 创建文件处理器
    one_case_file_path = osp.join(case_abs_path, 'score.txt')
    file_handler = logging.FileHandler(one_case_file_path, mode='w')
    file_handler.addFilter(one_case_file_filter)
    file_handler.setFormatter(logging.Formatter('%(message)s'))
    file_handler.setLevel(logging.INFO)
    task4_logger.addHandler(file_handler)
    condition_dict['one_case_file'] = True
    condition_dict['all_cases_file'] = False
    condition_dict['console'] = False

    task4_logger.info('测试用例: %s' % case)
    task4_logger.info('测试用例绝对路径: %s' % case_abs_path)

    score = 0.0

    def score_one_case_exit(score):
        task4_logger.info('分数: %f' % score)
        condition_dict['one_case_file'] = False
        condition_dict['all_cases_file'] = False
        condition_dict['console'] = True
        task4_logger.removeHandler(file_handler)
        return score

    # 如果 case_abs_path 下没有 answer.ll 文件，就返回 0
    answer_path = osp.join(case_abs_path, 'answer.ll')
    if not osp.exists(answer_path):
        task4_logger.error('%s 的标准答案未生成，请先生成 task4-answer' % case_abs_path)
        score = 0.0
        manager.add_test_report(case, score, 100.0, '标准答案未生成',
                                one_case_file_path)
        return score_one_case_exit(score)

    # 如果 case_abs_path 下没有 output.ll 文件，就返回 0
    output_path = osp.join(case_abs_path, 'output.ll')
    if not osp.exists(output_path):
        task4_logger.error('%s 的用户答案未生成，请调用测试查看是否能正常生成' % case_abs_path)
        score = 0.0
        manager.add_test_report(case, score, 100.0, '用户答案未生成',
                                one_case_file_path)
        return score_one_case_exit(score)

    # 计算评分
    try:
        inputs = None
        gz = osp.join(case_abs_path, "answer.in.gz")
        if osp.exists(gz):
            with gzip.open(gz, "rb") as f:
                inputs = f.read()
        answer_exe = osp.join(case_abs_path, 'answer.out')
        output_exe = osp.join(case_abs_path, 'output.out')

        answer_comp_result = subprocess.run([
            task4_test_clang, "-O0", "-L" + task4_test_rtlib_path, "-o",
            answer_exe, answer_path, "-ltest-rtlib"
        ],
                                            stdout=subprocess.PIPE,
                                            stderr=subprocess.PIPE,
                                            timeout=task4_test_timeout)
        if answer_comp_result.returncode:
            task4_logger.error('\nERROR: 编译标准答案失败')
            task4_logger.error(answer_comp_result)
            score = 0.0
            manager.add_test_report(case, score, 100.0, '编译标准答案失败',
                                    one_case_file_path)
            return score_one_case_exit(score)
        answer_exec_result = subprocess.run([answer_exe],
                                            input=inputs,
                                            stdout=subprocess.PIPE,
                                            stderr=subprocess.PIPE,
                                            timeout=task4_test_timeout)
        answer_log = "clang -O3 代码执行用时: {}us, 返回值 {}".format(
            get_tm(answer_exec_result), answer_exec_result.returncode)
        task4_logger.info(answer_log)

        output_comp_result = subprocess.run([
            task4_test_clang, "-O0", "-L" + task4_test_rtlib_path, "-o",
            output_exe, output_path, "-ltest-rtlib"
        ],
                                            stdout=subprocess.PIPE,
                                            stderr=subprocess.PIPE,
                                            timeout=task4_test_timeout)
        if output_comp_result.returncode:
            task4_logger.error('\nERROR: 编译用户答案失败')
            task4_logger.error(output_comp_result)
            score = 0.0
            manager.add_test_report(case, score, 100.0, '编译用户答案失败',
                                    one_case_file_path)
            return score_one_case_exit(score)
        output_exec_result = subprocess.run([output_exe],
                                            input=inputs,
                                            stdout=subprocess.PIPE,
                                            stderr=subprocess.PIPE,
                                            timeout=task4_test_timeout)
        output_log = "sysu-lang 代码执行用时: {}us, 返回值 {}".format(
            get_tm(output_exec_result), output_exec_result.returncode)
        task4_logger.info(output_log)

        if answer_exec_result.returncode != output_exec_result.returncode:
            task4_logger.info("\n返回值不匹配")
            task4_logger.info(">----")
            task4_logger.info("标准答案返回值: %d" % answer_exec_result.returncode)
            task4_logger.info("用户答案返回值: %d" % output_exec_result.returncode)
            task4_logger.info("<----")
            score = 0.0
            manager.add_test_report(case, score, 100.0, '返回值不匹配',
                                    one_case_file_path)
            return score_one_case_exit(score)

        if answer_exec_result.stdout != output_exec_result.stdout:
            task4_logger.info("\n输出不匹配")
            task4_logger.info(">----")
            task4_logger.info("标准答案输出: %s" % answer_exec_result.stdout)
            task4_logger.info("用户答案输出: %s" % output_exec_result.stdout)
            task4_logger.info("<----")
            score = 0.0
            manager.add_test_report(case, score, 100.0, '输出不匹配',
                                    one_case_file_path)
            return score_one_case_exit(score)

    except subprocess.TimeoutExpired:
        # 判断 answer_comp_result 是否存在
        if not answer_comp_result:
            task4_logger.error('编译标准答案超时')
            score = 0.0
            manager.add_test_report(case, score, 100.0, '编译标准答案超时',
                                    one_case_file_path)
            return score_one_case_exit(score)
        # 判断 answer_exec_result 是否存在
        if not answer_exec_result:
            task4_logger.error('运行标准答案超时')
            score = 0.0
            manager.add_test_report(case, score, 100.0, '运行标准答案超时',
                                    one_case_file_path)
            return score_one_case_exit(score)
        # 判断 output_comp_result 是否存在
        if not output_comp_result:
            task4_logger.error('编译用户答案超时')
            score = 0.0
            manager.add_test_report(case, score, 100.0, '编译用户答案超时',
                                    one_case_file_path)
            return score_one_case_exit(score)
        # 判断 output_exec_result 是否存在
        if not output_exec_result:
            task4_logger.error('运行用户答案超时')
            score = 0.0
            manager.add_test_report(case, score, 100.0, '运行用户答案超时',
                                    one_case_file_path)
            return score_one_case_exit(score)
    except Exception as e:
        # 输出异常信息，及在哪一行出现的异常
        task4_logger.error('评分出错')
        task4_logger.error(e)
        task4_logger.error('出错位置: %s' % e.__traceback__.tb_lineno)
        task4_logger.error('出错文件: %s' %
                           e.__traceback__.tb_frame.f_globals['__file__'])
        score = 0.0
        manager.add_test_report(case, score, 100.0, '评分出错', one_case_file_path)
        return score_one_case_exit(score)

    if get_tm(answer_exec_result) == -1 or get_tm(answer_exec_result) == 0:
        task4_logger.info('\nERROR: 标准答案未计时')
        score = 0.0
        manager.add_test_report(case, score, 100.0, '标准答案未计时',
                                one_case_file_path)
        return score_one_case_exit(score)
    if get_tm(output_exec_result) == -1 or get_tm(output_exec_result) == 0:
        task4_logger.info('\nERROR: 用户答案未计时')
        score = 0.0
        manager.add_test_report(case, score, 100.0, '用户答案未计时',
                                one_case_file_path)
        return score_one_case_exit(score)
    score = get_tm(answer_exec_result) * 1.0 / get_tm(
        output_exec_result) * 100.0
    task4_logger.info('\nINFO: 测试用例 %s 通过' % case)
    # 计算评分
    manager.add_test_report(case, score, 100.0, '通过正确性测试', one_case_file_path)
    return score_one_case_exit(score)


def score_all_case(task4_logger, condition_dict, manager, task4_test_log_level,
                   task4_test_dir, task4_test_weight, task4_test_clang,
                   task4_test_rtlib_path, task4_test_timeout):
    # 检查 task4_test_dir, task4_case_dir, task4_test_weight 是否存在, 并获取算例名字
    flag, task4_test_weight_dict = check_and_get_case(task4_logger,
                                                      condition_dict,
                                                      task4_test_dir,
                                                      task4_test_weight)

    if not flag:
        return 0

    # 对每一个算例进行评分
    weighted_average_score = 0.0
    weights_sum = 0.0
    case_idx = 1
    case_len = len(task4_test_weight_dict)
    for case, weight in task4_test_weight_dict.items():
        score = score_one_case(task4_logger, condition_dict, manager,
                               task4_test_log_level, task4_test_dir, case,
                               task4_test_clang, task4_test_rtlib_path,
                               task4_test_timeout)
        task4_logger.info(f'[{case_idx}/{case_len}] {case} 分数: {score}')
        weighted_average_score, weights_sum = make_weighted_averge(
            weighted_average_score, weights_sum, score, weight)
        case_idx += 1

    manager.add_leaderboard_report("总分", weighted_average_score, 1, True)
    task4_logger.info('总分: %f' % weighted_average_score)
    return 1


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('task4_test_dir', type=str, help='task4 的测试总目录')
    parser.add_argument('task4_test_weight',
                        type=str,
                        help='保存 task4 的测试用例及权重的文件')
    parser.add_argument('task4_test_clang',
                        type=str,
                        help='用于编译 task4 的 clang 路径')
    parser.add_argument('task4_test_rtlib_path',
                        type=str,
                        help='保存 task4 的运行时库的路径')
    parser.add_argument('task4_test_timeout',
                        type=int,
                        help='保存 task4 的测试用例及权重的文件')
    # parser.add_argument('task4_test_log_level',
    #                     type=int,
    #                     help='保存 task4 的测试用例及权重的文件')
    args = parser.parse_args()
    args.task4_test_log_level = 1
    # 判断输入参数是否合法
    if args.task4_test_log_level < 1 or args.task4_test_log_level > 3:
        raise ValueError('task4_test_log_level must be 1, 2 or 3.')

    # 将路径转换为绝对路径
    args.task4_test_dir = osp.abspath(args.task4_test_dir)
    args.task4_test_weight = osp.abspath(args.task4_test_weight)
    condition_dict = {
        'console': True,
        'all_cases_file': False,
        'one_case_file': False
    }

    # 生成总成绩单的日志保存到 task4_test_dir 下的 score.txt 文件中
    all_cases_file_filter = CustomFilter(name='all_cases_file_filter',
                                         condition_dict=condition_dict,
                                         condition='all_cases_file')
    scoresfile_for_all_cases = osp.abspath(
        osp.join(args.task4_test_dir, 'score.txt'))
    file_handler = logging.FileHandler(scoresfile_for_all_cases, mode='w')
    file_handler.addFilter(all_cases_file_filter)
    file_handler.setLevel(logging.INFO)

    # 生成控制台的日志
    console_filter = CustomFilter(name='console_filter',
                                  condition_dict=condition_dict,
                                  condition='console')
    console_handler = logging.StreamHandler(stream=sys.stdout)
    console_handler.addFilter(console_filter)
    console_handler.setLevel(logging.INFO)

    # 设置日志格式
    formatter = logging.Formatter('%(message)s')
    file_handler.setFormatter(formatter)
    console_handler.setFormatter(formatter)

    # 设置日志产生器
    task4_logger = logging.getLogger('task4')
    task4_logger.setLevel(logging.INFO)
    task4_logger.addHandler(file_handler)
    task4_logger.addHandler(console_handler)

    # 打印输入参数
    task4_logger.info('Task4 测试总目录路径: %s' % args.task4_test_dir)
    task4_logger.info('Task4 测试用例及权重文件路径: %s' % args.task4_test_weight)

    task4_logger.info('-' * 40)
    manager = ReportsManager()
    # 对 task4 的结果进行评分
    grade_done = score_all_case(task4_logger, condition_dict, manager,
                                args.task4_test_log_level, args.task4_test_dir,
                                args.task4_test_weight, args.task4_test_clang,
                                args.task4_test_rtlib_path,
                                args.task4_test_timeout)
    if grade_done:
        task4_logger.info('Task4 评分完成.')
    else:
        task4_logger.error('Task4 评分出错.')
    task4_logger.info('-' * 40)
    results_txt = manager.toTxt()
    condition_dict['all_cases_file'] = True
    task4_logger.info(results_txt)
    results_json = manager.toJson()
