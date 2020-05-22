package de.snitchi.system.factorys;

import de.snitchi.itembuilder.api.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class InstantSmeltItemFactory {

  public ItemStack createIronIngot(){
    return new ItemBuilder(Material.IRON_INGOT).build();
  }

  public ItemStack createGoldIngot(){
    return new ItemBuilder(Material.GOLD_INGOT).build();
  }

}
