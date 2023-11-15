package alsender.earthworks.main;

import alsender.earthworks.main.proxy.CommonProxy;
import alsender.earthworks.main.registry.BlockRegistry;
import alsender.earthworks.main.world.ModWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

/**
 * Created by alsender on 12/12/16.
 */
@Mod(modid = Earthworks.MODID, name = Earthworks.MODNAME)

public class Earthworks {

    public static final String MODID = "earthworks";
    public static final String MODNAME = "Earthworks";

    public static final CreativeTabs creativeTab = (new CreativeTabs("earthworks") {

        @Override
		public String getTabLabel() {
            return "earthworks";
        }
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.block_wattle);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }).setBackgroundImageName("item_search.png");

    @Mod.Instance
    public static Earthworks instance;
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Config.init(event.getSuggestedConfigurationFile());
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @SidedProxy(serverSide = "alsender.earthworks.main.proxy.CommonProxy", clientSide = "alsender.earthworks.main.proxy.ClientProxy")
    public static CommonProxy proxy;

}
