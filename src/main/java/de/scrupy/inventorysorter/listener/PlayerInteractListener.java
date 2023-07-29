package de.scrupy.inventorysorter.listener;

import de.scrupy.inventorysorter.InventorySorter;
import de.scrupy.inventorysorter.util.InventorySort;
import de.scrupy.inventorysorter.util.PlayerRepository;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    private final PlayerRepository playerRepository;

    public PlayerInteractListener(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!playerRepository.contains(player)) {
            return;
        }

        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        if (event.getClickedBlock() == null) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block.getType() == Material.CHEST) {
            event.setCancelled(true);
            Chest chest = (Chest) block.getState();
            InventorySort inventorySort = new InventorySort(chest.getInventory());
            inventorySort.sortInventory();
            playerRepository.remove(player);
            player.sendMessage(InventorySorter.getPrefix() + "Kiste wurde sortiert.");
        }
    }
}
