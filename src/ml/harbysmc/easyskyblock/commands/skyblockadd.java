package ml.harbysmc.easyskyblock.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ml.harbysmc.easyskyblock.Main;

public class skyblockadd implements CommandExecutor {

	private Main plugin;
	public skyblockadd(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("skyblockadd").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You need to be a player");
			return true;
		}
		Player p = (Player)sender;
		if(p.hasPermission("skyblock.add")) {
			if(args.length == 1) {
				ArrayList<String> configList = (ArrayList<String>)plugin.getConfig().getStringList(p.getName()+"friends");
				if(configList.contains(args[0])) {
					p.sendMessage("§2 Player is already added");
					return true;
				}
				configList.add(args[0]);
				plugin.getConfig().set(p.getName()+"friends", configList);
				plugin.saveConfig();
				p.sendMessage("§2"+args[0]+" added");
				return true;
			}
			else {
				p.sendMessage("§2 Usage skyblockadd <Playername>");
				return true;
			}
		}
		else {
			p.sendMessage("§2 You can't do this now!");
			return true;
		}
	}
}
