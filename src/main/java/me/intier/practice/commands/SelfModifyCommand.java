package me.intier.practice.commands;

import me.intier.practice.Practice;
import me.intier.practice.stats.EntityStats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SelfModifyCommand implements CommandExecutor {
    private Practice plugin;

    public SelfModifyCommand(Practice plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 커맨드 사용자가 플레이어인지 확인
        if (!(sender instanceof Player)) {
            sender.sendMessage("이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("사용법: /selfmodify <대상> <수치>");
            return true;
        }

        String target = args[0].toLowerCase();
        double amount;

        try {
            amount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("수치는 실수로 입력해야 합니다.");
            return true;
        }

        Player player = (Player) sender;
        EntityStats playerStats = plugin.getPlayerStatsManager().getPlayerStats(player);

        switch (target) {
            case "basehealth":
                // baseHealth 스탯은 기본적으로 고정되어 있으므로 조정할 수 없다는 메시지를 보낼 수 있습니다.
                sender.sendMessage(ChatColor.RED + "BaseHealth는 고정값입니다.");
                break;
            case "basehealthboost":
                playerStats.setBaseHealthBoost(playerStats.getBaseHealthBoost() + amount);
                break;
            case "healthincrease":
                playerStats.setHealthIncrease(playerStats.getHealthIncrease() + amount);
                break;
            case "healthregen":
                playerStats.setHealthRegen(playerStats.getHealthRegen() + amount);
                break;


            case "basemana":
                sender.sendMessage(ChatColor.RED + "BaseMana는 고정값입니다.");
                break;
            case "basemanaboost":
                playerStats.setBaseManaBoost(playerStats.getBaseManaBoost() + amount);
                break;
            case "manaincrease":
                playerStats.setManaIncrease(playerStats.getManaIncrease() + amount);
                break;
            case "manaregen":
                playerStats.setManaRegen(playerStats.getHealthRegen() + amount);
                break;

            default:
                sender.sendMessage(ChatColor.RED + target + "는(은) 유효한 대상이 아닙니다.");
                return true;
        }

        sender.sendMessage(ChatColor.GREEN + target + "이(가) " + amount + "만큼 조정되었습니다.");
        return true;
    }
}
