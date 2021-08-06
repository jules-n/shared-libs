# shared-libs
SS project shared libraries.

# usage
Designed to be used in local installation. 

To install all libs locally:
```sh
$ cd shared-libs
$ ./gradlew publishToMavenLocal
```

To install one particular lib (non-functional-lib) only:
```sh
$ cd shared-libs
$ ./gradlew :non-functional-lib:publishToMavenLocal
```
