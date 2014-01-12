package draco18s.micromods.redstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
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
import draco18s.micromods.redstone.blocks.BlockRainSensor;

@Mod(modid="RedstoneRainsensor", name="RedstoneRainsensor", version="1.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class RainSensorBase {

	@Instance("RedstoneRainsensor")
    public static RainSensorBase instance;
	
	public static BlockRainSensor rainSensor;
	 
	public RainSensorBase() {
	}
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	//System.out.println("Loadnig Redstone Mod");
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	config.load();
	    	int rsRainID = config.getBlock("RainSensor", 2453).getInt();
    	config.save();

    	rainSensor = new BlockRainSensor(rsRainID, Material.circuits);
    }
   
	@EventHandler
    public void init(FMLInitializationEvent event) {
		LanguageRegistry.addName(rainSensor, "Rain Sensor");
		GameRegistry.registerBlock(rainSensor, "rsRain");
		
		GameRegistry.addShapedRecipe(new ItemStack(rainSensor, 1), "G", "Q", "S", 'G', new ItemStack(Block.glass), 'Q', new ItemStack(Item.netherQuartz), 'S', new ItemStack(Block.woodSingleSlab));
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	//Stub Method
    }
}
