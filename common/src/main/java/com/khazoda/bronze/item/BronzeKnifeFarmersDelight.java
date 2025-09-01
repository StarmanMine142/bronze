package com.khazoda.bronze.item;

import com.khazoda.bronze.material.BronzeToolMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BronzeKnifeFarmersDelight extends DiggerItem {
  public static final TagKey<Item> KNIVES = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));
  public static final TagKey<Block> MINEABLE_WITH_KNIFE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("farmersdelight", "mineable/knife"));

  public BronzeKnifeFarmersDelight(Tier tier) {
    super(tier, MINEABLE_WITH_KNIFE, getItemSettings());
  }

  private static Properties getItemSettings() {
    return (new Properties()).attributes(DiggerItem.createAttributes(BronzeToolMaterial.INSTANCE, 0.5F, -2F));
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    Level level = context.getLevel();
    ItemStack toolStack = context.getItemInHand();
    BlockPos pos = context.getClickedPos();
    BlockState state = level.getBlockState(pos);
    Direction facing = context.getClickedFace();

    System.out.println(toolStack.is(KNIVES));
    if (state.getBlock() == Blocks.PUMPKIN && toolStack.is(KNIVES)) {
      Player player = context.getPlayer();
      if (player != null && !level.isClientSide) {
        Direction direction = facing.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : facing;
        level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.setBlock(pos, Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, direction), 11);
        ItemEntity itemEntity = new ItemEntity(level, (double) pos.getX() + 0.5D + (double) direction.getStepX() * 0.65D, (double) pos.getY() + 0.1D, (double) pos.getZ() + 0.5D + (double) direction.getStepZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));
        itemEntity.setDeltaMovement(0.05D * (double) direction.getStepX() + level.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) direction.getStepZ() + level.random.nextDouble() * 0.02D);
        level.addFreshEntity(itemEntity);
        toolStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
      }
      return InteractionResult.sidedSuccess(level.isClientSide);
    } else {
      return InteractionResult.PASS;
    }
  }
}
