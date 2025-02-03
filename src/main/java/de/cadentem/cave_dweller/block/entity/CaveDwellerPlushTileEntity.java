package de.cadentem.cave_dweller.block.entity;

import java.util.Iterator;
import java.util.stream.IntStream;
import javax.annotation.Nullable;

import de.cadentem.cave_dweller.init.CaveDwellerModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class CaveDwellerPlushTileEntity extends RandomizableContainerBlockEntity implements IAnimatable, WorldlyContainer {
   public AnimationFactory factory = GeckoLibUtil.createFactory(this);
   private NonNullList<ItemStack> stacks;
   private final LazyOptional<? extends IItemHandler>[] handlers;

   public CaveDwellerPlushTileEntity(BlockPos pos, BlockState state) {
      super(CaveDwellerModBlockEntities.CAVE_DWELLER_PLUSH.get(), pos, state);
      this.stacks = NonNullList.withSize(9, ItemStack.EMPTY);
      this.handlers = SidedInvWrapper.create(this, Direction.values());
   }

   private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
      Property<?> property = this.getBlockState().getBlock().getStateDefinition().getProperty("animation");
      int animationState = (property instanceof IntegerProperty integerProperty) ? this.getBlockState().getValue(integerProperty) : 0;
      String animationProcedure = String.valueOf(animationState);

      if (animationProcedure.equals("0")) {
         event.getController().setAnimation(new AnimationBuilder().addAnimation(animationProcedure, EDefaultLoopTypes.LOOP));
         return PlayState.CONTINUE;
      }
      return PlayState.STOP;
   }

   private <E extends BlockEntity & IAnimatable> PlayState procedurePredicate(AnimationEvent<E> event) {
      Property<?> property = this.getBlockState().getBlock().getStateDefinition().getProperty("animation");
      int animationState = (property instanceof IntegerProperty integerProperty) ? this.getBlockState().getValue(integerProperty) : 0;
      String animationProcedure = String.valueOf(animationState);

      if (!animationProcedure.equals("0") && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
         event.getController().setAnimation(new AnimationBuilder().addAnimation(animationProcedure, EDefaultLoopTypes.PLAY_ONCE));
         if (event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            if (property instanceof IntegerProperty integerProperty) {
               this.level.setBlock(this.worldPosition, this.getBlockState().setValue(integerProperty, 0), 3);
            }
            event.getController().markNeedsReload();
         }
      }
      return PlayState.CONTINUE;
   }

   public void registerControllers(AnimationData data) {
      data.addAnimationController(new AnimationController<>(this, "controller", 0.0F, this::predicate));
      data.addAnimationController(new AnimationController<>(this, "procedurecontroller", 0.0F, this::procedurePredicate));
   }

   public AnimationFactory getFactory() {
      return this.factory;
   }

   @Override
   public void load(CompoundTag compound) {
      super.load(compound);
      this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
      ContainerHelper.loadAllItems(compound, this.stacks);
   }

   @Override
   protected void saveAdditional(CompoundTag compound) {
      super.saveAdditional(compound);
      ContainerHelper.saveAllItems(compound, this.stacks);
   }

   @Override
   public ClientboundBlockEntityDataPacket getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   @Override
   public CompoundTag getUpdateTag() {
      return this.saveWithoutMetadata();
   }

   @Override
   public int getContainerSize() {
      return this.stacks.size();
   }

   @Override
   public boolean isEmpty() {
      return this.stacks.stream().allMatch(ItemStack::isEmpty);
   }

   @Override
   public Component getName() {
      return Component.literal("cave_dweller_plush");
   }

   @Override
   protected Component getDefaultName() {
      return null;
   }

   @Override
   public int getMaxStackSize() {
      return 64;
   }

   @Override
   public AbstractContainerMenu createMenu(int id, Inventory inventory) {
      return ChestMenu.threeRows(id, inventory);
   }

   @Override
   protected NonNullList<ItemStack> getItems() {
      return this.stacks;
   }

   @Override
   protected void setItems(NonNullList<ItemStack> stacks) {
      this.stacks = stacks;
   }

   @Override
   public boolean canPlaceItem(int index, ItemStack stack) {
      return true;
   }

   @Override
   public int[] getSlotsForFace(Direction side) {
      return IntStream.range(0, this.getContainerSize()).toArray();
   }

   @Override
   public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
      return this.canPlaceItem(index, stack);
   }

   @Override
   public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
      return true;
   }

   @Override
   public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
      return !this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER ? this.handlers[facing.ordinal()].cast() : super.getCapability(capability, facing);
   }

   @Override
   public void invalidateCaps() {
      super.invalidateCaps();
      for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
         handler.invalidate();
      }
   }
}
