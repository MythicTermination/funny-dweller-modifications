package de.cadentem.cave_dweller.init;

import de.cadentem.cave_dweller.block.CaveDwellerPlushBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CaveDwellerModBlocks {
   public static final DeferredRegister<Block> ITEMS;
   public static final RegistryObject<Block> CAVE_DWELLER_PLUSH;

   static {
      ITEMS = DeferredRegister.create(ForgeRegistries.BLOCKS, "cave_dweller");
      CAVE_DWELLER_PLUSH = ITEMS.register("cave_dweller_plush", () -> {
         return new CaveDwellerPlushBlock();
      });
   }
}
