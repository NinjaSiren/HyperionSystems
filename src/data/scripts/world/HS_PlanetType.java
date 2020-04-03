package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_PlanetType {
    
    // Roll the dice
    private double rand() {
        Random rand = new Random();
        final double max = 1.0;
        final double min = 0.0;
        return min + rand.nextDouble() * (max - min);
    }
    
    // Sets the planet type depending on the distance of the planet from its parent star
    // And to what type of star it is
    public String  planetType(PlanetAPI[] star, int star_count, PlanetAPI[] planet, int planet_count,
            float orbit_radius) {
        
        // Checks if Unknown Skies is enabled
        boolean isUS = Global.getSettings().getModManager().isModEnabled("US");
        
        float[] habitableZoneMin = new float[star_count];
        float[] habitableZoneMax = new float[star_count];
        float[] habitableZoneMiddleMin = new float[star_count];
        float[] habitableZoneMiddleMax = new float[star_count];
        int counter = 0;
        do {
            if(counter < 0 || counter >= star_count) break;
            else {
                habitableZoneMin[counter] = starHabitableMin(star[counter]);
                habitableZoneMax[counter] = starHabitableMax(star[counter]);
                habitableZoneMiddleMin[counter] = 
                        starHabitableMax(star[counter]) - 
                        ((starHabitableMax(star[counter]) + starHabitableMin(star[counter])) / 4);
                habitableZoneMiddleMax[counter] = 
                        ((starHabitableMax(star[counter]) + starHabitableMin(star[counter])) / 4) + 
                        starHabitableMin(star[counter]);
                counter++;
            }
        } while(counter < star_count + 1);
        
        int counter_2 = 0;
        // Planets in the habitable zone of the star
        do {
            if(counter_2 < 0 || counter_2 >= planet_count) break;
            else if(orbit_radius > habitableZoneMin[counter_2] &&
                    orbit_radius < habitableZoneMax[counter_2]) {

                // Inner Habitable Zone
                if(orbit_radius >= habitableZoneMin[counter_2] &&
                        orbit_radius < habitableZoneMiddleMin[counter_2]) {
                    // Barrens
                    if(rand() <= 0.25) {      
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.125) return new Planet().BARREN_A;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().BARREN_CASTIRON;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().BARREN_B;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().BARREN_C;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().BARREN_VENUSLIKE;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().ROCKY_METALLIC;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().ROCKY_UNSTABLE;
                            else return new Planet().BARREN_BOMBARDED; 
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.1) return new Planet().US_AZURE;
                            else if(rand() > 0.1 && rand() <= 0.2) return new Planet().US_ARTIFICIAL;
                            else if(rand() > 0.2 && rand() <= 0.3) return new Planet().US_BURNT;
                            else if(rand() > 0.3 && rand() <= 0.4) return new Planet().US_DUST;
                            else if(rand() > 0.4 && rand() <= 0.5) return new Planet().US_BARREN_A;
                            else if(rand() > 0.5 && rand() <= 0.6) return new Planet().US_BARREN_B;
                            else if(rand() > 0.6 && rand() <= 0.7) return new Planet().US_BARREN_C;
                            else if(rand() > 0.7 && rand() <= 0.8) return new Planet().US_BARREN_D;
                            else if(rand() > 0.8 && rand() <= 0.9) return new Planet().US_BARREN_E;
                            else return new Planet().US_BARREN_F;  
                        }
                    // Habitables
                    } else if(rand() > 0.25 && rand() <= 0.8625) {  
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.125) return new Planet().TERRAN;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().TERRAN_ECCENTRIC;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().JUNGLE;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().OCEAN;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().ARID;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().DESERT_A;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().DESERT_B;
                            else return new Planet().BARREN_DESERT;
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.053) return new Planet().US_TERRAN;
                            else if(rand() > 0.053 && rand() <= 0.106) return new Planet().US_OCEAN_A;
                            else if(rand() > 0.106 && rand() <= 0.159) return new Planet().US_OCEAN_B;
                            else if(rand() > 0.159 && rand() <= 0.212) return new Planet().US_ALKALI;
                            else if(rand() > 0.212 && rand() <= 0.265) return new Planet().US_JUNGLE;
                            else if(rand() > 0.265 && rand() <= 0.318) return new Planet().US_AURIC;
                            else if(rand() > 0.318 && rand() <= 0.371) return new Planet().US_AURIC_CLOUDY;
                            else if(rand() > 0.371 && rand() <= 0.424) return new Planet().US_LIFELESS;
                            else if(rand() > 0.424 && rand() <= 0.447) return new Planet().US_MAGNETIC;
                            else if(rand() > 0.477 && rand() <= 0.53) return new Planet().US_STORM;
                            else if(rand() > 0.53 && rand() <= 0.583) return new Planet().US_ARID_LIFELESS;
                            else if(rand() > 0.583 && rand() <= 0.636) return new Planet().US_ARID;
                            else if(rand() > 0.636 && rand() <= 0.689) return new Planet().US_CRIMSON;
                            else if(rand() > 0.742 && rand() <= 0.795) return new Planet().US_DESERT_A;
                            else if(rand() > 0.795 && rand() <= 0.848) return new Planet().US_DESERT_B;
                            else if(rand() > 0.848 && rand() <= 0.901) return new Planet().US_DESERT_C;
                            else if(rand() > 0.901 && rand() <= 0.954) return new Planet().US_RED;
                            else return new Planet().US_RED_WIND;
                        }
                    // Other planets
                    } else {
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.167) return new Planet().GAS_GIANT;
                            else if(rand() > 0.167 && rand() <= 0.334) return new Planet().LAVA;
                            else if(rand() > 0.334 && rand() <= 0.501) return new Planet().LAVA_MINOR;
                            else if(rand() > 0.501 && rand() <= 0.668) return new Planet().IRRADIATED;
                            else if(rand() > 0.668 && rand() <= 0.835) return new Planet().TOXIC;
                            else return new Planet().TOXIC_COLD; 
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.125) return new Planet().US_GAS_GIANT_A;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().US_GAS_GIANT_B;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().US_LAVA;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().US_VOLCANIC;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().US_CHLORINE;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().US_ACID;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().US_ACIDRAIN;
                            else return new Planet().US_ACIDWIND;              
                        }
                    }
                    
                // Middle Habitable Zone
                } else if(orbit_radius >= habitableZoneMiddleMin[counter_2] &&
                        orbit_radius <= habitableZoneMiddleMax[counter_2]) {
                    // Barrens
                    if(rand() <= 0.2) {      
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.125) return new Planet().BARREN_A;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().BARREN_CASTIRON;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().BARREN_B;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().BARREN_C;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().BARREN_VENUSLIKE;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().ROCKY_METALLIC;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().ROCKY_UNSTABLE;
                            else return new Planet().BARREN_BOMBARDED; 
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.1) return new Planet().US_AZURE;
                            else if(rand() > 0.1 && rand() <= 0.2) return new Planet().US_ARTIFICIAL;
                            else if(rand() > 0.2 && rand() <= 0.3) return new Planet().US_BURNT;
                            else if(rand() > 0.3 && rand() <= 0.4) return new Planet().US_DUST;
                            else if(rand() > 0.4 && rand() <= 0.5) return new Planet().US_BARREN_A;
                            else if(rand() > 0.5 && rand() <= 0.6) return new Planet().US_BARREN_B;
                            else if(rand() > 0.6 && rand() <= 0.7) return new Planet().US_BARREN_C;
                            else if(rand() > 0.7 && rand() <= 0.8) return new Planet().US_BARREN_D;
                            else if(rand() > 0.8 && rand() <= 0.9) return new Planet().US_BARREN_E;
                            else return new Planet().US_BARREN_F;  
                        }
                    // Habitables
                    } else if(rand() > 0.2 && rand() <= 0.8625) {  
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.125) return new Planet().TERRAN;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().TERRAN_ECCENTRIC;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().JUNGLE;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().OCEAN;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().ARID;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().DESERT_A;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().DESERT_B;
                            else return new Planet().BARREN_DESERT;
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.053) return new Planet().US_TERRAN;
                            else if(rand() > 0.053 && rand() <= 0.106) return new Planet().US_OCEAN_A;
                            else if(rand() > 0.106 && rand() <= 0.159) return new Planet().US_OCEAN_B;
                            else if(rand() > 0.159 && rand() <= 0.212) return new Planet().US_ALKALI;
                            else if(rand() > 0.212 && rand() <= 0.265) return new Planet().US_JUNGLE;
                            else if(rand() > 0.265 && rand() <= 0.318) return new Planet().US_AURIC;
                            else if(rand() > 0.318 && rand() <= 0.371) return new Planet().US_AURIC_CLOUDY;
                            else if(rand() > 0.371 && rand() <= 0.424) return new Planet().US_LIFELESS;
                            else if(rand() > 0.424 && rand() <= 0.447) return new Planet().US_MAGNETIC;
                            else if(rand() > 0.477 && rand() <= 0.53) return new Planet().US_STORM;
                            else if(rand() > 0.53 && rand() <= 0.583) return new Planet().US_ARID_LIFELESS;
                            else if(rand() > 0.583 && rand() <= 0.636) return new Planet().US_ARID;
                            else if(rand() > 0.636 && rand() <= 0.689) return new Planet().US_CRIMSON;
                            else if(rand() > 0.742 && rand() <= 0.795) return new Planet().US_DESERT_A;
                            else if(rand() > 0.795 && rand() <= 0.848) return new Planet().US_DESERT_B;
                            else if(rand() > 0.848 && rand() <= 0.901) return new Planet().US_DESERT_C;
                            else if(rand() > 0.901 && rand() <= 0.954) return new Planet().US_RED;
                            else return new Planet().US_RED_WIND;
                        }
                    // Other planets
                    } else {
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.167) return new Planet().GAS_GIANT;
                            else if(rand() > 0.167 && rand() <= 0.334) return new Planet().LAVA;
                            else if(rand() > 0.334 && rand() <= 0.501) return new Planet().LAVA_MINOR;
                            else if(rand() > 0.501 && rand() <= 0.668) return new Planet().IRRADIATED;
                            else if(rand() > 0.668 && rand() <= 0.835) return new Planet().TOXIC;
                            else return new Planet().TOXIC_COLD; 
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.125) return new Planet().US_GAS_GIANT_A;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().US_GAS_GIANT_B;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().US_LAVA;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().US_VOLCANIC;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().US_CHLORINE;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().US_ACID;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().US_ACIDRAIN;
                            else return new Planet().US_ACIDWIND;              
                        }
                    }
                    
                // Outer Habitable Zone
                } else if(orbit_radius > habitableZoneMiddleMax[counter_2] &&
                        orbit_radius <= habitableZoneMax[counter_2]) {
                    // Barrens
                    if(rand() <= 0.25) {      
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.125) return new Planet().BARREN_A;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().BARREN_CASTIRON;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().BARREN_B;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().BARREN_C;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().BARREN_VENUSLIKE;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().ROCKY_METALLIC;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().ROCKY_UNSTABLE;
                            else return new Planet().BARREN_BOMBARDED; 
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.1) return new Planet().US_AZURE;
                            else if(rand() > 0.1 && rand() <= 0.2) return new Planet().US_ARTIFICIAL;
                            else if(rand() > 0.2 && rand() <= 0.3) return new Planet().US_BURNT;
                            else if(rand() > 0.3 && rand() <= 0.4) return new Planet().US_DUST;
                            else if(rand() > 0.4 && rand() <= 0.5) return new Planet().US_BARREN_A;
                            else if(rand() > 0.5 && rand() <= 0.6) return new Planet().US_BARREN_B;
                            else if(rand() > 0.6 && rand() <= 0.7) return new Planet().US_BARREN_C;
                            else if(rand() > 0.7 && rand() <= 0.8) return new Planet().US_BARREN_D;
                            else if(rand() > 0.8 && rand() <= 0.9) return new Planet().US_BARREN_E;
                            else return new Planet().US_BARREN_F;  
                        }
                    // Habitables
                    } else if(rand() > 0.25 && rand() <= 0.8625) {  
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.125) return new Planet().TERRAN;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().TERRAN_ECCENTRIC;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().JUNGLE;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().OCEAN;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().ARID;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().DESERT_A;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().DESERT_B;
                            else return new Planet().BARREN_DESERT;
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.053) return new Planet().US_TERRAN;
                            else if(rand() > 0.053 && rand() <= 0.106) return new Planet().US_OCEAN_A;
                            else if(rand() > 0.106 && rand() <= 0.159) return new Planet().US_OCEAN_B;
                            else if(rand() > 0.159 && rand() <= 0.212) return new Planet().US_ALKALI;
                            else if(rand() > 0.212 && rand() <= 0.265) return new Planet().US_JUNGLE;
                            else if(rand() > 0.265 && rand() <= 0.318) return new Planet().US_AURIC;
                            else if(rand() > 0.318 && rand() <= 0.371) return new Planet().US_AURIC_CLOUDY;
                            else if(rand() > 0.371 && rand() <= 0.424) return new Planet().US_LIFELESS;
                            else if(rand() > 0.424 && rand() <= 0.447) return new Planet().US_MAGNETIC;
                            else if(rand() > 0.477 && rand() <= 0.53) return new Planet().US_STORM;
                            else if(rand() > 0.53 && rand() <= 0.583) return new Planet().US_ARID_LIFELESS;
                            else if(rand() > 0.583 && rand() <= 0.636) return new Planet().US_ARID;
                            else if(rand() > 0.636 && rand() <= 0.689) return new Planet().US_CRIMSON;
                            else if(rand() > 0.742 && rand() <= 0.795) return new Planet().US_DESERT_A;
                            else if(rand() > 0.795 && rand() <= 0.848) return new Planet().US_DESERT_B;
                            else if(rand() > 0.848 && rand() <= 0.901) return new Planet().US_DESERT_C;
                            else if(rand() > 0.901 && rand() <= 0.954) return new Planet().US_RED;
                            else return new Planet().US_RED_WIND;
                        }
                    // Other planets
                    } else {
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.167) return new Planet().GAS_GIANT;
                            else if(rand() > 0.167 && rand() <= 0.334) return new Planet().LAVA;
                            else if(rand() > 0.334 && rand() <= 0.501) return new Planet().LAVA_MINOR;
                            else if(rand() > 0.501 && rand() <= 0.668) return new Planet().IRRADIATED;
                            else if(rand() > 0.668 && rand() <= 0.835) return new Planet().TOXIC;
                            else return new Planet().TOXIC_COLD; 
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.125) return new Planet().US_GAS_GIANT_A;
                            else if(rand() > 0.125 && rand() <= 0.25) return new Planet().US_GAS_GIANT_B;
                            else if(rand() > 0.25 && rand() <= 0.375) return new Planet().US_LAVA;
                            else if(rand() > 0.375 && rand() <= 0.5) return new Planet().US_VOLCANIC;
                            else if(rand() > 0.5 && rand() <= 0.625) return new Planet().US_CHLORINE;
                            else if(rand() > 0.625 && rand() <= 0.75) return new Planet().US_ACID;
                            else if(rand() > 0.75 && rand() <= 0.875) return new Planet().US_ACIDRAIN;
                            else return new Planet().US_ACIDWIND;              
                        }
                    }
                }
                
            // Planets too close to its star
            } else if(orbit_radius < habitableZoneMin[counter_2]) {
                
                // Lavas
                if(rand() <= 0.475) {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.5) return new Planet().LAVA;
                        else return new Planet().LAVA_MINOR;
                    // Unknown Skies planet types
                    } else {
                        if(rand() <= 0.5) return new Planet().US_LAVA;
                        else return new Planet().US_VOLCANIC;
                    }
                
                // Barrens
                } else if(rand() > 0.475 && rand() <= 0.875) {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.125) return new Planet().BARREN_A;
                        else if(rand() > 0.125 && rand() <= 0.25) return new Planet().BARREN_CASTIRON;
                        else if(rand() > 0.25 && rand() <= 0.375) return new Planet().BARREN_B;
                        else if(rand() > 0.375 && rand() <= 0.5) return new Planet().BARREN_C;
                        else if(rand() > 0.5 && rand() <= 0.625) return new Planet().BARREN_VENUSLIKE;
                        else if(rand() > 0.625 && rand() <= 0.75) return new Planet().ROCKY_METALLIC;
                        else if(rand() > 0.75 && rand() <= 0.875) return new Planet().ROCKY_UNSTABLE;
                        else return new Planet().BARREN_BOMBARDED; 
                    // Unknown Skies planet types
                    } else {
                        if(rand() <= 0.1) return new Planet().US_AZURE;
                        else if(rand() > 0.1 && rand() <= 0.2) return new Planet().US_ARTIFICIAL;
                        else if(rand() > 0.2 && rand() <= 0.3) return new Planet().US_BURNT;
                        else if(rand() > 0.3 && rand() <= 0.4) return new Planet().US_DUST;
                        else if(rand() > 0.4 && rand() <= 0.5) return new Planet().US_BARREN_A;
                        else if(rand() > 0.5 && rand() <= 0.6) return new Planet().US_BARREN_B;
                        else if(rand() > 0.6 && rand() <= 0.7) return new Planet().US_BARREN_C;
                        else if(rand() > 0.7 && rand() <= 0.8) return new Planet().US_BARREN_D;
                        else if(rand() > 0.8 && rand() <= 0.9) return new Planet().US_BARREN_E;
                        else return new Planet().US_BARREN_F;  
                    }
                    
                // Others
                } else {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.167) return new Planet().GAS_GIANT;
                        else if(rand() > 0.167 && rand() <= 0.334) return new Planet().LAVA;
                        else if(rand() > 0.334 && rand() <= 0.501) return new Planet().LAVA_MINOR;
                        else if(rand() > 0.501 && rand() <= 0.668) return new Planet().IRRADIATED;
                        else if(rand() > 0.668 && rand() <= 0.835) return new Planet().TOXIC;
                        else return new Planet().TOXIC_COLD; 
                    // Unknown Skies planet types
                    } else {
                        if(rand() <= 0.125) return new Planet().US_GAS_GIANT_A;
                        else if(rand() > 0.125 && rand() <= 0.25) return new Planet().US_GAS_GIANT_B;
                        else if(rand() > 0.25 && rand() <= 0.375) return new Planet().US_LAVA;
                        else if(rand() > 0.375 && rand() <= 0.5) return new Planet().US_VOLCANIC;
                        else if(rand() > 0.5 && rand() <= 0.625) return new Planet().US_CHLORINE;
                        else if(rand() > 0.625 && rand() <= 0.75) return new Planet().US_ACID;
                        else if(rand() > 0.75 && rand() <= 0.875) return new Planet().US_ACIDRAIN;
                        else return new Planet().US_ACIDWIND;              
                    }
                } 
                
            // Planets too far from its star
            } else {
                
                // Gas
                if(rand() <= 0.4) {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.5) return new Planet().GAS_GIANT;
                        else return new Planet().ICE_GIANT;
                    // Unknown Skies planet types
                    } else {
                        if(rand() <= 0.5) return new Planet().US_GAS_GIANT_A;
                        else return new Planet().US_GAS_GIANT_B;
                    }
                
                // Cryos & Frozens
                } else if(rand() > 0.4 && rand() <= 0.79375) {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.2) return new Planet().FROZEN_A;
                        else if(rand() > 0.2 && rand() <= 0.4) return new Planet().FROZEN_B;
                        else if(rand() > 0.4 && rand() <= 0.6) return new Planet().FROZEN_C;
                        else if(rand() > 0.6 && rand() <= 0.8) return new Planet().FROZEN_D;
                        else return new Planet().CRYOVOLCANIC;   
                    }
                    // Unknown Skies planet types
                    else {
                            if(rand() <= 0.33) return new Planet().US_CRYOVOLCANIC;
                            else if(rand() > 0.33 && rand() <= 0.66) return new Planet().US_FROZEN_A;
                            else return new Planet().US_FROZEN_B; 
                    }
                
                // Barrens
                } else if(rand() > 0.79375 && rand() <= 0.873125) {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.11) return new Planet().BARREN_A;
                        else if(rand() > 0.11 && rand() <= 0.22) return new Planet().BARREN_CASTIRON;
                        else if(rand() > 0.22 && rand() <= 0.33) return new Planet().BARREN_B;
                        else if(rand() > 0.33 && rand() <= 0.44) return new Planet().BARREN_C;
                        else if(rand() > 0.44 && rand() <= 0.55) return new Planet().BARREN_VENUSLIKE;
                        else if(rand() > 0.55 && rand() <= 0.66) return new Planet().ROCKY_METALLIC;
                        else if(rand() > 0.66 && rand() <= 0.77) return new Planet().ROCKY_UNSTABLE;
                        else if(rand() > 0.77 && rand() <= 0.88) return new Planet().ROCKY_ICE;
                        else return new Planet().BARREN_BOMBARDED;
                    }
                    // Unknown Skies planet types
                    else {             
                        if(rand() <= 0.1) return new Planet().US_AZURE;
                        else if(rand() > 0.1 && rand() <= 0.2) return new Planet().US_ARTIFICIAL;
                        else if(rand() > 0.2 && rand() <= 0.3) return new Planet().US_BURNT;
                        else if(rand() > 0.3 && rand() <= 0.4) return new Planet().US_DUST;
                        else if(rand() > 0.4 && rand() <= 0.5) return new Planet().US_BARREN_A;
                        else if(rand() > 0.5 && rand() <= 0.6) return new Planet().US_BARREN_B;
                        else if(rand() > 0.6 && rand() <= 0.7) return new Planet().US_BARREN_C;
                        else if(rand() > 0.7 && rand() <= 0.8) return new Planet().US_BARREN_D;
                        else if(rand() > 0.8 && rand() <= 0.9) return new Planet().US_BARREN_E;
                        else return new Planet().US_BARREN_F;
                    }
                }

                // Others
                else {
                    // Vanilla planet types
                    if(!isUS) {
                        if(rand() <= 0.25) return new Planet().LAVA;
                        else if(rand() > 0.25 && rand() <= 0.5) return new Planet().LAVA_MINOR;
                        else if(rand() > 0.5 && rand() <= 0.75) return new Planet().TOXIC;
                        else return new Planet().TOXIC_COLD;
                    }
                    // Unknown Skies planet types
                    else {
                        if(rand() <= 0.25) return new Planet().US_CHLORINE;
                        else if(rand() > 0.25 && rand() <= 0.5) return new Planet().US_ACID;
                        else if(rand() > 0.5 && rand() <= 0.75) return new Planet().US_ACIDRAIN;
                        else return new Planet().US_ACIDWIND;  
                    }
                }
            }
            counter++;
        } while(counter_2 < planet_count);
        return null;
    }
    
    // Gets what star type and returns habitable zone min orbit
    private float starHabitableMin(PlanetAPI star) {
        float starRadius = star.getRadius();
        float starCoronaRadius = star.getSpec().getCoronaSize();
        
        // Yellow Main Sequence
        if(star.getTypeId().equals(new Star().YELLOW) ||
                star.getTypeId().equals(new Star().YELLOW_US)) {
            return ((starRadius * 4.5f) + (starCoronaRadius / 3.5f));
            
        // Red Giant
        } else if(star.getTypeId().equals(new Star().RED_GIANT)) {
                return ((starRadius * 5.25f) + (starCoronaRadius / 4.25f));
            
        // Red Supergiant
        } else if(star.getTypeId().equals(new Star().RED_SUPERGIANT)) {
                return ((starRadius * 5.75f) + (starCoronaRadius / 4.75f));
                
        // Red Dwarf
        } else if(star.getTypeId().equals(new Star().RED_DWARF) ||
                star.getTypeId().equals(new Star().RED_DWARF_US)) {
            return ((starRadius * 4.25f) + (starCoronaRadius / 3.25f));
        
        // Orange Main Sequence
        } else if(star.getTypeId().equals(new Star().ORANGE)) {
            return ((starRadius * 4.75f) + (starCoronaRadius / 3.75f));
            
        // Orange Giant
        } else if(star.getTypeId().equals(new Star().ORANGE_GIANT) ||
                star.getTypeId().equals(new Star().ORANGE_GIANT_US)) {
            return ((starRadius * 5f) + (starCoronaRadius / 4));

        // Blue Giant
        } else if(star.getTypeId().equals(new Star().BLUE_GIANT) ||
                star.getTypeId().equals(new Star().BLUE_GIANT_US)) {
            return ((starRadius * 5.5f) + (starCoronaRadius / 4.5f));
            
        // Blue Supergiant
        } else if(star.getTypeId().equals(new Star().BLUE_SUPERGIANT)) {
            return ((starRadius * 5) + (starCoronaRadius / 5));
        
        // White Dwarf
        } else if(star.getTypeId().equals(new Star().WHITE_DWARF) ||
                star.getTypeId().equals(new Star().WHITE_DWARF_US)) {
            return ((starRadius * 4.15f) + (starCoronaRadius / 3.15f));
        
        // Brown Dwarf
        } else if(star.getTypeId().equals(new Star().BROWN_DWARF) ||
                star.getTypeId().equals(new Star().BROWN_DWARF_US)) {
            return ((starRadius * 2.15f) + (starCoronaRadius / 2));
        
        // Neutron Star
        } else if(star.getTypeId().equals(new Star().NEUTRON_STAR)) {
            return starRadius * 10;
        
        // Black Hole
        } else if(star.getTypeId().equals(new Star().BLACK_HOLE)) {
            return 0;
            
        // Nebula Young
        } else if(star.getTypeId().equals(new Star().NEBULA_YOUNG)) {
                return 0;
                
        // Nebula Average
        } else if(star.getTypeId().equals(new Star().NEBULA_AVERAGE)) {
            return 0;

        // Nebula Old
        } else if(star.getTypeId().equals(new Star().NEBULA_OLD)) {
            return 0;
        }
        return 0;
    } 
    
    // Gets what star type and returns habitable zone min orbit
    private float starHabitableMax(PlanetAPI star) {
        float starRadius = star.getRadius();
        float starCoronaRadius = star.getSpec().getCoronaSize();
        
        // Yellow Main Sequence
        if(star.getTypeId().equals(new Star().YELLOW) ||
                star.getTypeId().equals(new Star().YELLOW_US)) {
            return ((starRadius * 3.5f) + (starCoronaRadius / 3.25f)) * 1.715f;
            
        // Red Giant
        } else if(star.getTypeId().equals(new Star().RED_GIANT)) {
                return ((starRadius * 4.25f) + (starCoronaRadius / 4.25f)) * 1.925f;
            
        // Red Supergiant
        } else if(star.getTypeId().equals(new Star().RED_SUPERGIANT)) {
                return ((starRadius * 4.75f) + (starCoronaRadius / 4.75f)) * 2.25f;
                
        // Red Dwarf
        } else if(star.getTypeId().equals(new Star().RED_DWARF) ||
                star.getTypeId().equals(new Star().RED_DWARF_US)) {
            return ((starRadius * 3.25f) + (starCoronaRadius / 3.5f)) * 1.65f;
        
        // Orange Main Sequence
        } else if(star.getTypeId().equals(new Star().ORANGE)) {
            return ((starRadius * 3.75f) + (starCoronaRadius / 3.5f)) * 1.775f;
            
        // Orange Giant
        } else if(star.getTypeId().equals(new Star().ORANGE_GIANT) ||
                star.getTypeId().equals(new Star().ORANGE_GIANT_US)) {
            return ((starRadius * 4) + (starCoronaRadius / 3.75f)) * 1.805f;

        // Blue Giant
        } else if(star.getTypeId().equals(new Star().BLUE_GIANT) ||
                star.getTypeId().equals(new Star().BLUE_GIANT_US)) {
            return ((starRadius * 4.5f) + (starCoronaRadius / 4.5f)) * 2.15f;
            
        // Blue Supergiant
        } else if(star.getTypeId().equals(new Star().BLUE_SUPERGIANT)) {
            return ((starRadius * 5f) + (starCoronaRadius / 5)) * 2.5f;
        
        // White Dwarf
        } else if(star.getTypeId().equals(new Star().WHITE_DWARF) ||
                star.getTypeId().equals(new Star().WHITE_DWARF_US)) {
            return ((starRadius * 3.15f) + (starCoronaRadius / 3.15f)) * 1.55f;
        
        // Brown Dwarf
        } else if(star.getTypeId().equals(new Star().BROWN_DWARF) ||
                star.getTypeId().equals(new Star().BROWN_DWARF_US)) {
            return ((starRadius * 2.15f) + (starCoronaRadius / 2)) * 1.35f;
        
        // Neutron Star
        } else if(star.getTypeId().equals(new Star().NEUTRON_STAR)) {
            return starRadius * 20;
        
        // Black Hole
        } else if(star.getTypeId().equals(new Star().BLACK_HOLE)) {
            return 0;
            
        // Nebula Young
        } else if(star.getTypeId().equals(new Star().NEBULA_YOUNG)) {
                return 0;   
                
        // Nebula Average
        } else if(star.getTypeId().equals(new Star().NEBULA_AVERAGE)) {
            return 0;

        // Nebula Old
        } else if(star.getTypeId().equals(new Star().NEBULA_OLD)) {
            return 0;
        }
        return 0;
    } 
}
