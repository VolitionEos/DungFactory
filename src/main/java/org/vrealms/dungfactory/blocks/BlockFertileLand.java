package org.vrealms.dungfactory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import org.vrealms.dungfactory.init.ModBlocks;
import org.vrealms.dungfactory.utility.LogHelper;
import org.vrealms.dungfactory.utility.StableRandom;
import org.vrealms.dungfactory.utility.StemRandom;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.UP;

public class BlockFertileLand extends BlockDungFactory
{
    @SideOnly(Side.CLIENT)
    private IIcon Top_Wet;
    @SideOnly(Side.CLIENT)
    private IIcon Top_Dry;
    private StemRandom s_rnd;
    private StableRandom rnd;

    public BlockFertileLand()
    {
        super(Material.ground);
        this.setBlockName("fertileland");
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setHardness(0.5F);
        this.setLightOpacity(255);
        this.s_rnd = new StemRandom();
        this.rnd = new StableRandom();
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return AxisAlignedBB.getBoundingBox((double)(p_149668_2_ + 0), (double)(p_149668_3_ + 0), (double)(p_149668_4_ + 0), (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!this.func_149821_m(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z))
        {
            int l = world.getBlockMetadata(x, y, z);

            if (l > 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, l - 1, 2);
            }
            else if (!this.func_149822_e(world, x, y, z))
            {
                world.setBlock(x, y, z, ModBlocks.fertiledirt);
            }
        }
        else
        {
            world.setBlockMetadataWithNotify(x, y, z, 7, 2);
        }

        // Grow it faster -----Bellow here-----
        if (!world.blockExists(x, y + 1, z))
            return;

        Block plant_block = world.getBlock(x, y + 1, z);

        if (plant_block instanceof BlockStem)
        {
            // Mellon's need randomising of the grown block's placement
            if (world.getBlockMetadata(x, y + 1, z) >= 7)
                plant_block.updateTick(world, x, y + 1, z, this.s_rnd);
            else
                plant_block.updateTick(world, x, y + 1, z, this.rnd);
        }
        else if (plant_block instanceof IPlantable)
        {
            plant_block.updateTick(world, x, y + 1, z, this.rnd);
        }
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_)
    {
        if (!p_149746_1_.isRemote && p_149746_1_.rand.nextFloat() < p_149746_6_ - 0.5F)
        {
            if (!(p_149746_5_ instanceof EntityPlayer) && !p_149746_1_.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            p_149746_1_.setBlock(p_149746_2_, p_149746_3_, p_149746_4_, ModBlocks.fertiledirt);
        }
    }

    private boolean func_149822_e(World p_149822_1_, int p_149822_2_, int p_149822_3_, int p_149822_4_)
    {
        byte b0 = 0;

        for (int l = p_149822_2_ - b0; l <= p_149822_2_ + b0; ++l)
        {
            for (int i1 = p_149822_4_ - b0; i1 <= p_149822_4_ + b0; ++i1)
            {
                Block block = p_149822_1_.getBlock(l, p_149822_3_ + 1, i1);

                if (block instanceof IPlantable && canSustainPlant(p_149822_1_, p_149822_2_, p_149822_3_, p_149822_4_, ForgeDirection.UP, (IPlantable)block))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean func_149821_m(World p_149821_1_, int p_149821_2_, int p_149821_3_, int p_149821_4_)
    {
        for (int l = p_149821_2_ - 4; l <= p_149821_2_ + 4; ++l)
        {
            for (int i1 = p_149821_3_; i1 <= p_149821_3_ + 1; ++i1)
            {
                for (int j1 = p_149821_4_ - 4; j1 <= p_149821_4_ + 4; ++j1)
                {
                    if (p_149821_1_.getBlock(l, i1, j1).getMaterial() == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        Material material = p_149695_1_.getBlock(p_149695_2_, p_149695_3_ + 1, p_149695_4_).getMaterial();

        if (material.isSolid())
        {
            p_149695_1_.setBlock(p_149695_2_, p_149695_3_, p_149695_4_, ModBlocks.fertiledirt);
        }
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ModBlocks.fertiledirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(ModBlocks.fertiledirt);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.Top_Wet = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_wet"));
        this.Top_Dry = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_dry"));
    }

    //@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? (meta > 0 ? this.Top_Wet : this.Top_Dry) : ModBlocks.fertiledirt.getBlockTextureFromSide(side);
    }

    /*
    @SideOnly(Side.CLIENT)
    public IIcon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        return par5 == 1 ? this.Top_Wet : (par5 == 0 ? this.Top_Wet : (par5 != meta ? this.Top_Wet : this.Top_Dry));
    }
    */

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

        if (plant == Blocks.reeds && this == ModBlocks.fertileland)
        {
            return false;
        }

        switch (plantType)
        {
            case Crop:   return this == ModBlocks.fertileland;
            case Plains: return this == ModBlocks.fertileland;
            case Water:  return world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0;
            case Beach:
                boolean isBeach = this == ModBlocks.fertileland;
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
        if (this == ModBlocks.fertileland)
        {
            world.setBlock(x, y, z, ModBlocks.fertiledirt, 0, 2);
        }
    }

    /**
     * Checks if this soil is fertile, typically this means that growth rates
     * of plants on this soil will be slightly sped up.
     * Only vanilla case is tilledField when it is within range of water.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z position
     * @return True if the soil should be considered fertile.
     */
    @Override
    public boolean isFertile(World world, int x, int y, int z)
    {
        if (this == ModBlocks.fertileland)
        {
            return world.getBlockMetadata(x, y, z) > 0;
        }

        return false;
    }

}