package ga.uniquecoding.uniquedungeons.commands.admin;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import ga.uniquecoding.uniquedungeons.manager.GameManager;

import static ga.uniquecoding.uniquedungeons.utils.ColorUtils.c;

public class SidebarDebug extends CommandAPICommand {
        private GameManager gameManager;

        public SidebarDebug(GameManager gameManager) {
            super("sidebardebug");
            this.gameManager = gameManager;
            withArguments(new MultiLiteralArgument("WAITING", "STARTING", "ACTIVE", "RESET"))
            .withPermission("uniquedungeons.admin").executesPlayer((player, args) -> {
                String sidebar = (String) args[0];

                gameManager.getSidebarManager().setSidebar(player, sidebar);
                player.sendMessage(c("&aSuccessfully displayed the sidebar!"));
            });
        }
}
