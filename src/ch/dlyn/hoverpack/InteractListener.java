package ch.dlyn.hoverpack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem().getType().equals(HoverPack.getRefinedFuel().getType())) {
			if (e.getItem().getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
				for (ItemStack item : e.getPlayer().getInventory().all(HoverPack.getNewHoverPack().getType()).values()) {
					if (item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
						HoverPack.updateFuelLevel(item, 60, e.getPlayer());
						e.getItem().subtract();
						return;
					}
				}
			}
		}
	}
}
