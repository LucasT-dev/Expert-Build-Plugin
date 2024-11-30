package fr.marodeur.expertbuild.api.fawe.factory.parser;

import com.fastasyncworldedit.core.extension.factory.parser.RichParser;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.command.util.SuggestionHelper;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.pattern.Pattern;
import fr.marodeur.expertbuild.api.fawe.function.pattern.SquarePattern;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class SquarePatternParser extends RichParser<Pattern> {

    /**
     * Create a new rich parser with a defined prefix for the result, e.g. {@code #simplex}.
     *
     * @param worldEdit the worldedit instance.
     */
    public SquarePatternParser(WorldEdit worldEdit) {
        super(worldEdit, "#brick", "#bri");
    }


    /**
     *
     * @param argumentInput the already provided input for the argument at the given index.
     * @param index         the index of the argument to get suggestions for.
     * @return
     *
     * Example : //set #square[pattern,pattern][]
     */
    @Override
    protected Stream<String> getSuggestions(String argumentInput, int index) {

        return switch (index) {
            case 0 -> this.worldEdit.getBlockFactory().getSuggestions(argumentInput).stream();
            case 1, 2, 3 -> SuggestionHelper.suggestPositiveIntegers(argumentInput);
            default -> Stream.empty();
        };
    }

    @Override
    protected Pattern parseFromInput(@NotNull String[] arguments, ParserContext context) throws InputParseException {

        try {
            return new SquarePattern(context.getSession().createEditSession(context.getActor()), arguments);

        } catch (InputParseException inputParseException) {
            throw new InputParseException("#brick[block1,block2][length][width][border thickness][offset], Example : #brick[blue_wool,white_wool][10][3][1][5]");
        }
    }
}
