package de.snitchi.system;

import de.snitchi.system.commands.HomeCmd;
import de.snitchi.system.commands.HomesCmd;
import de.snitchi.system.commands.SetHomeCmd;
import de.snitchi.system.commands.WorkbenchCmd;
import de.snitchi.system.counter.WorkbenchCounter;
import de.snitchi.system.factorys.InstantSmeltItemFactory;
import de.snitchi.system.util.ConfigHandler;
import de.snitchi.system.util.ListenerRegistry;
import de.snitchi.system.util.ResourceMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class SystemPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    ListenerRegistry listenerRegistry = new ListenerRegistry();
    ConfigHandler configHandler = new ConfigHandler();
    ResourceMessage resourceMessage = new ResourceMessage();
    InstantSmeltItemFactory instantSmeltItemFactory = new InstantSmeltItemFactory();
    WorkbenchCounter workbenchCounter = new WorkbenchCounter(this);

    registerCommands(workbenchCounter);
    configHandler.loadConfig(this);
    listenerRegistry.registerListener(this, resourceMessage, instantSmeltItemFactory);
  }

  private void registerCommands(WorkbenchCounter workbenchCounter) {
    getCommand("sethome").setExecutor(new SetHomeCmd(getConfig(), this));
    getCommand("home").setExecutor(new HomeCmd(getConfig()));
    getCommand("homes").setExecutor(new HomesCmd(getConfig()));
    getCommand("workbench").setExecutor(new WorkbenchCmd(workbenchCounter));
  }
}
