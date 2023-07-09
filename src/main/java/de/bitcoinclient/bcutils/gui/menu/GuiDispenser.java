/*    */ package de.bitcoinclient.bcutils.gui.menu;
/*    */
/*    */ import de.bitcoinclient.bcutils.gui.listener.AGuiMenu;
import de.bitcoinclient.bcutils.gui.result.GuiMenu;
import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.inventory.InventoryType;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class GuiDispenser extends AGuiMenu {
/*    */   public GuiDispenser(GuiMenu parent) {
/* 17 */     super(parent);
/*    */   }
/*    */   
/*    */   public Inventory create() {
/* 22 */     return Bukkit.createInventory(null, InventoryType.DISPENSER, this.parent.getTitle());
/*    */   }
/*    */ }


/* Location:              C:\Users\bitco\Downloads\guiapi-1.5.2.jar!\de\pandadoxo\guiapi\menu\GuiDispenser.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */