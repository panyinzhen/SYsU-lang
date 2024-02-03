set(_cases_dir ${CMAKE_CURRENT_SOURCE_DIR}/../cases)

# 生成实验一的全部答案
add_custom_target(
  task1-answer
  bash ${CMAKE_CURRENT_SOURCE_DIR}/answer.sh ${CLANG_PATH} ${_cases_dir}
  ${CMAKE_CURRENT_BINARY_DIR}
  USES_TERMINAL
  SOURCES answer.sh)

# 为每个测试用例生成一个测试
file(
  GLOB_RECURSE _cases
  RELATIVE ${_cases_dir}
  ${_cases_dir}/*.sysu.c)

foreach(_case ${_cases})
  set(_output_dir ${CMAKE_CURRENT_BINARY_DIR}/${_case})
  file(MAKE_DIRECTORY ${_output_dir})
  add_test(NAME test1/${_case} COMMAND task1 ${_cases_dir}/${_case}
                                       ${_output_dir}/output)
endforeach()

# 对实验一进行评分
add_custom_target(
  task1-score
  ${Python3_EXECUTABLE} ${CMAKE_CURRENT_SOURCE_DIR}/score.py
  USES_TERMINAL
  SOURCES score.py)
