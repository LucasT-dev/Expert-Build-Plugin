package fr.marodeur.expertbuild.api.fawe;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BlockVectorTool;
import fr.marodeur.expertbuild.object.BrushBuilder;
import fr.marodeur.expertbuild.object.Message;

import org.bukkit.entity.Player;

public class FaweAPI {

    BukkitPlayer bukkitPlayer;

    public FaweAPI(Player p) {
        this.bukkitPlayer = BukkitAdapter.adapt(p);
    }

    public FaweAPI(BrushBuilder brushBuilder) {
        this.bukkitPlayer = BukkitAdapter.adapt(brushBuilder.getPlayer());
    }


    public ClipboardHolder copySelection(boolean copingBiomes, boolean copingEntities, boolean saveInClipboard, boolean sendMessage) {

        Region region = this.bukkitPlayer.getSelection();
        LocalSession session = this.bukkitPlayer.getSession();
        EditSession editSession = session.createEditSession(this.bukkitPlayer);

        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(session.getPlacementPosition(this.bukkitPlayer));

        ForwardExtentCopy copy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

        copy.setCopyingBiomes(copingBiomes);
        copy.setCopyingEntities(copingEntities);

        if (editSession.getMask() != null) {
            copy.setSourceMask(editSession.getMask());
        }

        Operations.completeLegacy(copy);

        if (saveInClipboard) {
            session.setClipboard(new ClipboardHolder(clipboard));
        }

        if (sendMessage) bukkitPlayer.getPlayer().sendMessage(new Message.MessageSender("expbuild.message.selection.copy_block", true, new String[]{String.valueOf(region.getArea())}).getMessage());

        return new ClipboardHolder(clipboard);
    }

    public ClipboardHolder rotateClipboard(ClipboardHolder clipboard, double rotateX, double rotateY, double rotateZ) {

        ClipboardHolder holder = clipboard;
        AffineTransform transform = new AffineTransform();

        transform = transform.rotateY(-rotateY);
        transform = transform.rotateX(-rotateX);
        transform = transform.rotateZ(-rotateZ);

        holder.setTransform(transform.combine(holder.getTransform()));

        return holder;

    }

    public void pasteClipboard(boolean ignoreAirBlock, BlockVectorTool to , boolean sendMessage) {
        pasteClipboard(this.bukkitPlayer.getSession().getClipboard(), ignoreAirBlock, to, sendMessage);
    }

    public void pasteClipboard(ClipboardHolder clipboardHolder, boolean ignoreAirBlock, BlockVectorTool to , boolean sendMessage) {

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(bukkitPlayer.getWorld())) {

            Operation operation = clipboardHolder

                    .createPaste(editSession)
                    .ignoreAirBlocks(ignoreAirBlock)
                    .maskSource(editSession.getMask())
                    .copyBiomes(true)

                    .to(BlockVector3.at(to.getBlockX(), to.getBlockY(), to.getBlockZ()))
                    .build();

            Operations.complete(operation);
        }

        if (sendMessage) this.bukkitPlayer.getPlayer().sendMessage(Main.prefix + "Clipboard paste at (" + this.bukkitPlayer.getLocation().getBlockX() + ", "
                + this.bukkitPlayer.getLocation().getBlockY() + ", " + this.bukkitPlayer.getLocation().getBlockZ() + ")");

    }
}
