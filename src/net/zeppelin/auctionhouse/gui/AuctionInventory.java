package net.zeppelin.auctionhouse.gui;

import net.zeppelin.auctionhouse.AuctionHouse;
import net.zeppelin.auctionhouse.util.Inventories;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuctionInventory {
    private Inventory inventory;
    private String name;

    private int numberPages;
    private int currentPage = 0;
    private List<AuctionPage> pages = new ArrayList<>();
    private List<AuctionItem> items = new ArrayList<>();

    private List<Integer> bannedSlots = Arrays.asList(45, 46, 47, 48, 49, 50, 51, 52, 53);

    public AuctionInventory(String name) {
        this.name = name;
        this.inventory = Inventories.getAuctionHouseInventory(name);
    }

    private AuctionPage createPageInventory() {
        AuctionPage newPage = new AuctionPage(Bukkit.createInventory(null, 54, "Page " + (numberPages + 1)), numberPages);
        pages.add(newPage);
        numberPages++;
        return newPage;
    }

    public void addAuctionItem(AuctionItem item) {
        if (pages.size() == 0) {
            AuctionPage newPage = createPageInventory();
            newPage.getInventory().setItem(newPage.getInventory().firstEmpty(), item.getItem());
            items.add(item);
            AuctionHouse.getInstance().getAuctionPlayerFromId(item.getSeller().getUniqueId()).getAuctionItems().add(item);
            return;
        }

        AuctionPage page = pages.get(pages.size() - 1);

        if (page.getInventory().firstEmpty() != -1 && !bannedSlots.contains(page.getInventory().firstEmpty())) {
            page.getInventory().setItem(page.getInventory().firstEmpty(), item.getItem());
        } else {
            AuctionPage newPage = createPageInventory();
            newPage.getInventory().setItem(newPage.getInventory().firstEmpty(), item.getItem());
        }
        items.add(item);
        AuctionHouse.getInstance().getAuctionPlayerFromId(item.getSeller().getUniqueId()).getAuctionItems().add(item);
    }

    public void removeAuctionItem(AuctionItem item) {

    }

    public AuctionPage getAuctionPageById(int id) {
        if (pages.get(id) == null)
            return null;

        return pages.get(id);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<AuctionPage> getPages() {
        return pages;
    }

    public void setPages(List<AuctionPage> pages) {
        this.pages = pages;
    }

    public List<AuctionItem> getItems() {
        return items;
    }

    public void setItems(List<AuctionItem> items) {
        this.items = items;
    }
}
