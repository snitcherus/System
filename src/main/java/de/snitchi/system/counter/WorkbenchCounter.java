package de.snitchi.system.counter;

import org.bukkit.plugin.Plugin;

public class WorkbenchCounter extends Counter {

  private static final int WORKBENCH_COUNTER_DURATION = 30;
  public boolean counting;
  public int currentCount;

  public WorkbenchCounter(Plugin plugin) {
    super(plugin, WORKBENCH_COUNTER_DURATION);
  }

  @Override
  protected void onStart() {
    counting = true;
  }

  @Override
  protected void onCount(int currentCounter) {
    currentCount = currentCounter;
  }

  @Override
  protected void onFinished() {
    counting = false;
  }

  @Override
  protected void onStopped() {

  }
}
