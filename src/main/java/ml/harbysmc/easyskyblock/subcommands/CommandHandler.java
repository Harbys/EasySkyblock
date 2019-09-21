package ml.harbysmc.easyskyblock.subcommands;

import ml.harbysmc.easyskyblock.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class CommandHandler implements CommandExecutor, TabCompleter {

    private Main plugin;

    private final Map<String, SubCommand> subcommands = new LinkedHashMap<>();

    public CommandHandler (Main plugin) {
        this.plugin = plugin;

        subcommands.put("add", new Add(plugin));
        subcommands.put("home", new Home(plugin));
        subcommands.put("remove", new Remove(plugin));
        subcommands.put("sethome", new Sethome(plugin));
        subcommands.put("sklep", new Sklep());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("NOT A PLAYER");
            return true;
        }

        String name = args.length == 0 ? "home" : args[0];
        SubCommand subcommand = subcommands.get(name);
        if (subcommand == null) {
            sender.sendMessage("Unknown command. Type \"/is help\" for help.");
            return true;
        }

        String[] subArgs = args.length != 0 ? Arrays.copyOfRange(args, 1, args.length) : new String[]{};
        if (subArgs.length < subcommand.getMinArgs()) {
            sender.sendMessage(String.format("Usage: /is %s %s", name, subcommand.getHelp()));
            return true;
        }

        Player player = (Player) sender;
        subcommand.handle(player, subArgs);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return subcommands.keySet().stream()
                .filter(subcommand -> subcommand.startsWith(strings[0]))
                .collect(Collectors.toList());
        } else {
            List<String> players = new ArrayList<>();
            plugin.getServer().getOnlinePlayers().forEach(player -> players.add(player.getName()));
            return players;
        }
    }
}
