package com.khazoda.bronze.datagen.advancements;

import com.khazoda.bronze.registry.MainRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

import static com.khazoda.bronze.Constants.ID;

@SuppressWarnings("removal")
public class BronzeAdvancements implements Consumer<Consumer<AdvancementHolder>> {
  private HolderGetter<Item> registryEntryLookup;

  public void accept(HolderGetter.Provider registryLookup, Consumer<AdvancementHolder> advancementConsumer) {
    registryEntryLookup = registryLookup.lookupOrThrow(Registries.ITEM);
    accept(advancementConsumer);
  }

  @Override
  public void accept(Consumer<AdvancementHolder> advancementConsumer) {
    AdvancementHolder gotTinIngotAdvancement = Advancement.Builder.advancement()
        .parent(ResourceLocation.withDefaultNamespace("story/upgrade_tools"))
        .display(
            MainRegistry.TIN_INGOT.get(),
            Component.translatable("advancement.bronze.got_tin_ingot.title"),
            Component.translatable("advancement.bronze.got_tin_ingot.description"),
            null,
            AdvancementType.TASK,
            true,
            true,
            false
        )
        .addCriterion("got_tin_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.TIN_INGOT.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/tin_block"))
            .addRecipe(ID("crafting/tin_nugget"))
            .addRecipe(ID("stonecutting/cut_tin"))
            .addRecipe(ID("stonecutting/tin_framed_glass"))
            .addRecipe(ID("stonecutting/chiseled_tin_from_tin_block"))
            .addRecipe(ID("stonecutting/cut_tin_from_tin_block"))
            .addRecipe(ID("stonecutting/cut_tin_slab_from_tin_block"))
            .addRecipe(ID("stonecutting/cut_tin_stairs_from_tin_block"))
            .addRecipe(ID("stonecutting/tin_tiles_from_tin_block")))
        .build(ID("bronze/got_tin_ingot"));

    advancementConsumer.accept(gotTinIngotAdvancement);

    AdvancementHolder gotCutTinAdvancement = Advancement.Builder.advancement()
        .parent(gotTinIngotAdvancement)
        .display(
            MainRegistry.CUT_TIN.get(),
            Component.translatable("advancement.bronze.got_cut_tin.title"),
            Component.translatable("advancement.bronze.got_cut_tin.description"),
            null,
            AdvancementType.TASK,
            true,
            true,
            false
        )
        .addCriterion("got_cut_tin", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.CUT_TIN.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/tin_tiles"))
            .addRecipe(ID("stonecutting/chiseled_tin_from_cut_tin"))
            .addRecipe(ID("stonecutting/cut_tin_slab_from_cut_tin"))
            .addRecipe(ID("stonecutting/cut_tin_stairs_from_cut_tin"))
            .addRecipe(ID("stonecutting/tin_tiles_from_cut_tin"))
            .addRecipe(ID("crafting/cut_tin_slab"))
            .addRecipe(ID("crafting/cut_tin_stairs"))
            .addRecipe(ID("crafting/chiseled_tin_from_slabs")))
        .build(ID("bronze/got_cut_tin"));

    advancementConsumer.accept(gotCutTinAdvancement);

    AdvancementHolder gotBronzeBlendAdvancement = Advancement.Builder.advancement()
        .parent(ResourceLocation.withDefaultNamespace("story/upgrade_tools"))
        .display(
            MainRegistry.BRONZE_BLEND.get(),
            Component.translatable("advancement.bronze.got_bronze_blend.title"),
            Component.translatable("advancement.bronze.got_bronze_blend.description"),
            null,
            AdvancementType.TASK,
            true,
            false,
            false
        )
        .addCriterion("got_bronze_blend", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_BLEND.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("smelting/bronze_ingot_from_smelting_bronze_blend"))
            .addRecipe(ID("smelting/bronze_ingot_from_blasting_bronze_blend"))
            .addRecipe(ID("crafting/bronze_blend_block")))
        .build(ID("bronze/got_bronze_blend"));

    advancementConsumer.accept(gotBronzeBlendAdvancement);

    AdvancementHolder gotBronzeIngotAdvancement = Advancement.Builder.advancement()
        .parent(gotBronzeBlendAdvancement)
        .display(
            MainRegistry.BRONZE_INGOT.get(),
            Component.translatable("advancement.bronze.got_bronze_ingot.title"),
            Component.translatable("advancement.bronze.got_bronze_ingot.description"),
            null,
            AdvancementType.TASK,
            true,
            true,
            false
        )
        .addCriterion("got_bronze_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_INGOT.get()))
        .rewards(AdvancementRewards.Builder
            .recipe(ID("crafting/bronze_helmet"))
            .addRecipe(ID("crafting/bronze_chestplate"))
            .addRecipe(ID("crafting/bronze_leggings"))
            .addRecipe(ID("crafting/bronze_boots"))
            .addRecipe(ID("crafting/bronze_sword"))
            .addRecipe(ID("crafting/bronze_pickaxe"))
            .addRecipe(ID("crafting/bronze_shovel"))
            .addRecipe(ID("crafting/bronze_axe"))
            .addRecipe(ID("crafting/bronze_hoe"))
            .addRecipe(ID("crafting/bronze_sickle"))
            .addRecipe(ID("crafting/bronze_nugget"))
            .addRecipe(ID("smelting/bronze_nugget_from_smelting"))
            .addRecipe(ID("smelting/bronze_nugget_from_blasting"))
            .addRecipe(ID("crafting/bronze_door"))
            .addRecipe(ID("crafting/bronze_trapdoor"))
            .addRecipe(ID("crafting/bronze_block")))
        .build(ID("bronze/got_bronze_ingot"));

    advancementConsumer.accept(gotBronzeIngotAdvancement);

    AdvancementHolder gotBronzeChestplateAdvancement = Advancement.Builder.advancement()
        .parent(gotBronzeIngotAdvancement)
        .display(
            MainRegistry.BRONZE_CHESTPLATE.get(),
            Component.translatable("advancement.bronze.got_bronze_chestplate.title"),
            Component.translatable("advancement.bronze.got_bronze_chestplate.description"),
            null,
            AdvancementType.TASK,
            true,
            true,
            false
        )
        .addCriterion("got_bronze_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_HELMET.get()))
        .addCriterion("got_bronze_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_CHESTPLATE.get()))
        .addCriterion("got_bronze_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_LEGGINGS.get()))
        .addCriterion("got_bronze_boots", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_BOOTS.get()))
        .build(ID("bronze/got_bronze_chestplate"));

    advancementConsumer.accept(gotBronzeChestplateAdvancement);

    AdvancementHolder gotSickleAdvancement = Advancement.Builder.advancement()
        .parent(gotBronzeIngotAdvancement)
        .display(
            MainRegistry.SICKLE.get(),
            Component.translatable("advancement.bronze.got_sickle.title"),
            Component.translatable("advancement.bronze.got_sickle.description"),
            null,
            AdvancementType.TASK,
            true,
            true,
            false
        )
        .addCriterion("got_sickle", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.SICKLE.get()))
        .build(ID("bronze/got_sickle"));

    advancementConsumer.accept(gotSickleAdvancement);

    AdvancementHolder gotBronzePickaxeAdvancement = Advancement.Builder.advancement()
        .parent(gotBronzeIngotAdvancement)
        .display(
            MainRegistry.BRONZE_PICKAXE.get(),
            Component.translatable("advancement.bronze.got_bronze_pickaxe.title"),
            Component.translatable("advancement.bronze.got_bronze_pickaxe.description"),
            null,
            AdvancementType.TASK,
            true,
            true,
            false
        )
        .addCriterion("got_bronze_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_PICKAXE.get()))
        .build(ID("bronze/got_bronze_pickaxe"));

    advancementConsumer.accept(gotBronzePickaxeAdvancement);

    /* Recipe unlock advancements */

    AdvancementHolder unlockBronzeBlendFromRawTin = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_raw_tin", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.RAW_TIN.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/bronze_blend_from_copper_and_tin"))
            .addRecipe(ID("crafting/raw_tin_block"))
            .addRecipe(ID("crafting/tin_ingot"))
            .addRecipe(ID("smelting/tin_ingot_from_smelting_deepslate_tin_ore"))
            .addRecipe(ID("smelting/tin_ingot_from_smelting_raw_tin"))
            .addRecipe(ID("smelting/tin_ingot_from_smelting_tin_ore"))
            .addRecipe(ID("smelting/tin_ingot_from_blasting_deepslate_tin_ore"))
            .addRecipe(ID("smelting/tin_ingot_from_blasting_raw_tin"))
            .addRecipe(ID("smelting/tin_ingot_from_blasting_tin_ore")))
        .build(ID("recipes/got_raw_tin"));

    advancementConsumer.accept(unlockBronzeBlendFromRawTin);

    AdvancementHolder unlockRawTinFromRawTinBlock = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_raw_tin_block", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.RAW_TIN_BLOCK.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/raw_tin")))
        .build(ID("recipes/got_raw_tin_block"));

    advancementConsumer.accept(unlockRawTinFromRawTinBlock);

    AdvancementHolder unlockBronzeBlendFromBronzeBlendBlock = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_bronze_blend_block", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_BLEND_BLOCK.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/bronze_blend")))
        .build(ID("recipes/got_bronze_blend_block"));

    advancementConsumer.accept(unlockBronzeBlendFromBronzeBlendBlock);

    AdvancementHolder unlockBronzeIngotFromBronzeBlock = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_bronze_block", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_BLOCK.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/bronze_ingot")))
        .build(ID("recipes/got_bronze_block"));

    advancementConsumer.accept(unlockBronzeIngotFromBronzeBlock);

    AdvancementHolder unlockBronzeIngotFromBronzeNugget = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_bronze_nuggets", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.BRONZE_NUGGET.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/bronze_ingot_from_nuggets")))
        .build(ID("recipes/got_bronze_nuggets"));

    advancementConsumer.accept(unlockBronzeIngotFromBronzeNugget);

    AdvancementHolder unlockTinIngotFromTinNugget = Advancement.Builder.recipeAdvancement()
        .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
        .addCriterion("got_tin_nuggets", InventoryChangeTrigger.TriggerInstance.hasItems(MainRegistry.TIN_NUGGET.get()))
        .rewards(AdvancementRewards.Builder.recipe(ID("crafting/tin_ingot_from_nuggets")))
        .build(ID("recipes/got_tin_nuggets"));

    advancementConsumer.accept(unlockTinIngotFromTinNugget);
  }
}
