package de.snitchi.system.commands;

import de.snitchi.system.counter.WorkbenchCounter;
import de.snitchi.system.util.ResourceMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCmd implements CommandExecutor {

  private final WorkbenchCounter workbenchCounter;
  private final ResourceMessage resourceMessage;

  public WorkbenchCmd(WorkbenchCounter workbenchCounter) {
    this.workbenchCounter = workbenchCounter;
    this.resourceMessage = new ResourceMessage();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    Player player = (Player) sender;

    if (args.length != 0) {
      resourceMessage.sendMessage(player, "workbench.usage");
      return true;
    }
    if (workbenchCounter.counting) {
      resourceMessage.sendMessage(player, "workbench.counting", workbenchCounter.currentCount);
      return true;
    }

    workbenchCounter.start();
    player.openWorkbench(null, true);
    return true;
  }
}
