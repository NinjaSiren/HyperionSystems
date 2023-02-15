package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.scripts.world.HyperionGen;
import exerelin.campaign.SectorManager; 
import java.util.logging.Logger;
/**
 *
 * @author NinjaSiren
 */
public class HyperionModPlugin extends BaseModPlugin {
    protected static final Logger Log = Logger.getLogger(HyperionModPlugin.class.getName());
    
    @Override
    public void onNewGame() { 
        
        // Checks if Nexrelin is enabled or not
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("HS_Corporation_Separatist");
        if(!new HyperionModDependencies().isExerelin() || SectorManager.getCorvusMode()) {
            new HyperionGen().generate(Global.getSector());
        }
    } 
    
    @Override
    public void onNewGameAfterEconomyLoad() {
        
        // Add Hyperion Confederate faction leader
        MarketAPI capitalMarket = Global.getSector().getEconomy().getMarket("hs_planet_neuejangala");
        if(capitalMarket != null) {
            PersonAPI factionLeader = Global.getFactory().createPerson();
            factionLeader.setFaction("HS_Corporation_Separatist");
            factionLeader.setGender(FullName.Gender.FEMALE);
            factionLeader.setRankId(Ranks.FACTION_LEADER);
            factionLeader.setPostId(Ranks.POST_FACTION_LEADER);
            factionLeader.setImportance(PersonImportance.VERY_HIGH);
            factionLeader.setId("hyperion_leader");
            factionLeader.setPortraitSprite("graphics/portraits/leaderPortrait.png");
            /*factionLeader.getStats().setSkillLevel(Skills.APT_LEADERSHIP, 3);
            factionLeader.getStats().setSkillLevel(Skills.APT_TECHNOLOGY, 3);
            factionLeader.getStats().setSkillLevel(Skills.APT_INDUSTRY, 3);*/
            factionLeader.setMarket(capitalMarket);
            capitalMarket.setAdmin(factionLeader);
            capitalMarket.getCommDirectory().addPerson(factionLeader, 0);
            capitalMarket.addPerson(factionLeader);
        }
    }
    
    @Override
    public void onApplicationLoad() {
        // Check important dependencies
        if(!new HyperionModDependencies().hasLazyLib()) {
            throw new RuntimeException("Hyperion Systems requires LazyLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=5444");
        }
        
        if(!new HyperionModDependencies().hasGraphicsLib()) {
            throw new RuntimeException("Hyperion Systems requires GraphicsLib!" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=10982");
        }
        
        if(!new HyperionModDependencies().hasMagicLib()) {
            throw new RuntimeException("Hyperion Systems requires MagicLib! Where is the magic?" +
            "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=13718.0");
        }
    }
}
