package com.khazoda.bronze.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;


import static com.khazoda.bronze.Constants.ID;

public class Sickle extends DiggerItem {
  public static final TagKey<Block> SICKLE_EFFECTIVE_BLOCKS = TagKey.create(Registries.BLOCK, ID("mineable/sickle"));
  public static final TagKey<Block> SICKLE_AOE_BLOCKS = TagKey.create(Registries.BLOCK, ID("sickle_aoe"));

  public Sickle(Tier tier, Properties properties) {
    super(tier, SICKLE_EFFECTIVE_BLOCKS, properties);
  }

  @Override
  public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
    /* Prevent crop blocks from being destroyed */
    return !(state.getBlock() instanceof CropBlock) && !(state.getBlock() instanceof NetherWartBlock);
  }

  @Override
  public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
    if (state.is(SICKLE_AOE_BLOCKS)) {
      aoeMow(level, miningEntity, state, state, pos, 0);
    }

    /* Damage sickle when mining blocks */
    Tool tool = stack.get(DataComponents.TOOL);
    if (tool == null) {
      return false;
    }
    if (!level.isClientSide && state.getDestroySpeed(level, pos) != 0.0f && tool.damagePerBlock() > 0) {
      stack.hurtAndBreak(tool.damagePerBlock(), miningEntity, EquipmentSlot.MAINHAND);
    }
    return true;
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    Player player = context.getPlayer();
    if (level.isClientSide() || player == null) return InteractionResult.PASS;
    ItemStack stack = context.getItemInHand();
    BlockPos pos = context.getClickedPos();
    BlockState state = level.getBlockState(pos);

    if ((state.getBlock() instanceof CropBlock || state.getBlock() instanceof NetherWartBlock) && isMature(state)) {
      level.playSound(null, pos, state.getSoundType().getBreakSound(), SoundSource.BLOCKS, 1.0F, 1.0F);
      player.swing(context.getHand());
      ((ServerLevel) level).sendParticles(ParticleTypes.SWEEP_ATTACK,
              pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
              1, 0, 0, 0, 0);

      stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
      aoeHarvest(level, player, state, state, pos, 0);
      return InteractionResult.SUCCESS;
    }
    return InteractionResult.PASS;
  }

  @Override
  public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    return true;
  }

  private static void aoeMow(Level level, LivingEntity entity, BlockState initialBlockState, BlockState currentBlockState, BlockPos pos, int iteration) {
    if (level.isClientSide()) return;
    /* Cut Grass */
    if (initialBlockState.is(SICKLE_AOE_BLOCKS)) {
      Block currentBlock = currentBlockState.getBlock();
      if (currentBlockState.is(SICKLE_AOE_BLOCKS)) {
        if (entity instanceof Player player) {
          if (level.getBlockState(pos).getBlock() == currentBlock) {
            currentBlock.playerDestroy(level, player, pos, currentBlockState, null, player.getMainHandItem());
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

            // Add block breaking particles
            ((ServerLevel) level).sendParticles(new BlockParticleOption(
                            ParticleTypes.BLOCK, currentBlockState),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    5, 0.2, 0.2, 0.2, 0.1);
          }
        } else {
          Block.dropResources(currentBlockState, level, pos, null, entity, ItemStack.EMPTY);
          level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
      }
      if (iteration < 4) {
        aoeMow(level, entity, initialBlockState, level.getBlockState(pos.east()), pos.east(), iteration + 1);
        aoeMow(level, entity, initialBlockState, level.getBlockState(pos.north()), pos.north(), iteration + 1);
        aoeMow(level, entity, initialBlockState, level.getBlockState(pos.west()), pos.west(), iteration + 1);
        aoeMow(level, entity, initialBlockState, level.getBlockState(pos.south()), pos.south(), iteration + 1);
      }
    }
  }

  private static void aoeHarvest(Level level, LivingEntity entity, BlockState initialBlockState, BlockState currentBlockState, BlockPos pos, int iteration) {
    if (level.isClientSide()) return;
    /* Harvest Crops */
    if ((initialBlockState.getBlock() instanceof CropBlock || initialBlockState.getBlock() instanceof NetherWartBlock)) {
      /* Ascertain the age IntegerProperty of the block */
      IntegerProperty ageProperty = null;
      boolean isMature = switch (initialBlockState.getBlock()) {
        case CropBlock cropBlock -> {
          // Dynamically find the age property
          ageProperty = initialBlockState.getProperties().stream()
                  .filter(p -> p instanceof IntegerProperty)
                  .filter(p -> p.getName().equals("age"))
                  .map(p -> (IntegerProperty) p)
                  .findFirst()
                  .orElse(null);
          if (ageProperty == null) yield false;

          int currentAge = initialBlockState.getValue(ageProperty);
          yield currentAge >= cropBlock.getMaxAge();
        }
        case NetherWartBlock netherWart -> {
          int currentAge = initialBlockState.getValue(NetherWartBlock.AGE);
          ageProperty = NetherWartBlock.AGE;
          yield currentAge >= 3;
        }
        default -> false;
      };
      if (!isMature) return;

      /* Break block, drop drops */
      if (entity instanceof Player player) {
        if (level.getBlockState(pos).getBlock() == currentBlockState.getBlock()) {
          currentBlockState.getBlock().playerDestroy(level, player, pos, currentBlockState, null, player.getMainHandItem());
          ((ServerLevel) level).sendParticles(new BlockParticleOption(
                          ParticleTypes.BLOCK, currentBlockState),
                  pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                  5, 0.2, 0.2, 0.2, 0.1);
          tryExtraHappyLootChance(level, pos);
        }
      } else {
        Block.dropResources(currentBlockState, level, pos, null, entity, ItemStack.EMPTY);
      }
      /* Reset crop to lowest growth stage */
      replantCrop(level, initialBlockState, pos, ageProperty);
      if (iteration < 2) {
        aoeHarvest(level, entity, level.getBlockState(pos.east()), level.getBlockState(pos.east()), pos.east(), iteration + 1);
        aoeHarvest(level, entity, level.getBlockState(pos.north()), level.getBlockState(pos.north()), pos.north(), iteration + 1);
        aoeHarvest(level, entity, level.getBlockState(pos.west()), level.getBlockState(pos.west()), pos.west(), iteration + 1);
        aoeHarvest(level, entity, level.getBlockState(pos.south()), level.getBlockState(pos.south()), pos.south(), iteration + 1);
      }
    }
  }

  private static void tryExtraHappyLootChance(Level level, BlockPos pos) {
    if (!level.isClientSide()) {
      /* 1/50 chance to drop bonemeal when a crop tile is harvested */
      if (level.random.nextInt(50) == 0) {
        int count = level.random.nextInt(4) + 2; // Random number between 2-5
        ItemStack bonemeal = new ItemStack(Items.BONE_MEAL, count);
        Block.popResource(level, pos, bonemeal);

        // Play a happy congratulatory note block sound with random pitch :)
        float randomPitch = 1.0F + level.random.nextFloat() * 0.5F; // Random pitch between 1.0 and 1.5
        level.playSound(null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
                SoundEvents.NOTE_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, randomPitch);
      }
      /* 1/150 chance to drop mob loot */
      if (level.random.nextInt(150) == 0) {
        ItemStack drop;
        // Randomly choose one of the mob drops
        switch (level.random.nextInt(4)) {
          case 0:
            drop = new ItemStack(Items.SPIDER_EYE);
            break;
          case 1:
            drop = new ItemStack(Items.ROTTEN_FLESH);
            break;
          case 2:
            drop = new ItemStack(Items.BONE);
            break;
          default:
            drop = new ItemStack(Items.GUNPOWDER);
            break;
        }
        Block.popResource(level, pos, drop);
        // Play a lower bell sound with random pitch
        float spookyPitch = 0.8F + level.random.nextFloat() * 0.3F; // Random pitch between 0.8 and 1.1
        level.playSound(null, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
                SoundEvents.NOTE_BLOCK_BELL, SoundSource.BLOCKS, 1.0F, spookyPitch);
      }
    }
  }

  private static boolean isMature(BlockState state) {
    if (state.getBlock() instanceof CropBlock cropBlock) {
      return cropBlock.isMaxAge(state);
    } else if (state.getBlock() instanceof NetherWartBlock) {
      return state.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE;
    }
    return false;
  }

  /* 'state' must reference the blockstate before the crop is destroyed */
  private static void replantCrop(Level level, BlockState state, BlockPos pos, IntegerProperty ageProperty) {
    level.setBlock(pos, state.setValue(ageProperty, 0), 3);
  }
}


