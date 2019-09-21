package ml.harbysmc.easyskyblock.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class StoneGenerators {
    public static ItemStack getCoal() {
        return get(Material.COAL_ORE, 1);
    }

    public static boolean isCoal(ItemStack other) {
        return Objects.equals(getCoal(), other);
    }

    public static ItemStack getIron() {
        return get(Material.IRON_ORE, 2);
    }

    public static boolean isIron(ItemStack other) {
        return Objects.equals(getIron(), other);
    }

    public static ItemStack getDiamond() {
        return get(Material.DIAMOND_ORE, 3);
    }

    public static boolean isDiamond(ItemStack other) {
        return Objects.equals(getDiamond(), other);
    }

    public static boolean isGenerator(Material mat) {
        return mat.equals(Material.COAL_ORE) || mat.equals(Material.IRON_ORE) || mat.equals(Material.DIAMOND_ORE);
    }

    private static ItemStack get(Material mat, int level) {
        ItemStack drop = new ItemStack(mat, 1);
        ItemMeta meta = drop.getItemMeta();
        meta.setDisplayName("§5STONE MAKER LVL " + level);
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, level, true);
        drop.setItemMeta(meta);
        return drop;
    }
}
