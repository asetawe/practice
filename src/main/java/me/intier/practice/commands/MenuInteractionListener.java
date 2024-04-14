package me.intier.practice.commands;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class MenuInteractionListener implements Listener {

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        // 메뉴 인벤토리의 타이틀 확인
        if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "메뉴")) {
            // 메뉴 인벤토리 내에서 드래그하는 것을 취소
            event.setCancelled(true);
        }
    }
}