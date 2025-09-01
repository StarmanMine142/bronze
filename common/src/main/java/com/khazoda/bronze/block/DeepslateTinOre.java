package com.khazoda.bronze.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DeepslateTinOre extends Block {
  public DeepslateTinOre() {
    super(BlockBehaviour.Properties.of().strength(3.5f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops());
  }
}
