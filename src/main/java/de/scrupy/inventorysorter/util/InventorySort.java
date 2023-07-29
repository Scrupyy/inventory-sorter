package de.scrupy.inventorysorter.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventorySort {
    private final Inventory inventory;
    private final int start;
    private final int end;

    public InventorySort(Inventory inventory, int start, int end) {
        this.inventory = inventory;
        this.start = start;
        this.end = end;
    }

    public InventorySort(Inventory inventory) {
        this.inventory = inventory;
        this.start = 0;
        this.end = inventory.getSize();
    }

    public void sortInventory() {
        stackItems();
        List<ItemStack> contents = getInventoryContents();
        contents.sort(Comparator.comparing(itemStack -> itemStack.getType().name()));
        clearInventory();
        fillInventory(contents);
    }

    private void fillInventory(List<ItemStack> contents) {
        int index = start;
        for (ItemStack itemStack : contents) {
            inventory.setItem(index, itemStack);
            index++;
        }
    }

    private List<ItemStack> getInventoryContents() {
        ItemStack[] contents = inventory.getContents();
        List<ItemStack> itemStacks = new ArrayList<>();

        for (int i = start; i < end; i++) {
            if (contents[i] != null) {
                itemStacks.add(contents[i]);
            }
        }
        return itemStacks;
    }

    private void clearInventory() {
        for (int i = start; i < end; i++) {
            inventory.setItem(i, new ItemStack(Material.AIR));
        }
    }

    private void stackItems() {
        for (int i = start; i < end; i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (itemStack == null)
                continue;

            if (itemStack.getAmount() == itemStack.getMaxStackSize())
                continue;

            int difference = itemStack.getMaxStackSize() - itemStack.getAmount();

            for (int j = start; j < end; j++) {
                if (j == i)
                    continue;
                ItemStack next = inventory.getItem(j);
                if (next == null)
                    continue;
                if (!next.isSimilar(itemStack))
                    continue;

                if (next.getAmount() == difference) {
                    itemStack.setAmount(itemStack.getMaxStackSize());
                    inventory.setItem(j, new ItemStack(Material.AIR));
                    break;
                } else if (next.getAmount() < difference) {
                    int itemsFound = next.getAmount();
                    itemStack.setAmount(itemStack.getAmount() + itemsFound);
                    inventory.setItem(j, new ItemStack(Material.AIR));
                    difference = difference - itemsFound;
                } else if (next.getAmount() > difference) {
                    itemStack.setAmount(itemStack.getMaxStackSize());
                    next.setAmount(next.getAmount() - difference);
                    break;
                }
            }
        }
    }
}
