package de.snitchi.system.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import org.bukkit.entity.Player;

public class ResourceMessage {

  public void sendMessage(Player player, String path, Object... replaces) {
    player.sendMessage(getMessage(path, replaces));
  }

  /**
   * Gets the message from the Bedwars resourceBundle.
   *
   * @param path      path to the message
   * @param replaces replaces a part of the message
   * @return formats the message
   */
  public String getMessage(String path, Object... replaces) {
    ResourceBundle systemBundle = ResourceBundle.getBundle("System");
    String pattern = systemBundle.getString(path);
    return MessageFormat.format(pattern, replaces);
  }
}
