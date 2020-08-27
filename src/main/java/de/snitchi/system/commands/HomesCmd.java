package de.snitchi.system.commands;

import de.snitchi.system.util.ResourceMessage;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class HomesCmd implements CommandExecutor {

  private final Configuration configuration;
  private final ResourceMessage resourceMessage;

  private List<String> playerHomes = new ArrayList<>();

  public HomesCmd(Configuration configuration) {
    this.configuration = configuration;
    this.resourceMessage = new ResourceMessage();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    if (!configuration.contains(player.getUniqueId() + ".Homes")) {
      resourceMessage.sendMessage(player, "home.no_home_set");
      return true;
    }

    playerHomes.addAll(configuration.getConfigurationSection(player.getUniqueId().toString()
        + ".Homes").getKeys(false));

    String seperator = resourceMessage.getMessage("home.seperator");
    String homeColor = resourceMessage.getMessage("home.home_color");

    StringBuilder homesJoin = new StringBuilder();

    if (playerHomes.size() > 0) {
      homesJoin = new StringBuilder(homeColor + playerHomes.get(0));
    }

    for (int i = 1; i < playerHomes.size(); i++) {
      homesJoin.append(seperator).append(homeColor).append(playerHomes.get(i));
    }

    resourceMessage.sendMessage(player, "home.homes_show", homesJoin.toString());
    return true;
  }
}
