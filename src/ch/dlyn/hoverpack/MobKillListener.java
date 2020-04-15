package ch.dlyn.hoverpack;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKillListener implements Listener  {

	@EventHandler
	public void mobKill(EntityDeathEvent e) {
		if (e.getEntityType().equals(EntityType.BLAZE)) {	
			e.getDrops().add(HoverPack.getCustomFuelRod());
		}
	}
}
