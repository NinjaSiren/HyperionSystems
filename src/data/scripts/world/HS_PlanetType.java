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
    
    // Gets what star type and returns habitable zone min orbit
    private float starHabitableMin(PlanetAPI star) {
        float starRadius = star.getRadius();
        float starCoronaRadius = star.getSpec().getCoronaSize();
        
        // Yellow Main Sequence
        if(star.getTypeId().equals(new Star().YELLOW) ||
                star.getTypeId().equals(new Star().YELLOW_US)) {
            return ((starRadius * 1.725f) + (starCoronaRadius / 2));
            
        // Red Giant
        } else if(star.getTypeId().equals(new Star().RED_GIANT)) {
                return ((starRadius * 2.3f) + (starCoronaRadius / 2));
            
        // Red Supergiant
        } else if(star.getTypeId().equals(new Star().RED_SUPERGIANT)) {
                return ((starRadius * 4.6f) + (starCoronaRadius / 2));
                
        // Red Dwarf
        } else if(star.getTypeId().equals(new Star().RED_DWARF) ||
                star.getTypeId().equals(new Star().RED_DWARF_US)) {
            return ((starRadius * 1.05f) + (starCoronaRadius / 2));
        
        // Orange Main Sequence
        } else if(star.getTypeId().equals(new Star().ORANGE)) {
            return ((starRadius * 1.575f) + (starCoronaRadius / 2));
            
        // Orange Giant
        } else if(star.getTypeId().equals(new Star().ORANGE_GIANT) ||
                star.getTypeId().equals(new Star().ORANGE_GIANT_US)) {
            return ((starRadius * 3.15f) + (starCoronaRadius / 2));

        // Blue Giant
        } else if(star.getTypeId().equals(new Star().BLUE_GIANT) ||
                star.getTypeId().equals(new Star().BLUE_GIANT_US)) {
            return ((starRadius * 5.175f) + (starCoronaRadius / 2));
            
        // Blue Supergiant
        } else if(star.getTypeId().equals(new Star().BLUE_SUPERGIANT)) {
            return ((starRadius * 10.35f) + (starCoronaRadius / 2));
        
        // White Dwarf
        } else if(star.getTypeId().equals(new Star().WHITE_DWARF) ||
                star.getTypeId().equals(new Star().WHITE_DWARF_US)) {
            return ((starRadius * 1.725f) + (starCoronaRadius / 2));
        
        // Brown Dwarf
        } else if(star.getTypeId().equals(new Star().BROWN_DWARF) ||
                star.getTypeId().equals(new Star().BROWN_DWARF_US)) {
            return ((starRadius * 1.015f) + (starCoronaRadius / 2));
        
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
    
    // Sets the planet type depending on the distance of the planet from its parent star
    // And to what type of star it is
    public String  planetType(PlanetAPI[] star, int star_count, PlanetAPI[] planet, int planet_count,
            float orbit_radius) {
        int counter = 0;
        
        // Checks if Unknown Skies is enabled
        boolean isUS = Global.getSettings().getModManager().isModEnabled("US");

        float[] habitableZoneMin = new float[star_count];
        float[] habitableZoneMax = new float[star_count];
        float[] habitableZoneMiddleMin = new float[star_count];
        float[] habitableZoneMiddleMax = new float[star_count];
        do {
            if(counter < 0 || counter >= star_count) break;
            else {
                habitableZoneMin[counter] = starHabitableMin(star[counter]);
                habitableZoneMax[counter] = starHabitableMin(star[counter]) * 1.25f;
                habitableZoneMiddleMin[counter] = ((starHabitableMin(star[counter]) - 
                        starHabitableMin(star[counter])) / 4) + habitableZoneMin[counter]; 
                habitableZoneMiddleMax[counter] =  starHabitableMin(star[counter]) - 
                        ((starHabitableMin(star[counter]) - starHabitableMin(star[counter])) / 4);
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
                    if(rand() <= 0.325) {      
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
                    } else if(rand() > 0.325 && rand() <= 0.8625) {  
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.25) return new Planet().ARID;
                            else if(rand() > 0.25 && rand() <= 0.5) return new Planet().DESERT_A;
                            else if(rand() > 0.5 && rand() <= 0.75) return new Planet().DESERT_B;
                            else return new Planet().BARREN_DESERT;
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.071) return new Planet().US_ALKALI;
                            else if(rand() > 0.071 && rand() <= 0.142) return new Planet().US_AURIC;
                            else if(rand() > 0.142 && rand() <= 0.213) return new Planet().US_AURIC_CLOUDY;
                            else if(rand() > 0.213 && rand() <= 0.284) return new Planet().US_LIFELESS;
                            else if(rand() > 0.284 && rand() <= 0.355) return new Planet().US_MAGNETIC;
                            else if(rand() > 0.355 && rand() <= 0.426) return new Planet().US_STORM;
                            else if(rand() > 0.426 && rand() <= 0.497) return new Planet().US_ARID_LIFELESS;
                            else if(rand() > 0.497 && rand() <= 0.568) return new Planet().US_ARID;
                            else if(rand() > 0.568 && rand() <= 0.639) return new Planet().US_CRIMSON;
                            else if(rand() > 0.639 && rand() <= 0.71) return new Planet().US_DESERT_A;
                            else if(rand() > 0.71 && rand() <= 0.781) return new Planet().US_DESERT_B;
                            else if(rand() > 0.781 && rand() <= 0.852) return new Planet().US_DESERT_C;
                            else if(rand() > 0.852 && rand() <= 0.923) return new Planet().US_RED;
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
                    if(rand() <= 0.225) {      
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
                    } else if(rand() > 0.225 && rand() <= 0.8625) {  
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
                    if(rand() <= 0.325) {      
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
                    } else if(rand() > 0.325 && rand() <= 0.8625) {  
                        // Vanilla planet types
                        if(!isUS) {
                            if(rand() <= 0.25) return new Planet().TERRAN;
                            else if(rand() > 0.25 && rand() <= 0.5) return new Planet().TERRAN_ECCENTRIC;
                            else if(rand() > 0.5 && rand() <= 0.75) return new Planet().JUNGLE;
                            else return new Planet().TUNDRA;
                        // Unknown Skies planet types
                        } else {
                            if(rand() <= 0.083) return new Planet().US_TERRAN;
                            else if(rand() > 0.166 && rand() <= 0.249) return new Planet().US_ALKALI;
                            else if(rand() > 0.249 && rand() <= 0.332) return new Planet().US_JUNGLE;
                            else if(rand() > 0.332 && rand() <= 0.415) return new Planet().US_AURIC;
                            else if(rand() > 0.415 && rand() <= 0.498) return new Planet().US_AURIC_CLOUDY;
                            else if(rand() > 0.498 && rand() <= 0.581) return new Planet().US_LIFELESS;
                            else if(rand() > 0.581 && rand() <= 0.664) return new Planet().US_MAGNETIC;
                            else if(rand() > 0.664 && rand() <= 0.747) return new Planet().US_STORM; 
                            else if(rand() > 0.747 && rand() <= 0.83) return new Planet().US_CRIMSON;
                            else if(rand() > 0.83 && rand() <= 0.913) return new Planet().US_METHANE;
                            else if(rand() > 0.913 && rand() <= 0.996) return new Planet().US_RED;
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
                } else if(rand() > 0.475 && rand() <= 0.775) {
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
}
