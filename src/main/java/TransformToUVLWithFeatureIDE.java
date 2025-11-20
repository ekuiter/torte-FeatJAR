import de.featjar.base.cli.Commands;
import de.ovgu.featureide.fm.core.io.manager.FileHandler;
import de.ovgu.featureide.fm.core.io.uvl.UVLFeatureModelFormat;

import java.nio.file.Path;
import java.time.Duration;

public class TransformToUVLWithFeatureIDE implements ITransformation {
    public void transform(Path inputPath, Path outputPath, Duration timeout) {
        Commands.runInThread(() -> {
            FileHandler.save(
                    outputPath,
                    ITransformation.loadFormulaFileWithFeatureIDE(inputPath),
                    new UVLFeatureModelFormat());
        }, timeout);
    }
}