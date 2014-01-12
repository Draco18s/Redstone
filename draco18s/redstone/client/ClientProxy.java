package draco18s.redstone.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import draco18s.redstone.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	public static Minecraft minecraft;
	
	public void load() {
		minecraft = ModLoader.getMinecraftInstance();
	}
	@Override
    public void registerRenderers() {
        
    }
	
	@Override
	public World getWorld() {
		return minecraft.theWorld;
	}
}
