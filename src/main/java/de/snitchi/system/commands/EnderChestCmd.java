package de.snitchi.system.commands;

import de.snitchi.system.counter.CooldownManager;
import de.snitchi.system.util.ResourceMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class EnderChestCmd implements CommandExecutor {

  private final ResourceMessage resourceMessage;
  private final CooldownManager cooldownManager;

  public EnderChestCmd(Plugin plugin) {
    this.resourceMessage = new ResourceMessage();
    this.cooldownManager = new CooldownManager(plugin);
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

    int timeLeft = cooldownManager.getCooldown(player.getUniqueId());

    if (timeLeft != 0) {
      resourceMessage.sendMessage(player, "command.counting", timeLeft);
      return true;
    }

    player.openInventory(player.getEnderChest());
    cooldownManager.startCooldown(player, CooldownManager.ENDERCHEST_COOLDOWN, "enderchest.ready");

    return true;
  }
}
