package ch.dlyn.hoverpack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class InteractListener implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() != null && e.getItem().getType().equals(CraftingAdditions.getRefinedFuel().getType())) {
			if (e.getItem().getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
				for (ItemStack item : e.getPlayer().getInventory().all(CraftingAdditions.getNewHoverPack().getType()).values()) {
					if (item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE) && ((Damageable) item.getItemMeta()).getDamage() > 0) {
						HoverPack.updateFuelLevel(item, 60, e.getPlayer());
						e.getItem().subtract();
						return;
					}
				}
			}
		}
	}
}
