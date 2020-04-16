package ch.dlyn.hoverpack;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKillListener implements Listener  {
	
	public static HashMap<Location, Long> recentBlazeKillLocations;
	
	public MobKillListener() {
		recentBlazeKillLocations = new HashMap<>();
	}
	
	@EventHandler
	public void mobKill(EntityDeathEvent e) {
		if (e.getEntityType().equals(EntityType.BLAZE)) {
			if (Math.random() < calculateDropChance(e.getEntity())) {
				e.getDrops().add(CraftingAdditions.getCustomRawFuel());
			}
		}
	}
	
	private float calculateDropChance(Entity entity) {
		float dropChance = 3;
		
		for (Entry<Location, Long> e : recentBlazeKillLocations.entrySet()) {
			if (System.currentTimeMillis() - (1000 * 300) > e.getValue()) {
				recentBlazeKillLocations.remove(e.getKey(), e.getValue());
			} else if (e.getKey().distance(entity.getLocation()) < 24) {
				dropChance++;
			}
		}
			
		recentBlazeKillLocations.put(entity.getLocation(), System.currentTimeMillis());
		
		dropChance = 2F / dropChance;
		return dropChance;
	}
}
