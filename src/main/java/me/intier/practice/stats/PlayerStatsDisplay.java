package me.intier.practice.stats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerStatsDisplay {
    private final PlayerStatsManager statsManager;
    private final Plugin plugin;

    public PlayerStatsDisplay(Plugin plugin, PlayerStatsManager statsManager) {
        this.plugin = plugin;
        this.statsManager = statsManager;
        startDisplayTask();
    }

    private void startDisplayTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    EntityStats stats = statsManager.getPlayerStats(player);
                    double finalHealth = stats.getFinalHealth();
                    double maxHealth = finalHealth;
                    double currentHealth = stats.getCurrentHealth();
                    double currentMana = stats.getCurrentMana();
                    double maxMana = stats.getMaxMana();
                    String healthMessage = ChatColor.RED + "HP: " + ChatColor.WHITE + String.format("%.1f/%.1f", currentHealth, maxHealth);
                    String manaMessage = ChatColor.AQUA + "Mana: " + ChatColor.WHITE + String.format("%.1f/%.1f", currentMana, maxMana);

                    player.sendActionBar(healthMessage + "  " + manaMessage);
                }
            }
            // 5틱마다 액션바 업데이트
        }.runTaskTimer(plugin, 0L, 5L);
    }
}