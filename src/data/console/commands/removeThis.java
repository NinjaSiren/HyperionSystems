/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.console.commands;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import data.scripts.HyperionModDependencies;
import org.lazywizard.console.BaseCommand;
import static org.lazywizard.console.CommandUtils.*;
import org.lazywizard.console.CommonStrings;
import org.lazywizard.console.Console;

/**
 *
 * @author NinjaSiren
 * 
 * Cheat commands for industry items of mods and base game.
 * 
 * Remove this on final builds
 * 
 */
public class removeThis implements BaseCommand {
    
    @Override
    public BaseCommand.CommandResult runCommand(String args, BaseCommand.CommandContext context) {
        
        if(new HyperionModDependencies().hasConsoleCommands() && Global.getSettings().getBoolean("enableHiddenCheats")) {  
            if (!context.isInCampaign()) {
                Console.showMessage(CommonStrings.ERROR_CAMPAIGN_ONLY);
                return BaseCommand.CommandResult.WRONG_CONTEXT;
            }
            if (args.isEmpty() || !isInteger(args)) {
                return BaseCommand.CommandResult.BAD_SYNTAX;
            }

            final int amount = Integer.parseInt(args);
            final CargoAPI cargo = Global.getSector().getPlayerFleet().getCargo();

            // DIY Planets special items
            if(new HyperionModDependencies().hasDIYPlanets()) {
                cargo.addSpecial(new SpecialItemData("atmo_mineralizer", "atmo_mineralizer"), amount);
                cargo.addSpecial(new SpecialItemData("atmo_sublimator", "atmo_sublimator"), amount);
                cargo.addSpecial(new SpecialItemData("solar_reflector", "solar_reflector"), amount);
                cargo.addSpecial(new SpecialItemData("tectonic_attenuator", "tectonic_attenuator"), amount);
                cargo.addSpecial(new SpecialItemData("weather_core", "weather_core"), amount);
                cargo.addSpecial(new SpecialItemData("climate_sculptor", "climate_sculptor"), amount);
                cargo.addSpecial(new SpecialItemData("gravity_oscillator", "gravity_oscillator"), amount);
                cargo.addSpecial(new SpecialItemData("rad_remover", "rad_remover"), amount);
            } else {
                Console.showMessage("The mod DIY Planets is not installed or enabled.");
            }

            // Industrial Evolution special items
            if(new HyperionModDependencies().hasIndEvolution()) {
                cargo.addSpecial(new SpecialItemData("IndEvo_log_core", "IndEvo_log_core"), amount);
                cargo.addSpecial(new SpecialItemData("IndEvo_neurals", "IndEvo_neurals"), amount);
                cargo.addSpecial(new SpecialItemData("IndEvo_transmitter", "IndEvo_transmitter"), amount);  
                cargo.addSpecial(new SpecialItemData("IndEvo_simulator", "IndEvo_simulator"), amount);         
            } else {
                Console.showMessage("The mod Industrial Evolution is not installed or enabled.");
            }
            
            // Base stuff special items
            cargo.addSpecial(new SpecialItemData("pristine_nanoforge", "pristine_nanoforge"), amount);
            cargo.addSpecial(new SpecialItemData("synchrotron", "synchrotron"), amount);
            cargo.addSpecial(new SpecialItemData("orbital_fusion_lamp", "orbital_fusion_lamp"), amount);
            cargo.addSpecial(new SpecialItemData("coronal_portal", "coronal_portal"), amount);
            cargo.addSpecial(new SpecialItemData("mantle_bore", "mantle_bore"), amount);
            cargo.addSpecial(new SpecialItemData("catalytic_core", "catalytic_core"), amount);
            cargo.addSpecial(new SpecialItemData("soil_nanites", "soil_nanites"), amount);
            cargo.addSpecial(new SpecialItemData("biofactory_embryo", "biofactory_embryo"), amount);
            cargo.addSpecial(new SpecialItemData("fullerene_spool", "fullerene_spool"), amount);
            cargo.addSpecial(new SpecialItemData("plasma_dynamo", "plasma_dynamo"), amount);
            cargo.addSpecial(new SpecialItemData("cryoarithmetic_engine", "cryoarithmetic_engine"), amount);
            cargo.addSpecial(new SpecialItemData("dealmaker_holosuite", "dealmaker_holosuite"), amount);
            cargo.addSpecial(new SpecialItemData("drone_replicator", "drone_replicator"), amount);

            // AI Cores
            cargo.addCommodity("alpha_core", amount);
            cargo.addCommodity("beta_core", amount);
            cargo.addCommodity("gamma_core", amount);
            
            Console.showMessage("Added " + format(amount) + " of all planetary enhancements in your inventory.");
            return BaseCommand.CommandResult.SUCCESS;
        }
        
        else {
            return null;
        }
    }
}
