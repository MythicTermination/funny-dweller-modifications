package de.cadentem.cave_dweller.init;

import com.mojang.datafixers.types.Type;
import de.cadentem.cave_dweller.block.entity.CaveDwellerPlushTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CaveDwellerModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> ITEMS;
   public static final RegistryObject<BlockEntityType<CaveDwellerPlushTileEntity>> CAVE_DWELLER_PLUSH;

   private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntitySupplier<?> supplier) {
      return ITEMS.register(registryname, () -> {
         return Builder.of(supplier, new Block[]{(Block)block.get()}).build((Type)null);
      });
   }

   static {
      ITEMS = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "cave_dweller");
      CAVE_DWELLER_PLUSH = ITEMS.register("cave_dweller_plush", () -> {
         return Builder.of(CaveDwellerPlushTileEntity::new, new Block[]{(Block)CaveDwellerModBlocks.CAVE_DWELLER_PLUSH.get()}).build((Type)null);
      });
   }
}
