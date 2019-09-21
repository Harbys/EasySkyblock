package ml.harbysmc.easyskyblock.listeners;

import lombok.AllArgsConstructor;
import ml.harbysmc.easyskyblock.Main;
import ml.harbysmc.easyskyblock.utils.StoneGenerators;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class BlockBreakListener implements Listener {

    private Main plugin;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!e.getBlock().getWorld().getName().equals("skyblock")) {
            return;
        }

        Block b = e.getBlock();
        if (b.getType().equals(Material.STONE)) {
            dropRandom(b.getLocation());

            int delay = 0;
            Material blockBelow = b.getLocation().subtract(0, 1, 0).getBlock().getType();

            if (blockBelow.equals(Material.COAL_ORE)) {
                delay = 20;
            }
            if (blockBelow.equals(Material.IRON_ORE)) {
                delay = 10;
            }
            if (blockBelow.equals(Material.DIAMOND_ORE)) {
                delay = 2;
            }

            if (StoneGenerators.isGenerator(blockBelow)) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> b.setType(Material.STONE), delay);
            }
        }

        if (StoneGenerators.isGenerator(b.getType())) {
            e.setDropItems(false);

            if (b.getType().equals(Material.COAL_ORE)) {
                b.getWorld().dropItem(b.getLocation(), StoneGenerators.getCoal());
            }

            if (b.getType().equals(Material.IRON_ORE)) {
                b.getWorld().dropItem(b.getLocation(), StoneGenerators.getIron());
            }

            if (b.getType().equals(Material.DIAMOND_ORE)) {
                b.getWorld().dropItem(b.getLocation(), StoneGenerators.getDiamond());
            }
        }
    }

    private void dropRandom(Location location) {
        Material drop = null;
        int rand = (int) (Math.random() * 1000 + 1);

        if (rand <= 5) {
            drop = Material.DIAMOND;
        } else if (rand <= 35) {
            drop = Material.REDSTONE;
        } else if (rand <= 55) {
            drop = Material.DIRT;
        } else if (rand <= 105) {
            drop = Material.IRON_INGOT;
        } else if (rand <= 115) {
            drop = Material.SLIME_BALL;
        } else if (rand <= 125) {
            drop = Material.GOLD_INGOT;
        } else if (rand <= 145) {
            drop = Material.SAND;
        } else if (rand <= 155) {
            drop = Material.GRAVEL;
        } else if (rand <= 160) {
            drop = Material.OBSIDIAN;
        } else if (rand <= 260) {
            drop = Material.COAL;
        } else if (rand <= 310) {
            ExperienceOrb orb = location.getWorld().spawn(location, ExperienceOrb.class);
            orb.setExperience(5);
        }

        if (drop == null) return;
        location.getWorld().dropItemNaturally(location, new ItemStack(drop, 1));
    }
}

