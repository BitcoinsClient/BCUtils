package de.bitcoinclient.bcutils.gui.builder;

import de.bitcoinclient.bcutils.gui.listener.PressListener;
import de.bitcoinclient.bcutils.gui.result.GuiButton;
import de.bitcoinclient.bcutils.item.ItemBuilder;
import org.apache.commons.lang.Validate;
import org.bukkit.Sound;

public class GuiButtonBuilder implements Cloneable {
    private ItemBuilder guiItem;

    private int slot;

    private PressListener pressListener;

    private Sound pressSound;

    private float volume = 1.0F;

    private float pitch = 1.0F;

    public GuiButton create() {
        Validate.isTrue((this.guiItem != null), "GuiItem needs to be specified!");
        return new GuiButton(this.guiItem, this.slot, this.pressListener, this.pressSound, this.volume, this.pitch);
    }

    public GuiButtonBuilder setGuiItem(ItemBuilder guiItem) {
        this.guiItem = guiItem;
        return this;
    }

    public GuiButtonBuilder setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public GuiButtonBuilder setPressListener(PressListener pressListener) {
        this.pressListener = pressListener;
        return this;
    }

    public GuiButtonBuilder setPressSound(Sound pressSound) {
        this.pressSound = pressSound;
        return this;
    }

    public GuiButtonBuilder setVolume(float volume) {
        this.volume = volume;
        return this;
    }

    public GuiButtonBuilder setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public GuiButtonBuilder clone() {
        try {
            GuiButtonBuilder clone = (GuiButtonBuilder)super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
