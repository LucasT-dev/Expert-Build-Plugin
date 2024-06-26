package fr.marodeur.expertbuild.api.fawe;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
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

    private final BukkitPlayer bukkitPlayer;

    public FaweAPI(Player p) {
        this.bukkitPlayer = BukkitAdapter.adapt(p);
    }

    public FaweAPI(BrushBuilder brushBuilder) {
        this.bukkitPlayer = BukkitAdapter.adapt(brushBuilder.getPlayer());
    }

    public ClipboardHolder copySelection(boolean copingBiomes, boolean copingEntities, boolean saveInClipboard, boolean sendMessage) {
        return this.copySelection(copingBiomes, copingEntities, saveInClipboard, sendMessage, new BlockVectorTool().toBlockVectorTool(this.bukkitPlayer.getSession().getPlacementPosition(this.bukkitPlayer)));
    }

    public ClipboardHolder copySelection(boolean copingBiomes, boolean copingEntities, boolean saveInClipboard, boolean sendMessage, BlockVectorTool origin) {

        Region region = this.bukkitPlayer.getSelection();
        LocalSession session = this.bukkitPlayer.getSession();
        EditSession editSession = session.createEditSession(this.bukkitPlayer);

        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(origin.toBlockVector3());

        ForwardExtentCopy copy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

        copy.setCopyingBiomes(copingBiomes);
        copy.setCopyingEntities(copingEntities);


        Operations.complete(copy);

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
        pasteClipboard(this.bukkitPlayer.getSession().getClipboard(), ignoreAirBlock, false, false , to, sendMessage, 0, 0, 0);
    }

    public void pasteClipboard(ClipboardHolder clipboardHolder, boolean ignoreAirBlock, boolean copingEntities, boolean copingBiomes, BlockVectorTool to , boolean sendMessage, double rotateX, double rotateY, double rotateZ) {

        LocalSession localSession = this.bukkitPlayer.getSession();
        EditSession editSession = localSession.createEditSession(this.bukkitPlayer);

        // Vérification de l'initialisation de clipboardHolder
        if (clipboardHolder == null || clipboardHolder.getClipboard() == null) {
            this.bukkitPlayer.getPlayer().sendMessage(Main.prefix + "ClipboardHolder or Clipboard is null.");
            return;
        }

        // Appliquer les rotations
        AffineTransform transform = new AffineTransform();

        if (rotateX != 0) {
            transform = transform.rotateX(-rotateX);
        }
        if (rotateY != 0) {
            transform = transform.rotateY(-rotateY);
        }
        if (rotateZ != 0) {
            transform = transform.rotateZ(-rotateZ);
        }

        // Créer une nouvelle instance de ClipboardHolder avec la transformation
        ClipboardHolder transformedClipboardHolder = new ClipboardHolder(clipboardHolder.getClipboard());
        transformedClipboardHolder.setTransform(transform);


        Operation operation = transformedClipboardHolder

                .createPaste(editSession)
                .to(BlockVector3.at(to.getBlockX(), to.getBlockY(), to.getBlockZ()))
                .ignoreAirBlocks(ignoreAirBlock)
                .copyBiomes(copingBiomes)
                .copyEntities(copingEntities)
                .build();

        try {

            Operations.completeLegacy(operation);

            // Enregistrer l'opération pour permettre dans l'historique du joueur
            localSession.remember(editSession);

        } catch (WorldEditException e) {

            this.bukkitPlayer.getPlayer().sendMessage(Main.prefix + "Failed to paste clipboard: " + e.getMessage());

        } finally {
            // Assurer que l'editSession est fermée pour éviter les fuites de ressources
            editSession.close();

            if (sendMessage) this.bukkitPlayer.getPlayer().sendMessage(Main.prefix + "Clipboard paste at (" +
                    to.getBlockX() + ", " + to.getBlockY() + ", " + to.getBlockZ() + ")");
        }
    }
}
