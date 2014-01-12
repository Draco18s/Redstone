package draco18s.micromods.redstone.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRainSensor extends Block {
    private Icon[] iconArray = new Icon[2];

	public BlockRainSensor(int par1, Material par2Material) {
		super(par1, par2Material);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
        setCreativeTab(CreativeTabs.tabRedstone);
        setUnlocalizedName("Rain Sensor");
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
    }
	
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    }
	
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
		if(world.isRaining()) {
			if(world.isThundering()) {
				world.setBlockMetadataWithNotify(x, y, z, 15, 3);
			}
			else {
				world.setBlockMetadataWithNotify(x, y, z, 7, 3);
			}
		}
		else {
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		}
    	world.scheduleBlockUpdate(x, y, z, blockID, 200);
	}

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {}
    
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    	par1World.scheduleBlockUpdate(par2, par3, par4, blockID, 200);
    }
    
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean canProvidePower()
    {
        return true;
    }
    
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.iconArray[0] : this.iconArray[1];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray[0] = par1IconRegister.registerIcon("Redstone:Rain_top");
        this.iconArray[1] = par1IconRegister.registerIcon("Redstone:Rain_side");
    }
}
