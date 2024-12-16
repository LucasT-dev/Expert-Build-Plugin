package fr.marodeur.expertbuild.api.fawe.factory.parser;

import com.fastasyncworldedit.core.extension.factory.parser.RichParser;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.pattern.Pattern;
import fr.marodeur.expertbuild.api.fawe.function.pattern.TypeChangePattern;
import fr.marodeur.expertbuild.enums.BlockCategoryEnum;
import fr.marodeur.expertbuild.object.block.category.TypeCategory;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

public class TypeChangeParser extends RichParser<Pattern> {

    /**
     * Create a new rich parser with a defined prefix for the result, e.g. {@code #simplex}.
     *
     * @param worldEdit the worldedit instance.
     */
    public TypeChangeParser(WorldEdit worldEdit) {
        super(worldEdit, "#typechange", "#tc");
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
            case 0 -> Arrays.stream(BlockCategoryEnum.values())
                    .filter(blockCat -> blockCat.getCategoryType() instanceof TypeCategory)
                    .map(BlockCategoryEnum::getName);

            default -> Stream.empty();
        };
    }

    @Override
    protected Pattern parseFromInput(@NotNull String[] arguments, ParserContext context) throws InputParseException {

        try {
            return new TypeChangePattern(context.getSession().createEditSession(context.getActor()), arguments);

        } catch (InputParseException inputParseException) {
            throw new InputParseException("#tc[typeCategory], Example : #tc[spruce]");
        }
    }
}
