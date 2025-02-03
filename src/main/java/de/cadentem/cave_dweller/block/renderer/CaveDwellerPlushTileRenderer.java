package de.cadentem.cave_dweller.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.cadentem.cave_dweller.block.entity.CaveDwellerPlushTileEntity;
import de.cadentem.cave_dweller.block.model.CaveDwellerPlushBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CaveDwellerPlushTileRenderer extends GeoBlockRenderer<CaveDwellerPlushTileEntity> {
   public CaveDwellerPlushTileRenderer(Context rendererDispatcherIn) {
      super(rendererDispatcherIn, new CaveDwellerPlushBlockModel());
   }

   public RenderType getRenderType(CaveDwellerPlushTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
