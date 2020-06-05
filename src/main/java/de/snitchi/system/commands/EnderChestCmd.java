package de.snitchi.system.commands;

import de.snitchi.system.util.ResourceMessage;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCmd implements CommandExecutor {

  private final ResourceMessage resourceMessage;

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

    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
    player.openInventory(player.getEnderChest());
    return true;
  }
}
