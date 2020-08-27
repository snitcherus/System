package de.snitchi.system.util;

import de.snitchi.system.factorys.InstantSmeltItemFactory;
import de.snitchi.system.listener.BlockBreakListener;
import de.snitchi.system.listener.PlayerJoinListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerRegistry {

  /**
   * Registers all listeners.
   * @param plugin System plugin
   * @param resourceMessage resource message
   * @param instantSmeltItemFactory factory class
   */
  public void registerListener(Plugin plugin, ResourceMessage resourceMessage,
                               InstantSmeltItemFactory instantSmeltItemFactory) {
    PluginManager pluginManager = plugin.getServer().getPluginManager();

    Listener playerJoinListener = new PlayerJoinListener(resourceMessage);
    pluginManager.registerEvents(playerJoinListener, plugin);

    Listener blockBreakListener = new BlockBreakListener(instantSmeltItemFactory);
    pluginManager.registerEvents(blockBreakListener, plugin);
  }
}
