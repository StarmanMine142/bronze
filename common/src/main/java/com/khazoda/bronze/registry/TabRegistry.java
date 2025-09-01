package com.khazoda.bronze.registry;

import com.khazoda.bronze.BronzeCommon;
import com.khazoda.bronze.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import static com.khazoda.bronze.BronzeCommon.mod_loaded_farmersdelight;

@SuppressWarnings("unused")
public class TabRegistry {
  public static final Supplier<CreativeModeTab> BRONZE_TAB = BronzeCommon.REGISTRARS
      .get(Registries.CREATIVE_MODE_TAB)
      .register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
          .title(Component.translatable("bronze.itemGroup"))
          .icon(() -> MainRegistry.BRONZE_BLOCK_ITEM.get().getDefaultInstance())
          .displayItems((parameters, output) -> {
            output.accept(MainRegistry.BRONZE_SWORD.get());
            output.accept(MainRegistry.BRONZE_AXE.get());
            output.accept(MainRegistry.BRONZE_PICKAXE.get());
            output.accept(MainRegistry.BRONZE_SHOVEL.get());
            output.accept(MainRegistry.BRONZE_HOE.get());
            output.accept(MainRegistry.SICKLE.get());
            if(mod_loaded_farmersdelight) output.accept(MainRegistry.BRONZE_KNIFE.get());
            output.accept(MainRegistry.BRONZE_HELMET.get());
            output.accept(MainRegistry.BRONZE_CHESTPLATE.get());
            output.accept(MainRegistry.BRONZE_LEGGINGS.get());
            output.accept(MainRegistry.BRONZE_BOOTS.get());
            output.accept(MainRegistry.TIN_BLOCK_ITEM.get());
            output.accept(MainRegistry.BRONZE_BLOCK_ITEM.get());
            output.accept(MainRegistry.RAW_TIN_BLOCK_ITEM.get());
            output.accept(MainRegistry.BRONZE_BLEND_BLOCK_ITEM.get());
            output.accept(MainRegistry.TIN_ORE_ITEM.get());
            output.accept(MainRegistry.DEEPSLATE_TIN_ORE_ITEM.get());
            output.accept(MainRegistry.RAW_TIN.get());
            output.accept(MainRegistry.BRONZE_BLEND.get());
            output.accept(MainRegistry.TIN_INGOT.get());
            output.accept(MainRegistry.BRONZE_INGOT.get());
            output.accept(MainRegistry.BRONZE_NUGGET.get());
            output.accept(MainRegistry.BRONZE_HORSE_ARMOR.get());
            output.accept(MainRegistry.BRONZE_DOOR_ITEM.get());
            output.accept(MainRegistry.BRONZE_TRAPDOOR_ITEM.get());
            output.accept(MainRegistry.TIN_FRAMED_GLASS_ITEM.get());
            output.accept(MainRegistry.CHISELED_TIN_ITEM.get());
            output.accept(MainRegistry.TIN_TILES_ITEM.get());
            output.accept(MainRegistry.CUT_TIN_ITEM.get());
            output.accept(MainRegistry.CUT_TIN_STAIRS_ITEM.get());
            output.accept(MainRegistry.CUT_TIN_SLAB_ITEM.get());
          })
          .build());

  public static void init() {
  }
}