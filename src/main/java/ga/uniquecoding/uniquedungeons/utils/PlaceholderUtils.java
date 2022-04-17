package ga.uniquecoding.uniquedungeons.utils;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.placeholder.PlaceholderManager;

import java.util.function.Function;
import java.util.function.Supplier;

public class PlaceholderUtils {
    public static void registerServerPlaceholder(String identifier, int refresh, Supplier<Object> supplier) {
        TabAPI instance = TabAPI.getInstance();
        PlaceholderManager placeholderManager = instance.getPlaceholderManager();

        placeholderManager.registerServerPlaceholder(identifier, refresh, supplier);
    }

    public static void registerPlayerPlaceholder(String identifier, int refresh, Function<TabPlayer, Object> function) {
        TabAPI instance = TabAPI.getInstance();
        PlaceholderManager placeholderManager = instance.getPlaceholderManager();

        placeholderManager.registerPlayerPlaceholder(identifier, refresh, function);
    }

    public static void clearPlaceholderDebug() {
        TabAPI instance = TabAPI.getInstance();
        PlaceholderManager placeholderManager = instance.getPlaceholderManager();

        placeholderManager.getUsedPlaceholders().clear();
    }
}
