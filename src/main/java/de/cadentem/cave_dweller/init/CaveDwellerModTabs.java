package de.cadentem.cave_dweller.init;

import de.cadentem.cave_dweller.registry.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class CaveDwellerModTabs {
   public static CreativeModeTab TAB_CAVE_DWELLER;

   public static void load() {
      TAB_CAVE_DWELLER = new CreativeModeTab("tab_cave_dweller") {
         public ItemStack makeIcon() {
            return new ItemStack((ItemLike) ModItems.CAVE_DWELLER_PLUSH.get());
         }

         public boolean hasSearchBar() {
            return false;
         }
      };
   }
}
