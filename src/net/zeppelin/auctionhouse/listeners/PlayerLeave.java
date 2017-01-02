package net.zeppelin.auctionhouse.listeners;

import net.zeppelin.auctionhouse.AuctionHouse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        AuctionHouse.getInstance().getPlayers().remove(AuctionHouse.getInstance().getAuctionPlayerFromId(event.getPlayer().getUniqueId()));
    }
}
