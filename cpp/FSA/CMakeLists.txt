cmake_minimum_required(VERSION 3.12)
project(FSA)

set(CMAKE_CXX_STANDARD 14)

add_executable(fsa tester.cpp Node.h FSA.h Node_partial.cpp FSA_partial.cpp)
add_executable(pics tester_pictures.cpp Node.h)