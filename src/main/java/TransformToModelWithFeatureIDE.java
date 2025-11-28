import de.featjar.base.cli.Commands;
import de.ovgu.featureide.fm.core.io.manager.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class TransformToModelWithFeatureIDE implements ITransformation {


    public void transform(Path inputPath, Path outputPath, Duration timeout) {
        Commands.runInThread(() -> {
            FileHandler.save(
                    outputPath,
                    ITransformation.loadFormulaFileWithFeatureIDE(inputPath),
                    new KConfigReaderFormat()
            );
        }, timeout);
    }
}