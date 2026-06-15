package de.xclearlag;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public class XClearlag extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("clearlag").setExecutor(new ClearLagCommand(this));

        startAutoClear();

        getLogger().info("XClearlag aktiviert.");
    }

    private void startAutoClear() {

        int interval = getConfig().getInt("clear-interval-seconds");

        for (Integer warning : getConfig().getIntegerList("warnings")) {

            Bukkit.getScheduler().runTaskTimer(this, () -> {
                Bukkit.broadcastMessage(
                        "§e[XClearlag] §7Items werden in §c"
                                + warning
                                + " Sekunden §7entfernt!");
            }, (interval - warning) * 20L, interval * 20L);
        }

        Bukkit.getScheduler().runTaskTimer(this, () -> {

            int removed = clearItems();

            Bukkit.broadcastMessage(
                    "§a[XClearlag] §7"
                            + removed
                            + " Items wurden entfernt.");

        }, interval * 20L, interval * 20L);
    }

    public int clearItems() {

        int removed = 0;

        for (World world : Bukkit.getWorlds()) {

            for (Entity entity : world.getEntities()) {

                if (entity instanceof Item) {
                    entity.remove();
                    removed++;
                }
            }
        }

        return removed;
    }
}
