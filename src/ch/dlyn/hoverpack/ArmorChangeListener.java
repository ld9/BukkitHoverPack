package ch.dlyn.hoverpack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import com.codingforcookies.armorequip.ArmorEquipEvent;

public class ArmorChangeListener implements Listener {
	@EventHandler
	public void onInventoryChange(ArmorEquipEvent e) {
		ItemStack newArmor = e.getNewArmorPiece();
		ItemStack oldArmor = e.getOldArmorPiece();
		
		if (newArmor != null &&
				newArmor.getType().equals(HoverPack.getNewHoverPack().getType()) &&
				newArmor.getEnchantments().containsKey(Enchantment.ARROW_INFINITE) &&
				((Damageable) newArmor.getItemMeta()).getDamage() < HoverPack.getNewHoverPack().getType().getMaxDurability() ) {
			e.getPlayer().setAllowFlight(true);
			HoverPack.activePlayers.add(e.getPlayer());
		}
		
		if (oldArmor != null &&
				oldArmor.getType().equals(HoverPack.getNewHoverPack().getType()) &&
				oldArmor.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
			e.getPlayer().setAllowFlight(false);
			HoverPack.activePlayers.remove(e.getPlayer());
		}
	}
}
