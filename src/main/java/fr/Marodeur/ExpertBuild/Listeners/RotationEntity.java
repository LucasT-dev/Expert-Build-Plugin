package fr.Marodeur.ExpertBuild.Listeners;

import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class RotationEntity implements Listener {

    @EventHandler
    public void clickHead(@NotNull PlayerInteractEvent e) {

        ItemStack it = e.getItem();
        Block block = e.getClickedBlock();
        Player p = e.getPlayer();
        Action action = e.getAction();

        if(!p.isOp()) return;

        final List<BlockFace> skullfaces = Arrays.asList(BlockFace.NORTH, BlockFace.NORTH_NORTH_WEST,
                BlockFace.NORTH_WEST, BlockFace.WEST_NORTH_WEST, BlockFace.WEST, BlockFace.WEST_SOUTH_WEST,
                BlockFace.SOUTH_WEST, BlockFace.SOUTH_SOUTH_WEST, BlockFace.SOUTH, BlockFace.SOUTH_SOUTH_EAST,
                BlockFace.SOUTH_EAST, BlockFace.EAST_SOUTH_EAST, BlockFace.EAST, BlockFace.EAST_NORTH_EAST,
                BlockFace.NORTH_EAST, BlockFace.NORTH_NORTH_EAST);

        if (block == null) return;

        if (it == null) return;

        if (block.getType().name().contains("WALL")) return;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && block.getState() instanceof Skull thisSkull) {

            Rotatable skullRotation = (Rotatable) thisSkull.getBlockData();
            BlockFace currentDirection = skullRotation.getRotation();

            for (int i = 0; i < skullfaces.size(); i++) {
                if (currentDirection.toString().equals(skullfaces.get(i).toString())) {
                    if (i == 15) {
                        i = -1;
                    }
                    skullRotation.setRotation(skullfaces.get(i + 1));
                    thisSkull.setBlockData(skullRotation);
                    thisSkull.update(true);
                    e.setCancelled(true);
                    return;
                }
            }
            return;
        }
        if (action == Action.RIGHT_CLICK_BLOCK && block.getState() instanceof Banner thisSkull) {

            Rotatable skullRotation = (Rotatable) thisSkull.getBlockData();
            BlockFace currentDirection = skullRotation.getRotation();

            for (int i = 0; i < skullfaces.size(); i++) {
                if (currentDirection.toString().equals(skullfaces.get(i).toString())) {
                    if (i == 15) {
                        i = -1;
                    }
                    skullRotation.setRotation(skullfaces.get(i + 1));
                    thisSkull.setBlockData(skullRotation);
                    thisSkull.update(true);
                    e.setCancelled(true);
                    return;
                }
            }
            return;
        }

        //permettre au /Sign Editor de fonctionner
        if (p.isSneaking() && action == Action.RIGHT_CLICK_BLOCK) return;

        //Permet de glow un panneau
        if (action == Action.RIGHT_CLICK_BLOCK && it.getType().equals(Material.GLOW_INK_SAC)
                && block.getState() instanceof Sign) { return ;}

        //Permet de colorer un panneau
        if (action == Action.RIGHT_CLICK_BLOCK  && block.getState() instanceof Sign
                && it.toString().contains("DYE")) { return; }

        if (action == Action.RIGHT_CLICK_BLOCK && block.getState() instanceof Sign sign) {

            Rotatable signRotation = (Rotatable) sign.getBlockData();
            BlockFace currentDirection = signRotation.getRotation();

            for (int i = 0; i < skullfaces.size(); i++) {
                if (currentDirection.toString().equals(skullfaces.get(i).toString())) {
                    if (i == 15) {
                        i = -1;
                    }
                    signRotation.setRotation(skullfaces.get(i + 1));
                    sign.setBlockData(signRotation);
                    sign.update(true);
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
}