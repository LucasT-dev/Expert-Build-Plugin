package fr.Marodeur.ExpertBuild.API.FAWE.mask;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.mask.AbstractMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockState;
import org.jetbrains.annotations.NotNull;

public class OngroundExtendMask extends AbstractMask {

    private final String[] argument;
    private final EditSession editSession;
    private final BlockState directMask;
    private final BlockState lowermask;

    public OngroundExtendMask(EditSession editSession, String @NotNull [] argument) {

        this.argument = argument;
        this.editSession = editSession;
        this.lowermask = BlockState.get(argument[0]);
        this.directMask = BlockState.get(argument[1]);

    }

    public boolean test(Extent extent, BlockVector3 position) {
        return test(position);
    }

    @Override
    public boolean test(BlockVector3 vector) {

        if (editSession.getBlock(vector).equals(directMask)) {
            return editSession.getBlock(vector.add(0,-1,0)).equals(lowermask);
        }

        return false;
    }

    @Override
    public Mask copy() {

        return new OngroundExtendMask(this.editSession, this.argument);
    }
}
