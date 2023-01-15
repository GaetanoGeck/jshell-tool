#!/usr/bin/env bash


updateClasspathsFromPom() {
	if [[ ! -f "pom.xml" ]]; then
		echo 1>&2 "Error: POM is missing"
		exit 1
	fi
	mvn dependency:build-classpath -DincludeTypes=jar -Dmdep.outputFile=classpaths-from-pom.generated.txt
}


updateClasspathsFromPom
