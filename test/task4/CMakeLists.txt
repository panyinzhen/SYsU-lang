file(REAL_PATH ../cases _cases_dir)
file(REAL_PATH ../rtlib _rtlib_dir)
file(REAL_PATH ../task0 _task0_out BASE_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR})
file(REAL_PATH ../task3 _task3_out BASE_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR})

# 生成实验三的全部答案
add_custom_target(
  task4-answer
  bash ${CMAKE_CURRENT_SOURCE_DIR}/answer.sh ${CLANG_EXECUTABLE} ${_rtlib_dir}
  ${_cases_dir} ${CMAKE_CURRENT_BINARY_DIR}
  USES_TERMINAL
  SOURCES answer.sh)

# 为每个测试用例生成一个测试
file(
  GLOB_RECURSE _cases
  RELATIVE ${_cases_dir}
  ${_cases_dir}/*.sysu.c)

if(TASK4_REVIVE)
  # 如果启用复活，则将前一个实验的标准答案作为输入
  foreach(_case ${_cases})
    set(_output_dir ${CMAKE_CURRENT_BINARY_DIR}/${_case})
    file(MAKE_DIRECTORY ${_output_dir})
    add_test(NAME task4/${_case}
             COMMAND task4 ${_task3_out}/${_case}/answer.ll
                     ${_output_dir}/output.ll)
  endforeach()

  message(AUTHOR_WARNING "在实验四复活：请在构建 task3-answer 后再使用 task4 的测试项目。")

else()
  # 否则以实验零的标准答案作为输入
  foreach(_case ${_cases})
    set(_output_dir ${CMAKE_CURRENT_BINARY_DIR}/${_case})
    file(MAKE_DIRECTORY ${_output_dir})
    add_test(NAME task4/${_case} COMMAND task3 ${_task0_out}/${_case}
                                         ${_output_dir}/output.ll)
  endforeach()

  message(AUTHOR_WARNING "实验四复活已禁用，请在构建 task0-answer 后再使用 task4 的测试项目。")

endif()

# 对实验一进行评分
add_custom_target(
  task4-score
  ${Python3_EXECUTABLE} ${CMAKE_CURRENT_SOURCE_DIR}/score.py
  USES_TERMINAL
  SOURCES score.py)
