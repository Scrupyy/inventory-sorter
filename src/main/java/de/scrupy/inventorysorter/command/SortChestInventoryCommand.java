package de.scrupy.inventorysorter.command;

import de.scrupy.inventorysorter.InventorySorter;
import de.scrupy.inventorysorter.util.PlayerRepository;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SortChestInventoryCommand implements CommandExecutor {
    private final PlayerRepository playerRepository;

    public SortChestInventoryCommand(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (playerRepository.contains(player)) {
            player.sendMessage(InventorySorter.getPrefix() + "Du musst eine Kiste rechtsklicken um diese zu sortieren.");
            return true;
        }

        playerRepository.add(player);
        player.sendMessage(InventorySorter.getPrefix() + "Klicke auf eine Kiste um diese zu sortieren.");

        return true;
    }
}
