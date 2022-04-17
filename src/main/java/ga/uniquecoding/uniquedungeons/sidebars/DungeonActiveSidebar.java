package ga.uniquecoding.uniquedungeons.sidebars;

import ga.uniquecoding.uniquedungeons.manager.GameManager;
import ga.uniquecoding.uniquedungeons.utils.HexUtils;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import me.neznamy.tab.api.scoreboard.ScoreboardManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static ga.uniquecoding.uniquedungeons.manager.GameManager.players;

public class DungeonActiveSidebar {

    private GameManager manager;
    private Scoreboard sidebar;

    public DungeonActiveSidebar(GameManager manager) {
        this.manager = manager;
    }

    public Scoreboard getSidebar() {
        TabAPI instance = TabAPI.getInstance();
        ScoreboardManager sbManager = instance.getScoreboardManager();

        return sbManager.createScoreboard("dungeon-active", HexUtils.colorify("&6&lDUNGEONS"), getLines());
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();

        lines.add(HexUtils.colorify("&r"));
        lines.add(HexUtils.colorify("&fTime left:&c %player_dungeon_time_left_minutes%m %player_dungeon_time_left_seconds%s"));

        for (Player player : players) {
            lines.add("&a" + player.getName() + "&c " + player.getHealth());
        }

        return lines;
    }
}
