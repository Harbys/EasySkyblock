package ml.harbysmc.easyskyblock.subcommands;

import lombok.AllArgsConstructor;
import ml.harbysmc.easyskyblock.Main;
import ml.harbysmc.easyskyblock.Permissions;
import ml.harbysmc.easyskyblock.models.IslandConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
class Add extends SubCommand {

    private Main plugin;

    @Override
	void handle(Player player, String[] args) {
		if (!player.hasPermission(Permissions.ADD)) {
			player.sendMessage("§2 You can't do this now!");
			return;
		}

		if (Objects.equals(args[0], player.getName())) {
			player.sendMessage("§cYou can't add yourself!");
			return;
		}

		IslandConfig config = plugin.getConfig().getSerializable(player.getName(), IslandConfig.class);
		if (config == null) {
			player.sendMessage("§cYou don't have an island!");
			return;
		}
		List<String> friends = config.getFriends();
		if (friends.contains(args[0])) {
			player.sendMessage("§cPlayer is added already to your island!");
			return;
		}
		friends.add(args[0]);
		plugin.getConfig().set(player.getName(), config);
		plugin.saveConfig();

		player.sendMessage("§a" + args[0] + " was added to your island!");
	}

    @Override
    int getMinArgs() {
        return 1;
    }

    @Override
    String getHelp() {
        return "<player name>";
    }
}
