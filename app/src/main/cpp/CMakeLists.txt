# = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# CMAKE
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

set( PROJECT_CMAKE_VERSION 3.22.1 )
cmake_minimum_required( VERSION "${PROJECT_CMAKE_VERSION}" )

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# PROJECT
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

set( PROJECT_NAME "AR-Diorama" )
set( APP_VERSION 0.1.0 )
set( PROJECT_DESCRIPTION "AR-Diorama app" )
project( ${PROJECT_NAME} VERSION ${APP_VERSION} DESCRIPTION ${PROJECT_DESCRIPTION} LANGUAGES C CXX )

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# C/C++
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# C++ Version
set( CMAKE_CXX_STANDARD ${PROJECT_CXX_VERSION} )
set( CMAKE_CXX_STANDARD_REQUIRED ON )

set( CMAKE_INCLUDE_CURRENT_DIR ON )

set( CMAKE_AUTOUIC ON )
set( CMAKE_AUTOMOC ON )
set( CMAKE_AUTORCC ON )

# Enable Position Independent Code
set( CMAKE_POSITION_INDEPENDENT_CODE ON )

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# HEADERS
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

set( MY_APP_HEADERS "" )

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# SOURCES
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

set( MY_APP_SOURCES "native-lib.cpp" )

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# BUILD
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# Add SHARED Library Object
add_library( ${CMAKE_PROJECT_NAME} SHARED ${MY_APP_HEADERS} ${MY_APP_SOURCES} )

# Link
target_link_libraries( ${CMAKE_PROJECT_NAME}
    android
    log
)

# = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
