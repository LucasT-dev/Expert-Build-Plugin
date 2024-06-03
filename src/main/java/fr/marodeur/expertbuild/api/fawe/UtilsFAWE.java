package fr.marodeur.expertbuild.api.fawe;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.object.BlockVec4;
import fr.marodeur.expertbuild.object.BrushBuilder;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.extension.platform.permission.ActorSelectorLimits;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.regions.RegionSelector;
import com.sk89q.worldedit.regions.selector.RegionSelectorType;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;

import fr.marodeur.expertbuild.object.Message;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UtilsFAWE {

    private BukkitPlayer bukkitPlayer;
    private RegionSelector regionSelector;
    private RegionSelectorType regionSelectorType;
    private Player p;
    private BrushBuilder b;
    private Actor actor;
    private LocalSession session;
    private Region region;
    private World world;
    private BlockVector3 bv3;

    public UtilsFAWE() {

    }

    public UtilsFAWE(Player p) {

        this.p = p;
        this.bukkitPlayer = BukkitAdapter.adapt(p);
        this.session = bukkitPlayer.getSession();
        this.world = bukkitPlayer.getWorld();
        this.session = bukkitPlayer.getSession();
        this.regionSelector = this.session.getRegionSelector(world);

        if (Main.containsBrushBuilder(p)) {
            this.b = BrushBuilder.getBrushBuilderPlayer(p, true);
        }

    }

    public UtilsFAWE setSelectionType(@NotNull RegionSelectorType regionSelectorType) {

        RegionSelector selector = regionSelectorType.createSelector();
        this.session.setRegionSelector(this.world, selector);
        return this;
    }

    public UtilsFAWE setPrimaryPos(BlockVector3 bv3) {
        this.session.getRegionSelector(world).selectPrimary(bv3, ActorSelectorLimits.forActor(bukkitPlayer));
        return this;
    }

    public Boolean isCompleteSelection() {
        return this.session.getRegionSelector(world).isDefined();
        
    }

    public BlockVector3 getPrimaryPos() {
        return this.session.getRegionSelector(world).getPrimaryPosition();

    }

    public UtilsFAWE setSecondaryPos(BlockVector3 bv3) {
        this.bv3 = bv3;
        this.session.getRegionSelector(world).selectSecondary(bv3, ActorSelectorLimits.forActor(bukkitPlayer));
        return this;
    }

    public UtilsFAWE clearSelection() {
        this.session.getRegionSelector(this.world).clear();
        return this;
    }

    public UtilsFAWE refreshChunk(@NotNull Chunk chunk) {
        this.bukkitPlayer.getWorldForEditing().refreshChunk(chunk.getX(), chunk.getZ());
        return this;
    }

    public boolean isValidSelection(@NotNull RegionSelectorType regionSelectorType) {

        try {
            Region r = this.session.getSelection();

            return !(r.getClass().getName().equalsIgnoreCase(regionSelectorType.getClass().getName()));

        } catch (IncompleteRegionException e) {

            return false;
        }
    }

    public boolean ignoredBlock(@NotNull Block b) {
        return b.isLiquid() || b.isPassable() || b.getType().isAir() || b.toString().contains("leaves");
    }

    public boolean isSolidBlock(@NotNull Block b) {
        return !b.isLiquid() || !b.isPassable() || !b.getType().isAir() || !b.toString().contains("leaves");
    }

    @Deprecated
    public void setCuboidRegion(Player p) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        Region r = actor.getSelection();

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    editsession.setBlocks(r, BrushBuilder.getBrushBuilderPlayer(p, true).getPattern());

                } finally {
                    localSession.remember(editsession);
                }
            }
        });
    }

    @Deprecated
    public void setCuboidRegion(Player p, CuboidRegion cuboidRegion) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);

        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

            LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);

            try (EditSession editsession = localSession.createEditSession(actor)) {
                try {
                    editsession.setFastMode(false);

                    editsession.setBlocks((Region) cuboidRegion, BrushBuilder.getBrushBuilderPlayer(p, true).getPattern());

                } finally {
                    localSession.remember(editsession);
                }
            }
        });
    }

    @Deprecated
    public void setBlockList(Player p, @NotNull List<BlockVec4> blocks, Boolean sendMessageBlock) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        EditSession editsession = localSession.createEditSession(actor);
        long startTime = System.currentTimeMillis();

        try {
            editsession.setFastMode(false);

            blocks.forEach(block -> {

                try {
                    editsession.setBlock(Vector3.at(block.getX(), block.getY(), block.getZ()).toBlockPoint(),
                            block.getBaseblock());

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
            localSession.remember(editsession);

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        float r = (endTime - startTime);

        if (sendMessageBlock & r > 1000) {
            float t = r / 1000;
            float d = (blocks.size() / t);
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified_with_time", true, new String[]{String.valueOf(blocks.size()), String.valueOf(d)}).getMessage());
        } else if (sendMessageBlock) {
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified", true, new String[]{String.valueOf(blocks.size())}).getMessage());
        }
    }

    @Deprecated
    public void setBlockListSimple(Player p, @NotNull List<BlockVec4> blocks, boolean sendMessageBlock) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        EditSession editsession = localSession.createEditSession(actor);
        long startTime = System.currentTimeMillis();

        try {
            editsession.setFastMode(false);

            blocks.forEach(block -> {

                try {

                    editsession.setBlock(Vector3.at(block.getX(), block.getY(), block.getZ()).toBlockPoint(),
                            BrushBuilder.getBrushBuilderPlayer(p, true).getPattern());

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
            localSession.remember(editsession);

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        float r = (endTime - startTime);

        if (sendMessageBlock & r > 1000) {
            float t = r / 1000;
            float d = (blocks.size() / t);
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified_with_time", true, new String[]{String.valueOf(blocks.size()), String.valueOf(d)}).getMessage());
        } else if (sendMessageBlock) {
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified", true, new String[]{String.valueOf(blocks.size())}).getMessage());
        }
    }

    @Deprecated
    public void setBlockAnyPattern(Player p, @NotNull List<BlockVec4> blocks, boolean sendMessageBlock) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        EditSession editsession = localSession.createEditSession(actor);
        long startTime = System.currentTimeMillis();

        try {
            editsession.setFastMode(false);

            blocks.forEach(block -> {

                try {
                    editsession.setBlock(Vector3.at(block.getX(), block.getY(), block.getZ()).toBlockPoint(),
                            BukkitAdapter.asBlockType(block.getMat()).getDefaultState()
                    );

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
            localSession.remember(editsession);

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        float r = (endTime - startTime);

        if (sendMessageBlock & r > 1000) {
            float t = r / 1000;
            float d = (blocks.size() / t);
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified_with_time", true, new String[]{String.valueOf(blocks.size()), String.valueOf(d)}).getMessage());
        } else if (sendMessageBlock) {
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified", true, new String[]{String.valueOf(blocks.size())}).getMessage());
        }
    }

    public void setBlockPersonnalBlockPattern(Player p, List<BlockVec4> blocks, boolean sendMessageBlock) {

        BukkitPlayer actor = BukkitAdapter.adapt(p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        EditSession editsession = localSession.createEditSession(actor);
        long startTime = System.currentTimeMillis();

        try {
            editsession.setFastMode(false);

            blocks.forEach(block -> {

                try {
                    editsession.setBlock(Vector3.at(block.getX(), block.getY(), block.getZ()).toBlockPoint(),
                            block.getPattern());

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });
            localSession.remember(editsession);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        float r = (endTime - startTime);

        if (sendMessageBlock & r > 1000) {
            float t = r / 1000;
            float d = (blocks.size() / t);
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified_with_time", true, new String[]{String.valueOf(blocks.size()), String.valueOf(d)}).getMessage());
        } else if (sendMessageBlock) {
            p.sendMessage(new Message.MessageSender("expbuild.message.selection.block_modified", true, new String[]{String.valueOf(blocks.size())}).getMessage());
        }

    }

    public Pattern getPattern(String s) {

        BukkitPlayer actor = BukkitAdapter.adapt(this.p);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);

        ParserContext context = new ParserContext();
        context.setActor(actor);
        context.setWorld(BukkitAdapter.adapt(this.p.getWorld()));
        context.setSession(localSession);

        return WorldEdit.getInstance().getPatternFactory().parseFromInput(s, context);
    }


    public boolean isGmask(BlockVector3 v) {

        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(this.bukkitPlayer);
        return localSession.getMask() == null || localSession.getMask().test(v);
    }

    public int getHeight(int x, int z, @NotNull Location l) {
        int y = l.getBlockY();
        for (int i = y+b.getRadius(); i >= y-b.getRadius(); i--) {
            //System.out.println(i);
            if (!new Location(l.getWorld(), x, i, z).getBlock().getType().isAir()) {
                return i;
            }
        }
        return 999;
        //return y-b.getRayon();
    }
    public int getLowest(int x, int z, @NotNull Location l) {
        int y = l.getBlockY();

        for (int i = y-b.getRadius(); i <= y+b.getRadius(); i++) {
            //System.out.println(i);
            if (!new Location(l.getWorld(), x, i, z).getBlock().getType().isAir()) {
                //System.out.println("i = " + i);
                return i;

            }
        }
        return 999;
    }

    public @NotNull List<String> getFileList() {

        //Commun Schematic list

        String[] pathnames;
        File f = new File("plugins/FastAsyncWorldEdit/schematics");
        String list = "";

        pathnames = f.list();

        for (String pathname : pathnames) list = list + pathname + ";";

        //Personnel Schematic list

        f = new File("plugins/FastAsyncWorldEdit/schematics/" + this.p.getUniqueId().toString());

        pathnames = f.list();

        for (String pathname : pathnames) list = list + pathname + ";";

        return Arrays.asList(list.split(";"));
    }

    public Boolean fileExist(String filename) {

        File communFile = new File("plugins/FastAsyncWorldEdit/schematics/" + filename);
        File personnelFile = new File("plugins/FastAsyncWorldEdit/schematics/" + p.getUniqueId() + "/" + filename);

        if (communFile.exists() || personnelFile.exists()) {
            return true;
        }
        return false;
    }
    public File getFile(String filename) {

        File communFile = new File("plugins/FastAsyncWorldEdit/schematics/" + filename);
        File personnelFile = new File("plugins/FastAsyncWorldEdit/schematics/" + p.getUniqueId() + "/" + filename);

        if (communFile.exists()) {
            System.out.println("communFile = " + communFile);
            return communFile;
        }
        if (personnelFile.exists()) {
            System.out.println("communFile = " + personnelFile);
            return personnelFile;
        }
        System.out.println("null file");
        return null;
    }

    //MESSAGE

    public final String getMainPrefix() {
        return Main.prefix;
    }

    public final String getFawePrefix() {
        return Main.FawePrefix;
    }








    public void playerPasteSchem(@NotNull Player p, File file, BlockVector3 blockVector3) throws IOException {

        Clipboard clipboard;
        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(p.getWorld()))) {

                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(blockVector3)
                        .ignoreAirBlocks(true)
                        .build();
                Operations.complete(operation);
            }
        }
    }

    @Deprecated
    public void playerPasteSchem22(@NotNull Player p, File file, BlockVector3 blockVector3) throws IOException {

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(this.world, -1);

        editSession.setFastMode(true);
        ClipboardFormats.findByFile(file).load(file).paste(this.world, blockVector3, false);

        editSession.flushQueue();
        editSession.setFastMode(false);
        //bukkitPlayer.getWorldForEditing().refreshChunk(blockVector3.getX()*16,blockVector3.getZ()*16);

        //System.out.println("blockVector3.getX() * 16 = " + blockVector3.getX() / 16);

        //System.out.println("blockVector3.getZ() * 16 = " + blockVector3.getZ() / 16);

        /**
         *
         *  var NMSrelighterFactory
         *  maze operation necessite to deco/ reco player
         *
         */
    }

    @Deprecated
    public UtilsFAWE pasteClipboard() {

        Clipboard clipboard = this.bukkitPlayer.getSession().getClipboard().getClipboard();

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(this.world)) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(this.p.getLocation().getX(), this.p.getLocation().getY(), this.p.getLocation().getZ()))
                    .build();
            Operations.complete(operation);
        }

        p.sendMessage(Main.prefix + "Clipboard paste at (" + this.bukkitPlayer.getLocation().getBlockX() + ", "
                + this.bukkitPlayer.getLocation().getBlockY() + ", " + this.bukkitPlayer.getLocation().getBlockZ() + ")");
        return this;
    }

    @Deprecated
    public UtilsFAWE pasteClipboardIgnoreAir() {

        Clipboard clipboard = this.bukkitPlayer.getSession().getClipboard().getClipboard();

        try (EditSession editSession = WorldEdit.getInstance().newEditSession(this.world)) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .ignoreAirBlocks(true)
                    .maskSource(editSession.getMask())
                    .to(BlockVector3.at(this.p.getLocation().getX(), this.p.getLocation().getY(), this.p.getLocation().getZ()))
                    .build();
            Operations.complete(operation);
        }

        p.sendMessage(Main.prefix + "Clipboard paste at (" + this.bukkitPlayer.getLocation().getBlockX() + ", "
                + this.bukkitPlayer.getLocation().getBlockY() + ", " + this.bukkitPlayer.getLocation().getBlockZ() + ")");
        return this;
    }

    @Deprecated
    public Clipboard CopySelection(boolean sendMessage) {

        Region region = this.bukkitPlayer.getSelection();
        LocalSession session = this.bukkitPlayer.getSession();
        EditSession editSession = session.createEditSession(this.bukkitPlayer);

        this.region = region;
        this.session = session;
        this.world = this.bukkitPlayer.getWorld();

        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        clipboard.setOrigin(session.getPlacementPosition(this.bukkitPlayer));

        ForwardExtentCopy copy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());

        copy.setCopyingBiomes(false);
        copy.setCopyingEntities(false);

        if (editSession.getMask() != null) {
            copy.setSourceMask(editSession.getMask());
        }

                /*if (mask != null) {
					copy.setSourceMask(mask);
				}*/
        Operations.completeLegacy(copy);
        session.setClipboard(new ClipboardHolder(clipboard));

        if (sendMessage) p.sendMessage(new Message.MessageSender("expbuild.message.selection.copy_block", true, new String[]{String.valueOf(region.getArea())}).getMessage());

        return clipboard;
    }
}