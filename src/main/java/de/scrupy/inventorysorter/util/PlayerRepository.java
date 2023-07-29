package de.scrupy.inventorysorter.util;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private final List<Player> playerList;

    public PlayerRepository() {
        this.playerList = new ArrayList<>();
    }

    public void add(Player player) {
        playerList.add(player);
    }

    public void remove(Player player) {
        playerList.remove(player);
    }

    public boolean contains(Player player) {
        return playerList.contains(player);
    }

    public void clear() {
        playerList.clear();
    }
}
