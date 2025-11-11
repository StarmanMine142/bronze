package com.khazoda.bronze;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigFabric {
  private static final Path FILE =
      FabricLoader.getInstance().getConfigDir().resolve("bronze.json");
  private static JsonObject root;

  public static boolean generateTinOre() {
    if (root == null) load();
    return root.has("generateTinOre") && root.get("generateTinOre").getAsBoolean();
  }

  private static void load() {
    try {
      if (Files.notExists(FILE)) {
        root = new JsonObject();
        root.addProperty("generateTinOre", true);
        Files.writeString(FILE, root.toString());
      } else {
        root = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}