package de.snitchi.system.commands;

import de.snitchi.system.util.ResourceMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class HomeCmd implements CommandExecutor {

  private final Configuration configuration;
  private final ResourceMessage resourceMessage;

  public HomeCmd(Configuration configuration) {
    this.configuration = configuration;
    this.resourceMessage = new ResourceMessage();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    if (args.length != 1) {
      resourceMessage.sendMessage(player, "home.home_usage");
      return true;
    }

    String homeName = args[0].toLowerCase();

    if (!configuration.isSet(
        player.getUniqueId() + ".Homes" + "." + homeName + ".pos")) {
      resourceMessage.sendMessage(player, "home.home_not_set");
      return true;
    }

    Location homeLocation = (Location) configuration.get(
        player.getUniqueId() + ".Homes" + "." + homeName + ".pos");

    assert homeLocation != null;
    player.teleport(homeLocation);
    resourceMessage.sendMessage(player, "home.home_teleport", homeName);
    return true;
  }
}
