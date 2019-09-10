package ml.harbysmc.easyskyblock.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ml.harbysmc.easyskyblock.Main;

public class skyblockspawn implements CommandExecutor{
	private Main plugin;
	public skyblockspawn(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("skyblockspawn").setExecutor(this);
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
		if(!(p.hasPermission("easyskyblock.spawn"))) {
			p.sendMessage("§2 You Can't do this right now");
			return true;
		}
		Location tploc = new Location(p.getWorld(), plugin.getConfig().getDouble(p.getName()+"Xloc"), plugin.getConfig().getDouble(p.getName()+"Yloc"), plugin.getConfig().getDouble(p.getName()+"Zloc"));
		if (tploc.getX() != 0) {
			p.sendMessage("§2Teleporting...");
			p.teleport(tploc);
			return true;
		}
		else {
			p.sendMessage("§2Err...");
			return true;
		}
	}
}
