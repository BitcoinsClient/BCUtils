package de.bitcoinclient.bcutils.gui.result;

import de.bitcoinclient.bcutils.gui.listener.PressListener;
import de.bitcoinclient.bcutils.item.ItemBuilder;
import org.bukkit.Sound;

public class GuiButton {
    private final ItemBuilder guiItem;

    private final int slot;

    private final PressListener pressListener;

    private final Sound pressSound;

    private final float volume;

    private final float pitch;

    public GuiButton(ItemBuilder guiItem, int slot, PressListener pressListener, Sound pressSound, float volume, float pitch) {
        this.guiItem = guiItem;
        this.slot = slot;
        this.pressListener = pressListener;
        this.pressSound = pressSound;
        this.volume = volume;
        this.pitch = pitch;
    }

    public ItemBuilder getGuiItem() {
        return this.guiItem;
    }

    public int getSlot() {
        return this.slot;
    }

    public PressListener getPressListener() {
        return this.pressListener;
    }

    public Sound getPressSound() {
        return this.pressSound;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getPitch() {
        return this.pitch;
    }
}
