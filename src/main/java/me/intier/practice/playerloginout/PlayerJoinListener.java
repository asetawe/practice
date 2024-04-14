package me.intier.practice.playerloginout;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage(null);

        String playerName = event.getPlayer().getName();

        Component playerJoinMessage = Component.text()
                .append(Component.text(playerName).color(NamedTextColor.AQUA))
                .append(Component.text(" 이(가) 접속했습니다.").color(NamedTextColor.YELLOW))
                .build();

        Bukkit.getServer().sendMessage(playerJoinMessage);
    }
}