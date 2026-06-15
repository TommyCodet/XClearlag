package de.xclearlag;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearLagCommand implements CommandExecutor {

    private final XClearlag plugin;

    public ClearLagCommand(XClearlag plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        int removed = plugin.clearItems();

        sender.sendMessage(
                "§a[XClearlag] §7"
                        + removed
                        + " Items entfernt.");

        return true;
    }
}
