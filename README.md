[![Build Status](https://travis-ci.org/CycloneDX/cyclonedx-core-java.svg?branch=master)](https://travis-ci.org/CycloneDX/cyclonedx-core-java)
[![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)][License]


CycloneDX Core (Java)
=========

The CycloneDX core module provides a model representation of the BoM along with utilities to assist in creating and 
validating BoMs. CycloneDX is a lightweight BoM specification that is easily created, human readable, and simple to 
parse. The resulting bom.xml can be used with tools such as [OWASP Dependency-Track](https://dependencytrack.org/) 
for the continuous analysis of components.

Maven Usage
-------------------

```xml
<!-- uses default configuration -->
<plugins>
    <plugin>
        <groupId>org.cyclonedx</groupId>
        <artifactId>cyclonedx-core-java</artifactId>
        <version>1.0.0</version>
    </plugin>
</plugins>
```

Copyright & License
-------------------

CycloneDX Core (Java) is Copyright (c) Steve Springett. All Rights Reserved.

Permission to modify and redistribute is granted under the terms of the Apache 2.0 license. See the [LICENSE] file for the full license.

[License]: https://github.com/CycloneDX/cyclonedx-core-java/blob/master/LICENSE
