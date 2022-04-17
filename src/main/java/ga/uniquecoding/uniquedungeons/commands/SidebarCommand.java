package ga.uniquecoding.uniquedungeons.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import ga.uniquecoding.uniquedungeons.UniqueDungeons;
import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.manager.SidebarManager;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import org.bukkit.entity.Player;

@Command("sidebar")
@Permission("uniquedungeons.admin")
public class SidebarCommand {

    static GameManager manager = UniqueDungeons.plugin.getManager();

    @Default
    public static void sidebar(Player player) {
        SidebarManager sidebarManager = manager.getSidebarManager();
        String sidebar = sidebarManager.getSidebar(player);

        player.sendMessage(HexUtils.colorify("&aYou currently have the&e&l " + sidebar + " &asidebar applied!"));
    }

    @Subcommand("apply")
    @Permission("uniquedungeons.admin")
    public static void sidebarApply(Player player, @AMultiLiteralArgument({"WAITING", "STARTING", "ACTIVE"}) String args) {
        SidebarManager sidebarManager = manager.getSidebarManager();

        sidebarManager.setSidebar(player, args);
        player.sendMessage(HexUtils.colorify("&aSuccessfully applied the sidebar!"));
    }

    @Subcommand("reset")
    @Permission("uniquedungeons.admin")
    public static void sidebarReset(Player player) {
        SidebarManager sidebarManager = manager.getSidebarManager();

        sidebarManager.resetSidebar(player);
        player.sendMessage(HexUtils.colorify("&aSuccessfully reset the sidebar!"));
    }
}
