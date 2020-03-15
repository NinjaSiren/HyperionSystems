package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import exerelin.campaign.SectorManager;

import data.scripts.world.HyperionGen;
import org.dark.shaders.light.LightData;
import org.dark.shaders.util.ShaderLib;
import org.dark.shaders.util.TextureData;

/**
 *
 * @author NinjaSiren
 */
public class HyperionModPlugin extends BaseModPlugin {
    public static boolean isExerelin = false;
        
    private static void initHyperion() {
        if(isExerelin && !SectorManager.getCorvusMode()) {
            return;
        }
        new HyperionGen().generate(Global.getSector());
    }
    
    @Override
    public void onNewGame() { 
        initHyperion();
    }
    
    @Override
    public void onApplicationLoad() {
        boolean hasLazyLib = Global.getSettings().getModManager().isModEnabled("lw_lazylib");
        boolean hasGraphicsLib = Global.getSettings().getModManager().isModEnabled("shaderLib");
        boolean hasMagicLib = Global.getSettings().getModManager().isModEnabled("MagicLib");
        isExerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        
        if (!hasLazyLib) {
            throw new RuntimeException("Hyperion Systems requires LazyLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=5444");
        }
        
        if (hasGraphicsLib) {
            //ShaderLib.init();
            //LightData.readLightDataCSV("data/lights/istl_light_data.csv");
            //TextureData.readTextureDataCSV("data/lights/istl_texture_data.csv");
        } else {
            throw new RuntimeException("Hyperion Systems requires GraphicsLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=10982");
        }
        
        if (!hasMagicLib) {
            throw new RuntimeException("Hyperion Systems requires MagicLib! Where is the magic?" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=13718.0");
        }
    }
}
