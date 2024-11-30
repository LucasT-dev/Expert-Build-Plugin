package fr.marodeur.expertbuild.api.fawe.function.mask;

import com.fastasyncworldedit.core.extension.factory.parser.RichParser;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.mask.Mask;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class OngroundMask extends RichParser<Mask> {

    public OngroundMask(WorldEdit paramWorldEdit) {
        super(paramWorldEdit, "#onground");
    }

    @Override
    protected Stream<String> getSuggestions(String paramString, int paramInt) {

        return (paramInt == 1) ? this.worldEdit.getMaskFactory().getSuggestions(paramString).stream() :
                ((paramInt == 0) ? this.worldEdit.getMaskFactory().getSuggestions(paramString).stream() :
                        Arrays.asList("")
                                .stream());    }

    @Override
    protected Mask parseFromInput(@NotNull String @NotNull [] arguments, ParserContext context) throws InputParseException {

        try {
            return new OngroundExtendMask(Objects.requireNonNull(context.getSession()).createEditSession(context.getActor()), arguments);
        } catch (InputParseException inputParseException) {
            throw new InputParseException("#onground[ Ground block ][ Applied on (air) ], Example : onground[grass_block][air]");
        }
    }
}
