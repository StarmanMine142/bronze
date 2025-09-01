package com.khazoda.bronze;

import com.khazoda.bronze.platform.Services;
import com.khazoda.bronze.registry.MainRegistry;
import com.khazoda.bronze.registry.TabRegistry;
import com.khazoda.bronze.registry.helper.Reginald;

public class BronzeCommon {

  public static final boolean mod_loaded_farmersdelight = Services.PLATFORM.isModLoaded("farmersdelight");
  public static final Reginald REGISTRARS = new Reginald();

  public static void init() {
    MainRegistry.init();
    TabRegistry.init();

    if (Services.PLATFORM.isModLoaded("bronze")) Constants.LOG.info("- Bronze Loaded -");
  }

  public static void postInit() {

  }
}