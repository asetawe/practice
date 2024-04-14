package me.intier.practice.stats;

import me.intier.practice.Practice;
import me.intier.practice.stats.EntityStats;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatSimulationListener implements Listener {
    private final Practice plugin;

    public CombatSimulationListener(Practice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof LivingEntity) || !(event.getEntity() instanceof LivingEntity)) {
            return; // 공격자나 피해받는 대상이 LivingEntity가 아니면 처리하지 않음
        }

        LivingEntity damager = (LivingEntity) event.getDamager();
        LivingEntity victim = (LivingEntity) event.getEntity();

        EntityStats damagerStats = plugin.getPlayerStatsManager().getPlayerStats(damager);
        EntityStats victimStats = plugin.getPlayerStatsManager().getPlayerStats(victim);

        // 가상 피해 계산
        double damage = calculateDamage(damagerStats, victimStats);

        // 피해를 가상 체력에서 차감
        double newHealth = victimStats.getCurrentHealth() - damage;
        victimStats.setCurrentHealth(newHealth);

        // 이벤트 취소 (실제 게임에서는 피해가 발생하지 않음)
        event.setCancelled(true);

        // 실제 게임 세계에 가상 체력 업데이트 (예: 체력 바 업데이트)
        if (victim instanceof LivingEntity) {
            updateHealthDisplay((LivingEntity) victim, victimStats);
        }
    }

    private double calculateDamage(EntityStats attackerStats, EntityStats defenderStats) {
        double finalDamage = attackerStats.getFinalDamage();
        double defense = defenderStats.getFinalDefense();
        // 단순한 예로, 방어력을 고려하여 최종 피해량을 계산
        return Math.max(baseDamage - defense, 0);
    }

    private void updateHealthDisplay(LivingEntity entity, EntityStats stats) {
        entity.setCustomName(String.format("체력: %.1f/%.1f", stats.getCurrentHealth(), stats.getFinalHealth()));
        entity.setCustomNameVisible(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(entity);

        // 모든 피해를 가상으로 처리
        double newHealth = stats.getCurrentHealth() - event.getFinalDamage();
        stats.setCurrentHealth(newHealth);
        event.setCancelled(true); // 실제 피해 적용 취소

        updateHealthDisplay(entity, stats);
    }
}
