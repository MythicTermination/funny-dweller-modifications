package de.cadentem.cave_dweller.block.listener;

import de.cadentem.cave_dweller.init.CaveDwellerModBlockEntities;
import de.cadentem.cave_dweller.block.renderer.CaveDwellerPlushTileRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "cave_dweller",
   bus = Bus.MOD
)
public class ClientListener {
   @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void registerRenderers(RegisterRenderers event) {
      event.registerBlockEntityRenderer((BlockEntityType) CaveDwellerModBlockEntities.CAVE_DWELLER_PLUSH.get(), CaveDwellerPlushTileRenderer::new);
   }
}
