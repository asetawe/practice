package me.intier.practice.stats;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class PlayerStatsManager {
    private Map<UUID, EntityStats> playerStatsMap = new HashMap<>();
    private final JavaPlugin plugin;
    private final File statsFile;
    private Properties statsProps = new Properties();

    public PlayerStatsManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.statsFile = new File(plugin.getDataFolder(), "playerStats.properties");
        loadStats();
        scheduleRegeneration();
    }

    private void loadStats() {
        try {
            if (!statsFile.exists()) {
                statsFile.getParentFile().mkdirs();
                statsFile.createNewFile();
            }
            statsProps.load(new FileInputStream(statsFile));
            // 불러온 스탯 정보를 playerStatsMap에 반영
            statsProps.forEach((key, value) -> {
                String[] keyParts = ((String)key).split("\\.");
                if (keyParts.length == 2) {
                    UUID playerId = UUID.fromString(keyParts[0]);
                    String statName = keyParts[1];
                    double statValue = Double.parseDouble((String)value);
                    EntityStats stats = playerStatsMap.computeIfAbsent(playerId, k -> new EntityStats());
                    switch(statName) {
                        //체력
                        case "baseHealth":
                            stats.setBaseHealth(statValue);
                            break;
                        case "baseHealthBoost":
                            stats.setBaseHealthBoost(statValue);
                            break;
                        case "healthIncrease":
                            stats.setHealthIncrease(statValue);
                            break;
                        case "healthRegen":
                            stats.setHealthRegen(statValue);
                            break;
                        //마나
                        case "baseMana":
                            stats.setBaseMana(statValue);
                            break;
                        case "baseManaBoost":
                            stats.setBaseManaBoost(statValue);
                            break;
                        case "manaIncrease":
                            stats.setManaIncrease(statValue);
                            break;
                        case "manaRegen":
                            stats.setManaRegen(statValue);
                            break;
                        //공격력
                        case "baseDamage":
                            stats.setBaseDamage(statValue);
                            break;
                        case "baseDamageBoost":
                            stats.setBaseDamageBoost(statValue);
                            break;
                        case "damageIncrease":
                            stats.setDamageIncrease(statValue);
                            break;
                        //방어력
                        case "baseDefense":
                            stats.setBaseDefense(statValue);
                            break;
                        case "baseDefenseBoost":
                            stats.setBaseDefenseBoost(statValue);
                            break;
                        case "defenseIncrease":
                            stats.setDefenseIncrease(statValue);
                            break;
                        //저항
                        case "resistance":
                            stats.setResistance(statValue);
                            break;
                        //속성 피해 보너스
                        case "fireDamageBonus":
                            stats.setFireDamageBonus(statValue);
                            break;
                        case "waterDamageBonus":
                            stats.setWaterDamageBonus(statValue);
                            break;
                        case "iceDamageBonus":
                            stats.setIceDamageBonus(statValue);
                            break;
                        case "dragonDamageBonus":
                            stats.setDragonDamageBonus(statValue);
                            break;
                        case "physicalDamageBonus":
                            stats.setPhysicalDamageBonus(statValue);
                            break;
                        //속성 내성
                        case "fireResistance":
                            stats.setFireResistance(statValue);
                            break;
                        case "waterResistance":
                            stats.setWaterResistance(statValue);
                            break;
                        case "iceResistance":
                            stats.setIceResistance(statValue);
                            break;
                        case "dragonResistance":
                            stats.setDragonResistance(statValue);
                            break;
                        case "physicalResistance":
                            stats.setPhysicalResistance(statValue);
                            break;
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStats() {
        playerStatsMap.forEach((uuid, stats) -> {
            //체력
            statsProps.setProperty(uuid.toString() + ".baseHealth", String.valueOf(stats.getBaseHealth()));
            statsProps.setProperty(uuid.toString() + ".baseHealthBoost", String.valueOf(stats.getBaseHealthBoost()));
            statsProps.setProperty(uuid.toString() + ".healthIncrease", String.valueOf(stats.getHealthIncrease()));
            statsProps.setProperty(uuid.toString() + ".healthRegen", String.valueOf(stats.getHealthRegen()));
            //마나
            statsProps.setProperty(uuid.toString() + ".baseMana", String.valueOf(stats.getBaseMana()));
            statsProps.setProperty(uuid.toString() + ".baseManaBoost", String.valueOf(stats.getBaseManaBoost()));
            statsProps.setProperty(uuid.toString() + ".manaIncrease", String.valueOf(stats.getManaIncrease()));
            statsProps.setProperty(uuid.toString() + ".manaRegen", String.valueOf(stats.getManaRegen()));
            //공격력
            statsProps.setProperty(uuid.toString() + ".baseDamage", String.valueOf(stats.getBaseDamage()));
            statsProps.setProperty(uuid.toString() + ".baseDamageBoost", String.valueOf(stats.getBaseDamageBoost()));
            statsProps.setProperty(uuid.toString() + ".damageIncrease", String.valueOf(stats.getDamageIncrease()));
            //방어력
            statsProps.setProperty(uuid.toString() + ".baseDefense", String.valueOf(stats.getBaseDefense()));
            statsProps.setProperty(uuid.toString() + ".baseDefenseBoost", String.valueOf(stats.getBaseDefenseBoost()));
            statsProps.setProperty(uuid.toString() + ".defenseIncrease", String.valueOf(stats.getDefenseIncrease()));
            //저항
            statsProps.setProperty(uuid.toString() + ".resistance", String.valueOf(stats.getResistance()));
            //속성 피해 보너스
            statsProps.setProperty(uuid.toString() + ".fireDamageBonus", String.valueOf(stats.getFireDamageBonus()));
            statsProps.setProperty(uuid.toString() + ".waterDamageBonus", String.valueOf(stats.getWaterDamageBonus()));
            statsProps.setProperty(uuid.toString() + ".iceResistance", String.valueOf(stats.getIceDamageBonus()));
            statsProps.setProperty(uuid.toString() + ".dragonDamageBonus", String.valueOf(stats.getDragonDamageBonus()));
            statsProps.setProperty(uuid.toString() + ".physicalDamageBonus", String.valueOf(stats.getPhysicalDamageBonus()));
            //속성 내성
            statsProps.setProperty(uuid.toString() + ".fireResistance", String.valueOf(stats.getFireResistance()));
            statsProps.setProperty(uuid.toString() + ".waterResistance", String.valueOf(stats.getWaterResistance()));
            statsProps.setProperty(uuid.toString() + ".iceResistance", String.valueOf(stats.getIceResistance()));
            statsProps.setProperty(uuid.toString() + ".dragonResistance", String.valueOf(stats.getDragonResistance()));
            statsProps.setProperty(uuid.toString() + ".physicalResistance", String.valueOf(stats.getPhysicalResistance()));
        });

        try {
            statsProps.store(new FileOutputStream(statsFile), "Player Stats");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntityStats getPlayerStats(Entity entity) {
        return playerStatsMap.computeIfAbsent(entity.getUniqueId(), k -> new EntityStats());
    }

    private void scheduleRegeneration() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (EntityStats playerStats : playerStatsMap.values()) {
                    playerStats.regenerateMana(); // 마나 회복
                    playerStats.regenerateHealth(); // 체력 회복
                }
                // 변화된 마나와 체력 정보를 저장
                saveStats();
            }
        }.runTaskTimer(plugin, 0L, 20L); // 20L = 1 second
    }
}

