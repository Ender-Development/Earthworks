package alsender.earthworks.main.registry;

import alsender.earthworks.main.Config;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import static alsender.earthworks.main.registry.BlockRegistry.*;
import static alsender.earthworks.main.registry.ItemRegistry.*;

/**
 * Created by alsender on 6/20/17.
 */
public class OreDictRegistry {

    public static void init() {
        OreDictionary.registerOre("pileDirt", item_dirt);
        OreDictionary.registerOre("dustDirt", item_dirt);
        OreDictionary.registerOre("pileSand", item_sand);
        OreDictionary.registerOre("dustSand", item_sand);

        OreDictionary.registerOre("logTimber", block_timber_oak);
        OreDictionary.registerOre("logTimber", block_timber_spruce);
        OreDictionary.registerOre("logTimber", block_timber_birch);
        OreDictionary.registerOre("logTimber", block_timber_jungle);
        OreDictionary.registerOre("logTimber", block_timber_acacia);
        OreDictionary.registerOre("logTimber", block_timber_dark_oak);

        if (Config.default_binding) {
            OreDictionary.registerOre("materialBinding", Items.REEDS);
            OreDictionary.registerOre("materialBinding", Items.STRING);
            OreDictionary.registerOre("materialBinding", Items.WHEAT);
            OreDictionary.registerOre("materialBinding", Blocks.CACTUS);
            OreDictionary.registerOre("materialBinding", Blocks.DEADBUSH);
            OreDictionary.registerOre("materialBinding", Blocks.DOUBLE_PLANT);
            OreDictionary.registerOre("materialBinding", Blocks.TALLGRASS);
            OreDictionary.registerOre("materialBinding", Blocks.VINE);
            OreDictionary.registerOre("materialBinding", Blocks.RED_FLOWER);
            OreDictionary.registerOre("materialBinding", Blocks.YELLOW_FLOWER);
            OreDictionary.registerOre("materialBinding", Blocks.WEB);
        }

        for (String item_name: Config.binding) {
            if (item_name.startsWith("ore")) {
                for (ItemStack item: OreDictionary.getOres(item_name.split(":")[1])
                     ) {
                    OreDictionary.registerOre("materialBinding", item);
                }
            } else if (Item.getByNameOrId(item_name) != null) {
                OreDictionary.registerOre("materialBinding",Item.getByNameOrId(item_name));
            }
        }

        if ((!Loader.isModLoaded("quark") || Config.persistantplanks) && (OreDictionary.doesOreNameExist("plankWood")) ){
                OreDictionary.registerOre("plankWood", block_planks_vert);
        }
    }
}
