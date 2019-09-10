package ml.harbysmc.easyskyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ml.harbysmc.easyskyblock.Main;


public class sklep implements CommandExecutor{
	private Main plugin;
	public sklep(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("sklep").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("NOT A PLAYER");
			return true;
		}
		Player p = (Player) sender;
		if(!(p.hasPermission("easyskyblock.shop"))) {
			p.sendMessage("§2 You Can't do this right now");
			return true;
		}
		
		if(p.getWorld().getName().equals("skyblock")) {
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp shop " + p.getName());
			return true;
		}
		else {
			p.sendMessage("§2 You can do this only in skyblock");
			return true;
		}
	}
}
