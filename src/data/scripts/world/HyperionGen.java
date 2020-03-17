package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.scripts.world.systems.Base_Penelope;

import data.scripts.world.systems.DME_Kostroma;
import data.scripts.world.systems.HS_Neue_Jangala;
import data.scripts.world.systems.HyperionRandomizer;
import java.util.ArrayList;
import java.util.Random;

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
        hyperion.setRelationship(hegemony.getId(), RepLevel.HOSTILE);
        hyperion.setRelationship(tritachyon.getId(), RepLevel.FAVORABLE);
        hyperion.setRelationship(pirates.getId(), RepLevel.VENGEFUL);
        hyperion.setRelationship(independent.getId(), RepLevel.FRIENDLY);
        hyperion.setRelationship(kol.getId(), RepLevel.SUSPICIOUS);
        hyperion.setRelationship(church.getId(), RepLevel.NEUTRAL);
        hyperion.setRelationship(path.getId(), RepLevel.VENGEFUL);
        hyperion.setRelationship(diktat.getId(), RepLevel.FAVORABLE);
        hyperion.setRelationship(league.getId(), RepLevel.NEUTRAL);
        hyperion.setRelationship(remnants.getId(), RepLevel.HOSTILE);
        hyperion.setRelationship(neutral.getId(), RepLevel.NEUTRAL);
        
        //Custom factions
        hyperion.setRelationship("dassault_mikoyan", RepLevel.FAVORABLE);
        hyperion.setRelationship("6eme_bureau", RepLevel.NEUTRAL);
        hyperion.setRelationship("the_deserter", RepLevel.HOSTILE);
        hyperion.setRelationship("blade_breakers", RepLevel.HOSTILE);
        hyperion.setRelationship("shadow_industry", RepLevel.HOSTILE);
        hyperion.setRelationship("blackrock_driveyards", RepLevel.INHOSPITABLE);
        hyperion.setRelationship("exigency", RepLevel.HOSTILE);
        hyperion.setRelationship("tiandong", RepLevel.INHOSPITABLE);
        hyperion.setRelationship("diableavionics", RepLevel.HOSTILE);
        hyperion.setRelationship("ORA", RepLevel.SUSPICIOUS);
        hyperion.setRelationship("SCY", RepLevel.NEUTRAL);
        hyperion.setRelationship("neutrinocorp", RepLevel.INHOSPITABLE);
        hyperion.setRelationship("interstellarimperium", RepLevel.HOSTILE);
        hyperion.setRelationship("syndicate_asp", RepLevel.INHOSPITABLE);
        hyperion.setRelationship("pack", RepLevel.INHOSPITABLE);
        hyperion.setRelationship("junk_pirates", RepLevel.HOSTILE);
        hyperion.setRelationship("fob", RepLevel.HOSTILE);
        hyperion.setRelationship("sylphon", RepLevel.HOSTILE);
        hyperion.setRelationship("nullorder", RepLevel.HOSTILE);
        hyperion.setRelationship("Coalition", RepLevel.HOSTILE);
        hyperion.setRelationship("vass_shipyards", RepLevel.HOSTILE);
        hyperion.setRelationship("nomads", RepLevel.HOSTILE);
        hyperion.setRelationship("crystanite", RepLevel.HOSTILE);
        hyperion.setRelationship("sad", RepLevel.HOSTILE);
        hyperion.setRelationship("new_galactic_order", RepLevel.VENGEFUL);
    }
    
    @Override
    public void generate(SectorAPI sector) {
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("HS_Corporation_Separatist");
        initFactionRelationships(sector);
        new HS_Neue_Jangala().generate(sector);
        
        // Adds Penelope Star System under the Hyperion Confederacy
        new Base_Penelope().generate(sector);
        
        // Adds Marie-Galante planet under the Hyperion Confederacy
        boolean hasDME = Global.getSettings().getModManager().isModEnabled("Dassault-Mikoyan Engineering");
        if(hasDME) {
            new DME_Kostroma().generate(sector);
        }
    }
}
