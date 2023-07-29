package de.scrupy.inventorysorter.command;

import de.scrupy.inventorysorter.InventorySorter;
import de.scrupy.inventorysorter.util.InventorySort;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SortInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        InventorySort inventorySort = new InventorySort(player.getInventory(), 9, 36);
        inventorySort.sortInventory();

        player.sendMessage(InventorySorter.getPrefix() + "Inventar wurde sortiert.");

        return true;
    }
}
