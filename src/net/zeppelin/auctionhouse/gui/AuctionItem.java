package net.zeppelin.auctionhouse.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AuctionItem {
    private ItemStack item;
    private String name;
    private double price;
    private Player seller;

    public AuctionItem(ItemStack item, Player seller) {
        this.item = item;
        this.seller = seller;
        this.name = item.getItemMeta().getDisplayName();
    }

    public AuctionItem(ItemStack item, Player seller, double price) {
        this.item = item;
        this.seller = seller;
        this.price = price;
        this.name = item.getItemMeta().getDisplayName();
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Player getSeller() {
        return seller;
    }

    public void setSeller(Player seller) {
        this.seller = seller;
    }
}
