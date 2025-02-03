package de.cadentem.cave_dweller.block.model;

import de.cadentem.cave_dweller.block.display.CaveDwellerPlushDisplayItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CaveDwellerPlushDisplayModel extends AnimatedGeoModel<CaveDwellerPlushDisplayItem> {
   public ResourceLocation getAnimationResource(CaveDwellerPlushDisplayItem animatable) {
      return new ResourceLocation("cave_dweller", "animations/cave_dweller_plush.animation.json");
   }

   public ResourceLocation getModelResource(CaveDwellerPlushDisplayItem animatable) {
      return new ResourceLocation("cave_dweller", "geo/cave_dweller_plush.geo.json");
   }

   public ResourceLocation getTextureResource(CaveDwellerPlushDisplayItem entity) {
      return new ResourceLocation("cave_dweller", "textures/blocks/cave_dweller_plush_texture.png");
   }
}
