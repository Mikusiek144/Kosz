package pl.kosz;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class KoszPlugin extends JavaPlugin implements Listener, CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("kosz").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin Kosz zostal wlaczony!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Kosz zostal wylaczony!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kosz")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                openKoszGUI(player);
                return true;
            } else {
                sender.sendMessage("Tylko gracz moze uzyc tej komendy!");
                return false;
            }
        }
        return false;
    }


    private void openKoszGUI(Player player) {
        Inventory koszGUI = Bukkit.createInventory(player, 45, "§8Kosz");
        player.openInventory(koszGUI);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§cKosz")) {
            if (event.getClickedInventory() != null && event.getClickedInventory().equals(event.getView().getTopInventory())) {
                if (event.getCurrentItem() != null && !event.getCurrentItem().getType().equals(Material.AIR)) {
                    event.setCancelled(true);
                    event.getCurrentItem().setAmount(0);
                    event.getWhoClicked().sendMessage("§aPrzedmiot zostal pochłonięty!");
                }
            }
        }
    }
}