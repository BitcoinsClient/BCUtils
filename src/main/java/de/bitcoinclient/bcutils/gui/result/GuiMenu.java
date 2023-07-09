package de.bitcoinclient.bcutils.gui.result;

import de.bitcoinclient.bcutils.BCUtils;
import de.bitcoinclient.bcutils.gui.listener.AGuiMenu;
import de.bitcoinclient.bcutils.gui.listener.IEventListener;
import de.bitcoinclient.bcutils.gui.menu.GuiChest;
import de.bitcoinclient.bcutils.item.ItemBuilder;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GuiMenu implements Listener {
    private final Plugin plugin;

    private final Player player;

    private final AGuiMenu menuType;

    private final List<GuiButton> buttons;

    private final IEventListener.OnEventListener onEventListener;

    private final IEventListener.OnClickListener onClickListener;

    private final IEventListener.OnDragListener onDragListener;

    private final IEventListener.OnCloseListener onCloseListener;

    private String title;

    private int size;

    private int curPage;

    private int maxPage;

    private Runnable onFill;

    public GuiMenu(Plugin plugin, Player player, Inventory inventory, Class<? extends AGuiMenu> clazz, String title, int size, int curPage, int maxPage, List<GuiButton> buttons, IEventListener.OnEventListener onEventListener, IEventListener.OnClickListener onClickListener, IEventListener.OnDragListener onDragListener, IEventListener.OnCloseListener onCloseListener) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.plugin = plugin;
        this.player = player;
        this.menuType = clazz.getConstructor(new Class[] { GuiMenu.class }).newInstance(this);
        this.title = title;
        this.size = size;
        this.curPage = curPage;
        this.maxPage = maxPage;
        this.buttons = buttons;
        this.onEventListener = onEventListener;
        this.onClickListener = onClickListener;
        this.onDragListener = onDragListener;
        this.onCloseListener = onCloseListener;
        AGuiMenu aGuiMenu = this.menuType;
        if (aGuiMenu instanceof GuiChest) {
            GuiChest guiChest = (GuiChest)aGuiMenu;
            guiChest.setSize(this.size);
        }
        if (inventory != null)
            this.menuType.setInventory(inventory);
        registerListener();
        BCUtils.OPEN_MENUS.put(player, this);
    }

    private void registerListener() {
        unregisterListener();
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    private void unregisterListener() {
        HandlerList.unregisterAll(this);
    }

    public void open() {
        this.player.openInventory(this.menuType.getInventory());
    }

    public void fill() {
        if (this.onFill != null)
            this.onFill.run();
    }

    public void update() {
        AGuiMenu aGuiMenu = this.menuType;
        if (aGuiMenu instanceof GuiChest) {
            GuiChest guiChest = (GuiChest)aGuiMenu;
            guiChest.setSize(this.size);
        }
        this.menuType.setInventory(this.menuType.create());
        this.buttons.clear();
        fill();
        open();
    }

    public void updateSame() {
        for (GuiMenu menu : BCUtils.OPEN_MENUS.values()) {
            if (getClass() == menu.getClass())
                menu.update();
        }
    }

    public static void updateAll(Class<? extends GuiMenu> clazz) {
        for (GuiMenu menu : BCUtils.OPEN_MENUS.values()) {
            if (clazz == menu.getClass())
                menu.update();
        }
    }

    public void nextPage() {
        this.curPage++;
        if (this.curPage > this.maxPage)
            this.curPage = this.maxPage;
        update();
    }

    public void lastPage() {
        this.curPage--;
        if (this.curPage < 1)
            this.curPage = 1;
        update();
    }

    public void goToPage(int page) {
        this.curPage = page;
        if (this.curPage > this.maxPage)
            this.curPage = this.maxPage;
        if (this.curPage < 1)
            this.curPage = 1;
        update();
    }

    public void destroy() {
        BCUtils.OPEN_MENUS.remove(this.player, this);
        unregisterListener();
    }

    public void close() {
        if (this.player.getOpenInventory().getTopInventory().equals(this.menuType.getInventory()))
            this.player.closeInventory();
    }

    public GuiMenu setOnFill(Runnable onFill) {
        this.onFill = onFill;
        return this;
    }

    public void setItem(ItemStack itemStack, int slot) {
        this.menuType.getInventory().setItem(slot, itemStack);
    }

    public void setItem(ItemBuilder guiItem, int slot) {
        this.menuType.getInventory().setItem(slot, guiItem.toItemStack());
    }

    public void setItem(Material material, int slot) {
        this.menuType.getInventory().setItem(slot, (new ItemBuilder(material).setName(" ").toItemStack()));
    }

    public void setItem(Material material, String name, int slot) {
        this.menuType.getInventory().setItem(slot, (new ItemBuilder(material).setName(name).toItemStack()));
    }

    public void addButton(GuiButton button) {
        this.buttons.add(button);
        setItem(button.getGuiItem(), button.getSlot());
    }

    public void removeButton(GuiButton button) {
        this.buttons.remove(button);
        setItem((ItemStack)null, button.getSlot());
    }

    public GuiMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    public GuiMenu setSize(int size) {
        this.size = size;
        return this;
    }

    public int getSize() {
        Validate.isTrue((this.menuType != null), "MenuType needs to be specified!");
        return this.menuType.getInventory().getSize();
    }

    @EventHandler
    public void onInventoryEvent(InventoryEvent event) {
        if (!event.getInventory().equals(this.menuType.getInventory()))
            return;
        if (this.onEventListener != null)
            this.onEventListener.onEvent(event, this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player;
        HumanEntity humanEntity = event.getWhoClicked();
        if (humanEntity instanceof Player) {
            player = (Player)humanEntity;
        } else {
            return;
        }
        if (!this.player.getUniqueId().equals(player.getUniqueId()))
            return;
        if (!event.getInventory().equals(this.menuType.getInventory()) && !event.isShiftClick())
            return;
        this.onClickListener.onClick(event, this);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player;
        HumanEntity humanEntity = event.getWhoClicked();
        if (humanEntity instanceof Player) {
            player = (Player)humanEntity;
        } else {
            return;
        }
        if (!this.player.getUniqueId().equals(player.getUniqueId()))
            return;
        if (!event.getInventory().equals(this.menuType.getInventory()))
            return;
        if (event.getRawSlots().stream().noneMatch(slot -> (slot.intValue() < this.menuType.getInventory().getSize() && slot.intValue() >= 0)))
            return;
        this.onDragListener.onDrag(event, this);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player;
        HumanEntity humanEntity = event.getPlayer();
        if (humanEntity instanceof Player) {
            player = (Player)humanEntity;
        } else {
            return;
        }
        if (!this.player.getUniqueId().equals(player.getUniqueId()))
            return;
        if (!event.getInventory().equals(this.menuType.getInventory()))
            return;
        this.onCloseListener.onClose(event, this);
    }

    @EventHandler
    public void onButtonClick(InventoryClickEvent event) {
        Player player;
        HumanEntity humanEntity = event.getWhoClicked();
        if (humanEntity instanceof Player) {
            player = (Player)humanEntity;
        } else {
            return;
        }
        if (!this.player.getUniqueId().equals(player.getUniqueId()))
            return;
        if (!event.getInventory().equals(this.menuType.getInventory()))
            return;
        for (GuiButton button : new ArrayList<>(this.buttons)) {
            if (event.getRawSlot() != button.getSlot())
                continue;
            event.setCancelled(true);
            button.getPressListener().performAction(event.getClick(), this, button, player);
            if (button.getPressSound() != null)
                player.playSound(player.getLocation(), button.getPressSound(), button.getVolume(), button.getPitch());
        }
    }

    public GuiMenu setMaxPage(int maxPage) {
        this.maxPage = maxPage;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public int getCurPage() {
        return this.curPage;
    }

    public int getMaxPage() {
        return this.maxPage;
    }

    public Player getPlayer() {
        return this.player;
    }

    public AGuiMenu getMenuType() {
        return this.menuType;
    }

    public List<GuiButton> getButtons() {
        return this.buttons;
    }

    public IEventListener.OnEventListener getOnEventListener() {
        return this.onEventListener;
    }

    public IEventListener.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public IEventListener.OnDragListener getOnDragListener() {
        return this.onDragListener;
    }

    public IEventListener.OnCloseListener getOnCloseListener() {
        return this.onCloseListener;
    }
}
