package ch.dlyn.hoverpack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getType().equals(CraftingAdditions.getNewHoverPack().getType()) && p.getInventory().getChestplate().containsEnchantment(Enchantment.ARROW_INFINITE)) {
			if (!HoverPack.activePlayers.contains(p)) {
				HoverPack.activePlayers.add(p);
			}
		}
	}
}
