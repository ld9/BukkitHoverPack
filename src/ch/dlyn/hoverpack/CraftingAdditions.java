package ch.dlyn.hoverpack;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class CraftingAdditions {
	public static void AddCraftingRecipes() {	
		ShapedRecipe fuelRecipe = new ShapedRecipe(getRefinedFuelNamespacedKey(), getRefinedFuel());
		fuelRecipe.shape("XAX", "ABA", "XAX");
		fuelRecipe.setIngredient('X', Material.AIR);
		fuelRecipe.setIngredient('A', getCustomRawFuel());
		fuelRecipe.setIngredient('B', Material.DIAMOND);
		
		HoverPack.getPlugin(HoverPack.class).getServer().addRecipe(fuelRecipe);
		
		ShapedRecipe hoverPack = new ShapedRecipe(getHoverPackNamespacedKey(), getNewHoverPack());
		hoverPack.shape("XAX", "ABA", "CAC");
		hoverPack.setIngredient('X', Material.AIR);
		hoverPack.setIngredient('A', getCustomRawFuel());
		hoverPack.setIngredient('B', Material.DIAMOND_CHESTPLATE);
		hoverPack.setIngredient('C', Material.NETHER_STAR);
		
		HoverPack.getPlugin(HoverPack.class).getServer().addRecipe(hoverPack);
	}
	
	public static NamespacedKey getRefinedFuelNamespacedKey() {
		return new NamespacedKey(HoverPack.getPlugin(HoverPack.class), "refinedFuel");
	}
	
	public static NamespacedKey getHoverPackNamespacedKey() {
		return new NamespacedKey(HoverPack.getPlugin(HoverPack.class), "hoverPack");
	}
	
	public static ItemStack getCustomRawFuel() {
		ItemStack customFuel = new ItemStack(Material.BLAZE_POWDER);
		
		ItemMeta customFuelMeta = customFuel.getItemMeta();
		customFuelMeta.setDisplayName(ChatColor.RED + "Unstable Fuel");
		ArrayList<String> rawFuelItemLore = new ArrayList<>();
		rawFuelItemLore.add(ChatColor.DARK_AQUA + "Use this to craft refined fuel.");
		rawFuelItemLore.add(ChatColor.GREEN + "Four" + ChatColor.DARK_AQUA + " unstable fuel are required to craft " + ChatColor.GREEN + "one" + ChatColor.DARK_AQUA + " refined fuel.");
		customFuelMeta.setLore(rawFuelItemLore);
		customFuel.setItemMeta(customFuelMeta);
		
		customFuel.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		return customFuel;
	}
	
	public static ItemStack getRefinedFuel() {
		ItemStack fuelItem = new ItemStack(Material.MAGMA_CREAM);
		fuelItem.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		
		ItemMeta fuelItemMeta = fuelItem.getItemMeta();
		fuelItemMeta.setDisplayName(ChatColor.DARK_GREEN + "Refined Fuel");
		ArrayList<String> fuelItemLore = new ArrayList<>();
		fuelItemLore.add(ChatColor.DARK_AQUA + "Use this to re-fuel a hoverpack.");
		fuelItemLore.add(ChatColor.DARK_AQUA + "One refined fuel will run the pack for " + ChatColor.GREEN + "60" + ChatColor.DARK_AQUA + " seconds.");
		fuelItemMeta.setLore(fuelItemLore);
		fuelItem.setItemMeta(fuelItemMeta);
		
		return fuelItem;
	}
	
	public static ItemStack getNewHoverPack() {
		ItemStack hoverPack = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		
		hoverPack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		ItemMeta hoverPackMeta = hoverPack.getItemMeta();
		hoverPackMeta.setDisplayName(ChatColor.GOLD + "Hoverpack");
		ArrayList<String> hoverPackLore = new ArrayList<>();
		hoverPackLore.add(ChatColor.DARK_AQUA + "Charge with fuel and equip to fly for a short period.");
		hoverPackLore.add(ChatColor.DARK_AQUA + "Hoverpack will use fuel whenever equipped.");
		hoverPackLore.add(ChatColor.RED + "Current Charge: 240/240");
		hoverPackMeta.setLore(hoverPackLore);
		
		hoverPack.setItemMeta(hoverPackMeta);
		
		return hoverPack;
	}
}
