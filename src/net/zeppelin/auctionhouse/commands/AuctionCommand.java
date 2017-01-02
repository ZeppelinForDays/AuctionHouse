package net.zeppelin.auctionhouse.commands;

import net.zeppelin.auctionhouse.AuctionHouse;
import net.zeppelin.auctionhouse.gui.AuctionItem;
import net.zeppelin.auctionhouse.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class AuctionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "The console cannot use that command.");
            return false;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("auction")) {
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("sell")) {
                    if (player.getItemInHand().getType() != Material.AIR) {
                        if (args.length >= 2) {
                            AuctionItem auctionItem = new AuctionItem(player.getItemInHand(), player, Double.parseDouble(args[1]));
                            player.getInventory().removeItem(auctionItem.getItem());
                            auctionItem.setItem(Utils.addCustomMetaToItem(auctionItem.getItem().clone(),
                                    Arrays.asList("§7Price: §6" + auctionItem.getPrice(), "§7Seller: §6" + auctionItem.getSeller().getName())));
                            AuctionHouse.getInstance().getAuctionHouse().addAuctionItem(auctionItem);
                            player.sendMessage(ChatColor.GREEN + "Item has been put up for auction.");

                            return true;
                        } else {
                            AuctionItem auctionItem = new AuctionItem(player.getItemInHand(), player);
                            player.getInventory().removeItem(auctionItem.getItem());
                            auctionItem.setItem(Utils.addCustomMetaToItem(auctionItem.getItem().clone(),
                                    Arrays.asList("§7Price: §6FREE", "§7Seller: §6" + auctionItem.getSeller().getName())));
                            AuctionHouse.getInstance().getAuctionHouse().addAuctionItem(auctionItem);
                            player.sendMessage(ChatColor.GREEN + "Item has been put up for auction.");

                            return true;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You must have an item that you want to sell in your hand.");
                        return false;
                    }
                }
            } else
                player.openInventory(AuctionHouse.getInstance().getAuctionHouse().getInventory());
        }

        return false;
    }
}
