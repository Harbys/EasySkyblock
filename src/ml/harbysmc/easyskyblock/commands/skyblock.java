package ml.harbysmc.easyskyblock.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ml.harbysmc.easyskyblock.Main;

public class skyblock implements CommandExecutor {
	
	private Main plugin;
	public skyblock(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("skyblock").setExecutor(this);
	}
	public void makechest(Location l) {
		l.add(6,1,5);
		l.getBlock().setType(Material.CHEST);
		Chest chest = (Chest)l.getBlock().getState();
		Inventory inv = chest.getInventory();
		ItemStack water = new ItemStack(Material.WATER_BUCKET, 1);
        ItemStack lava = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemStack cactus = new ItemStack(Material.CACTUS, 1);
        ItemStack sugarcane = new ItemStack(Material.SUGAR_CANE, 1);
        ItemStack sapling = new ItemStack(Material.OAK_SAPLING, 1);
        ItemStack pumpkin = new ItemStack(Material.PUMPKIN_SEEDS, 1);
        ItemStack watermelon = new ItemStack(Material.MELON_SEEDS, 1);
        ItemStack bonemeal = new ItemStack(Material.BONE_MEAL, 16);
        ItemStack wheat = new ItemStack(Material.WHEAT_SEEDS, 16);
        ItemStack bed = new ItemStack(Material.BLUE_BED, 1);
        ItemStack dirt = new ItemStack(Material.DIRT, 64);
        inv.addItem(bed, water, water,lava, lava, cactus, sugarcane, sapling, pumpkin, watermelon, bonemeal, wheat, dirt);
	}
	public void maketree(Location l, World world) {
		l.add(0,1,0);
		l.add(5,0,-5);
		l.getBlock().setType(Material.OAK_LOG);
		l.add(0,1,0);
		l.getBlock().setType(Material.OAK_LOG);
		l.add(0,1,0);
		l.getBlock().setType(Material.OAK_LOG);
		l.add(0,1,0);
		l.getBlock().setType(Material.OAK_LOG);
		l.add(0,1,0);
		l.getBlock().setType(Material.OAK_LOG);
		l.add(0,1,0);
		l.getBlock().setType(Material.OAK_LOG);
		l.add(0,1,0);
		l.getBlock().setType(Material.OAK_LEAVES);
		l.subtract(0,5,0);
		l.add(1,0,0);
		for(int x = 0; x<=3;x++) {
			l.add(0,1,0);
			l.getBlock().setType(Material.OAK_LEAVES);
		}
		l.subtract(0,4,0);
		l.subtract(2,0,0);
		for(int x = 0; x<=3;x++) {
			l.add(0,1,0);
			l.getBlock().setType(Material.OAK_LEAVES);
		}
		l.subtract(0,4,0);
		l.add(1,0,1);
		for(int x = 0; x<=3;x++) {
			l.add(0,1,0);
			l.getBlock().setType(Material.OAK_LEAVES);
		}
		l.subtract(0,4,2);
		for(int x = 0; x<=3;x++) {
			l.add(0,1,0);
			l.getBlock().setType(Material.OAK_LEAVES);
		}
	}
	public void makerect(Location l, Material mat, World world) {
		Location lcopy = new Location(world, l.getX(), l.getY(), l.getZ());
		Block block = l.getBlock();
		for(int x = 0; x<=9; x++) {
			for(int y = 0; y<=9; y++) {
				l.add(1,0,0);
				block = l.getBlock();
				block.setType(mat);
			}
			l.setX(lcopy.getX());
			l.add(0,0,1);
		}
	}
	public void makeskyblock(double xcord, double zcord, String username) {
		World world = Bukkit.getWorld("skyblock");
		Location l = new Location(world, xcord, 56, zcord);
		makerect(l, Material.DIRT, world);
		l = new Location(world, xcord, 57, zcord);
		makerect(l, Material.DIRT, world);
		l = new Location(world, xcord, 58, zcord);
		makerect(l,Material.SAND, world);
		l = new Location(world, xcord, 59, zcord);
		makerect(l, Material.DIRT, world);
		l = new Location(world, xcord, 60, zcord);
		makerect(l, Material.GRASS_BLOCK, world);
		maketree(l,world);
		l = new Location(world, xcord, 60, zcord);
		makechest(l);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player");
			return true;
		}
		Player p = (Player) sender;
		if(!(p.hasPermission("easyskyblock.skyblock"))) {
			p.sendMessage("§2 You can't do this now!");
			return true;
		}
		if(args.length == 1) {
			if(plugin.getConfig().contains(args[0]+"Xloc")) {
				if(plugin.getConfig().getStringList(args[0]+"friends").contains(p.getName())) {
					if(p.getWorld().getName().equals("hub")) {
						World world = Bukkit.getWorld("skyblock");
			        	Location tploc = new Location(world, plugin.getConfig().getDouble(p.getName()+"Xloc"), plugin.getConfig().getDouble(p.getName()+"Yloc"), plugin.getConfig().getDouble(p.getName()+"Zloc"));
			        	p.teleport(tploc);
			        	return true;
					}
					p.sendMessage("§2Don't move for 5 seconds");
					Location oldloc = p.getLocation();
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						 
					    @Override
					    public void run() {
					        if(oldloc.getX() == p.getLocation().getX() && oldloc.getY() == p.getLocation().getY() && oldloc.getZ() == p.getLocation().getZ()) {
					        	World world = Bukkit.getWorld("skyblock");
					        	Location tploc = new Location(world, plugin.getConfig().getDouble(args[0]+"Xloc"), plugin.getConfig().getDouble(args[0]+"Yloc"), plugin.getConfig().getDouble(args[0]+"Zloc"));
					        	p.teleport(tploc);
					        }
					        else {
					        	p.sendMessage("§2 You Moved");
					        }
					    }
					 
					}, 20*5);
					return true;
				}
				else {
					p.sendMessage("§2 You're not added by this player");
					return true;
				}
			}
			else {
				p.sendMessage("§2 Player Not found or has no skyblock");
				return true;
			}
		}
		if(p.getWorld().getName().equals("skyblock")) {
			p.sendMessage("§2 You're arleady in skyblock");
			return true;
		}
		if (plugin.getConfig().getDouble(p.getName()+"Xloc") != 0) {
			if(p.getWorld().getName().equals("hub")) {
				World world = Bukkit.getWorld("skyblock");
	        	Location tploc = new Location(world, plugin.getConfig().getDouble(p.getName()+"Xloc"), plugin.getConfig().getDouble(p.getName()+"Yloc"), plugin.getConfig().getDouble(p.getName()+"Zloc"));
	        	p.teleport(tploc);
	        	return true;
			}
			p.sendMessage("§2Don't move for 5 seconds");
			Location oldloc = p.getLocation();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				 
			    @Override
			    public void run() {
			        if(oldloc.getX() == p.getLocation().getX() && oldloc.getY() == p.getLocation().getY() && oldloc.getZ() == p.getLocation().getZ()) {
			        	World world = Bukkit.getWorld("skyblock");
			        	Location tploc = new Location(world, plugin.getConfig().getDouble(p.getName()+"Xloc"), plugin.getConfig().getDouble(p.getName()+"Yloc"), plugin.getConfig().getDouble(p.getName()+"Zloc"));
			        	p.teleport(tploc);
			        }
			        else {
			        	p.sendMessage("§2 You Moved");
			        }
			    }
			 
			}, 20*5);
		}
		else {
			p.sendMessage("§2 Makeing Island, use command again to teleport to your island");
			int last = plugin.getConfig().getInt("LastIsland");
			makeskyblock(256*last,0,p.getName());
			plugin.getConfig().set(p.getName()+"plotnr", last);
			ArrayList<String> configList = new ArrayList<String>();
			configList.add(p.getName());
			plugin.getConfig().set(p.getName()+"friends", configList);
			plugin.getConfig().set(p.getName()+"Xloc", last*256+3);
			plugin.getConfig().set(p.getName()+"Yloc", 61);
			plugin.getConfig().set(p.getName()+"Zloc", 3);
			last += 1;
			plugin.getConfig().set("LastIsland", last);
			plugin.saveConfig();
		}

		return true;
	}
}