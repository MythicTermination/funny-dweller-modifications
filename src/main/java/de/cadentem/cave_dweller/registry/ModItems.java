package de.cadentem.cave_dweller.registry;

import de.cadentem.cave_dweller.block.display.CaveDwellerPlushDisplayItem;
import de.cadentem.cave_dweller.init.CaveDwellerModBlocks;
import de.cadentem.cave_dweller.init.CaveDwellerModTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> CAVE_DWELLER_SPAWN_EGG;
    public static final RegistryObject<Item> CAVE_DWELLER_PLUSH;


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "cave_dweller");
        CAVE_DWELLER_SPAWN_EGG = ITEMS.register("cave_dweller_spawn_egg", () -> {
            return new ForgeSpawnEggItem(ModEntityTypes.CAVE_DWELLER, 12895428, 790333, (new Properties()).tab(CaveDwellerModTabs.TAB_CAVE_DWELLER));
        });
        CAVE_DWELLER_PLUSH = ITEMS.register(CaveDwellerModBlocks.CAVE_DWELLER_PLUSH.getId().getPath(), () -> {
            return new CaveDwellerPlushDisplayItem((Block)CaveDwellerModBlocks.CAVE_DWELLER_PLUSH.get(), (new Properties()).tab(CaveDwellerModTabs.TAB_CAVE_DWELLER));
        });
    }
}