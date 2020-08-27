package de.snitchi.system.counter;

import de.snitchi.system.util.ResourceMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownManager {

  private final Map<UUID, Integer> cooldowns = new HashMap<>();

  private final ResourceMessage resourceMessage;
  private final Plugin plugin;

  public static final int ENDERCHEST_COOLDOWN = 5;

  public CooldownManager(Plugin plugin) {
    this.plugin = plugin;
    this.resourceMessage = new ResourceMessage();
  }

  public void setCooldown(UUID player, int time){
    if(time < 1) {
      cooldowns.remove(player);
    } else {
      cooldowns.put(player, time);
    }
  }

  public int getCooldown(UUID player){
    return cooldowns.getOrDefault(player, 0);
  }

  public void startCooldown(Player player, int time, String messagePath){
    setCooldown(player.getUniqueId(), time);

    new BukkitRunnable() {
      @Override
      public void run() {
        int timeLeft = getCooldown(player.getUniqueId());
        setCooldown(player.getUniqueId(), --timeLeft);
        if(timeLeft == 0){
          resourceMessage.sendMessage(player, messagePath);
          this.cancel();
        }
      }
    }.runTaskTimer(plugin, 20, 20);
  }
}
