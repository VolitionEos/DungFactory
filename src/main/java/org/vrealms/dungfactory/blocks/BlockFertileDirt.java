package org.vrealms.dungfactory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockStem;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import org.vrealms.dungfactory.creativetab.CreativeTabDungFactory;
import org.vrealms.dungfactory.init.ModBlocks;
import org.vrealms.dungfactory.utility.LogHelper;
import org.vrealms.dungfactory.utility.StableRandom;

import java.util.Random;

public class BlockFertileDirt extends BlockDungFactory
{
    @SideOnly(Side.CLIENT)
    private IIcon blockTop;
    private StableRandom rnd;

    public BlockFertileDirt()
    {
        super(Material.ground);
        this.setBlockName("fertiledirt");
        this.setTickRandomly(true);
        this.setHardness(0.5F);
        this.setCreativeTab(CreativeTabDungFactory.DUNGFACTORY_TAB);
        this.rnd = new StableRandom();
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        // Grow it faster -----Bellow here-----
        if (!world.blockExists(x, y + 1, z))
            return;

        Block plant_block = world.getBlock(x, y + 1, z);

        if (plant_block instanceof BlockReed)
        {
            for (int l = 1; world.blockExists(x, y + l, z) && l < 5; l++)
            {
                world.getBlock(x, y + l, z).updateTick(world, x, y + l, z, this.rnd);
            }
        } else if (plant_block instanceof IPlantable)
        {
            plant_block.updateTick(world, x, y + 1, z, this.rnd);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_side"));
        this.blockTop = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_top"));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.blockTop : this.blockIcon; //(side == 0 ? Blocks.dirt.getBlockTextureFromSide(side) : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        return par5 == 1 ? this.blockIcon : (par5 == 0 ? this.blockIcon : (par5 != meta ? this.blockIcon : this.blockTop));
    }

    /**
     * Determines if this block can support the passed in plant, allowing it to be planted and grow.
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z position
     * @param direction The direction relative to the given position the plant wants to be, typically its UP
     * @param plantable The plant that wants to check
     * @return True to allow the plant to be planted/stay.
     */
    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);

        if (plant == Blocks.reeds && this == Blocks.reeds)
        {
            return true;
        }

        switch (plantType)
        {
            case Plains: return this == ModBlocks.fertiledirt;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
            case Beach:
                boolean isBeach = this == ModBlocks.fertiledirt;
                boolean hasWater = (world.getBlock(x - 1, y, z    ).getMaterial() == Material.water ||
                        world.getBlock(x + 1, y, z    ).getMaterial() == Material.water ||
                        world.getBlock(x,     y, z - 1).getMaterial() == Material.water ||
                        world.getBlock(x,     y, z + 1).getMaterial() == Material.water);
                return isBeach && hasWater;
        }

        return false;
    }

    /**
     * Called when a plant grows on this block, only implemented for saplings using the WorldGen*Trees classes right now.
     * Modder may implement this for custom plants.
     * This does not use ForgeDirection, because large/huge trees can be located in non-representable direction,
     * so the source location is specified.
     * Currently this just changes the block to dirt if it was grass.
     *
     * Note: This happens DURING the generation, the generation may not be complete when this is called.
     *
     * @param world Current world
     * @param x Soil X
     * @param y Soil Y
     * @param z Soil Z
     * @param sourceX Plant growth location X
     * @param sourceY Plant growth location Y
     * @param sourceZ Plant growth location Z
     */
    @Override
    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        if (this == ModBlocks.fertiledirt)
        {
            world.setBlock(x, y, z, ModBlocks.fertiledirt, 0, 2);
        }
    }
}