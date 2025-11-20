import de.featjar.base.cli.Commands;
import de.featjar.base.io.IO;
import de.featjar.formula.computation.ComputeCNFFormula;
import de.featjar.formula.computation.ComputeNNFFormula;
import de.featjar.formula.io.dimacs.FormulaDimacsFormat;

import java.nio.file.Path;
import java.time.Duration;

import static de.featjar.base.computation.Computations.async;

public class TransformToDIMACSWithFeatJAR implements ITransformation {
    public void transform(Path inputPath, Path outputPath, Duration timeout) {
        Commands.runInThread(() -> {
            IO.save(async(ITransformation.loadFormulaFileWithFeatJAR(inputPath))
                    .map(ComputeNNFFormula::new)
                    .map(ComputeCNFFormula::new)
                    .get()
                    .get(), outputPath, new FormulaDimacsFormat());
        }, timeout);
    }
}