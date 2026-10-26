# torte-FeatJAR

*This repository contains a FeatJAR module that transforms feature-model formulas into various formats.*

This repository is intended to be used from within [torte](https://github.com/ekuiter/torte), which provides full Docker automation for the transformations implemented here.
Note that this code is generally geared towards the version of FeatJAR pinned within torte.

Alternatively, use Java 21 or newer and clone this module inside the FeatJAR monorepo:

```
git clone https://github.com/FeatureIDE/FeatJAR.git
cd FeatJAR
git clone https://github.com/ekuiter/torte-FeatJAR.git
./gradlew -p torte-FeatJAR assemble
java -jar torte-FeatJAR/build/libs/torte-FeatJAR-all.jar --help
```

## License

The source code of this project is released under the [LGPL v3 license](LICENSE.txt).
