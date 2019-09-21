package ml.harbysmc.easyskyblock.listeners;

import ml.harbysmc.easyskyblock.utils.StoneGenerators;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!e.getBlock().getWorld().getName().equals("skyblock")) {
            return;
        }

        Block b = e.getBlock();
        if (StoneGenerators.isGenerator(e.getBlock().getType())) {
            b.getLocation().add(0, 1, 0).getBlock().setType(Material.STONE);
        }
    }
}
