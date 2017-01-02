package net.zeppelin.auctionhouse;

import net.zeppelin.auctionhouse.commands.AuctionCommand;
import net.zeppelin.auctionhouse.gui.AuctionInventory;
import net.zeppelin.auctionhouse.listeners.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuctionHouse extends JavaPlugin {
    private static AuctionHouse instance;

    private AuctionInventory auctionHouse;
    private List<AuctionPlayer> players = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        auctionHouse = new AuctionInventory("Auction House");

        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        getCommand("auction").setExecutor(new AuctionCommand());

        for (Player online : Bukkit.getOnlinePlayers()) {
            players.add(new AuctionPlayer(online.getUniqueId()));
        }
    }

    public static AuctionHouse getInstance() {
        return instance;
    }

    public AuctionPlayer getAuctionPlayerFromId(UUID id) {
        for (int i = 0; i < players.size(); i++) {
            AuctionPlayer tempPlayer = players.get(i);

            if (tempPlayer.getId() == id)
                return tempPlayer;
        }

        return null;
    }

    public AuctionInventory getAuctionHouse() {
        return auctionHouse;
    }

    public void setAuctionHouse(AuctionInventory auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    public List<AuctionPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<AuctionPlayer> players) {
        this.players = players;
    }
}
