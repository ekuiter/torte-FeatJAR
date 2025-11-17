import de.featjar.base.Exceptions;
import de.featjar.base.FeatJAR;
import de.featjar.base.cli.ICommand;
import de.featjar.base.cli.Option;
import de.featjar.base.cli.OptionList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static de.featjar.analysis.AAnalysisCommand.TIMEOUT_OPTION;

public class Transform implements ICommand {
    private static final Option<Path> INPUT_PATH_OPTION = Option.newOption("input", Option.PathParser)
            .setDescription("Path to input file");

    private static final Option<Path> OUTPUT_PATH_OPTION = Option.newOption("output", Option.PathParser)
            .setDescription("Path to output file");

    private static final Option<ITransformation> TRANSFORMATION_OPTION = Option.newOption(
            "transformation", s -> FeatJAR.extensionPoint(Transformations.class).getMatchingExtension(s).get())
            .setDescription(() -> "Specify transformation by identifier. One of "
                    + FeatJAR.extensionPoint(Transformations.class).getExtensions().stream()
                    .map(ITransformation::getIdentifier)
                    .map(String::toLowerCase)
                    .collect(Collectors.joining(", ")));

    @Override
    public List<Option<?>> getOptions() {
        return List.of(INPUT_PATH_OPTION, OUTPUT_PATH_OPTION, TRANSFORMATION_OPTION, TIMEOUT_OPTION);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Transforms feature-model formulas");
    }

    @Override
    public int run(OptionList optionParser) {
        Path inputPath = optionParser.get(INPUT_PATH_OPTION);
        Path outputPath = optionParser.get(OUTPUT_PATH_OPTION);
        Duration timeout = optionParser.get(TIMEOUT_OPTION);
        if (timeout.equals(Duration.ZERO))
            timeout = null;
        ITransformation transformation =
                optionParser.get(TRANSFORMATION_OPTION);
        if (!Files.exists(inputPath)) {
            throw new IllegalArgumentException("input file " + inputPath + " does not exist");
        }
        FeatJAR.log().info("transforming");
        FeatJAR.log().info(String.format("input: %s", inputPath));
        FeatJAR.log().info(String.format("output: %s", outputPath));
        FeatJAR.log().info(String.format("timeout: %s", timeout));
        FeatJAR.log().info(String.format("transformation: %s", transformation.getClass().getSimpleName()));
        try {
            transformation.transform(inputPath, outputPath, timeout);
        } catch (Exception e) {
            if (Exceptions.isCausedBy(e, TimeoutException.class)) {
                System.err.printf("transformation of %s aborted due to timeout%n", inputPath);
                System.exit(0);
            } else
                throw new RuntimeException(e);
        }
        return 0;
    }
}
