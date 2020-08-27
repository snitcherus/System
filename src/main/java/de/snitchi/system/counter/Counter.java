package de.snitchi.system.counter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class Counter {

  private final Plugin plugin;
  private final int counterDuration;

  private int currentCounter;
  private int taskId = -1;

  public Counter(Plugin plugin, int counterDuration) {
    this.plugin = plugin;
    this.counterDuration = counterDuration;
  }

  /**
   * Starts the counter.
   */
  public final void start() {
    if (taskId == -1) {
      onStart();
      currentCounter = counterDuration;
      taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::processCounter, 0L,
          20L);
    }
  }

  protected abstract void onStart();

  private void processCounter() {
    onCount(currentCounter);
    currentCounter--;

    if (currentCounter == 0) {
      onFinished();
      stopTask();
    }
  }

  protected abstract void onCount(int currentCounter);

  protected abstract void onFinished();

  private void stopTask() {
    Bukkit.getScheduler().cancelTask(taskId);
    taskId = -1;
  }

  public final void stop() {
    stopTask();
    onStopped();
  }

  protected abstract void onStopped();
}
