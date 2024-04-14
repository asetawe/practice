package me.intier.practice.mobs;

import me.intier.practice.Practice;
import me.intier.practice.stats.EntityStats;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.EntityType;

public class CustomZombieSpawner implements Listener {

    private final Practice plugin;

    public CustomZombieSpawner(Practice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        // 스폰된 엔티티가 좀비인지 확인합니다.
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();

            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(zombie);
            // 초기 설정을 진행합니다.
            stats.setBaseHealth(100.0);
            stats.setBaseHealthBoost(0.0);
            stats.setHealthIncrease(100.0);
            stats.setHealthRegen(0.0);

            String hexColor = "§x§C§E§C§E§C§E"; // 분홍색
            zombie.setCustomName(hexColor + "좀비" + ChatColor.WHITE + "(" + stats.getCurrentHealth() + "/" + stats.getFinalHealth() + ")");
            zombie.setCustomNameVisible(true); // 이름표를 항상 보이도록 설정
        }
    }
}