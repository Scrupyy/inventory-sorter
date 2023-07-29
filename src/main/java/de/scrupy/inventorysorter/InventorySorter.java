package de.scrupy.inventorysorter;

import de.scrupy.inventorysorter.command.SortChestInventoryCommand;
import de.scrupy.inventorysorter.command.SortInventoryCommand;
import de.scrupy.inventorysorter.listener.PlayerInteractListener;
import de.scrupy.inventorysorter.util.PlayerRepository;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class InventorySorter extends JavaPlugin {
    private static final String PREFIX = "§5§lInventory-Sorter §8>> §7";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(this.getPrefix() + "plugin successfully §a§lstarted.");

        PlayerRepository chestInteractingPlayers = new PlayerRepository();

        this.getCommand("sort").setExecutor(new SortInventoryCommand());
        this.getCommand("sortc").setExecutor(new SortChestInventoryCommand(chestInteractingPlayers));

        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(chestInteractingPlayers), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(this.getPrefix() + "plugin successfully §c§lstopped.");
    }

    public static String getPrefix() {
        return PREFIX;
    }
}
