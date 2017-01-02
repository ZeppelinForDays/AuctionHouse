package net.zeppelin.auctionhouse.listeners;

import net.zeppelin.auctionhouse.AuctionHouse;
import net.zeppelin.auctionhouse.gui.AuctionInventory;
import net.zeppelin.auctionhouse.util.Inventories;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getInventory();
        AuctionInventory auctionHouse = AuctionHouse.getInstance().getAuctionHouse();
        Player player = (Player) event.getWhoClicked();

        if (clickedInventory == null)
            return;

        for (int i = 0; i < auctionHouse.getPages().size(); i++) {
            if (clickedInventory.equals(auctionHouse.getPages().get(i).getInventory())) {
                event.setCancelled(true);

                if (event.getCurrentItem() == null)
                    return;

                if (!event.getCurrentItem().getItemMeta().hasDisplayName() || !event.getCurrentItem().hasItemMeta())
                    return;

                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNext")) {
                    auctionHouse.setCurrentPage(auctionHouse.getCurrentPage() + 1);
                    if (auctionHouse.getCurrentPage() > auctionHouse.getPages().size() - 1)
                        auctionHouse.setCurrentPage(0);

                    player.openInventory(auctionHouse.getAuctionPageById(auctionHouse.getCurrentPage()).getInventory());
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cPrevious")) {
                    auctionHouse.setCurrentPage(auctionHouse.getCurrentPage() - 1);
                    if (auctionHouse.getCurrentPage() < 0)
                        auctionHouse.setCurrentPage(auctionHouse.getPages().size() - 1);

                    player.openInventory(auctionHouse.getAuctionPageById(auctionHouse.getCurrentPage()).getInventory());
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eRefresh")) {
                    player.openInventory(auctionHouse.getAuctionPageById(auctionHouse.getCurrentPage()).getInventory());
                }
            }
        }

        if (clickedInventory.getName().equals(auctionHouse.getName())) {
            event.setCancelled(true);

            if (!event.getCurrentItem().hasItemMeta())
                return;

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cAuction House")) {
                if (auctionHouse.getItems().size() == 0) {
                    player.sendMessage(ChatColor.RED + "There are currently no items in auction.");
                    return;
                }

                player.openInventory(auctionHouse.getAuctionPageById(0).getInventory());
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aYour Auctions")) {
                if (auctionHouse.getItems().size() == 0) {
                    player.sendMessage(ChatColor.RED + "You currently have nothing listed.");
                    return;
                }

                player.openInventory(Inventories.getYourAuctionsInventory(AuctionHouse.getInstance().getAuctionPlayerFromId(player.getUniqueId())));
            }
        }

        if (clickedInventory.getName().equals("Your Auctions")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null)
                return;

            if (!event.getCurrentItem().getItemMeta().hasDisplayName() || !event.getCurrentItem().hasItemMeta())
                return;

            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eRefresh")) {
                player.openInventory(Inventories.getYourAuctionsInventory(AuctionHouse.getInstance().getAuctionPlayerFromId(player.getUniqueId())));
            }
        }
    }
}
