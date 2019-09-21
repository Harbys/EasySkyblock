package ml.harbysmc.easyskyblock.subcommands;

import lombok.AllArgsConstructor;
import ml.harbysmc.easyskyblock.Permissions;
import ml.harbysmc.easyskyblock.models.IslandConfig;
import ml.harbysmc.easyskyblock.utils.Positions;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ml.harbysmc.easyskyblock.Main;

@AllArgsConstructor
class Sethome extends SubCommand {

    private Main plugin;

    @Override
    void handle(Player player, String[] args) {
        if (!player.hasPermission(Permissions.SETHOME)) {
            player.sendMessage("§cYou can't do this right now");
            return;
        }

        if (!player.getWorld().getName().equals("skyblock")) {
            player.sendMessage("§cYou need to be in Skyblock to do this");
            return;
        }

        Location loc = player.getLocation();

        IslandConfig config = plugin.getConfig().getSerializable(player.getName(), IslandConfig.class);

        if (config == null) {
            player.sendMessage("§cYou don't have an island!");
            return;
        }

        if (!Positions.isInBounds(config.getIndex(), loc)) {
            player.sendMessage("§cYou're outside your island!");
            return;
        }

        config.setX(loc.getX());
        config.setY(loc.getY());
        config.setZ(loc.getZ());
        config.setYaw(loc.getYaw());
        config.setPitch(loc.getPitch());
        plugin.getConfig().set(player.getName(), config);
        plugin.saveConfig();

        player.sendMessage("§aSet island spawn!");
    }

    @Override
    int getMinArgs() {
        return 0;
    }

    @Override
    String getHelp() {
        return null;
    }
}
