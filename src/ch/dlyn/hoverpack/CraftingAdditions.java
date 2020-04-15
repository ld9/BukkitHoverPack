package ch.dlyn.hoverpack;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingAdditions {
	public static void AddCraftingRecipes() {	
		ShapedRecipe fuelRecipe = new ShapedRecipe(HoverPack.getRefinedFuelNamespacedKey(), HoverPack.getRefinedFuel());
		fuelRecipe.shape("XAX", "ABA", "XAX");
		fuelRecipe.setIngredient('X', Material.AIR);
		fuelRecipe.setIngredient('A', HoverPack.getCustomRawFuel());
		fuelRecipe.setIngredient('B', Material.DIAMOND);
		
		HoverPack.getPlugin(HoverPack.class).getServer().addRecipe(fuelRecipe);
		
		ShapedRecipe hoverPack = new ShapedRecipe(HoverPack.getHoverPackNamespacedKey(), HoverPack.getNewHoverPack());
		hoverPack.shape("XAX", "ABA", "CAC");
		hoverPack.setIngredient('X', Material.AIR);
		hoverPack.setIngredient('A', HoverPack.getCustomRawFuel());
		hoverPack.setIngredient('B', Material.DIAMOND_CHESTPLATE);
		hoverPack.setIngredient('C', Material.NETHER_STAR);
		
		HoverPack.getPlugin(HoverPack.class).getServer().addRecipe(hoverPack);
	}
}
