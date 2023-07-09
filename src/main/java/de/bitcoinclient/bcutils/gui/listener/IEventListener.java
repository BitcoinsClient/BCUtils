/*    */ package de.bitcoinclient.bcutils.gui.listener;
/*    */
/*    */ import de.bitcoinclient.bcutils.gui.result.GuiMenu;
import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*    */ import org.bukkit.event.inventory.InventoryDragEvent;
/*    */ import org.bukkit.event.inventory.InventoryEvent;
/*    */ 
/*    */ public class IEventListener {
/*    */   public static OnEventListener DefaultOnEventListener() {
/* 21 */     return (event, menu) -> {
/*    */       
/*    */       };
/*    */   }
/*    */   
/*    */   public static OnClickListener DefaultOnClickListener() {
/* 30 */     return (event, menu) -> event.setCancelled(true);
/*    */   }
/*    */   
/*    */   public static OnDragListener DefaultOnDragListener() {
/* 40 */     return (event, menu) -> event.setCancelled(true);
/*    */   }
/*    */   
/*    */   public static OnCloseListener DefaultOnCloseListener() {
/* 50 */     return (event, menu) -> menu.destroy();
/*    */   }
/*    */   
/*    */   public static interface OnEventListener {
/*    */     void onEvent(InventoryEvent param1InventoryEvent, GuiMenu param1GuiMenu);
/*    */   }
/*    */   
/*    */   public static interface OnClickListener {
/*    */     void onClick(InventoryClickEvent param1InventoryClickEvent, GuiMenu param1GuiMenu);
/*    */   }
/*    */   
/*    */   public static interface OnDragListener {
/*    */     void onDrag(InventoryDragEvent param1InventoryDragEvent, GuiMenu param1GuiMenu);
/*    */   }
/*    */   
/*    */   public static interface OnCloseListener {
/*    */     void onClose(InventoryCloseEvent param1InventoryCloseEvent, GuiMenu param1GuiMenu);
/*    */   }
/*    */ }


/* Location:              C:\Users\bitco\Downloads\guiapi-1.5.2.jar!\de\pandadoxo\guiapi\interfaces\IEventListener.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */