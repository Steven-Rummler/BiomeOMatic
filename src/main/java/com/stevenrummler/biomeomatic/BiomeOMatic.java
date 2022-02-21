package com.stevenrummler.biomeomatic;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BiomeOMatic implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String M = "biomeomatic";

	public static final Logger LOGGER = LoggerFactory.getLogger(M);

	public static final UIBlock UIBLOCK = new UIBlock(FabricBlockSettings.of(Material.WOOD));

	public static final RegistryKey<World> BADLANDS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "badlands"));
	public static final RegistryKey<World> BAMBOO_JUNGLE = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "bamboo_jungle"));
	public static final RegistryKey<World> BEACH = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "beach"));
	public static final RegistryKey<World> BIRCH_FOREST = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "birch_forest"));
	public static final RegistryKey<World> COLD_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "cold_ocean"));
	public static final RegistryKey<World> DARK_FOREST = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "dark_forest"));
	public static final RegistryKey<World> DEEP_COLD_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "deep_cold_ocean"));
	public static final RegistryKey<World> DEEP_FROZEN_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "deep_frozen_ocean"));
	public static final RegistryKey<World> DEEP_LUKEWARM_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "deep_lukewarm_ocean"));
	public static final RegistryKey<World> DEEP_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "deep_ocean"));
	public static final RegistryKey<World> DESERT = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "desert"));
	public static final RegistryKey<World> ERODED_BADLANDS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "eroded_badlands"));
	public static final RegistryKey<World> FLOWER_FOREST = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "flower_forest"));
	public static final RegistryKey<World> FOREST = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "forest"));
	public static final RegistryKey<World> FROZEN_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "frozen_ocean"));
	public static final RegistryKey<World> FROZEN_PEAKS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "frozen_peaks"));
	public static final RegistryKey<World> FROZEN_RIVER = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "frozen_river"));
	public static final RegistryKey<World> GROVE = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "grove"));
	public static final RegistryKey<World> ICE_SPIKES = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "ice_spikes"));
	public static final RegistryKey<World> JAGGED_PEAKS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "jagged_peaks"));
	public static final RegistryKey<World> JUNGLE = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "jungle"));
	public static final RegistryKey<World> LUKEWARM_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "lukewarm_ocean"));
	public static final RegistryKey<World> MEADOW = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "meadow"));
	public static final RegistryKey<World> MUSHROOM_FIELDS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "mushroom_fields"));
	public static final RegistryKey<World> OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "ocean"));
	public static final RegistryKey<World> OLD_GROWTH_BIRCH_FOREST = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "old_growth_birch_forest"));
	public static final RegistryKey<World> OLD_GROWTH_PINE_TAIGA = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "old_growth_pine_taiga"));
	public static final RegistryKey<World> OLD_GROWTH_SPRUCE_TAIGA = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "old_growth_spruce_taiga"));
	public static final RegistryKey<World> PLAINS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "plains"));
	public static final RegistryKey<World> RIVER = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "river"));
	public static final RegistryKey<World> SAVANNA = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "savanna"));
	public static final RegistryKey<World> SAVANNA_PLATEAU = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "savanna_plateau"));
	public static final RegistryKey<World> SNOWY_BEACH = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "snowy_beach"));
	public static final RegistryKey<World> SNOWY_PLAINS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "snowy_plains"));
	public static final RegistryKey<World> SNOWY_SLOPES = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "snowy_slopes"));
	public static final RegistryKey<World> SNOWY_TAIGA = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "snowy_taiga"));
	public static final RegistryKey<World> SPARSE_JUNGLE = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "sparse_jungle"));
	public static final RegistryKey<World> STONY_PEAKS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "stony_peaks"));
	public static final RegistryKey<World> STONY_SHORE = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "stony_shore"));
	public static final RegistryKey<World> SUNFLOWER_PLAINS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "sunflower_plains"));
	public static final RegistryKey<World> SWAMP = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "swamp"));
	public static final RegistryKey<World> TAIGA = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "taiga"));
	public static final RegistryKey<World> WARM_OCEAN = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "warm_ocean"));
	public static final RegistryKey<World> WINDSWEPT_FOREST = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "windswept_forest"));
	public static final RegistryKey<World> WINDSWEPT_GRAVELLY_HILLS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "windswept_gravelly_hills"));
	public static final RegistryKey<World> WINDSWEPT_HILLS = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "windswept_hills"));
	public static final RegistryKey<World> WINDSWEPT_SAVANNA = RegistryKey.of(Registry.WORLD_KEY, new Identifier(M, "windswept_savanna"));
	public static final RegistryKey<World> WOODED_BADLANDS = RegistryKey.of(Registry .WORLD_KEY, new Identifier(M, "wooded_badlands"));

	public static final List<RegistryKey<World>> biomes;
	public static final List<RegistryKey<World>> open;
	public static final List<RegistryKey<World>> forest;
	public static final List<RegistryKey<World>> water;
	public static final List<RegistryKey<World>> rocky;
	public static final List<RegistryKey<World>> cold;
	static {
		biomes = new ArrayList<>();
		biomes.add(BADLANDS);
		biomes.add(BAMBOO_JUNGLE);
		biomes.add(BEACH);
		biomes.add(BIRCH_FOREST);
		biomes.add(COLD_OCEAN);
		biomes.add(DARK_FOREST);
		biomes.add(DEEP_COLD_OCEAN);
		biomes.add(DEEP_FROZEN_OCEAN);
		biomes.add(DEEP_LUKEWARM_OCEAN);
		biomes.add(DEEP_OCEAN);
		biomes.add(DESERT);
		biomes.add(ERODED_BADLANDS);
		biomes.add(FLOWER_FOREST);
		biomes.add(FOREST);
		biomes.add(FROZEN_OCEAN);
		biomes.add(FROZEN_PEAKS);
		biomes.add(FROZEN_RIVER);
		biomes.add(GROVE);
		biomes.add(ICE_SPIKES);
		biomes.add(JAGGED_PEAKS);
		biomes.add(JUNGLE);
		biomes.add(LUKEWARM_OCEAN);
		biomes.add(MEADOW);
		biomes.add(MUSHROOM_FIELDS);
		biomes.add(OCEAN);
		biomes.add(OLD_GROWTH_BIRCH_FOREST);
		biomes.add(OLD_GROWTH_PINE_TAIGA);
		biomes.add(OLD_GROWTH_SPRUCE_TAIGA);
		biomes.add(PLAINS);
		biomes.add(RIVER);
		biomes.add(SAVANNA);
		biomes.add(SAVANNA_PLATEAU);
		biomes.add(SNOWY_BEACH);
		biomes.add(SNOWY_PLAINS);
		biomes.add(SNOWY_SLOPES);
		biomes.add(SNOWY_TAIGA);
		biomes.add(SPARSE_JUNGLE);
		biomes.add(STONY_PEAKS);
		biomes.add(STONY_SHORE);
		biomes.add(SUNFLOWER_PLAINS);
		biomes.add(SWAMP);
		biomes.add(TAIGA);
		biomes.add(WARM_OCEAN);
		biomes.add(WINDSWEPT_FOREST);
		biomes.add(WINDSWEPT_GRAVELLY_HILLS);
		biomes.add(WINDSWEPT_HILLS);
		biomes.add(WINDSWEPT_SAVANNA);
		biomes.add(WOODED_BADLANDS);

		open = new ArrayList<>();
		open.add(BADLANDS);
		open.add(DESERT);
		open.add(ERODED_BADLANDS);
		open.add(MEADOW);
		open.add(MUSHROOM_FIELDS);
		open.add(PLAINS);
		open.add(SAVANNA);
		open.add(SNOWY_BEACH);
		open.add(SUNFLOWER_PLAINS);
		open.add(SWAMP);
		open.add(WOODED_BADLANDS);

		forest = new ArrayList<>();
		forest.add(BAMBOO_JUNGLE);
		forest.add(BIRCH_FOREST);
		forest.add(DARK_FOREST);
		forest.add(FLOWER_FOREST);
		forest.add(FOREST);
		forest.add(JUNGLE);
		forest.add(OLD_GROWTH_BIRCH_FOREST);
		forest.add(OLD_GROWTH_PINE_TAIGA);
		forest.add(OLD_GROWTH_SPRUCE_TAIGA);
		forest.add(SPARSE_JUNGLE);
		forest.add(TAIGA);

		water = new ArrayList<>();
		water.add(BEACH);
		water.add(COLD_OCEAN);
		water.add(DEEP_COLD_OCEAN);
		water.add(DEEP_LUKEWARM_OCEAN);
		water.add(DEEP_OCEAN);
		water.add(LUKEWARM_OCEAN);
		water.add(OCEAN);
		water.add(RIVER);
		water.add(WARM_OCEAN);

		rocky = new ArrayList<>();
		rocky.add(JAGGED_PEAKS);
		rocky.add(SAVANNA_PLATEAU);
		rocky.add(STONY_PEAKS);
		rocky.add(STONY_SHORE);
		rocky.add(WINDSWEPT_FOREST);
		rocky.add(WINDSWEPT_GRAVELLY_HILLS);
		rocky.add(WINDSWEPT_HILLS);
		rocky.add(WINDSWEPT_SAVANNA);

		cold = new ArrayList<>();
		cold.add(DEEP_FROZEN_OCEAN);
		cold.add(FROZEN_OCEAN);
		cold.add(FROZEN_PEAKS);
		cold.add(FROZEN_RIVER);
		cold.add(GROVE);
		cold.add(ICE_SPIKES);
		cold.add(SNOWY_PLAINS);
		cold.add(SNOWY_SLOPES);
		cold.add(SNOWY_TAIGA);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Registry.register(Registry.BLOCK, new Identifier(M,"uiblock"), UIBLOCK);

		Registry.register(Registry.ITEM, new Identifier(M, "uiblockitem"), new BlockItem(UIBLOCK, new FabricItemSettings().group(ItemGroup.MISC)));
	}
}
