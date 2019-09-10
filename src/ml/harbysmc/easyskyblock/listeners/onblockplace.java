package ml.harbysmc.easyskyblock.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import ml.harbysmc.easyskyblock.Main;

public class onblockplace implements Listener{

	private Main plugin;
	public onblockplace(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void on(BlockPlaceEvent e) {
		if(!(e.getBlock().getWorld().getName().equals("skyblock"))) {
			return;
		}
		Block b = (Block) e.getBlock();
		if(b.getType().equals(Material.COAL_ORE) || b.getType().equals(Material.IRON_ORE) || b.getType().equals(Material.DIAMOND_ORE)) {
			b.getLocation().add(0, 1, 0).getBlock().setType(Material.STONE);
		}
	}
}
