package net.zeppelin.auctionhouse.gui;

import net.zeppelin.auctionhouse.util.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class AuctionPage {
    private Inventory inventory;
    private int id;

    public AuctionPage(Inventory inventory, int id) {
        this.inventory = inventory;
        this.id = id;
        inventory.setItem(48, Utils.createCustomItem(Material.REDSTONE, "§cPrevious", Arrays.asList("§7Go back a page.")));
        inventory.setItem(49, Utils.createCustomItem(Material.IRON_INGOT, "§eRefresh", Arrays.asList("§7Refresh the current page.")));
        inventory.setItem(50, Utils.createCustomItem(Material.EMERALD, "§aNext", Arrays.asList("§7Go to the next page.")));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
