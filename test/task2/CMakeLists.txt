file(REAL_PATH ../rtlib _rtlib_dir)
file(REAL_PATH ../task0 _task0_out BASE_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR})
file(REAL_PATH ../task1 _task1_out BASE_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR})

# 生成实验的全部答案
add_custom_target(
  task2-answer
  bash ${CMAKE_CURRENT_SOURCE_DIR}/answer.sh ${CLANG_EXECUTABLE} ${_rtlib_dir}
  ${TASK_CASES_DIR} ${CMAKE_CURRENT_BINARY_DIR}
  USES_TERMINAL
  SOURCES answer.sh)

# 对实验进行评分
add_custom_target(
  task2-score
  ${CTEST_COMMAND}
  COMMAND ${Python3_EXECUTABLE} ${CMAKE_CURRENT_SOURCE_DIR}/score.py
  WORKING_DIRECTORY ${CMAKE_CURRENT_BINARY_DIR}
  USES_TERMINAL
  SOURCES score.py)

add_dependencies(task2-score task2 task2-answer)

# 生成测例权重文件
if(NOT TASK2_CASES_WEIGHT_TXT)
  set(_weight_txt ${CMAKE_CURRENT_BINARY_DIR}/weight.txt)
  string(REPLACE ";" "\n" _lines "${TEST_CASES}")
  file(WRITE ${_weight_txt} "${_lines}")
  set(TASK2_CASES_WEIGHT_TXT ${_weight_txt})
endif()

# 为每个测例创建一个测试
if(TASK2_REVIVE)
  # 如果启用复活，则将前一个实验的标准答案作为输入
  add_dependencies(task2-score task1-answer)

  foreach(_case ${TASK_CASES})
    set(_output_dir ${CMAKE_CURRENT_BINARY_DIR}/${_case})
    file(MAKE_DIRECTORY ${_output_dir})
    add_test(NAME task2/${_case}
             COMMAND task2 ${_task1_out}/${_case}/answer.txt
                     ${_output_dir}/output.json)
  endforeach()

  message(AUTHOR_WARNING "在实验二复活：请在构建 task1-answer 后再使用 task2 的测试项目。")

else()
  # 否则以实验零的标准答案作为输入
  add_dependencies(task2-score task0-answer)

  foreach(_case ${TASK_CASES})
    set(_output_dir ${CMAKE_CURRENT_BINARY_DIR}/${_case})
    file(MAKE_DIRECTORY ${_output_dir})
    add_test(NAME task2/${_case} COMMAND task2 ${_task0_out}/${_case}
                                         ${_output_dir}/output.json)
  endforeach()

  message(AUTHOR_WARNING "实验二复活已禁用，请在构建 task0-answer 后再使用 task2 的测试项目。")

endif()
