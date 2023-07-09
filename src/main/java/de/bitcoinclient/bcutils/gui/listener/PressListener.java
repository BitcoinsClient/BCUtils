package de.bitcoinclient.bcutils.gui.listener;

import de.bitcoinclient.bcutils.gui.result.GuiButton;
import de.bitcoinclient.bcutils.gui.result.GuiMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface PressListener {
    void performAction(ClickType paramClickType, GuiMenu paramGuiMenu, GuiButton paramGuiButton, Player paramPlayer);
}
