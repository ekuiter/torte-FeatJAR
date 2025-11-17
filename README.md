# torte-FeatJAR

*This repository contains a FeatJAR module that transforms feature-model formulas into various formats.*

This repository is intended to be used from within [torte](https://github.com/ekuiter/torte), which provides full Docker automation for the transformations implemented here.
Note that this code is generally geared towards the versions of these repositories pinned within torte.

Alternatively, this module can be cloned alongside its dependencies by creating a fresh installation of FeatJAR according to its README file, using the following `repo.txt`:

```
https://github.com/FeatureIDE/FeatJAR-base.git base
https://github.com/FeatureIDE/FeatJAR-formula.git formula
https://github.com/FeatureIDE/FeatJAR-formula-analysis-javasmt.git formula-analysis-javasmt
https://github.com/FeatureIDE/FeatJAR-bin-javasmt.git bin-javasmt
https://github.com/ekuiter/torte-FeatJAR.git torte-FeatJAR
```

## License

The source code of this project is released under the [LGPL v3 license](LICENSE.txt).