package net.zeppelin.auctionhouse;

import net.zeppelin.auctionhouse.gui.AuctionItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuctionPlayer {
    private UUID id;
    private List<AuctionItem> auctionItems = new ArrayList<>();

    public AuctionPlayer(UUID id) {
        this.id = id;
    }

    public Player getHandler() {
        return Bukkit.getPlayer(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<AuctionItem> getAuctionItems() {
        return auctionItems;
    }

    public void setAuctionItems(List<AuctionItem> auctionItems) {
        this.auctionItems = auctionItems;
    }
}
