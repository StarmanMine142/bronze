package com.khazoda.bronze;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigNeoForge {
  public static final ModConfigSpec SPEC;

  static {
    ModConfigSpec.Builder b = new ModConfigSpec.Builder();
    b.comment(
        "Tin-ore generation is controlled by datapacks in NeoForge.",
        "To disable it, create a datapack that overrides",
        "bronze:neoforge/biome_modifier/add_tin_ore.json and add_small_tin_ore.json",
        "with an empty JSON object {}",
        " ",
        " (ignore the value below)"
    ).push("ignoreMe");

    /* dummy entry */
    b.define("_dummy", true);
    b.pop();
    SPEC = b.build();
  }
}