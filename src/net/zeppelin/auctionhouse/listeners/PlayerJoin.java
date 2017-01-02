package net.zeppelin.auctionhouse.listeners;

import net.zeppelin.auctionhouse.AuctionHouse;
import net.zeppelin.auctionhouse.AuctionPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        AuctionHouse.getInstance().getPlayers().add(new AuctionPlayer(event.getPlayer().getUniqueId()));
    }
}
