package org.vrealms.dungfactory.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockFertileDirt extends BlockDungFactory {
    public BlockFertileDirt() {
        super(Material.ground);
        this.setBlockName("fertiledirt");
    }
}