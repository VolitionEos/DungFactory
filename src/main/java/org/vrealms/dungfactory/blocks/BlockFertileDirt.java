package org.vrealms.dungfactory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.vrealms.dungfactory.creativetab.CreativeTabDungFactory;
import org.vrealms.dungfactory.utility.LogHelper;

public class BlockFertileDirt extends BlockDungFactory
{
    @SideOnly(Side.CLIENT)
    private IIcon blockTop;

    public BlockFertileDirt()
    {
        super(Material.ground);
        this.setBlockName("fertiledirt");
        this.setHardness(0.5F);
        this.setCreativeTab(CreativeTabDungFactory.DUNGFACTORY_TAB);
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
}