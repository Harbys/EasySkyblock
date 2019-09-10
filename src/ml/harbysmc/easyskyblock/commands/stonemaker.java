package ml.harbysmc.easyskyblock.commands;

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

public class stonemaker implements CommandExecutor{
	
	
	private Main plugin;
	public stonemaker(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("stonemaker").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You need to be a player");
			return true;
		}
		Player p = (Player)sender;
		if(!(p.getWorld().getName().equals("skyblock"))) {
			p.sendMessage("§2 You can do this only in skyblock");
			return true;
		}
		if (!(p.hasPermission("easyskyblock.stonemaker"))){
			p.sendMessage("§2 You can't do this now!");
			return true;
		}
		Inventory inv = p.getInventory();
		ItemStack cobb = new ItemStack(Material.COBBLESTONE);
		if(inv.containsAtLeast(cobb, 192)) {
			ItemStack cobbtorem = new ItemStack(Material.COBBLESTONE, 192);
			inv.removeItem(cobbtorem);
	        ItemStack stonemaker = new ItemStack(Material.COAL_ORE, 1);
	        ItemMeta stonemakermt = stonemaker.getItemMeta();
	        stonemakermt.setDisplayName("§5STONE MAKER LVL 1");
	        stonemakermt.addEnchant(Enchantment.LUCK, 1, true);
	        stonemaker.setItemMeta(stonemakermt);
	        inv.addItem(stonemaker);
	        p.sendMessage("§2 Otzrymałeś StoneMaker LVL 1");
			return true;
		}
		else {
			p.sendMessage("§2 You don't have enough cobblestone, you need 3*64");
			return true;
		}
	}
}
