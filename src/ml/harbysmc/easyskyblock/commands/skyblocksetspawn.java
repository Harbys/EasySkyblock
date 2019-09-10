package ml.harbysmc.easyskyblock.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ml.harbysmc.easyskyblock.Main;

public class skyblocksetspawn implements CommandExecutor{
	private Main plugin;
	public skyblocksetspawn(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("setskyblockspawn").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("NOT A PLAYER");
			return true;
		}
		Player p = (Player) sender;
		if(!(p.getWorld().getName().equals("skyblock"))) {
			p.sendMessage("§2You need to be in skyblock to do this");
			return true;
		}
		if(!(p.hasPermission("easyskyblock.setspawn"))) {
			p.sendMessage("§2 You Can't do this right now");
			return true;
		}
		Location loc = p.getLocation();
		plugin.getConfig().set(p.getName()+"Xloc", loc.getX());
		plugin.getConfig().set(p.getName()+"Yloc", loc.getY());
		plugin.getConfig().set(p.getName()+"Zloc", loc.getZ());
		plugin.saveConfig();
		p.sendMessage("§2New Island Spawn Set");
		return true;
	}
}
