package de.snitchi.system.commands;

import de.snitchi.system.util.ResourceMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetHomeCmd implements CommandExecutor {

  private final ResourceMessage resourceMessage;
  private final Configuration configuration;
  private final Plugin plugin;

  public SetHomeCmd(Configuration configuration, Plugin plugin) {
    this.configuration = configuration;
    this.plugin = plugin;
    this.resourceMessage = new ResourceMessage();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    if (args.length != 1) {
      resourceMessage.getMessage("home.set_home_usage");
      return true;
    }

    String homeName = args[0].toLowerCase();

    if (configuration.isSet(
        player.getUniqueId() + ".Homes" + "." + homeName + ".pos")) {
      resourceMessage.sendMessage(player, "home.already_exists", homeName);
      return true;
    }

    configuration.set(player.getUniqueId() + ".Homes" + "." + homeName + ".pos",
        player.getLocation());
    plugin.saveConfig();

    resourceMessage.sendMessage(player, "home.success_set", homeName);

    return true;
  }
}
