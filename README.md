# JexPath Filter

A Java library that implements a custom filter syntax for JSON data processing, designed to support 3GPP JEX (JSON Expression) specifications with enhanced filtering capabilities.

[![Java CI](https://github.com/tusharjoshi/jex-filter/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/tusharjoshi/jex-filter/actions/workflows/main.yml)

## Overview

This project provides a JexPath Filter implementation that transforms custom filter expressions into standard JSONPath queries. It's built on top of the established [Jayway JsonPath](https://github.com/json-path/JsonPath) library and includes necessary customizations to support 3GPP JEX specifications.

## Objective

The primary objective is to create an example JEX (JSON Expression) parser that:

- Provides a simplified syntax for complex JSON filtering operations
- Supports 3GPP JEX specifications for telecommunications data processing
- Offers logical operators (`and`, `or`) and pipe-separated value matching
- Maintains compatibility with standard JSONPath while adding custom enhancements

## References

- [3GPP TS 28.532 - Generic management services](https://www.3gpp.org/DynaReport/28532.htm)
- [Jayway JsonPath Documentation](https://github.com/json-path/JsonPath)
- [JSONPath Specification](https://goessner.net/articles/JsonPath/)

## Build and test

Quick commands to build and run tests locally (requires Java 17):

```bash
# Use a Java 17 installation. Either set JAVA_HOME or point org.gradle.java.home.
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
./gradlew clean build
```

CI tips:
- In CI, use a Java 17 runner and set JAVA_HOME to the JDK path before running the wrapper.
- Alternatively remove `org.gradle.java.home` from `gradle.properties` and rely on the environment's
	`JAVA_HOME` setting to keep the repository portable across platforms.
