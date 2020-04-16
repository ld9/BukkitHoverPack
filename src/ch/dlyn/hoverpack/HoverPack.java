package ch.dlyn.hoverpack;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.plugin.java.JavaPlugin;


public class HoverPack extends JavaPlugin {
	public static ArrayList<Player> activePlayers;
	
	@Override
	public void onEnable() {
		getServer().getLogger().info("Starting Hoverpack Plugin");
		
		CraftingAdditions.AddCraftingRecipes();
		getServer().getPluginManager().registerEvents(new MobKillListener(), this);
		getServer().getPluginManager().registerEvents(new ArmorChangeListener(), this);
		getServer().getPluginManager().registerEvents(new InteractListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		
		activePlayers = new ArrayList<>();
		
		scheduleDecayEvent();
	}
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("Shutting down Hoverpack Plugin");
	}
	
	private void scheduleDecayEvent() {
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				try {
					for (Player player : activePlayers) {
						HoverPack.updateFuelLevel(player.getInventory().getChestplate(), -1, player);
					}
				} catch (ConcurrentModificationException e) {
					// I don't care.
				}
			}
			
		}, 0L, 20L);
	}
	
	public static void updateFuelLevel(ItemStack hoverPack, int amount, Player p) {
		ItemMeta hoverPackMeta = hoverPack.getItemMeta();
		int damage = ((Damageable) hoverPackMeta).getDamage();
		int maxDamage = hoverPack.getType().getMaxDurability();
		
		damage += (-1 * amount);
		
		if (damage < 0) {
			damage = 0;
		}
		
		((Damageable) hoverPackMeta).setDamage(damage);
		ArrayList<String> lore = new ArrayList<String>(hoverPackMeta.getLore());
		lore.remove(lore.size() - 1);
		lore.add(ChatColor.RED + "Current Charge: " + (maxDamage - damage) + "/" + maxDamage);
		hoverPackMeta.setLore(lore);
		hoverPack.setItemMeta(hoverPackMeta);
		
		if (damage >= maxDamage && p != null) {
			p.setAllowFlight(false);
			HoverPack.activePlayers.remove(p);
		}
	}
}
