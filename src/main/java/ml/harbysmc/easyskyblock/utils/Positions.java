package ml.harbysmc.easyskyblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Positions {
    final static private Location start = new Location(Bukkit.getWorld("skyblock"), 0, 64, 0);

    public static Location getIslandLocation(int index) {
        return start.clone().add(index * 256, 0, 0);
    }

    public static boolean isInBounds(int index, Location loc) {
        return loc.getBlockX() <= (index * 256 + 127) && loc.getBlockX() >= (index * 256 - 127) &&
        loc.getBlockZ() <= 127 && loc.getBlockZ() >= -127;
    }
}
