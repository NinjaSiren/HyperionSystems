package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.scripts.world.HyperionGen;
//import exerelin.campaign.SectorManager; 
/**
 *
 * @author NinjaSiren
 */
public class HyperionModPlugin extends BaseModPlugin {
    //public static boolean isExerelin = false;
    
    @Override
    public void onNewGame() { 
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("HS_Corporation_Separatist");
        new HyperionGen().generate(Global.getSector());
        /*if(!isExerelin || SectorManager.getCorvusMode()) {
            new HyperionGen().generate(Global.getSector());
        }*/
    }
    
    @Override
    public void onNewGameAfterEconomyLoad() {
	//special admins
        MarketAPI market =  Global.getSector().getEconomy().getMarket("hs_planet_neuejangala");
        if (market != null) {
            PersonAPI person = Global.getFactory().createPerson();
            person.setFaction("HS_Corporation_Separatist");
            person.setGender(FullName.Gender.FEMALE);
            person.setRankId(Ranks.FACTION_LEADER);
            person.setPostId(Ranks.POST_FACTION_LEADER);
            person.getStats().setSkillLevel(Skills.INDUSTRIAL_PLANNING, 3);
            person.getStats().setSkillLevel(Skills.SPACE_OPERATIONS, 3);
            person.getStats().setSkillLevel(Skills.PLANETARY_OPERATIONS, 3);

            market.setAdmin(person);
            market.getCommDirectory().addPerson(person, 0);
            market.addPerson(person);
        }
    }  
    
    @Override
    public void onApplicationLoad() {
        boolean hasLazyLib = Global.getSettings().getModManager().isModEnabled("lw_lazylib");
        boolean hasGraphicsLib = Global.getSettings().getModManager().isModEnabled("shaderLib");
        boolean hasMagicLib = Global.getSettings().getModManager().isModEnabled("MagicLib");
        //isExerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
        
        if(!hasLazyLib) {
            throw new RuntimeException("Hyperion Systems requires LazyLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=5444");
        }
        
        if(!hasGraphicsLib) {
            throw new RuntimeException("Hyperion Systems requires GraphicsLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=10982");
        }
        
        if(!hasMagicLib) {
            throw new RuntimeException("Hyperion Systems requires MagicLib! Where is the magic?" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=13718.0");
        }
    }
}
