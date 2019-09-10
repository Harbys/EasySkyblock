package ml.harbysmc.easyskyblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ml.harbysmc.easyskyblock.Main;

public class onblockbreak implements Listener{

	private Main plugin;
	public onblockbreak(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
		
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!(e.getBlock().getWorld().getName().equals("skyblock"))) {
			return;
		}
		Block b = (Block) e.getBlock();
		if(b.getType().equals(Material.STONE)) {
			int rand = (int)(Math.random() * 1000 + 1);
			if(rand <= 5) {
				ItemStack drop = new ItemStack(Material.DIAMOND, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 35 && rand > 5) {
				ItemStack drop = new ItemStack(Material.REDSTONE, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 55 && rand > 35) {
				ItemStack drop = new ItemStack(Material.DIRT, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 105 && rand > 55) {
				ItemStack drop = new ItemStack(Material.IRON_INGOT, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 115 && rand > 105) {
				ItemStack drop = new ItemStack(Material.SLIME_BALL, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 125 && rand > 115) {
				ItemStack drop = new ItemStack(Material.GOLD_INGOT, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 145 && rand > 125) {
				ItemStack drop = new ItemStack(Material.SAND, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 155 && rand > 145) {
				ItemStack drop = new ItemStack(Material.GRAVEL, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 160 && rand > 155) {
				ItemStack drop = new ItemStack(Material.OBSIDIAN, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 260 && rand > 160) {
				ItemStack drop = new ItemStack(Material.COAL, 1);
				b.getWorld().dropItem(b.getLocation(), drop);
			}
			else if(rand <= 310 && rand > 260) {
				ExperienceOrb exp = b.getWorld().spawn(b.getLocation(), ExperienceOrb.class);
				exp.setExperience(5);
			}
			int delay = 0;
			if(b.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.COAL_ORE))
			{
				delay = 20;
			}
			else if(b.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.IRON_ORE)) {
				delay = 10;
			}
			else if(b.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.DIAMOND_ORE)) {
				delay = 2;
			}
			if(b.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.COAL_ORE) || b.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.IRON_ORE) || b.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.DIAMOND_ORE)) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					 
				    @Override
				    public void run() {
				        b.setType(Material.STONE);
				    }
				 
				}, delay);
			}
		}
		else if(b.getType().equals(Material.COAL_ORE)) {
			b.setType(Material.AIR);
			ItemStack drop = new ItemStack(Material.COAL_ORE, 1);
			ItemMeta dropmt = drop.getItemMeta();
			dropmt.setDisplayName("§5STONE MAKER LVL 1");
			dropmt.addEnchant(Enchantment.LUCK, 1, true);
			drop.setItemMeta(dropmt);
			b.getWorld().dropItem(b.getLocation(), drop);
			return;
		}
		else if(b.getType().equals(Material.IRON_ORE)) {
			b.setType(Material.AIR);
			ItemStack drop = new ItemStack(Material.IRON_ORE, 1);
			ItemMeta dropmt = drop.getItemMeta();
			dropmt.setDisplayName("§5STONE MAKER LVL 2");
			dropmt.addEnchant(Enchantment.LUCK, 2, true);
			drop.setItemMeta(dropmt);
			b.getWorld().dropItem(b.getLocation(), drop);
			return;
		}
		else if(b.getType().equals(Material.DIAMOND_ORE)) {
			b.setType(Material.AIR);
			ItemStack drop = new ItemStack(Material.DIAMOND_ORE, 1);
			ItemMeta dropmt = drop.getItemMeta();
			dropmt.setDisplayName("§5STONE MAKER LVL 3");
			dropmt.addEnchant(Enchantment.LUCK, 3, true);
			drop.setItemMeta(dropmt);
			b.getWorld().dropItem(b.getLocation(), drop);
			return;
		}
	}
}

