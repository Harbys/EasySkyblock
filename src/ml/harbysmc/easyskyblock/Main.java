package ml.harbysmc.easyskyblock;

import org.bukkit.plugin.java.JavaPlugin;

import ml.harbysmc.easyskyblock.commands.sklep;
import ml.harbysmc.easyskyblock.commands.skyblock;
import ml.harbysmc.easyskyblock.commands.skyblockadd;
import ml.harbysmc.easyskyblock.commands.skyblockremove;
import ml.harbysmc.easyskyblock.commands.skyblocksetspawn;
import ml.harbysmc.easyskyblock.commands.skyblockspawn;
import ml.harbysmc.easyskyblock.commands.stonemaker;
import ml.harbysmc.easyskyblock.commands.ulepsz;
import ml.harbysmc.easyskyblock.listeners.onblockbreak;
import ml.harbysmc.easyskyblock.listeners.onblockplace;


public class Main extends JavaPlugin {

	public void loadConfiguration(){
		 getConfig().addDefault("LastIsland", 0);
	     getConfig().options().copyDefaults(true);
	     saveConfig();
	}
	@Override
	public void onEnable() {
		new skyblock(this);
		new skyblockadd(this);
		new skyblockremove(this);
		new onblockbreak(this);
		new onblockplace(this);
		new ulepsz(this);
		new stonemaker(this);
		new skyblocksetspawn(this);
		new skyblockspawn(this);
		new sklep(this);
		loadConfiguration();
	}
}
