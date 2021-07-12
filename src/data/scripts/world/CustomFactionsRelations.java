/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;

/**
 *
 * @author NinjaSiren
 */
public class CustomFactionsRelations {
    
    public CustomFactionsRelations(FactionAPI hyperion) {
        hyperion.setRelationship("dassault_mikoyan", RepLevel.NEUTRAL);
        hyperion.setRelationship("6eme_bureau", RepLevel.HOSTILE);
        hyperion.setRelationship("the_deserter", RepLevel.NEUTRAL);
        hyperion.setRelationship("blade_breakers", RepLevel.FAVORABLE);
        hyperion.setRelationship("shadow_industry", RepLevel.COOPERATIVE);
        hyperion.setRelationship("blackrock_driveyards", RepLevel.INHOSPITABLE);
        hyperion.setRelationship("exigency", RepLevel.HOSTILE);
        hyperion.setRelationship("tiandong", RepLevel.SUSPICIOUS);
        hyperion.setRelationship("diableavionics", RepLevel.FRIENDLY);
        hyperion.setRelationship("ORA", RepLevel.SUSPICIOUS);
        hyperion.setRelationship("SCY", RepLevel.NEUTRAL);
        hyperion.setRelationship("neutrinocorp", RepLevel.SUSPICIOUS);
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
        hyperion.setRelationship("redlegion", RepLevel.COOPERATIVE);
        hyperion.setRelationship("MVS", RepLevel.FAVORABLE);
        hyperion.setRelationship("HMI", RepLevel.FAVORABLE);
        hyperion.setRelationship("hmi_nightmare", RepLevel.SUSPICIOUS);
        hyperion.setRelationship("domres", RepLevel.NEUTRAL);
        hyperion.setRelationship("VAO", RepLevel.NEUTRAL);
    }
    
}
