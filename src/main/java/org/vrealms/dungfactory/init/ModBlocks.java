package org.vrealms.dungfactory.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import org.vrealms.dungfactory.blocks.BlockDungFactory;
import org.vrealms.dungfactory.blocks.BlockFertileDirt;
import org.vrealms.dungfactory.blocks.BlockFertileLand;
import org.vrealms.dungfactory.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockDungFactory fertiledirt = new BlockFertileDirt();
    public static final BlockDungFactory fertileland = new BlockFertileLand();

    public static void init()
    {
        GameRegistry.registerBlock(fertiledirt, "fertiledirt");
        GameRegistry.registerBlock(fertileland, "fertileland");
    }
}
