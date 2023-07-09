/*    */ package de.bitcoinclient.bcutils.gui.menu;

/*    */ import de.bitcoinclient.bcutils.gui.listener.AGuiMenu;
import de.bitcoinclient.bcutils.gui.result.GuiMenu;
import org.bukkit.Bukkit;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class GuiChest extends AGuiMenu {
/*    */   private int size;
/*    */   
/*    */   public GuiChest(GuiMenu parent) {
/* 18 */     super(parent);
/* 19 */     this.size = 27;
/*    */   }
/*    */   
/*    */   public Inventory create() {
/* 24 */     return Bukkit.createInventory(null, this.size, this.parent.getTitle());
/*    */   }
/*    */   
/*    */   public int getSize() {
/* 28 */     return this.size;
/*    */   }
/*    */   
/*    */   public GuiChest setSize(int size) {
/* 32 */     this.size = size;
/* 33 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\bitco\Downloads\guiapi-1.5.2.jar!\de\pandadoxo\guiapi\menu\GuiChest.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */