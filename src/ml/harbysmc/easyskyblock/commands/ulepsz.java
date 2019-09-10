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

public class ulepsz implements CommandExecutor{

	private Main plugin;
	public ulepsz(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ulepsz").setExecutor(this);
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
		if (!(p.hasPermission("easyskyblock.ulepsz"))){
			p.sendMessage("§2 You can't do this now!");
			return true;
		}
		
		ItemStack stonemaker1 = new ItemStack(Material.COAL_ORE, 1);
		ItemMeta stonemaker1mt = stonemaker1.getItemMeta();
		stonemaker1mt.setDisplayName("§5STONE MAKER LVL 1");
		stonemaker1mt.addEnchant(Enchantment.LUCK, 1, true);
		stonemaker1.setItemMeta(stonemaker1mt);
		
		ItemStack stonemaker2 = new ItemStack(Material.IRON_ORE, 1);
		ItemMeta stonemaker2mt = stonemaker2.getItemMeta();
		stonemaker2mt.setDisplayName("§5STONE MAKER LVL 2");
		stonemaker2mt.addEnchant(Enchantment.LUCK, 2, true);
		stonemaker2.setItemMeta(stonemaker2mt);
		
		if(p.getEquipment().getItemInMainHand().equals(stonemaker1)) {
			Inventory inv = p.getInventory();
			ItemStack iron = new ItemStack(Material.IRON_INGOT);
			if(inv.containsAtLeast(iron, 32)) {
				ItemStack irontorem = new ItemStack(Material.IRON_INGOT, 32);
				inv.removeItem(irontorem);
				inv.removeItem(stonemaker1);
				ItemStack drop = new ItemStack(Material.IRON_ORE, 1);
				ItemMeta dropmt = drop.getItemMeta();
				dropmt.setDisplayName("§5STONE MAKER LVL 2");
				dropmt.addEnchant(Enchantment.LUCK, 2, true);
				drop.setItemMeta(dropmt);
				inv.addItem(drop);
				return true;
			}
			else {
				p.sendMessage("§2 You don't have enough iron, you need 32");
				return true;
			}
		}
		else if(p.getEquipment().getItemInMainHand().equals(stonemaker2)) {
			Inventory inv = p.getInventory();
			ItemStack diam = new ItemStack(Material.DIAMOND);
			if(inv.containsAtLeast(diam, 32)) {
				ItemStack diamtorem = new ItemStack(Material.DIAMOND, 32);
				inv.removeItem(diamtorem);
				inv.removeItem(stonemaker2);
				ItemStack drop = new ItemStack(Material.DIAMOND_ORE, 1);
				ItemMeta dropmt = drop.getItemMeta();
				dropmt.setDisplayName("§5STONE MAKER LVL 3");
				dropmt.addEnchant(Enchantment.LUCK, 3, true);
				drop.setItemMeta(dropmt);
				inv.addItem(drop);
				return true;
			}
			else {
				p.sendMessage("§2 You don't have enough diamonds, you need 32");
				return true;
				}
		}
		else {
			p.sendMessage("§2 You need to hold StoneMaker in hand to upgrade it");
			return true;
			}
	}
}