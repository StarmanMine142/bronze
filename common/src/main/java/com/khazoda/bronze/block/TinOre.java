package com.khazoda.bronze.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TinOre extends Block {
  public TinOre() {
    super(BlockBehaviour.Properties.of().strength(2.5f).sound(SoundType.STONE).requiresCorrectToolForDrops());
  }
}
