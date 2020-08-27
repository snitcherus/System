package de.snitchi.system.listener;

import de.snitchi.system.factorys.InstantSmeltItemFactory;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

  private final InstantSmeltItemFactory instantSmeltItemFactory;

  private final List<Material> ironAllowedPickaxes = List.of(Material.STONE_PICKAXE,
      Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE);
  private final List<Material> goldAllowedPickaxes = List.of(Material.IRON_PICKAXE,
      Material.DIAMOND_PICKAXE);

  public BlockBreakListener(
      InstantSmeltItemFactory instantSmeltItemFactory) {
    this.instantSmeltItemFactory = instantSmeltItemFactory;
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();

    Block block = event.getBlock();
    World blockWorld = block.getWorld();

    ItemStack ironIngot = instantSmeltItemFactory.createIronIngot();
    ItemStack goldIngot = instantSmeltItemFactory.createGoldIngot();

    if (block.getType() == Material.IRON_ORE) {

      if (!ironAllowedPickaxes.contains(player.getInventory().getItemInMainHand().getType())) {
        return;
      }

      dropSmeltedItem(block, player, blockWorld, ironIngot);
      return;
    }

    if (block.getType() == Material.GOLD_ORE) {

      if (!goldAllowedPickaxes.contains(player.getInventory().getItemInMainHand().getType())) {
        return;
      }

      dropSmeltedItem(block, player, blockWorld, goldIngot);
    }
  }

  private void dropSmeltedItem(Block block, Player player, World world, ItemStack itemToDrop) {

    block.setType(Material.AIR);
    player.giveExp(1);
    world.dropItemNaturally(block.getLocation(), itemToDrop);
  }
}
