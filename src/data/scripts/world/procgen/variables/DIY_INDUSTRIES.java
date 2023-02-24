/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen.variables;

import com.fs.starfarer.api.campaign.StarSystemAPI;

/**
 *
 * @author NinjaSiren
 */
public class DIY_INDUSTRIES {
    public final String LOBSTER_BREEDING = "lobsterbreeding";
    public final String POLLUTION_REMOVER = "environmentalagency";
    public final String DECIVILIZED_REMOVER_INTEGRATE = "integrationcorps";
    public final String DECIVILIZED_REMOVER_SUBJUGATE = "subjugationcorps";
    public final String ATMOSPHERE_FILTER = "atmospherefilter";
    public final String ATMOSPHERE_REDUCER = "atmospherereducer";
    public final String ATMOSPHERE_PUMP = "atmospherepump";
    public final String COLD_REMOVER = "solarreflector";
    public final String HEAT_REMOVER = "solarshade";
    public final String TECTONIC_REMOVER = "tectonicengine";
    public final String EXTREME_WEATHER_REMOVER = "weatherregulator";
    public final String INIMICAL_REMOVER = "genepurge";
    public final String PERFECT_CLIMATE_TERRAN_WATER_ONLY = "perfectclimate";
    public final String ATMOSPHERE_PUMP_EXPENSIVE = "atmospherepump2";
    public final String STELLAR_MIRROR_EXPENSIVE = "solarreflector2";
    public final String STELLAR_SHADE_EXPENSIVE = "solarshade2";
    public final String TECTONIC_REMOVER_EXPENSIVE = "tectonicengine2";
    public final String POOR_LIGHT_REMOVER = "lightdistributor";
    public final String WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY = "waterlauncher";
    public final String WATER_RECIEVER = "waterreceiver";
    public final String GRAV_INCREASE = "gravityincrease";
    public final String GRAV_DECREASE = "gravitydecrease";
    public final String COMET_WATER = "waterreceiver2";
    public final String RAD_REMOVER = "radremoval";
    public final String RAD_ORE_REMOVER = "radoreremoval";
    public final String RAD_ORE_REMOVER2 = "radoreremoval2";
    public final String RAD_ORE_REMOVER3 = "radoreremoval3";
    public final String RAD_ORE_REMOVER4 = "radoreremoval4";
    public final String RAD_ORE_REMOVER5 = "radoreremoval5";
    public final String GRAV_OSCILLATOR = "gravitylow";
    public final String ATMOSPHERE_REMOVER = "atmoremover";
    
    public boolean hasWaterPlanetType(StarSystemAPI sector, int counter) {
        counter = counter - 1;
        do {
            if(sector.getPlanets().get(counter).getMarket().hasIndustry(
                    WATER_LAUNCHER_WATER_FROZEN_CRYO_ONLY)) {
                return true;
            }
            if(counter < 0) break;
            counter = counter - 1;
        } while(counter > 0);
        return false;
    }
}
