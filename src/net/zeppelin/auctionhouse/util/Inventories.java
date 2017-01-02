package net.zeppelin.auctionhouse.util;

import net.zeppelin.auctionhouse.AuctionPlayer;
import net.zeppelin.auctionhouse.gui.AuctionItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class Inventories {
    public static Inventory getAuctionHouseInventory(String name) {
        Inventory inventory = Bukkit.createInventory(null, 27, name);
        inventory.setItem(11, Utils.createCustomItem(Material.GOLD_INGOT, "§cAuction House", Arrays.asList("§7View the listings in the auction house.")));
        inventory.setItem(15, Utils.createCustomItem(Material.PAPER, "§aYour Auctions", Arrays.asList("§7View all the auctions that you currently have.")));
        return inventory;
    }

    public static Inventory getYourAuctionsInventory(AuctionPlayer player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Your Auctions");
        for (int i = 0; i < player.getAuctionItems().size(); i++) {
            AuctionItem tempItem = player.getAuctionItems().get(i);

            inventory.addItem(tempItem.getItem());
        }
        inventory.setItem(49, Utils.createCustomItem(Material.IRON_INGOT, "§eRefresh", Arrays.asList("§7Refresh the current page.")));
        return inventory;
    }
}
