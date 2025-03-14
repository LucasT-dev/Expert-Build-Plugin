package fr.marodeur.expertbuild.api.fawe;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.command.tool.DistanceWand;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.extension.platform.permission.ActorSelectorLimits;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.block.BaseBlock;

import com.sk89q.worldedit.world.block.BlockCategory;
import com.sk89q.worldedit.world.block.BlockTypes;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.function.blockMask.StainedGlassCategory;
import fr.marodeur.expertbuild.api.fawe.function.blockMask.StainedGlassPaneCategory;
import fr.marodeur.expertbuild.api.fawe.function.blockMask.StrippedCategory;
import fr.marodeur.expertbuild.object.*;

import org.bukkit.Chunk;
import org.bukkit.Warning;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class FaweAPI {

    /**
     *
     * <a href="https://worldedit.enginehub.org/en/latest/api/examples/clipboard/#schematic-examples">...</a>
     * <p>
     * <a href="https://madelinemiller.dev/blog/how-to-load-and-save-schematics-with-the-worldedit-api/#loading">...</a>
     *
     */

    private final BukkitPlayer bukkitPlayer;

    public FaweAPI(Player p) {
        this.bukkitPlayer = BukkitAdapter.adapt(p);
    }

    public FaweAPI(BrushBuilder brushBuilder) {
        this.bukkitPlayer = BukkitAdapter.adapt(brushBuilder.getPlayer());
    }


    private LocalSession getLocalSession() {
        return this.bukkitPlayer.getSession();
    }

    private RegionSelector getRegionSelector() {
        return getLocalSession().getRegionSelector(this.bukkitPlayer.getWorld());
    }

    public boolean getFarwandActive() {
        return getLocalSession().getTool(bukkitPlayer) instanceof DistanceWand;
    }


    public void setSelectionType(RegionSelectorType regionSelectorType) {
        this.getLocalSession().setRegionSelector(this.bukkitPlayer.getWorld(), regionSelectorType.createSelector());
    }

    public void setPrimaryPos(BlockVector3 bv3) {
        this.getRegionSelector().selectPrimary(bv3, ActorSelectorLimits.forActor(this.bukkitPlayer));
    }

    public BlockVector3 getPrimaryPos() {

        return this.getRegionSelector().getPrimaryPosition();
    }

    public Boolean isCompleteSelection() {

        return this.getRegionSelector().isDefined();

    }

    public FaweAPI setSecondaryPos(BlockVector3 bv3) {

        this.getRegionSelector().selectSecondary(bv3, ActorSelectorLimits.forActor(bukkitPlayer));
        return this;
    }

    public FaweAPI clearSelection() {

        this.getRegionSelector().clear();
        return this;
    }

    public FaweAPI refreshChunk(@NotNull Chunk chunk) {

        this.bukkitPlayer.getWorldForEditing().refreshChunk(chunk.getX(), chunk.getZ());
        return this;
    }


    public Pattern getPattern(String s) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(bukkitPlayer);
        ParserContext context = new ParserContext();

        context.setActor(bukkitPlayer);
        context.setWorld(bukkitPlayer.getWorld());
        context.setExtent(bukkitPlayer.getExtent());
        context.setSession(localSession);

        return WorldEdit.getInstance().getPatternFactory().parseFromInput(s, context);
    }

    public Mask getMask(String s) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(bukkitPlayer);
        ParserContext context = new ParserContext();

        context.setActor(bukkitPlayer);
        context.setWorld(bukkitPlayer.getWorld());
        context.setExtent(bukkitPlayer.getExtent());
        context.setSession(localSession);

        return WorldEdit.getInstance().getMaskFactory().parseFromInput(s, context);
    }

    public void setMask(Mask mask) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(bukkitPlayer);
        localSession.setMask(mask);

    }

    public BaseBlock getBlock(String s) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(bukkitPlayer);
        ParserContext context = new ParserContext();

        context.setActor(bukkitPlayer);
        context.setWorld(bukkitPlayer.getWorld());
        context.setExtent(bukkitPlayer.getExtent());
        context.setSession(localSession);

        return WorldEdit.getInstance().getBlockFactory().parseFromInput(s, context);
    }


    public ClipboardHolder copySelection(boolean copingBiomes, boolean copingEntities, boolean saveInClipboard, boolean sendMessage) {
        return this.copySelection(copingBiomes, copingEntities, saveInClipboard, sendMessage, new BlockVectorTool().toBlockVectorTool(this.bukkitPlayer.getSession().getPlacementPosition(this.bukkitPlayer)));
    }

    public ClipboardHolder copySelection(boolean copingBiomes, boolean copingEntities, boolean saveInClipboard, boolean sendMessage, BlockVectorTool origin) {

        Region region = this.bukkitPlayer.getSelection();
        LocalSession session = this.bukkitPlayer.getSession();

        try (EditSession editSession = session.createEditSession(this.bukkitPlayer)) {

            BlockArrayClipboard clipboard;
            ForwardExtentCopy copy;

            clipboard = new BlockArrayClipboard(region);
            clipboard.setOrigin(origin.toBlockVector3());

            copy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

            copy.setCopyingBiomes(copingBiomes);
            copy.setCopyingEntities(copingEntities);

            Operations.complete(copy);

            if (saveInClipboard) {
                session.setClipboard(new ClipboardHolder(clipboard));
            }

            if (sendMessage)
                bukkitPlayer.getPlayer().sendMessage(new Message.MessageSender("expbuild.message.selection.copy_block", true, new String[]{String.valueOf(region.getVolume())}).getMessage());

            return new ClipboardHolder(clipboard);
        }
    }

    public Iterator<BlockVector3> clipboardIterator() {

        Region region = this.bukkitPlayer.getSelection();

        try (BlockArrayClipboard clipboard = new BlockArrayClipboard(region)) {
            return clipboard.iterator();
        }
    }

    public BlockArrayClipboard getBlockArrayClipboard() {

        Region region = this.bukkitPlayer.getSelection();

        return new BlockArrayClipboard(region);
    }

    @Warning
    public ClipboardHolder rotateClipboard(ClipboardHolder clipboard, double rotateX, double rotateY, double rotateZ) {

        ClipboardHolder holder = clipboard;
        AffineTransform transform = new AffineTransform();

        transform = transform.rotateX(-rotateX);
        transform = transform.rotateY(-rotateY);
        transform = transform.rotateZ(-rotateZ);

        // Créer une nouvelle instance de ClipboardHolder avec la transformation
        ClipboardHolder transformedClipboardHolder = new ClipboardHolder(holder.getClipboard());
        transformedClipboardHolder.setTransform(transform);


        holder.setTransform(transform.combine(holder.getTransform()));

        return transformedClipboardHolder;

    }

    public void pasteClipboard(boolean ignoreAirBlock, BlockVectorTool to , boolean sendMessage) {

        try {
            pasteClipboard(this.bukkitPlayer.getSession().getClipboard(), ignoreAirBlock, false, false, to, sendMessage, 0, 0, 0);
        } catch (EmptyClipboardException ignored) {}
    }

    public void pasteClipboard(ClipboardHolder clipboardHolder, boolean ignoreAirBlock, boolean copingEntities, boolean copingBiomes, BlockVectorTool to , boolean sendMessage, double rotateX, double rotateY, double rotateZ) {

        LocalSession localSession = this.bukkitPlayer.getSession();
        EditSession editSession = localSession.createEditSession(this.bukkitPlayer);

        // Vérification de l'initialisation de clipboardHolder
        if (clipboardHolder == null || clipboardHolder.getClipboard() == null) {
            bukkitPlayer.getPlayer().sendMessage(new Message.MessageSender("expbuild.message.selection.ClipboardHolder_null").getMessage());
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
            bukkitPlayer.getPlayer().sendMessage(new Message.MessageSender("expbuild.message.selection.failed_paste_clipboard", new String[]{e.getMessage()}).getMessage());

        } finally {
            // Assurer que l'editSession est fermée pour éviter les fuites de ressources
            editSession.close();

            if (sendMessage) {
                bukkitPlayer.getPlayer().sendMessage(
                        new Message.MessageSender("expbuild.message.selection.clipboard_paste_at",
                                new String[]{"(" + to.getBlockX() + ", " + to.getBlockY() + ", " + to.getBlockZ() + ")"}).getMessage()
                );
            }
        }
    }

    @Deprecated
    public void setRegion(Region region, Pattern pattern, boolean sendMessage) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(this.bukkitPlayer);
        EditSession editsession = localSession.createEditSession(this.bukkitPlayer);

        long startTime = System.currentTimeMillis();
        int editedBlock;

        editedBlock = editsession.setBlocks(region, pattern);
        localSession.remember(editsession);

        long endTime = System.currentTimeMillis();

        if (sendMessage) this.sendSetBlockMessage(startTime, endTime, editedBlock, 0, 0);
    }

    public void setBlock(GlueList<BlockVectorTool> blocks, Pattern pattern, boolean sendMessage) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(this.bukkitPlayer);
        EditSession editsession = localSession.createEditSession(this.bukkitPlayer);

        long startTime = System.currentTimeMillis();

        editsession.setFastMode(false);

        blocks.forEach(block -> {

            editsession.setBlock(Vector3.at(
                    block.getX(),
                    block.getY(),
                    block.getZ()).toBlockPoint(),
                    pattern);

        });

        localSession.remember(editsession);


        long endTime = System.currentTimeMillis();

        if (sendMessage) this.sendSetBlockMessage(startTime, endTime, blocks.size(), 0, 0);
    }

    public void setBlock(BlockVectorMaterial blockVectorMaterial, boolean sendMessage) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(this.bukkitPlayer);
        EditSession editsession = localSession.createEditSession(this.bukkitPlayer);

        long startTime = System.currentTimeMillis();
        int size = blockVectorMaterial.baseBlockDeque().size();

        // Méthode pour itérer et récupérer les paires
        if (blockVectorMaterial.positionDeque().size() != blockVectorMaterial.baseBlockDeque().size()) {
            bukkitPlayer.getPlayer().sendMessage("Error les listes de position et de pattern ne sont pas synchronisées");
            throw new IllegalStateException("Les listes de positions et de matériaux ne sont pas synchronisées !");
        }

        while (!blockVectorMaterial.positionDeque().isEmpty()) {

            BlockVectorMaterial.PositionMaterialPair block = blockVectorMaterial.getLastPositionMaterial();

            editsession.setBlock(
                    block.position().toBlockVector3(),
                    block.material());
        }

        localSession.remember(editsession);

        long endTime = System.currentTimeMillis();

        if (sendMessage) this.sendSetBlockMessage(startTime, endTime, size, 0, 0);
    }


    private void sendSetBlockMessage(long startTime, long endTime, int blockModified, int biomeModified, int entityModified) {

        float r = (endTime - startTime);

        if (r > 1000) {
            float t = r / 1000;
            float d = (blockModified / t);

            bukkitPlayer.getPlayer().sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified_with_time", true, new String[]{String.valueOf(blockModified), String.valueOf(d)}).getMessage());
        } else {
            bukkitPlayer.getPlayer().sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified", true, new String[]{String.valueOf(blockModified)}).getMessage());
        }
    }



    public void saveClipboardToFile(File file, Clipboard clipboard) {

        try (ClipboardWriter writer = BuiltInClipboardFormat.FAST.getWriter(new FileOutputStream(file))) {
            writer.write(clipboard);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Clipboard loadClipboardToFile(File file) {

        Clipboard clipboard;

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            return reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void loadBlockCategoriesMask() {


        // STAINED GLASS PANE CATEGORY

        StainedGlassCategory stainedGlassCategory = new StainedGlassCategory("stained_glass");

        stainedGlassCategory.getAll().addAll(Arrays.asList(
                BlockTypes.WHITE_STAINED_GLASS,
                BlockTypes.GLASS,
                BlockTypes.WHITE_STAINED_GLASS,
                BlockTypes.LIGHT_GRAY_STAINED_GLASS,
                BlockTypes.GRAY_STAINED_GLASS,
                BlockTypes.BLACK_STAINED_GLASS,
                BlockTypes.BROWN_STAINED_GLASS,
                BlockTypes.RED_STAINED_GLASS,
                BlockTypes.ORANGE_STAINED_GLASS,
                BlockTypes.YELLOW_STAINED_GLASS,
                BlockTypes.LIME_STAINED_GLASS,
                BlockTypes.GREEN_STAINED_GLASS,
                BlockTypes.CYAN_STAINED_GLASS,
                BlockTypes.LIGHT_BLUE_STAINED_GLASS,
                BlockTypes.BLUE_STAINED_GLASS,
                BlockTypes.PURPLE_STAINED_GLASS,
                BlockTypes.MAGENTA_STAINED_GLASS,
                BlockTypes.PINK_STAINED_GLASS)
        );

        BlockCategory.REGISTRY.register("minecraft:stained_glass", stainedGlassCategory);


        // STAINED GLASS PANE CATEGORY

        StainedGlassPaneCategory stainedGlassPaneCategory = new StainedGlassPaneCategory("stained_glass_pane");

        stainedGlassPaneCategory.getAll().addAll(Arrays.asList(
                BlockTypes.WHITE_STAINED_GLASS_PANE,
                BlockTypes.GLASS_PANE,
                BlockTypes.WHITE_STAINED_GLASS_PANE,
                BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE,
                BlockTypes.GRAY_STAINED_GLASS_PANE,
                BlockTypes.BLACK_STAINED_GLASS_PANE,
                BlockTypes.BROWN_STAINED_GLASS_PANE,
                BlockTypes.RED_STAINED_GLASS_PANE,
                BlockTypes.ORANGE_STAINED_GLASS_PANE,
                BlockTypes.YELLOW_STAINED_GLASS_PANE,
                BlockTypes.LIME_STAINED_GLASS_PANE,
                BlockTypes.GREEN_STAINED_GLASS_PANE,
                BlockTypes.CYAN_STAINED_GLASS_PANE,
                BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE,
                BlockTypes.BLUE_STAINED_GLASS_PANE,
                BlockTypes.PURPLE_STAINED_GLASS_PANE,
                BlockTypes.MAGENTA_STAINED_GLASS_PANE,
                BlockTypes.PINK_STAINED_GLASS_PANE)
        );

        BlockCategory.REGISTRY.register("minecraft:stained_glass_pane", stainedGlassPaneCategory);


        // STRIPPED CATEGORY

        StrippedCategory strippedCategory = new StrippedCategory("stripped");

        strippedCategory.getAll().addAll(Arrays.asList(
                BlockTypes.STRIPPED_OAK_WOOD, BlockTypes.STRIPPED_OAK_LOG,
                BlockTypes.STRIPPED_SPRUCE_WOOD, BlockTypes.STRIPPED_SPRUCE_LOG,
                BlockTypes.STRIPPED_BIRCH_WOOD, BlockTypes.STRIPPED_BIRCH_LOG,
                BlockTypes.STRIPPED_JUNGLE_WOOD, BlockTypes.STRIPPED_JUNGLE_LOG,
                BlockTypes.STRIPPED_ACACIA_WOOD, BlockTypes.STRIPPED_ACACIA_LOG,
                BlockTypes.STRIPPED_DARK_OAK_WOOD, BlockTypes.STRIPPED_DARK_OAK_LOG,
                BlockTypes.STRIPPED_MANGROVE_WOOD, BlockTypes.STRIPPED_MANGROVE_LOG,
                BlockTypes.STRIPPED_CHERRY_WOOD, BlockTypes.STRIPPED_CHERRY_LOG,
                BlockTypes.STRIPPED_WARPED_HYPHAE, BlockTypes.STRIPPED_WARPED_STEM,
                BlockTypes.STRIPPED_CRIMSON_HYPHAE, BlockTypes.STRIPPED_CRIMSON_STEM,
                BlockTypes.STRIPPED_BAMBOO_BLOCK
        ));

        BlockCategory.REGISTRY.register("minecraft:stripped", strippedCategory);

    }
}