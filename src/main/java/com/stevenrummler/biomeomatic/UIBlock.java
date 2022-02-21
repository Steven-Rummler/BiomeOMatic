package com.stevenrummler.biomeomatic;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

public class UIBlock extends Block {
    private enum Status implements StringIdentifiable {
        NONE, OPEN, FOREST, WATER, ROCKY, COLD, BAMBOO, BIRCH, DARK, OAK, JUNGLE, SPRUCE, ACACIA, BADLANDS, DESERT, SWAMP;

        @Override
        public String asString() {
            return this.name();
        }
    }

    public static final EnumProperty<Status> STATUS = EnumProperty.of("status", Status.class);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATUS);
    }

    protected UIBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(STATUS, Status.NONE));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BiomeOMatic.LOGGER.info("Used the thing");
        if (!world.isClient) {
            BiomeOMatic.LOGGER.info("On the server");
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (BiomeOMatic.biomes.contains(world.getRegistryKey())) {
                BiomeOMatic.LOGGER.info("In a custom dimension");
                // We're in a custom dimension, let's go home
                RegistryKey<World> homeDimension = serverPlayer.getSpawnPointDimension();
                BlockPos homePosition = serverPlayer.getSpawnPointPosition();
                if (homeDimension == null || homePosition == null) {
                    homeDimension = ServerWorld.OVERWORLD;
                    homePosition = ((ServerWorld) world).getSpawnPos();
                }
                BiomeOMatic.LOGGER.info("Home is: ", homeDimension, homePosition);
                serverPlayer.teleport(((ServerWorld) world).getServer().getWorld(homeDimension), homePosition.getX(), homePosition.getY(), homePosition.getZ(), player.getYaw(), player.getPitch());
            } else {
                BiomeOMatic.LOGGER.info("In a normal dimension with current target: ", world.getBlockState(pos).get(STATUS));
                // We're in a normal dimension, let's see if we can change the target
                ItemStack item = player.getStackInHand(hand);
                // Groups
                if (item.isOf(Items.DIRT) || item.isOf(Items.SAND) || item.isOf(Items.GRAVEL)) {
                    world.setBlockState(pos, state.with(STATUS, Status.OPEN));
                    item.decrement(1);
                } else if (item.isOf(Items.STICK)) {
                    world.setBlockState(pos, state.with(STATUS, Status.FOREST));
                    item.decrement(1);
                } else if (item.isOf(Items.WATER_BUCKET)) {
                    world.setBlockState(pos, state.with(STATUS, Status.WATER));
                    item.decrement(1);
                    player.giveItemStack(new ItemStack(Items.BUCKET));
                } else if (item.isOf(Items.STONE) || item.isOf(Items.COBBLESTONE)) {
                    world.setBlockState(pos, state.with(STATUS, Status.ROCKY));
                    item.decrement(1);
                } else if (item.isOf(Items.SNOWBALL)) {
                    world.setBlockState(pos, state.with(STATUS, Status.COLD));
                    item.decrement(1);
                } else if (item.isOf(Items.BAMBOO)) {
                    world.setBlockState(pos, state.with(STATUS, Status.BAMBOO));
                    item.decrement(1);
                } else if (item.isOf(Items.BIRCH_SAPLING)) {
                    world.setBlockState(pos, state.with(STATUS, Status.BIRCH));
                    item.decrement(1);
                } else if (item.isOf(Items.DARK_OAK_SAPLING)) {
                    world.setBlockState(pos, state.with(STATUS, Status.DARK));
                    item.decrement(1);
                } else if (item.isOf(Items.OAK_SAPLING)) {
                    world.setBlockState(pos, state.with(STATUS, Status.FOREST));
                    item.decrement(1);
                } else if (item.isOf(Items.SPRUCE_SAPLING)) {
                    world.setBlockState(pos, state.with(STATUS, Status.SPRUCE));
                    item.decrement(1);
                } else if (item.isOf(Items.TERRACOTTA)) {
                    BiomeOMatic.LOGGER.info("It's a cotta!");
                    world.setBlockState(pos, state.with(STATUS, Status.BADLANDS));
                    item.decrement(1);
                } else if (item.isOf(Items.SANDSTONE)) {
                    world.setBlockState(pos, state.with(STATUS, Status.DESERT));
                    item.decrement(1);
                } else if (item.isOf(Items.ACACIA_SAPLING)) {
                    world.setBlockState(pos, state.with(STATUS, Status.ACACIA));
                    item.decrement(1);
                } else if (item.isOf(Items.LILY_PAD)) {
                    world.setBlockState(pos, state.with(STATUS, Status.SWAMP));
                    item.decrement(1);
                } else {
                    BiomeOMatic.LOGGER.info("Couldn't set target, attempting teleport.");
                    // If we can't change the target, let's try to teleport
                    if (world.getBlockState(pos).get(STATUS).equals(Status.NONE)) {
                        BiomeOMatic.LOGGER.info("No target, teleport failed.");
                        return ActionResult.FAIL;
                    } else {
                        BiomeOMatic.LOGGER.info("Target found, teleporting to: ", world.getBlockState(pos).get(STATUS));
                        ServerWorld targetWorld = getDimension(world, pos);

                        if (targetWorld != null && !targetWorld.isClient()) {
                            ServerPlayerEntity serverUser = (ServerPlayerEntity) player;
                            serverUser.teleport(targetWorld, 0, 64, 0, player.getYaw(), player.getPitch());
                        } else {
                            return ActionResult.FAIL;
                        }
                    }
                }
                BiomeOMatic.LOGGER.info("Target set or teleport attempted, target is now: ", world.getBlockState(pos).get(STATUS));
            }
        }
        return ActionResult.SUCCESS;
    }

    private ServerWorld getDimension(World world, BlockPos pos) {
        RegistryKey<World> key;
        Status status = world.getBlockState(pos).get(STATUS);
        switch (status) {
            case NONE: return null;
            case OPEN: key = BiomeOMatic.PLAINS; break;
        }
        return ((ServerWorld) world).getDimension().
    }
}
