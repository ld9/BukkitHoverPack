package ch.dlyn.hoverpack;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.plugin.java.JavaPlugin;


public class HoverPack extends JavaPlugin {
	public static ArrayList<Player> activePlayers;
	
	@Override
	public void onEnable() {
		getServer().getLogger().info("Starting HoverPack Plugin");
		
		CraftingAdditions.AddCraftingRecipes();
		getServer().getPluginManager().registerEvents(new MobKillListener(), this);
		getServer().getPluginManager().registerEvents(new ArmorChangeListener(), this);
		getServer().getPluginManager().registerEvents(new InteractListener(), this);
		
		activePlayers = new ArrayList<>();
		
		scheduleDecayEvent();
	}
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("Shutting down HoverPack Plugin");
	}
	
	private void scheduleDecayEvent() {
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for (Player player : activePlayers) {
					HoverPack.updateFuelLevel(player.getInventory().getChestplate(), -1, player);
				}
			}
			
		}, 0L, 20L);
	}
	
	public static void updateFuelLevel(ItemStack hoverPack, int amount, Player p) {
		ItemMeta hoverPackMeta = hoverPack.getItemMeta();
		int damage = ((Damageable) hoverPackMeta).getDamage();
		int maxDamage = hoverPack.getType().getMaxDurability();
		
		damage += (-1 * amount);
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
	
	public static NamespacedKey getRefinedFuelNamespacedKey() {
		return new NamespacedKey(HoverPack.getPlugin(HoverPack.class), "refinedFuel");
	}
	
	public static NamespacedKey getHoverPackNamespacedKey() {
		return new NamespacedKey(HoverPack.getPlugin(HoverPack.class), "hoverPack");
	}
	
	public static ItemStack getCustomFuelRod() {
		ItemStack customFuel = new ItemStack(Material.BLAZE_POWDER);
		
		ItemMeta customFuelMeta = customFuel.getItemMeta();
		customFuelMeta.setDisplayName(ChatColor.DARK_RED + "Unstable Fuel");
		customFuel.setItemMeta(customFuelMeta);
		
		customFuel.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		return customFuel;
	}
	
	public static ItemStack getRefinedFuel() {
		ItemStack fuelItem = new ItemStack(Material.BLAZE_POWDER);
		fuelItem.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		ItemMeta fuelItemMeta = fuelItem.getItemMeta();
		fuelItemMeta.setDisplayName(ChatColor.DARK_GREEN + "Refined Fuel");
		fuelItem.setItemMeta(fuelItemMeta);
		
		return fuelItem;
	}
	
	public static ItemStack getNewHoverPack() {
		ItemStack hoverPack = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		
		hoverPack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemMeta hoverPackMeta = hoverPack.getItemMeta();
		hoverPackMeta.setDisplayName(ChatColor.GOLD + "HoverPack");
		ArrayList<String> hoverPackLore = new ArrayList<>();
		hoverPackLore.add(ChatColor.GOLD + "Charge with fuel and equip to fly for a short period.");
		hoverPackLore.add(ChatColor.RED + "Current Charge: 240/240");
		hoverPackMeta.setLore(hoverPackLore);
		hoverPack.setItemMeta(hoverPackMeta);
		
		return hoverPack;
	}
}
