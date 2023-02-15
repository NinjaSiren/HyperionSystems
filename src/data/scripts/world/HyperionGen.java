package data.scripts.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import com.fs.starfarer.api.util.Misc;
import data.scripts.world.procgen.HS_Randomizer;
import data.scripts.world.systems.Base_Penelope;
import data.scripts.world.systems.HS_Diamant;
import data.scripts.world.systems.HS_Klat;
import data.scripts.world.systems.HS_Neue_Jangala;
import data.scripts.world.systems.HS_Nirraok;
import data.scripts.world.systems.HS_Phia;

/**
 *
 * @author NinjaSiren
 */
public class HyperionGen implements SectorGeneratorPlugin {

    public static void initFactionRelationships(SectorAPI sector) {
        FactionAPI hyperion = sector.getFaction("HS_Corporation_Separatist"); 
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
        FactionAPI league = sector.getFaction(Factions.PERSEAN);
        FactionAPI remnants = sector.getFaction(Factions.REMNANTS);
        FactionAPI neutral = sector.getFaction(Factions.NEUTRAL);
        
        //Vanilla factions
        hyperion.setRelationship(hegemony.getId(), RepLevel.INHOSPITABLE);
        hyperion.setRelationship(tritachyon.getId(), RepLevel.SUSPICIOUS);
        hyperion.setRelationship(pirates.getId(), RepLevel.VENGEFUL);
        hyperion.setRelationship(independent.getId(), RepLevel.COOPERATIVE);
        hyperion.setRelationship(kol.getId(), RepLevel.SUSPICIOUS);
        hyperion.setRelationship(church.getId(), RepLevel.NEUTRAL);
        hyperion.setRelationship(path.getId(), RepLevel.VENGEFUL);
        hyperion.setRelationship(diktat.getId(), RepLevel.FAVORABLE);
        hyperion.setRelationship(league.getId(), RepLevel.FAVORABLE);
        hyperion.setRelationship(remnants.getId(), RepLevel.HOSTILE);
        hyperion.setRelationship(neutral.getId(), RepLevel.NEUTRAL);
        
        //Custom factions
        new CustomFactionsRelations(hyperion);
    }
    
    @Override
    public void generate(SectorAPI sector) {
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("HS_Corporation_Separatist");
        initFactionRelationships(sector);
        
        // Main World
        new HS_Neue_Jangala().generate(sector); // Neue Jangala
        
        // Outer Worlds
        new HS_Phia().generate(sector); // Phia
        new HS_Diamant().generate(sector); // Diamant
        new HS_Klat().generate(sector); // Klat
        new HS_Nirraok().generate(sector); // Nirraok
        
        if(new HS_Randomizer().randFixed() <= 0.5) {
            new Base_Penelope().generate(sector); // Penelope's star
        }
        
        // All planets of ths mod's star systems are known
        Misc.setAllPlanetsKnown("Phia");
        Misc.setAllPlanetsKnown("Diamant");
        Misc.setAllPlanetsKnown("Klat");
        Misc.setAllPlanetsKnown("Nirraok");
        Misc.setAllPlanetsSurveyed(sector.getStarSystem("Phia"), true);
        Misc.setAllPlanetsSurveyed(sector.getStarSystem("Diamant"), true);
        Misc.setAllPlanetsSurveyed(sector.getStarSystem("Klat"), true);
        Misc.setAllPlanetsSurveyed(sector.getStarSystem("Nirraok"), true);
    }
}
