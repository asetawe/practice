package me.intier.practice.commands;

import me.intier.practice.Practice;
import me.intier.practice.stats.EntityStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;

public class MenuCommand implements CommandExecutor {
    private final Practice plugin;

    public MenuCommand(Practice plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;
        Inventory menu = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "메뉴");

        // 능력치 정보 아이템 생성
        ItemStack statsItem = new ItemStack(Material.ENDER_EYE);
        ItemMeta statsMeta = statsItem.getItemMeta();

        if (statsMeta != null) {
            statsMeta.setDisplayName(ChatColor.GREEN + "능력치 정보");

            List<String> lore = new ArrayList<>();
            EntityStats stats = plugin.getPlayerStatsManager().getPlayerStats(player);
            lore.add(ChatColor.GRAY + "최대 체력: " + ChatColor.RED + stats.getFinalHealth());
            lore.add(ChatColor.GRAY + "최대 마나: " + ChatColor.AQUA + stats.getMaxMana());
            lore.add(ChatColor.GRAY + "공격력: " + ChatColor.RED + stats.getFinalDamage());

            statsMeta.setLore(lore);
            statsItem.setItemMeta(statsMeta);
        }

        // 아이템을 인벤토리의 특정 위치에 추가
        menu.setItem(13, statsItem);

        player.openInventory(menu);

        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "메뉴")) {
            event.setCancelled(true);
        }
    }
}
