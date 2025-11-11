package com.khazoda.bronze;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

import static com.khazoda.bronze.registry.MainRegistry.TIN_ORE_PLACED_KEY;
import static com.khazoda.bronze.registry.MainRegistry.TIN_ORE_SMALL_PLACED_KEY;

public class BronzeFabric implements ModInitializer {

  @Override
  public void onInitialize() {

    BronzeCommon.init();

    /* Add Tin Ore to WorldGen */
    if (ConfigFabric.generateTinOre()) {
      BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, TIN_ORE_PLACED_KEY);
      BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, TIN_ORE_SMALL_PLACED_KEY);
    }
    BronzeCommon.postInit();
    BronzeCommon.REGISTRARS.registerAll();
  }
}
