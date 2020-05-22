package de.snitchi.system.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigHandler {

  private FileConfiguration userConfig;

  public void loadConfig(Plugin plugin) {
    plugin.getConfig().options().copyDefaults(true);
    plugin.saveConfig();

    userConfig = loadYml("userdata.yml", plugin);
  }

  private FileConfiguration loadYml(String name, Plugin plugin) {
    File file = new File(plugin.getDataFolder(), name);
    if (!plugin.getDataFolder().exists()) {
      plugin.getDataFolder().mkdirs();
    }

    if (!file.exists()) {
      try (InputStream in = plugin.getResource(name)) {
        Files.copy(in, file.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return YamlConfiguration.loadConfiguration(file);
  }

  public FileConfiguration getUserConfig() {
    return userConfig;
  }

  public void saveUserConfig(Plugin plugin) {
    try {
      userConfig.save(new File(plugin.getDataFolder(), "userdata.yml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
