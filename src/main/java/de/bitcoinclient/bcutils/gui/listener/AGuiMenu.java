/*    */ package de.bitcoinclient.bcutils.gui.listener;
/*    */
/*    */ import de.bitcoinclient.bcutils.gui.result.GuiMenu;
import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public abstract class AGuiMenu {
/*    */   protected final GuiMenu parent;
/*    */   
/*    */   protected Inventory inventory;
/*    */   
/*    */   public AGuiMenu(GuiMenu parent) {
/* 17 */     this.parent = parent;
/*    */   }
/*    */   
/*    */   public abstract Inventory create();
/*    */   
/*    */   public Inventory getInventory() {
/* 23 */     if (this.inventory == null)
/* 24 */       this.inventory = create(); 
/* 26 */     return this.inventory;
/*    */   }
/*    */   
/*    */   public AGuiMenu setInventory(Inventory inventory) {
/* 30 */     this.inventory = inventory;
/* 31 */     return this;
/*    */   }
/*    */   
/*    */   public GuiMenu getParent() {
/* 35 */     return this.parent;
/*    */   }
/*    */ }


/* Location:              C:\Users\bitco\Downloads\guiapi-1.5.2.jar!\de\pandadoxo\guiapi\interfaces\AGuiMenu.class
 * Java compiler version: 16 (60.0)
 * JD-Core Version:       1.1.3
 */