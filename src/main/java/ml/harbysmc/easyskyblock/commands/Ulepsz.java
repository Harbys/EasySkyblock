package ml.harbysmc.easyskyblock.commands;

import ml.harbysmc.easyskyblock.Permissions;
import ml.harbysmc.easyskyblock.utils.StoneGenerators;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Ulepsz implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player");
            return true;
        }
        Player p = (Player) sender;

        if (!p.hasPermission(Permissions.ULEPSZ)) {
            p.sendMessage("§cYou can't do this now!");
            return true;
        }

        if (p.getEquipment() == null) {
            p.sendMessage("§cYou need to hold a StoneMaker in your hand to upgrade it!");
            return true;
        }

        ItemStack hand = p.getEquipment().getItemInMainHand();
		Inventory inv = p.getInventory();

        if (StoneGenerators.isCoal(hand)) {
            if (!inv.containsAtLeast(new ItemStack(Material.IRON_INGOT), 32)) {
                p.sendMessage("§cYou don't have enough iron, you need 32");
                return true;
            }
            inv.removeItem(new ItemStack(Material.IRON_INGOT, 32));
            inv.removeItem(StoneGenerators.getCoal());
            inv.addItem(StoneGenerators.getIron());
            return true;
        }

        if (StoneGenerators.isIron(hand)) {
            if (!inv.containsAtLeast(new ItemStack(Material.DIAMOND), 32)) {
                p.sendMessage("§cYou don't have enough diamonds, you need 32");
                return true;
            }
            inv.removeItem(new ItemStack(Material.DIAMOND, 32));
            inv.removeItem(StoneGenerators.getIron());
            inv.addItem(StoneGenerators.getDiamond());
            return true;
        }

        if (StoneGenerators.isDiamond(hand)) {
        	p.sendMessage("§cYou can't upgrade it more!");
        	return true;
		}

		p.sendMessage("§cYou need to hold a StoneMaker in your hand to upgrade it!");
        return true;
    }
}