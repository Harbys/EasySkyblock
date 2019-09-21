package ml.harbysmc.easyskyblock.commands;

import ml.harbysmc.easyskyblock.Permissions;
import ml.harbysmc.easyskyblock.utils.StoneGenerators;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ml.harbysmc.easyskyblock.Main;

public class Stonemaker implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player");
            return true;
        }
        Player p = (Player) sender;

        if (!p.getWorld().getName().equals("skyblock")) {
            p.sendMessage("§cYou can do this only in Skyblock");
            return true;
        }
        if (!p.hasPermission(Permissions.STONEMAKER)) {
            p.sendMessage("§cYou can't do this now!");
            return true;
        }

        Inventory inv = p.getInventory();
        if (!inv.containsAtLeast(new ItemStack(Material.COBBLESTONE), 192)) {
            p.sendMessage("§cYou don't have enough cobblestone, you need 3*64");
            return true;
        }

        inv.removeItem(new ItemStack(Material.COBBLESTONE, 192));
        inv.addItem(StoneGenerators.getCoal());
        p.sendMessage("§aYou crafted StoneMaker LVL 1");
        return true;
    }
}
