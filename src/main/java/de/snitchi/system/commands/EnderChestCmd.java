package de.snitchi.system.commands;

import de.snitchi.system.util.ResourceMessage;
import java.util.HashMap;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCmd implements CommandExecutor {

  private final ResourceMessage resourceMessage;

  private HashMap<String, Long> cooldown = new HashMap<String, Long>();

  public EnderChestCmd() {
    this.resourceMessage = new ResourceMessage();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    if (args.length != 0) {
      resourceMessage.sendMessage(player, "enderchest.usage");
      return true;
    }

    int coolDownTime = 5;

    if (!cooldown.containsKey(player.getName())) {
      cooldown.put(player.getName(), System.currentTimeMillis());
      player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
      player.openInventory(player.getEnderChest());
      return true;
    }

    long secondsLeft =
        ((cooldown.get(sender.getName()) / 1000) + coolDownTime) - (System.currentTimeMillis()
            / 1000);

    if (secondsLeft > 0) {
      resourceMessage.sendMessage(player, "command.counting", secondsLeft);
      return true;
    }

    cooldown.remove(player.getName());

    return true;
  }
}
