package xyz.zoradevelopment.tablist;

import com.google.common.collect.Table;
import org.bukkit.entity.Player;

public interface TablistEntrySupplier {

    Table<Integer, Integer, String> getEntries(Player player);


}