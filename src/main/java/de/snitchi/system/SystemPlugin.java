package de.snitchi.system;

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

    registerCommands();
    configHandler.loadConfig(this);
    listenerRegistry.registerListener(this, resourceMessage, instantSmeltItemFactory);
  }

  private void registerCommands() {
    // getCommand("Name").setExecutor(new NameCmd());

  }
}
