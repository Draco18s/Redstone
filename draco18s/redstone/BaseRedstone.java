package draco18s.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="Dracos Redstone", name="Dracos Redstone", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class BaseRedstone {

	@Instance("BaseRedstone")
    public static BaseRedstone instance;
	
	@SidedProxy(clientSide="draco18s.redstone.client.ClientProxy", serverSide="draco18s.redstone.CommonProxy")
    public static CommonProxy proxy;
	
	public static RedstoneFencing redstoneFence;
	public static int rsDecauVal;
	 
	public BaseRedstone() {
		// TODO Auto-generated constructor stub
	}
	
	@PreInit
    public void preInit(FMLPreInitializationEvent event) {
    	//System.out.println("Loadnig Redstone Mod");
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
    	int rsFence = config.getBlock("RedstoneFence", 2454).getInt();
    	Property rsDecay = config.get(Configuration.CATEGORY_GENERAL, "Vertical Decay", 3);
    	rsDecay.comment = "Amount redstone signal should decay by per block (minimum 1).  Default 3.";
    	rsDecauVal = rsDecay.getInt();
    	config.save();

    	redstoneFence = new RedstoneFencing(rsFence, "Redstone:rsfence", Material.wood);
    }
   
    @Init
    public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		//LanguageRegistry.addName(realStoneBlock, "Falling Stone");
		//GameRegistry.registerBlock(realStoneBlock, "StoneFalling");
		//MinecraftForge.setBlockHarvestLevel(realStoneBlock, "pick", 0);
		
		LanguageRegistry.addName(redstoneFence, "Redstone Fence");
		GameRegistry.registerBlock(redstoneFence, "rsFence");
		MinecraftForge.setBlockHarvestLevel(redstoneFence, "axe", 0);
		
		ItemStack fence = new ItemStack(Block.fence);
		ItemStack redstone = new ItemStack(Item.redstone);
		
		GameRegistry.addRecipe(new ItemStack(redstoneFence, 2), " R ", " F ", " R ", 'R', redstone, 'F', fence);
    }
   
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    	//Stub Method
    }
}
