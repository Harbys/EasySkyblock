package ml.harbysmc.easyskyblock.subcommands;

import lombok.AllArgsConstructor;
import ml.harbysmc.easyskyblock.Main;
import ml.harbysmc.easyskyblock.Permissions;
import ml.harbysmc.easyskyblock.models.IslandConfig;
import ml.harbysmc.easyskyblock.utils.IslandBuilder;
import ml.harbysmc.easyskyblock.utils.Positions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@AllArgsConstructor
public class Home extends SubCommand {

    private Main plugin;

    private void checkIfMoved(Player player, Runnable runnable) {
        player.sendMessage("§2Don't move for 3 seconds");
        Location oldloc = player.getLocation();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Location loc = player.getLocation();
            if (oldloc.getX() == loc.getX() && oldloc.getY() == loc.getY() && oldloc.getZ() == loc.getZ()) {
                runnable.run();
            } else {
                player.sendMessage("§cYou moved!");
            }
        }, 20 * 3);
    }

    private void teleport(Player player) {
        IslandConfig island = plugin.getConfig().getSerializable(player.getName(), IslandConfig.class);
        if (island == null) return;
        teleport(player, island);
    }

    private void teleport(Player player, IslandConfig island) {
        World world = Bukkit.getWorld("skyblock");
        Location location = new Location(
            world,
            island.getX(),
            island.getY(),
            island.getZ(),
            island.getYaw(),
            island.getPitch()
        );
        player.teleport(location);
    }

    @Override
    public void handle(Player player, String[] args) {
        if (!(player.hasPermission(Permissions.SKYBLOCK))) {
            player.sendMessage("§2 You can't do this now!");
            return;
        }

        if (args.length == 1) {
            IslandConfig config = plugin.getConfig().getSerializable(args[0], IslandConfig.class);

            if (config == null) {
                player.sendMessage("§cPlayer not found or has no island");
                return;
            }

            if (!config.getFriends().contains(player.getName())) {
                player.sendMessage("§cYou're not added by this player!");
                return;
            }

            if (!player.getWorld().getName().equals("survival")) {
                teleport(player, config);
                return;
            }

            checkIfMoved(player, () -> teleport(player, config));
            return;
        }

        if (plugin.getConfig().getSerializable(player.getName(), IslandConfig.class) != null) {
            if (!player.getWorld().getName().equals("survival")) {
                teleport(player);
                return;
            }
            checkIfMoved(player, () -> teleport(player));
        } else {
            player.sendMessage("§aCreating your island...");
            int count = plugin.getConfig().getInt("count", 0);

            boolean success = IslandBuilder.pasteIsland(plugin, Positions.getIslandLocation(count));
            if (!success) {
                player.sendMessage("§cIsland creation failed");
                return;
            }

            IslandConfig config = new IslandConfig();
            config.setIndex(count);
            config.setFriends(new ArrayList<>());
            config.setX(count * 256 + 0.5);
            config.setY(64);
            config.setZ(-3 + 0.5);
            config.setYaw(0);
            config.setPitch(0);
            plugin.getConfig().set(player.getName(), config);

            count += 1;
            plugin.getConfig().set("count", count);
            plugin.saveConfig();

            player.sendMessage("§aIsland created, teleporting...");
            teleport(player);
        }
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