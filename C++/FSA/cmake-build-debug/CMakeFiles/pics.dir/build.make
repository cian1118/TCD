# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/cianhiggins/CLionProjects/FSA

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/cianhiggins/CLionProjects/FSA/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/pics.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/pics.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/pics.dir/flags.make

CMakeFiles/pics.dir/tester_pictures.cpp.o: CMakeFiles/pics.dir/flags.make
CMakeFiles/pics.dir/tester_pictures.cpp.o: ../tester_pictures.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/cianhiggins/CLionProjects/FSA/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/pics.dir/tester_pictures.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/pics.dir/tester_pictures.cpp.o -c /Users/cianhiggins/CLionProjects/FSA/tester_pictures.cpp

CMakeFiles/pics.dir/tester_pictures.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/pics.dir/tester_pictures.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/cianhiggins/CLionProjects/FSA/tester_pictures.cpp > CMakeFiles/pics.dir/tester_pictures.cpp.i

CMakeFiles/pics.dir/tester_pictures.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/pics.dir/tester_pictures.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/cianhiggins/CLionProjects/FSA/tester_pictures.cpp -o CMakeFiles/pics.dir/tester_pictures.cpp.s

# Object files for target pics
pics_OBJECTS = \
"CMakeFiles/pics.dir/tester_pictures.cpp.o"

# External object files for target pics
pics_EXTERNAL_OBJECTS =

pics: CMakeFiles/pics.dir/tester_pictures.cpp.o
pics: CMakeFiles/pics.dir/build.make
pics: CMakeFiles/pics.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/cianhiggins/CLionProjects/FSA/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable pics"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/pics.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/pics.dir/build: pics

.PHONY : CMakeFiles/pics.dir/build

CMakeFiles/pics.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/pics.dir/cmake_clean.cmake
.PHONY : CMakeFiles/pics.dir/clean

CMakeFiles/pics.dir/depend:
	cd /Users/cianhiggins/CLionProjects/FSA/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/cianhiggins/CLionProjects/FSA /Users/cianhiggins/CLionProjects/FSA /Users/cianhiggins/CLionProjects/FSA/cmake-build-debug /Users/cianhiggins/CLionProjects/FSA/cmake-build-debug /Users/cianhiggins/CLionProjects/FSA/cmake-build-debug/CMakeFiles/pics.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/pics.dir/depend
