package fr.marodeur.expertbuild.object.guibuilder;

import org.bukkit.entity.Player;

public interface InventoryProvider {


    void update(Player player, InventoryContents contents);

    void close(Player player, Inventory inventory);

    void init(Player player, InventoryContents contents);
}
