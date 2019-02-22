cmake_minimum_required(VERSION 3.12)
project(TransDict)

set(CMAKE_CXX_STANDARD 14)

add_executable(TransDict TranslationDictionary.h TranslationDictionary.cpp main.cpp)