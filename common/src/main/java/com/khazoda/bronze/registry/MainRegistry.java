package com.khazoda.bronze.registry;

import com.khazoda.bronze.BronzeCommon;
import com.khazoda.bronze.block.*;
import com.khazoda.bronze.item.*;
import com.khazoda.bronze.material.BronzeArmorMaterial;
import com.khazoda.bronze.material.BronzeToolMaterial;
import com.khazoda.bronze.platform.Services;
import com.khazoda.bronze.registry.helper.Reggie;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

import static com.khazoda.bronze.BronzeCommon.mod_loaded_farmersdelight;
import static com.khazoda.bronze.Constants.ID;
import static net.minecraft.world.item.ArmorItem.Type.CHESTPLATE;


public class MainRegistry {
  private static final Reggie<Block> BLOCK_REGISTRAR = BronzeCommon.REGISTRARS.get(Registries.BLOCK);
  private static final Reggie<Item> ITEM_REGISTRAR = BronzeCommon.REGISTRARS.get(Registries.ITEM);
  private static final Reggie<ArmorMaterial> ARMOR_MATERIAL_REGISTRAR = BronzeCommon.REGISTRARS.get(Registries.ARMOR_MATERIAL);

  /* ==========[ Item Registration ]========== */
  public static final Supplier<Item> RAW_TIN = ITEM_REGISTRAR.register("raw_tin", RawTin::new);
  public static final Supplier<Item> TIN_INGOT = ITEM_REGISTRAR.register("tin_ingot", TinIngot::new);
  public static final Supplier<Item> BRONZE_BLEND = ITEM_REGISTRAR.register("bronze_blend", BronzeBlend::new);
  public static final Supplier<Item> BRONZE_NUGGET = ITEM_REGISTRAR.register("bronze_nugget", BronzeNugget::new);
  public static final Supplier<Item> BRONZE_INGOT = ITEM_REGISTRAR.register("bronze_ingot", BronzeIngot::new);
  public static final Supplier<Item> BRONZE_HORSE_ARMOR = ITEM_REGISTRAR.register("bronze_horse_armor",
          () -> new AnimalArmorItem(BronzeArmorMaterial.HOLDER, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));

  public static final Supplier<TieredItem> BRONZE_SWORD = ITEM_REGISTRAR.register("bronze_sword", () -> new BronzeSword(BronzeToolMaterial.INSTANCE));
  public static final Supplier<TieredItem> BRONZE_AXE = ITEM_REGISTRAR.register("bronze_axe", () -> new BronzeAxe(BronzeToolMaterial.INSTANCE));
  public static final Supplier<TieredItem> BRONZE_PICKAXE = ITEM_REGISTRAR.register("bronze_pickaxe", () -> new BronzePickaxe(BronzeToolMaterial.INSTANCE));
  public static final Supplier<TieredItem> BRONZE_SHOVEL = ITEM_REGISTRAR.register("bronze_shovel", () -> new BronzeShovel(BronzeToolMaterial.INSTANCE));
  public static final Supplier<TieredItem> BRONZE_HOE = ITEM_REGISTRAR.register("bronze_hoe", () -> new BronzeHoe(BronzeToolMaterial.INSTANCE));
  public static final Supplier<TieredItem> SICKLE = ITEM_REGISTRAR.register("bronze_sickle", () -> new Sickle(BronzeToolMaterial.INSTANCE, new Item.Properties().stacksTo(1)));

  public static final Supplier<Item> BRONZE_HELMET = ITEM_REGISTRAR.register("bronze_helmet",
          () -> new ArmorItem(BronzeArmorMaterial.HOLDER, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));
  public static final Supplier<Item> BRONZE_CHESTPLATE = ITEM_REGISTRAR.register("bronze_chestplate",
          () -> new ArmorItem(BronzeArmorMaterial.HOLDER, CHESTPLATE, new Item.Properties().durability(CHESTPLATE.getDurability(18))));
  public static final Supplier<Item> BRONZE_LEGGINGS = ITEM_REGISTRAR.register("bronze_leggings",
          () -> new ArmorItem(BronzeArmorMaterial.HOLDER, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));
  public static final Supplier<Item> BRONZE_BOOTS = ITEM_REGISTRAR.register("bronze_boots",
          () -> new ArmorItem(BronzeArmorMaterial.HOLDER, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));

  /* Farmers Delight Knife Compatibility */
  public static Supplier<Item> BRONZE_KNIFE;

  /* ==========[ Block Registration ]========== */
  public static final Supplier<Block> TIN_BLOCK = BLOCK_REGISTRAR.register("tin_block", TinBlock::new);
  public static final Supplier<Block> BRONZE_BLOCK = BLOCK_REGISTRAR.register("bronze_block", BronzeBlock::new);
  public static final Supplier<Block> TIN_ORE = BLOCK_REGISTRAR.register("tin_ore_block", TinOre::new);
  public static final Supplier<Block> DEEPSLATE_TIN_ORE = BLOCK_REGISTRAR.register("deepslate_tin_ore_block", DeepslateTinOre::new);
  public static final Supplier<Block> TIN_FRAMED_GLASS = BLOCK_REGISTRAR.register("tin_framed_glass", TinFramedGlass::new);
  public static final Supplier<Block> BRONZE_TRAPDOOR = BLOCK_REGISTRAR.register("bronze_trapdoor_block", BronzeTrapdoor::new);
  public static final Supplier<Block> BRONZE_DOOR = BLOCK_REGISTRAR.register("bronze_door_block", BronzeDoor::new);
  public static final Supplier<Block> TIN_TILES = BLOCK_REGISTRAR.register("tin_tiles", TinTiles::new);
  public static final Supplier<Block> BRONZE_BLEND_BLOCK = BLOCK_REGISTRAR.register("bronze_blend_block", BronzeBlendBlock::new);
  public static final Supplier<Block> CHISELED_TIN = BLOCK_REGISTRAR.register("chiseled_tin", ChiseledTin::new);
  public static final Supplier<Block> CUT_TIN = BLOCK_REGISTRAR.register("cut_tin", CutTin::new);
  public static final Supplier<Block> RAW_TIN_BLOCK = BLOCK_REGISTRAR.register("raw_tin_block", TinRawBlock::new);
  public static final Supplier<Block> CUT_TIN_STAIRS = BLOCK_REGISTRAR.register("cut_tin_stairs", () -> new CutTinStairs(CUT_TIN.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CUT_TIN.get())));
  public static final Supplier<Block> CUT_TIN_SLAB = BLOCK_REGISTRAR.register("cut_tin_slab", () -> new CutTinSlab(BlockBehaviour.Properties.ofFullCopy(CUT_TIN.get())));

  /* ==========[ BlockItem Registration ]========== */
  public static final Supplier<BlockItem> TIN_BLOCK_ITEM = register("tin_block", TIN_BLOCK);
  public static final Supplier<BlockItem> BRONZE_BLOCK_ITEM = register("bronze_block", BRONZE_BLOCK);
  public static final Supplier<BlockItem> TIN_ORE_ITEM = register("tin_ore_block", TIN_ORE);
  public static final Supplier<BlockItem> DEEPSLATE_TIN_ORE_ITEM = register("deepslate_tin_ore_block", DEEPSLATE_TIN_ORE);
  public static final Supplier<BlockItem> TIN_FRAMED_GLASS_ITEM = register("tin_framed_glass", TIN_FRAMED_GLASS);
  public static final Supplier<BlockItem> BRONZE_TRAPDOOR_ITEM = register("bronze_trapdoor_block", BRONZE_TRAPDOOR);
  public static final Supplier<BlockItem> BRONZE_DOOR_ITEM = register("bronze_door_block", BRONZE_DOOR);
  public static final Supplier<BlockItem> TIN_TILES_ITEM = register("tin_tiles", TIN_TILES);
  public static final Supplier<BlockItem> BRONZE_BLEND_BLOCK_ITEM = register("bronze_blend_block", BRONZE_BLEND_BLOCK);
  public static final Supplier<BlockItem> CHISELED_TIN_ITEM = register("chiseled_tin", CHISELED_TIN);
  public static final Supplier<BlockItem> CUT_TIN_ITEM = register("cut_tin", CUT_TIN);
  public static final Supplier<BlockItem> RAW_TIN_BLOCK_ITEM = register("raw_tin_block", RAW_TIN_BLOCK);
  public static final Supplier<BlockItem> CUT_TIN_STAIRS_ITEM = register("cut_tin_stairs", CUT_TIN_STAIRS);
  public static final Supplier<BlockItem> CUT_TIN_SLAB_ITEM = register("cut_tin_slab", CUT_TIN_SLAB);

  /* WorldGen */
  public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, ID("ore_tin"));
  public static final ResourceKey<PlacedFeature> TIN_ORE_SMALL_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, ID("ore_tin_small"));

  public static void init() {
    if (mod_loaded_farmersdelight) BRONZE_KNIFE = ITEM_REGISTRAR.register("bronze_knife",
            () -> new BronzeKnifeFarmersDelight(BronzeToolMaterial.INSTANCE));

  }

  private static Supplier<BlockItem> register(String name, Supplier<Block> block) {
    Supplier<BlockItem> blockItemSupplier = ITEM_REGISTRAR.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    return blockItemSupplier;
  }
}