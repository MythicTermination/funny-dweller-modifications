package de.cadentem.cave_dweller.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.cadentem.cave_dweller.block.display.CaveDwellerPlushDisplayItem;
import de.cadentem.cave_dweller.block.model.CaveDwellerPlushDisplayModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CaveDwellerPlushDisplayItemRenderer extends GeoItemRenderer<CaveDwellerPlushDisplayItem> {
   public CaveDwellerPlushDisplayItemRenderer() {
      super(new CaveDwellerPlushDisplayModel());
   }

   public RenderType getRenderType(CaveDwellerPlushDisplayItem animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
      return RenderType.entityTranslucent(this.getTextureLocation(animatable));
   }
}
