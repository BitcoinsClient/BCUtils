/*     */ package de.bitcoinclient.bcutils.gui.builder;
/*     */ 
/*     */ import de.bitcoinclient.bcutils.gui.listener.AGuiMenu;
import de.bitcoinclient.bcutils.gui.listener.IEventListener;
import de.bitcoinclient.bcutils.gui.menu.GuiChest;
import de.bitcoinclient.bcutils.gui.result.GuiButton;
/*     */ import de.bitcoinclient.bcutils.gui.result.GuiMenu;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class GuiMenuBuilder implements Listener, Cloneable {
/*  26 */   private final List<GuiButton> buttons = new ArrayList<>();
/*     */   
/*     */   private Player player;
/*     */   
/*     */   private Inventory inventory;
/*     */   
/*     */   private Class<? extends AGuiMenu> menuType;
/*     */   
/*     */   private String title;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private int curPage;
/*     */   
/*     */   private int maxPage;
/*     */   
/*  36 */   private IEventListener.OnEventListener onEventListener = IEventListener.DefaultOnEventListener();
/*     */   
/*  37 */   private IEventListener.OnClickListener onClickListener = IEventListener.DefaultOnClickListener();
/*     */   
/*  38 */   private IEventListener.OnDragListener onDragListener = IEventListener.DefaultOnDragListener();
/*     */   
/*  39 */   private IEventListener.OnCloseListener onCloseListener = IEventListener.DefaultOnCloseListener();
/*     */   
/*     */   public GuiMenuBuilder() {
/*  42 */     this.curPage = 1;
/*  43 */     this.maxPage = 1;
/*  44 */     this.menuType = (Class) GuiChest.class;
/*     */   }
/*     */   
/*     */   public GuiMenu create(Plugin plugin) {
/*  48 */     return create(plugin, GuiMenu.class);
/*     */   }
/*     */   
/*     */   public <T extends GuiMenu> T create(Plugin plugin, Class<T> clazz) {
/*  52 */     Validate.isTrue((this.player != null), "Player needs to be specified!");
/*  53 */     Validate.isTrue((this.menuType != null), "Menu-Type needs to be specified!");
/*     */     try {
/*  57 */       Constructor<T> constructor = clazz.getConstructor(new Class[] { 
/*  57 */             Plugin.class, Player.class, Inventory.class, Class.class, String.class, int.class, int.class, int.class, List.class, IEventListener.OnEventListener.class, 
/*  57 */             IEventListener.OnClickListener.class, IEventListener.OnDragListener.class, IEventListener.OnCloseListener.class });
/*  61 */       return constructor.newInstance(new Object[] { 
/*  61 */             plugin, this.player, this.inventory, this.menuType, this.title, Integer.valueOf(this.size), Integer.valueOf(this.curPage), Integer.valueOf(this.maxPage), this.buttons, this.onEventListener, 
/*  61 */             this.onClickListener, this.onDragListener, this.onCloseListener });
/*  63 */     } catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException|InstantiationException|IllegalAccessException e) {
/*  65 */       e.printStackTrace();
/*  67 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setPlayer(Player player) {
/*  72 */     this.player = player;
/*  73 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setInventory(Inventory inventory) {
/*  77 */     this.inventory = inventory;
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setMenuType(Class<? extends AGuiMenu> menuType) {
/*  82 */     this.menuType = menuType;
/*  83 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setTitle(String title) {
/*  87 */     this.title = title;
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setSize(int size) {
/*  92 */     this.size = size;
/*  93 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setCurPage(int curPage) {
/*  97 */     this.curPage = curPage;
/*  98 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setMaxPage(int maxPage) {
/* 102 */     this.maxPage = maxPage;
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setOnEventListener(IEventListener.OnEventListener onEventListener) {
/* 107 */     this.onEventListener = onEventListener;
/* 108 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setOnClickListener(IEventListener.OnClickListener onClickListener) {
/* 112 */     this.onClickListener = onClickListener;
/* 113 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setOnDragListener(IEventListener.OnDragListener onDragListener) {
/* 117 */     this.onDragListener = onDragListener;
/* 118 */     return this;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder setOnCloseListener(IEventListener.OnCloseListener onCloseListener) {
/* 122 */     this.onCloseListener = onCloseListener;
/* 123 */     return this;
/*     */   }
/*     */   
/*     */   public void onClick(IEventListener.OnClickListener listener) {
/* 128 */     this.onClickListener = listener;
/*     */   }
/*     */   
/*     */   public void onDrag(IEventListener.OnDragListener listener) {
/* 132 */     this.onDragListener = listener;
/*     */   }
/*     */   
/*     */   public void onClose(IEventListener.OnCloseListener listener) {
/* 136 */     this.onCloseListener = listener;
/*     */   }
/*     */   
/*     */   public void onEvent(IEventListener.OnEventListener listener) {
/* 140 */     this.onEventListener = listener;
/*     */   }
/*     */   
/*     */   public GuiMenuBuilder clone() {
/*     */     try {
/* 146 */       GuiMenuBuilder clone = (GuiMenuBuilder)super.clone();
/* 148 */       return clone;
/* 149 */     } catch (CloneNotSupportedException e) {
/* 150 */       throw new AssertionError();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\bitco\Downloads\guiapi-1.5.2.jar!\de\pandadoxo\guiapi\builder\GuiMenuBuilder.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */