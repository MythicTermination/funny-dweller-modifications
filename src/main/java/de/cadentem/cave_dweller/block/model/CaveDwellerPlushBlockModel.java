package de.cadentem.cave_dweller.block.model;

import de.cadentem.cave_dweller.block.entity.CaveDwellerPlushTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CaveDwellerPlushBlockModel extends AnimatedGeoModel<CaveDwellerPlushTileEntity> {
   public ResourceLocation getAnimationResource(CaveDwellerPlushTileEntity animatable) {
      return new ResourceLocation("cave_dweller", "animations/cave_dweller_plush.animation.json");
   }

   public ResourceLocation getModelResource(CaveDwellerPlushTileEntity animatable) {
      return new ResourceLocation("cave_dweller", "geo/cave_dweller_plush.geo.json");
   }

   public ResourceLocation getTextureResource(CaveDwellerPlushTileEntity entity) {
      return new ResourceLocation("cave_dweller", "textures/blocks/cave_dweller_plush_texture.png");
   }
}
