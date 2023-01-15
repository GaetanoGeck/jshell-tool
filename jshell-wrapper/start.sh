#!/usr/bin/env bash

classPath=`cat resources/classpaths-from-pom.generated.txt` 
jshell resources/setup.jsh -class-path "$classPath" --enable-preview
