package com.gmail.nossr50.datatypes.skills.behaviours;

import com.gmail.nossr50.core.MetadataConstants;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.skills.herbalism.HerbalismManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;

import java.util.HashSet;

/**
 * These behaviour classes are a band-aid fix for a larger problem
 * Until the new skill system for mcMMO is finished/implemented, there is no good place to store the hardcoded behaviours for each skill
 * These behaviour classes server this purpose, they act as a bad solution to a bad problem
 * These classes will be removed when the new skill system is in place
 */
@Deprecated
public class HerbalismBehaviour {

    private final mcMMO pluginRef;

    public HerbalismBehaviour(mcMMO pluginRef) {
        this.pluginRef = pluginRef;
    }

    /**
     * Convert blocks affected by the Green Thumb & Green Terra abilities.
     *
     * @param blockState The {@link BlockState} to check ability activation for
     * @return true if the ability was successful, false otherwise
     */
    public boolean convertGreenTerraBlocks(BlockState blockState) {
        switch (blockState.getType()) {
            case COBBLESTONE_WALL:
                blockState.setType(Material.MOSSY_COBBLESTONE_WALL);
                return true;

            case STONE_BRICKS:
                blockState.setType(Material.MOSSY_STONE_BRICKS);
                return true;

            case DIRT:
            case GRASS_PATH:
                blockState.setType(Material.GRASS_BLOCK);
                return true;

            case COBBLESTONE:
                blockState.setType(Material.MOSSY_COBBLESTONE);
                return true;

            default:
                return false;
        }
    }

    private int calculateChorusPlantDrops(Block target, boolean triple, HerbalismManager herbalismManager) {
        return calculateChorusPlantDropsRecursive(target, new HashSet<>(), triple, herbalismManager);
    }

    private int calculateChorusPlantDropsRecursive(Block target, HashSet<Block> traversed, boolean triple, HerbalismManager herbalismManager) {
        if (target.getType() != Material.CHORUS_PLANT)
            return 0;

        // Prevent any infinite loops, who needs more than 64 chorus anyways
        if (traversed.size() > 64)
            return 0;

        if (!traversed.add(target))
            return 0;

        int dropAmount = 0;

        if (pluginRef.getPlaceStore().isTrue(target))
            pluginRef.getPlaceStore().setFalse(target);
        else {
            dropAmount++;

            if (herbalismManager.checkDoubleDrop(target.getState()))
                pluginRef.getBlockTools().markDropsAsBonus(target.getState(), triple);
        }

        for (BlockFace blockFace : new BlockFace[]{BlockFace.UP, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST})
            dropAmount += calculateChorusPlantDropsRecursive(target.getRelative(blockFace, 1), traversed, triple, herbalismManager);

        return dropAmount;
    }

    /**
     * Calculate the drop amounts for multi block plants based on the blocks
     * relative to them.
     *
     * @param blockState The {@link BlockState} of the bottom block of the plant
     * @return the number of bonus drops to award from the blocks in this plant
     */
    public int countAndMarkDoubleDropsMultiBlockPlant(BlockState blockState, boolean triple, HerbalismManager herbalismManager) {
        Block block = blockState.getBlock();
        Material blockType = blockState.getType();
        int dropAmount = 0;
        int bonusDropAmount = 0;
        int bonusAdd = triple ? 2 : 1;

        if (blockType == Material.CHORUS_PLANT) {
            dropAmount = 1;

            if (block.getRelative(BlockFace.DOWN, 1).getType() == Material.END_STONE) {
                dropAmount = calculateChorusPlantDrops(block, triple, herbalismManager);
            }
        } else {
            //Check the block itself first
            if (!pluginRef.getPlaceStore().isTrue(block)) {
                dropAmount++;

                if (herbalismManager.checkDoubleDrop(blockState))
                    bonusDropAmount += bonusAdd;
            } else {
                pluginRef.getPlaceStore().setFalse(blockState);
            }

            // Handle the two blocks above it - cacti & sugar cane can only grow 3 high naturally
            for (int y = 1; y < 255; y++) {
                Block relativeBlock = block.getRelative(BlockFace.UP, y);

                if (relativeBlock.getType() != blockType) {
                    break;
                }

                if (pluginRef.getPlaceStore().isTrue(relativeBlock)) {
                    pluginRef.getPlaceStore().setFalse(relativeBlock);
                } else {
                    dropAmount++;

                    if (herbalismManager.checkDoubleDrop(relativeBlock.getState()))
                        bonusDropAmount += bonusAdd;
                }
            }
        }

        //Mark the original block for bonus drops
        pluginRef.getBlockTools().markDropsAsBonus(blockState, bonusDropAmount);

        return dropAmount;
    }

    /**
     * Calculate the drop amounts for kelp plants based on the blocks
     * relative to them.
     *
     * @param blockState The {@link BlockState} of the bottom block of the plant
     * @return the number of bonus drops to award from the blocks in this plant
     */
    public int countAndMarkDoubleDropsKelp(BlockState blockState, boolean triple, HerbalismManager herbalismManager) {
        Block block = blockState.getBlock();

        int kelpMaxHeight = 255;
        int amount = 1;

        // Handle the two blocks above it - cacti & sugar cane can only grow 3 high naturally
        for (int y = 1; y < kelpMaxHeight; y++) {
            Block relativeUpBlock = block.getRelative(BlockFace.UP, y);

            if (!isKelp(relativeUpBlock))
                break;

            amount += 1;

            if (herbalismManager.checkDoubleDrop(relativeUpBlock.getState()))
                pluginRef.getBlockTools().markDropsAsBonus(relativeUpBlock.getState(), triple);

        }

        return amount;
    }

    private int addKelpDrops(int dropAmount, Block relativeBlock) {
        if (isKelp(relativeBlock) && !pluginRef.getPlaceStore().isTrue(relativeBlock)) {
            dropAmount++;
        } else {
            pluginRef.getPlaceStore().setFalse(relativeBlock);
        }

        return dropAmount;
    }

    private boolean isKelp(Block relativeBlock) {
        Material kelptype_1 = Material.KELP_PLANT;
        Material kelptype_2 = Material.KELP;

        return relativeBlock.getType() == kelptype_1 || relativeBlock.getType() == kelptype_2;
    }

    /**
     * Convert blocks affected by the Green Thumb & Green Terra abilities.
     *
     * @param blockState The {@link BlockState} to check ability activation for
     * @return true if the ability was successful, false otherwise
     */
    public boolean convertShroomThumb(BlockState blockState) {
        switch (blockState.getType()) {
            case DIRT:
            case GRASS_BLOCK:
            case GRASS_PATH:
                blockState.setType(Material.MYCELIUM);
                return true;

            default:
                return false;
        }
    }

    /**
     * Check if the block has a recently grown crop from Green Thumb
     *
     * @param blockState The {@link BlockState} to check green thumb regrown for
     * @return true if the block is recently regrown, false otherwise
     */
    public boolean isRecentlyRegrown(BlockState blockState) {
        return blockState.hasMetadata(MetadataConstants.GREEN_THUMB_METAKEY) && !pluginRef.getSkillTools().cooldownExpired(blockState.getMetadata(MetadataConstants.GREEN_THUMB_METAKEY).get(0).asInt(), 1);
    }
}