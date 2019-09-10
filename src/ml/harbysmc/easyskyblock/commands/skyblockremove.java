package ml.harbysmc.easyskyblock.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ml.harbysmc.easyskyblock.Main;

public class skyblockremove implements CommandExecutor {

	private Main plugin;
	public skyblockremove(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("skyblockremove").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You need to be a player");
			return true;
		}
		Player p = (Player)sender;
		if(p.hasPermission("skyblock.remove")) {
			if(args.length == 1) {
				if(args[0].equals(p.getName())) {
					p.sendMessage("§2 You can't remove yourself");
					return true;
				}
				ArrayList<String> configList = (ArrayList<String>)plugin.getConfig().getStringList(p.getName()+"friends");
				if(!(configList.contains(args[0]))) {
					p.sendMessage("§2 Player is not added");
					return true;
				}
				configList.remove(args[0]);
				plugin.getConfig().set(p.getName()+"friends", configList);
				plugin.saveConfig();
				p.sendMessage("§2"+args[0]+" removed");
				return true;
			}
			else {
				p.sendMessage("§2 Usage skyblockremove <Playername>");
				return true;
			}
		}
		else {
			p.sendMessage("§2 You can't do this now!");
			return true;
		}
	}
}
