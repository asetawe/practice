package me.intier.practice.playerloginout;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(null);

        String playerName = event.getPlayer().getName();

        Component playerQuitMessage = Component.text()
                .append(Component.text(playerName).color(NamedTextColor.AQUA))
                .append(Component.text(" 이(가) 종료했습니다..").color(NamedTextColor.RED))
                .build();

        Bukkit.getServer().sendMessage(playerQuitMessage);
    }
}