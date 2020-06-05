package de.snitchi.system.commands;

import de.snitchi.system.util.ResourceMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HomeCmd implements CommandExecutor {

  private final Configuration configuration;
  private final Plugin plugin;
  private final ResourceMessage resourceMessage;

  public HomeCmd(Configuration configuration, Plugin plugin) {
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
    String homeName = args[0].toLowerCase();

    if (args.length != 1) {
      resourceMessage.sendMessage(player, "home.home_usage");
      return true;
    }

    if (!configuration.isSet(
        player.getUniqueId() + ".Homes" + "." + args[0].toLowerCase() + ".pos")) {
      resourceMessage.sendMessage(player, "");
    }

    Location homeLocation = (Location) configuration.get(
        player.getUniqueId() + ".Homes" + homeName + ".pos");

    assert homeLocation != null;
    player.teleport(homeLocation);
    resourceMessage.sendMessage(player, "home.home_teleport", homeName);

    return true;
  }
}
