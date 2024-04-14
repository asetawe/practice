package me.intier.practice;

import me.intier.practice.commands.InventoryEventListener;
import me.intier.practice.commands.MenuCommand;
import me.intier.practice.commands.MenuInteractionListener;
import me.intier.practice.playerloginout.PlayerJoinListener;
import me.intier.practice.playerloginout.PlayerQuitListener;
import me.intier.practice.commands.SelfModifyCommand;
import me.intier.practice.stats.PlayerStatsDisplay;
import me.intier.practice.stats.PlayerStatsManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Practice extends JavaPlugin {
    private PlayerStatsManager playerStatsManager;

    @Override
    public void onEnable() {
        System.out.println("[[[[[[[[[[[[[[[[[[Server ONLINE.]]]]]]]]]]]]]]]]]]]]");

        // PlayerStatsManager 인스턴스를 초기화하고 멤버 변수에 저장합니다.
        this.playerStatsManager = new PlayerStatsManager(this);

        // PlayerStatsDisplay를 초기화할 때 멤버 변수에서 가져온 playerStatsManager를 사용합니다.
        new PlayerStatsDisplay(this, this.playerStatsManager);

        // 커맨드 등록
        this.getCommand("selfmodify").setExecutor(new SelfModifyCommand(this));
        this.getCommand("menu").setExecutor(new MenuCommand(this));

        getServer().getPluginManager().registerEvents(new InventoryEventListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuInteractionListener(), this);

        // 이벤트 리스너 등록
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[[[[[[[[[[[[[[[[[[Server OFFLINE.]]]]]]]]]]]]]]]]]]]]");
        // 필요한 경우, 플러그인 비활성화 시 처리 로직 추가

        playerStatsManager.saveStats();
    }

    public PlayerStatsManager getPlayerStatsManager() {
        return this.playerStatsManager;
    }
}