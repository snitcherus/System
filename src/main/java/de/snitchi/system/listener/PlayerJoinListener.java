package de.snitchi.system.listener;

import de.snitchi.system.util.ResourceMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

  private final ResourceMessage resourceMessage;

  public PlayerJoinListener(ResourceMessage resourceMessage) {
    this.resourceMessage = resourceMessage;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    event.setJoinMessage(resourceMessage.getMessage("join_message", player.getDisplayName()));
  }
}
