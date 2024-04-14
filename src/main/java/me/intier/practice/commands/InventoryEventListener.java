package me.intier.practice.commands;

import me.intier.practice.Practice;
import me.intier.practice.stats.EntityStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryEventListener implements Listener {
    private final Practice plugin;

    public InventoryEventListener(Practice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || !clickedItem.hasItemMeta()) return;
        ItemMeta meta = clickedItem.getItemMeta();
        if (meta.getDisplayName().equals(ChatColor.GREEN + "능력치 정보")) {
            event.setCancelled(true);
            openStatsInventory(player);
        }
    }

    public void openStatsInventory(Player player) {

        Inventory statsInventory = Bukkit.createInventory(null, 54, ChatColor.DARK_PURPLE + "능력치 상세 정보");

        // 아이템을 인벤토리에 배치하는 로직 여기에 작성.

        ItemStack healthInfoItem = createHealthInfoItem(Material.REDSTONE, ChatColor.RED + "체력 정보", player);
        statsInventory.setItem(10, healthInfoItem);

        ItemStack manaInfoItem = createManaInfoItem(Material.DRAGON_BREATH, ChatColor.AQUA + "마나 정보", player);
        statsInventory.setItem(19, manaInfoItem);

        ItemStack damageInfoItem = createDamageInfoItem(Material.IRON_SWORD, ChatColor.RED + "공격력 정보", player);
        statsInventory.setItem(28, damageInfoItem);

        ItemStack defenseInfoItem = createDefenseInfoItem(Material.CHAINMAIL_CHESTPLATE, ChatColor.GREEN + "방어력 정보", player);
        statsInventory.setItem(37, defenseInfoItem);

        ItemStack resistanceInfoItem = createResistanceInfoItem(Material.NETHERITE_INGOT, ChatColor.DARK_GRAY + "저항력 정보", player);
        statsInventory.setItem(11, resistanceInfoItem);

        ItemStack elementInfoItem = createElementInfoItem(Material.ENCHANTED_BOOK, ChatColor.DARK_AQUA + "속성 정보", player);
        statsInventory.setItem(20, elementInfoItem);

        player.openInventory(statsInventory);
    }

    private ItemStack createHealthInfoItem(Material material, String name, Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();

            // 플레이어의 PlayerStats 인스턴스를 가져옵니다.
            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);
            // 실제 능력치 데이터를 Lore에 추가합니다.
            if (name.equals(ChatColor.RED + "체력 정보")) {
                double baseHealth = stats.getBaseHealth();
                double baseHealthBoost = stats.getBaseHealthBoost();
                double healthIncrease = stats.getHealthIncrease();
                double finalHealth = stats.getFinalHealth();
                double currentHealth = stats.getCurrentHealth();
                double healthRegen = stats.getHealthRegen();

                lore.add(ChatColor.GRAY + "최대 체력: " + ChatColor.WHITE + String.format("%.1f", finalHealth));
                lore.add(ChatColor.GRAY + "현재 체력: " + ChatColor.WHITE + String.format("%.1f", currentHealth));
                lore.add("");
                lore.add(ChatColor.GRAY + "기본 체력: " + ChatColor.WHITE + String.format("%.1f", baseHealth));
                lore.add(ChatColor.GRAY + "추가 체력: " + ChatColor.WHITE + "+" + String.format("%.1f", baseHealthBoost));
                lore.add(ChatColor.GRAY + "추가 체력 보너스 (%): " + ChatColor.WHITE + "+" + String.format("%.1f", healthIncrease));
                lore.add(ChatColor.GRAY + "초 당 체력 재생 (%): " + ChatColor.WHITE + "+" + String.format("%.1f", healthRegen));
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createManaInfoItem(Material material, String name, Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();

            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);
            // 실제 능력치 데이터를 Lore에 추가합니다.
            if  (name.equals(ChatColor.AQUA + "마나 정보")) {
                double baseMana = stats.getBaseMana();
                double baseManaBoost = stats.getBaseManaBoost();
                double manaIncrease = stats.getManaIncrease();
                double maxMana = stats.getMaxMana();
                double currentMana = stats.getCurrentMana();
                double manaRegen = stats.getManaRegen();

                lore.add(ChatColor.GRAY + "최대 마나: " + ChatColor.WHITE + String.format("%.1f", maxMana));
                lore.add(ChatColor.GRAY + "현재 마나: " + ChatColor.WHITE + String.format("%.1f", currentMana));
                lore.add("");
                lore.add(ChatColor.GRAY + "기본 마나: " + ChatColor.WHITE + String.format("%.1f", baseMana));
                lore.add(ChatColor.GRAY + "추가 마나: " + ChatColor.WHITE + "+" + String.format("%.1f", baseManaBoost));
                lore.add(ChatColor.GRAY + "추가 마나 보너스 (%): " + ChatColor.WHITE + "+" + String.format("%.1f", manaIncrease));
                lore.add(ChatColor.GRAY + "초 당 마나 재생 (%): " + ChatColor.WHITE + "+" + String.format("%.1f", manaRegen));
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createDamageInfoItem(Material material, String name, Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);

            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            List<String> lore = new ArrayList<>();

            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);
            // 실제 능력치 데이터를 Lore에 추가합니다.
            if  (name.equals(ChatColor.RED + "공격력 정보")) {
                double baseDamage = stats.getBaseDamage();
                double baseDamageBoost = stats.getBaseDamageBoost();
                double damageIncrease = stats.getDamageIncrease();
                double finalDamage = stats.getFinalDamage();

                lore.add(ChatColor.GRAY + "공격력: " + ChatColor.WHITE + String.format("%.1f", finalDamage));
                lore.add("");
                lore.add(ChatColor.GRAY + "기본 공격력: " + ChatColor.WHITE + String.format("%.1f", baseDamage));
                lore.add(ChatColor.GRAY + "추가 공격력: " + ChatColor.WHITE + "+" + String.format("%.1f", baseDamageBoost));
                lore.add(ChatColor.GRAY + "추가 공격력 보너스 (%): " + ChatColor.WHITE + "+" + String.format("%.1f", damageIncrease));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createDefenseInfoItem(Material material, String name, Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);

            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            List<String> lore = new ArrayList<>();

            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);
            // 실제 능력치 데이터를 Lore에 추가합니다.
            if  (name.equals(ChatColor.GREEN + "방어력 정보")) {
                double baseDefense = stats.getBaseDefense();
                double baseDefenseBoost = stats.getBaseDefenseBoost();
                double defenseIncrease = stats.getDefenseIncrease();
                double finalDefense = stats.getFinalDefense();

                lore.add(ChatColor.GRAY + "방어력: " + ChatColor.WHITE + String.format("%.1f", finalDefense));
                lore.add("");
                lore.add(ChatColor.GRAY + "기본 방어력: " + ChatColor.WHITE + String.format("%.1f", baseDefense));
                lore.add(ChatColor.GRAY + "추가 방어력: " + ChatColor.WHITE + "+" + String.format("%.1f", baseDefenseBoost));
                lore.add(ChatColor.GRAY + "추가 방어력 보너스 (%): " + ChatColor.WHITE + "+" + String.format("%.1f", defenseIncrease));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createResistanceInfoItem(Material material, String name, Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();

            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);

            if (name.equals(ChatColor.DARK_GRAY + "저항력 정보")) {
                double resistance = stats.getResistance();

                lore.add(ChatColor.GRAY + "저항력: " + ChatColor.WHITE + String.format("%.1f", resistance));
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createElementInfoItem(Material material, String name, Player player) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            List<String> lore = new ArrayList<>();

            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);

            if (name.equals(ChatColor.DARK_AQUA + "속성 정보")) {
                double fireDamageBonus = stats.getFireDamageBonus();
                double waterDamageBonus = stats.getWaterDamageBonus();
                double iceDamageBonus = stats.getIceDamageBonus();
                double dragonDamageBonus = stats.getDragonDamageBonus();
                double physicalDamageBonus = stats.getPhysicalDamageBonus();

                double fireResistance = stats.getFireResistance();
                double waterResistance = stats.getWaterResistance();
                double iceResistance = stats.getIceResistance();
                double dragonResistance = stats.getDragonResistance();
                double physicalResistance = stats.getPhysicalResistance();

                lore.add(ChatColor.GRAY + "불 속성 피해 보너스 (%): " + ChatColor.WHITE + String.format("%.1f", fireDamageBonus));
                lore.add(ChatColor.GRAY + "물 속성 피해 보너스 (%): " + ChatColor.WHITE + String.format("%.1f", waterDamageBonus));
                lore.add(ChatColor.GRAY + "얼음 속성 피해 보너스 (%): " + ChatColor.WHITE + String.format("%.1f", iceDamageBonus));
                lore.add(ChatColor.GRAY + "용 속성 피해 보너스 (%): " + ChatColor.WHITE + String.format("%.1f", dragonDamageBonus));
                lore.add(ChatColor.GRAY + "물리 속성 피해 보너스 (%): " + ChatColor.WHITE + String.format("%.1f", physicalDamageBonus));
                lore.add("");
                lore.add(ChatColor.GRAY + "불 속성 내성: " + ChatColor.WHITE + String.format("%.1f", fireResistance));
                lore.add(ChatColor.GRAY + "물 속성 내성: " + ChatColor.WHITE + String.format("%.1f", waterResistance));
                lore.add(ChatColor.GRAY + "얼음 속성 내성: " + ChatColor.WHITE + String.format("%.1f", iceResistance));
                lore.add(ChatColor.GRAY + "용 속성 내성: " + ChatColor.WHITE + String.format("%.1f", dragonResistance));
                lore.add(ChatColor.GRAY + "물리 속성 내성: " + ChatColor.WHITE + String.format("%.1f", physicalResistance));
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onStatsInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.DARK_PURPLE + "능력치 상세 정보")) {
            event.setCancelled(true); // "능력치 상세 정보" 인벤토리 내에서의 클릭을 취소
        }
    }
}
