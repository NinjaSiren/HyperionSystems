package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.StarTypes;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_PlanetCondition {

    // Roll the dice
    private double rand2() {
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
    
    // Sets the planet conditions, Unknown Skies Updated
    public HS_PlanetCondition(String planetType, PlanetAPI star, PlanetAPI[] stars, int star_count, 
            float orbit_radius, PlanetAPI[] planet, int planetNumber, int planetSize) {

        boolean isUS = Global.getSettings().getModManager().isModEnabled("US");
        
        float[] habitableZoneMin = new float[star_count];
        float[] habitableZoneMax = new float[star_count];
        float[] habitableZoneMiddleMin = new float[star_count];
        float[] habitableZoneMiddleMax = new float[star_count];
        int counter = 0;
        do {
            if(counter < 0 || counter >= star_count) break;
            else {
                habitableZoneMin[counter] = starHabitableMin(stars[counter]);
                habitableZoneMax[counter] = starHabitableMax(stars[counter]);
                habitableZoneMiddleMin[counter] = 
                        starHabitableMax(stars[counter]) - 
                        ((starHabitableMax(stars[counter]) + starHabitableMin(stars[counter])) / 4);
                habitableZoneMiddleMax[counter] = 
                        ((starHabitableMax(stars[counter]) + starHabitableMin(stars[counter])) / 4) + 
                        starHabitableMin(stars[counter]);
                counter++;
            }
        } while(counter < star_count + 1);
        
        // Light
        if(star.getTypeId().equals(StarTypes.BLACK_HOLE)) {
            planet[planetNumber].getMarket().addCondition(Conditions.DARK);
        } else if(star.getTypeId().equals(StarTypes.NEUTRON_STAR)) {
            // Do nothing
        } else if(star.getTypeId().equals(new Star().NEBULA_AVERAGE) ||
                star.getTypeId().equals(new Star().NEBULA_OLD) ||
                star.getTypeId().equals(new Star().NEBULA_YOUNG)) {
            if(rand2() <= 0.5) {
                planet[planetNumber].getMarket().addCondition(Conditions.DARK);
            } else {
                planet[planetNumber].getMarket().addCondition(Conditions.POOR_LIGHT);
            }
        } else {
            for(counter = 0; counter < star_count; counter++) {
                if(counter < 0 || counter >= star_count) break;
                else if(orbit_radius > habitableZoneMax[counter] * 1.5) {
                    if(rand2() <= 0.1) {
                        planet[planetNumber].getMarket().addCondition(Conditions.POOR_LIGHT);
                    }
                } else if(orbit_radius > habitableZoneMax[counter] * 2) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.POOR_LIGHT);
                    }
                } else if(orbit_radius > habitableZoneMax[counter] * 2.5) {
                    if(rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.POOR_LIGHT);
                    }
                } else if(orbit_radius > habitableZoneMax[counter] * 3) {
                    if(rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.POOR_LIGHT);
                    }
                }
            }
        }
        
        // Gas Giants
        if(planetType.equals(new Planet().GAS_GIANT) || 
                planetType.equals(new Planet().US_GAS_GIANT_A) ||
                planetType.equals(new Planet().US_GAS_GIANT_B)) {

            // Weather
            if(rand2() <= 0.4 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_WEATHER)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            } else {
                if(rand2() <= 0.1)
                    planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            }
            
            // High Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY))
                planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE))
                planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
            if(rand2() <= 0.1 && 
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
                
                if(isUS  &&
                    !planet[planetNumber].getMarket().hasCondition(new Unknown_Conditions().FLOATING_CONTINENTS)) {
                    planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().FLOATING_CONTINENTS);
                }
            }
            
            // Ruins
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.25) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                } else if(rand2() > 0.25 && rand2() <= 0.5) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                } else if(rand2() > 0.5 && rand2() <= 0.75) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                } else {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                }
                
                if(isUS  &&
                    !planet[planetNumber].getMarket().hasCondition(new Unknown_Conditions().FLOATING_CONTINENTS))
                    planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().FLOATING_CONTINENTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.2) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                } else if(rand2() > 0.2 && rand2() <= 0.4) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                } else if(rand2() > 0.4 && rand2() <= 0.6) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                } else if(rand2() > 0.6 && rand2() <= 0.8) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                } 
            }
            
            // Meteors
            if(rand2() <= 0.1 && 
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
        // Ice Giants
        } else if(planetType.equals(new Planet().ICE_GIANT)) {

            // Weather
            if(rand2() <= 0.4 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_WEATHER)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            } else {
                if(rand2() <= 0.1)
                    planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            }
            
            // High Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY))
                planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE))
                planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
                
                if(isUS  &&
                    !planet[planetNumber].getMarket().hasCondition(new Unknown_Conditions().FLOATING_CONTINENTS)) {
                    planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().FLOATING_CONTINENTS);
                }
            }
            
            // Ruins
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.25) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                } else if(rand2() > 0.25 && rand2() <= 0.5) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                } else if(rand2() > 0.5 && rand2() <= 0.75) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                } else {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                }
                
                if(isUS  &&
                    !planet[planetNumber].getMarket().hasCondition(new Unknown_Conditions().FLOATING_CONTINENTS))
                    planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().FLOATING_CONTINENTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.2) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                } else if(rand2() > 0.2 && rand2() <= 0.4) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                } else if(rand2() > 0.4 && rand2() <= 0.6) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                } else if(rand2() > 0.6 && rand2() <= 0.8) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                } 
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }

        // Lavas and Moltens
        } else if(planetType.equals(new Planet().LAVA) || 
                planetType.equals(new Planet().LAVA_MINOR) ||
                planetType.equals(new Planet().US_LAVA) || 
                planetType.equals(new Planet().US_VOLCANIC)) {
            
            // Hot or Cold
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VERY_HOT) ||
                    !planet[planetNumber]. getMarket().hasCondition(Conditions.HOT)) {
                if(rand2() <= 0.5) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                } else {
                    planet[planetNumber]. getMarket().addCondition(Conditions.HOT);
                }
            }
            
            // Tectonic Activity
            if(rand2() <= 0.4 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TECTONIC_ACTIVITY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TECTONIC_ACTIVITY);
            } else if(rand2() > 0.6 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_TECTONIC_ACTIVITY);
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.2) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                } else if(rand2() > 0.2 && rand2() <= 0.4) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                } else if(rand2() > 0.4 && rand2() <= 0.6) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                } else if(rand2() > 0.6 && rand2() <= 0.8) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.2 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.8 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        // Cryovolcanics
        } else if(planetType.equals(new Planet().CRYOVOLCANIC) || 
                planetType.equals(new Planet().US_CRYOVOLCANIC)) {

            // Ice
            if(!planet[planetNumber].getMarket().hasCondition("cryovolcanic")) {
                planet[planetNumber].getMarket().addCondition("cryovolcanic");
            }
            
            // Tectonic Activity
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TECTONIC_ACTIVITY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TECTONIC_ACTIVITY);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_TECTONIC_ACTIVITY);
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        // Frozens
        } else if(planetType.equals(new Planet().FROZEN_A) ||
                planetType.equals(new Planet().FROZEN_B) ||
                planetType.equals(new Planet().FROZEN_C) ||
                planetType.equals(new Planet().FROZEN_D) ||
                planetType.equals(new Planet().US_FROZEN_A) ||
                planetType.equals(new Planet().US_FROZEN_B)) {

            // Ice
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ICE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.ICE);
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.2 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        // Barrens
        } else if(planetType.equals(new Planet().BARREN_A) ||
                planetType.equals(new Planet().BARREN_B) ||
                planetType.equals(new Planet().BARREN_C) ||
                planetType.equals(new Planet().US_BARREN_A) ||
                planetType.equals(new Planet().US_BARREN_B) ||
                planetType.equals(new Planet().US_BARREN_C) ||
                planetType.equals(new Planet().US_BARREN_D) ||
                planetType.equals(new Planet().US_BARREN_E) ||
                planetType.equals(new Planet().US_BARREN_F)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        } if(planetType.equals(new Planet().BARREN_CASTIRON)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.2) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.2 && rand2() <= 0.4) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.4 && rand2() <= 0.6) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.6 && rand2() <= 0.8) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        } else if(planetType.equals(new Planet().BARREN_VENUSLIKE)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.2 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.8 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.25) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        } else if(planetType.equals(new Planet().BARREN_BOMBARDED)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 90) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 150) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.75 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }  
            
        // Rocky
        } else if(planetType.equals(new Planet().ROCKY_METALLIC)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.2) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.2 && rand2() <= 0.4) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.4 && rand2() <= 0.6) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.6 && rand2() <= 0.8) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.2) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.2 && rand2() <= 0.4) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.4 && rand2() <= 0.6) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.6 && rand2() <= 0.8) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        } else if(planetType.equals(new Planet().ROCKY_UNSTABLE)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        } else if(planetType.equals(new Planet().ROCKY_ICE)) {
            
            // Ice
            if(rand2() <= 0.7 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ICE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.ICE);
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.2) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        // Irradiated and Azures
        } else if(planetType.equals(new Planet().IRRADIATED) ||
                planetType.equals(new Planet().US_AZURE)) {
            
            // Irradiated
            if(planetType.equals(new Planet().IRRADIATED)) {
                if(rand2() <= 0.75 && 
                        !planet[planetNumber].getMarket().hasCondition(Conditions.IRRADIATED)) {
                    planet[planetNumber].getMarket().addCondition(Conditions.IRRADIATED);
                }
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }
            
        // Toxics and Acidics
        } else if(planetType.equals(new Planet().TOXIC) ||
                planetType.equals(new Planet().TOXIC_COLD) ||
                planetType.equals(new Planet().US_ACID) ||
                planetType.equals(new Planet().US_ACIDRAIN) ||
                planetType.equals(new Planet().US_ACIDWIND) ||
                planetType.equals(new Planet().US_CHLORINE)) {
            
            // Weather
            if(rand2() <= 0.75 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_WEATHER) &&
                    planetType.equals(new Planet().US_ACIDRAIN) ||
                    planetType.equals(new Planet().US_ACIDWIND)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            } else {
                if(rand2() <= 0.1)
                    planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            }
            
            // Crashed Terraformation Drone
            if(isUS &&
                    !planet[planetNumber].getMarket().hasCondition(new Unknown_Conditions().TERRAFORMATION_DRONE) &&
                    rand2() <= 0.3) {
                planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().TERRAFORMATION_DRONE);
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.6 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.6 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }

        // Artificial
        } else if(planetType.equals(new Planet().US_ARTIFICIAL)) {
            
            // Chaotic Structures
            if(isUS && 
                    !planet[planetNumber].getMarket().hasCondition(new Unknown_Conditions().CHAOTIC_STRUCTURES) &&
                    rand2() <= 0.85) {
                planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().CHAOTIC_STRUCTURES);
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.7 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }
            
        // Burnt
        } else if(planetType.equals(new Planet().US_BURNT)) {
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }
            
        // Dust
        } else if(planetType.equals(new Planet().US_DUST)) {

            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }
            
        // All Habitables
        } else if(planetType.equals(new Planet().ARID) ||
                planetType.equals(new Planet().DESERT_A) ||
                planetType.equals(new Planet().DESERT_B) ||
                planetType.equals(new Planet().JUNGLE) ||
                planetType.equals(new Planet().OCEAN) ||
                planetType.equals(new Planet().TERRAN) ||
                planetType.equals(new Planet().TERRAN_ECCENTRIC) ||
                planetType.equals(new Planet().TUNDRA) ||
                planetType.equals(new Planet().US_ALKALI) ||
                planetType.equals(new Planet().US_ARID) ||
                planetType.equals(new Planet().US_ARID_LIFELESS) ||
                planetType.equals(new Planet().US_AURIC) ||
                planetType.equals(new Planet().US_AURIC_CLOUDY) ||
                planetType.equals(new Planet().US_CRIMSON) ||
                planetType.equals(new Planet().US_DESERT_A) ||
                planetType.equals(new Planet().US_DESERT_B) ||
                planetType.equals(new Planet().US_DESERT_C) ||
                planetType.equals(new Planet().US_JUNGLE) ||
                planetType.equals(new Planet().US_LIFELESS) ||
                planetType.equals(new Planet().US_MAGNETIC) ||
                planetType.equals(new Planet().US_METHANE) ||
                planetType.equals(new Planet().US_OCEAN_A) ||
                planetType.equals(new Planet().US_OCEAN_B) ||
                planetType.equals(new Planet().US_RED) ||
                planetType.equals(new Planet().US_RED_WIND) ||
                planetType.equals(new Planet().US_STORM) ||
                planetType.equals(new Planet().US_TERRAN)) {
            
            // Habitable
            planet[planetNumber].getMarket().addCondition(Conditions.HABITABLE);
            
            // Gravity
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.HIGH_GRAVITY) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.LOW_GRAVITY)) {
                if(planetSize > 130) {
                    planet[planetNumber].getMarket().addCondition(Conditions.HIGH_GRAVITY);
                } else if (planetSize < 80) {
                    planet[planetNumber].getMarket().addCondition(Conditions.LOW_GRAVITY);
                }
            }
            
            // Ruins
            if(rand2() <= 0.125 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                if(rand2() <= 0.15) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
                
                if(isUS) {
                    if(rand2() <= 0.065 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().MILITARY_VIRUS)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().MILITARY_VIRUS);
                    }
                    
                    if(rand2() <= 0.2 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().OLD_MILITARY_BASE)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().OLD_MILITARY_BASE);
                    }
                    
                    if(rand2() <= 0.095 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().RELIGIOUS_LANDMARK))  {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().RELIGIOUS_LANDMARK);
                    }  
                    
                    if(rand2() <= 0.6 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SPACE_ELEVATOR)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SPACE_ELEVATOR);
                    }  
                    
                    if(rand2() <= 0.3 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().UNDERGROUND_NETWORK)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().UNDERGROUND_NETWORK);
                    }
                }
            }
            
            // Atmosphere
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                if(planetSize < 60) {
                    planet[planetNumber].getMarket().addCondition(Conditions.NO_ATMOSPHERE);
                } else if(planetSize > 60 && planetSize < 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.THIN_ATMOSPHERE);
                } else if(planetSize > 125) {
                    planet[planetNumber].getMarket().addCondition(Conditions.DENSE_ATMOSPHERE);
                }
            }
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
            } else if(rand2() > 0.9 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TOXIC_ATMOSPHERE) &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.POLLUTION)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TOXIC_ATMOSPHERE);
                planet[planetNumber].getMarket().addCondition(Conditions.POLLUTION);
                
                // Ruins
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_EXTENSIVE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_SCATTERED) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_VAST) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.RUINS_WIDESPREAD)) {
                    if(rand2() <= 0.25) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_EXTENSIVE);
                    } else if(rand2() > 0.25 && rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_SCATTERED);
                    } else if(rand2() > 0.5 && rand2() <= 0.75) {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_VAST);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.RUINS_WIDESPREAD);
                    }
                }
            }
            
            // Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.ORE_ULTRARICH);
                }
            }
            
            // Rare Ore
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_MODERATE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_RICH) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_SPARSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.RARE_ORE_ULTRARICH)) {
                if(rand2() <= 0.167) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ABUNDANT);
                } else if(rand2() > 0.167 && rand2() <= 0.334) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_MODERATE);
                } else if(rand2() > 0.334 && rand2() <= 0.501) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_RICH);
                } else if(rand2() > 0.501 && rand2() <= 0.668) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_SPARSE);
                } else if(rand2() > 0.668 && rand2() <= 0.835) {
                    planet[planetNumber].getMarket().addCondition(Conditions.RARE_ORE_ULTRARICH);
                }
            }
            
            // Tectonic Activity
            if(rand2() <= 0.05 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.TECTONIC_ACTIVITY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.TECTONIC_ACTIVITY);
            } else if(rand2() > 0.95 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_TECTONIC_ACTIVITY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_TECTONIC_ACTIVITY);
            }      
            
            // Weather
            if(rand2() <= 0.14 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_WEATHER) &&
                    planetType.equals(new Planet().US_STORM) ||
                    planetType.equals(new Planet().US_MAGNETIC)) {
                if(planetType.equals(new Planet().US_MAGNETIC) &&
                        !planet[planetNumber].getMarket().hasCondition(
                                new Unknown_Conditions().MAGNETIC_CRUST)) {
                    planet[planetNumber].getMarket().addCondition(
                            new Unknown_Conditions().MAGNETIC_CRUST);
                } else {
                    if(!planet[planetNumber].getMarket().hasCondition(
                            new Unknown_Conditions().DUST_STORM)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().DUST_STORM);
                    }
                }
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            } else if(rand2() <= 0.13 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_WEATHER) &&
                    planetType.equals(new Planet().US_AURIC_CLOUDY)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            } else if(rand2() <= 0.12 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.EXTREME_WEATHER) &&
                    planetType.equals(new Planet().US_AURIC) ||
                    planetType.equals(new Planet().US_ALKALI) ||
                    planetType.equals(new Planet().US_RED_WIND) ||
                    planetType.equals(new Planet().US_JUNGLE)) {
                planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            } else {
                if(rand2() <= 0.1)
                    planet[planetNumber].getMarket().addCondition(Conditions.EXTREME_WEATHER);
            }
            
            if(planetType.equals(new Planet().ARID) ||
                    planetType.equals(new Planet().DESERT_A) ||
                    planetType.equals(new Planet().DESERT_B) ||
                    planetType.equals(new Planet().US_ARID) ||
                    planetType.equals(new Planet().US_DESERT_A) ||
                    planetType.equals(new Planet().US_DESERT_B) ||
                    planetType.equals(new Planet().US_DESERT_C) ||
                    planetType.equals(new Planet().TUNDRA)) {
                
                // Other US Conditions
                if(isUS){
                    if(rand2() <= 0.025 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SUB_ICE_BEDROCK)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SUB_ICE_BEDROCK);
                    }

                    if(rand2() <= 0.1 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().PARASITIC_SPORES)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().PARASITIC_SPORES);
                    }

                    if(rand2() <= 0.1 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SHROOMS)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SHROOMS);
                    }
                }
                
                // Arid
                if(planetType.equals(new Planet().ARID) ||
                        planetType.equals(new Planet().US_ARID)) {
                    if(!planet[planetNumber].getMarket().hasCondition(Conditions.ARID))
                        planet[planetNumber].getMarket().addCondition(Conditions.ARID);
                    if(rand2() <= 0.05 &&
                            !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                        planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                    if(rand2() <= 0.05 && isUS && !planet[planetNumber].getMarket().hasCondition(
                            new Unknown_Conditions().DUST_STORM)) {
                        planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().DUST_STORM);
                    }
                // Desert
                } else if(planetType.equals(new Planet().DESERT_A) ||
                    planetType.equals(new Planet().DESERT_B) ||
                    planetType.equals(new Planet().US_DESERT_A) ||
                    planetType.equals(new Planet().US_DESERT_B) ||
                    planetType.equals(new Planet().US_DESERT_C)) {
                    if(!planet[planetNumber].getMarket().hasCondition(Conditions.DESERT)) 
                        planet[planetNumber].getMarket().addCondition(Conditions.DESERT);
                    if(rand2() <= 0.075 &&
                            !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                        planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                    if(rand2() <= 0.05 && isUS && !planet[planetNumber].getMarket().hasCondition(
                            new Unknown_Conditions().DUST_STORM)) {
                        planet[planetNumber].getMarket().addCondition(new Unknown_Conditions().DUST_STORM);
                    }
                } else {
                    if(!planet[planetNumber].getMarket().hasCondition("tundra")) 
                        planet[planetNumber].getMarket().addCondition("tundra");
                    if(rand2() <= 0.05 &&
                            !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                        planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                }
                
                // Organics Arid/Desert
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_COMMON) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_TRACE)) {
                    if(rand2() <= 0.5) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_COMMON);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_TRACE);
                    }
                }
                
                // Farmland Arid/Desert
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_ADEQUATE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_POOR)) {
                    if(rand2() <= 0.33) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_ADEQUATE);
                    } else if(rand2() > 0.33 && rand2() <= 0.66) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_POOR);
                    }
                }
            } else if(planetType.equals(new Planet().TERRAN) ||
                    planetType.equals(new Planet().TERRAN_ECCENTRIC) || 
                    planetType.equals(new Planet().JUNGLE) ||
                    planetType.equals(new Planet().US_TERRAN) ||
                    planetType.equals(new Planet().US_JUNGLE)) {
                
                // Other US Conditions
                if(isUS){
                    if(rand2() <= 0.1 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SUB_ICE_BEDROCK)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SUB_ICE_BEDROCK);
                    }

                    if(rand2() <= 0.125 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().PARASITIC_SPORES)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().PARASITIC_SPORES);
                    }

                    if(rand2() <= 0.125 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SHROOMS)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SHROOMS);
                    }
                }
                
                // Inimical Biosphere
                if(planetType.equals(new Planet().JUNGLE) ||
                    planetType.equals(new Planet().US_JUNGLE)) {
                    if(!planet[planetNumber].getMarket().hasCondition(Conditions.JUNGLE)) 
                        planet[planetNumber].getMarket().addCondition(Conditions.JUNGLE);
                    if(rand2() <= 0.4 &&
                            !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                        planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                    
                // Terran
                } else if(planetType.equals(new Planet().TERRAN) ||
                    planetType.equals(new Planet().TERRAN_ECCENTRIC) || 
                    planetType.equals(new Planet().US_TERRAN)) {
                    if(!planet[planetNumber].getMarket().hasCondition(Conditions.TERRAN))
                        planet[planetNumber].getMarket().addCondition(Conditions.TERRAN);
                    if(rand2() <= 0.15 &&
                            !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                        planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                }
                
                // Organics Terran-like
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_ABUNDANT) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_COMMON) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_PLENTIFUL)) {
                    if(rand2() <= 0.33) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_ABUNDANT);
                    } else if(rand2() > 0.33 && rand2() <= 0.66) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_COMMON);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_PLENTIFUL);
                    }
                }

                // Farmland Terran-like
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_ADEQUATE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_BOUNTIFUL) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_RICH)) {
                    if(rand2() <= 0.33) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_ADEQUATE);
                    } else if(rand2() > 0.33 && rand2() <= 0.66) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_BOUNTIFUL);
                    } else {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_RICH);
                    }
                } 
            } else if(planetType.equals(new Planet().US_ALKALI) ||
                    planetType.equals(new Planet().US_AURIC) ||
                    planetType.equals(new Planet().US_AURIC_CLOUDY)) {
                
                // Other US Conditions
                if(isUS){
                    if(rand2() <= 0.1 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SUB_ICE_BEDROCK)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SUB_ICE_BEDROCK);
                    }

                    if(rand2() <= 0.25 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().PARASITIC_SPORES)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().PARASITIC_SPORES);
                    }

                    if(rand2() <= 0.25 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SHROOMS)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SHROOMS);
                    }
                }
                
                // Inimical Biosphere
                if(rand2() <= 0.4 &&
                        !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                    planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                
                // Organics Terran-like
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_ABUNDANT) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_COMMON) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_PLENTIFUL) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.ORGANICS_TRACE)) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_COMMON);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.ORGANICS_TRACE);
                    }
                }

                // Farmland Terran-like
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_ADEQUATE) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_BOUNTIFUL) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_POOR) ||
                        !planet[planetNumber].getMarket().hasCondition(Conditions.FARMLAND_RICH)) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_ADEQUATE);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_BOUNTIFUL);
                    } else if (rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_RICH);
                    } else if (rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.FARMLAND_POOR);
                    }
                } 
            } else if(planetType.equals(new Planet().OCEAN) ||
                    planetType.equals(new Planet().US_OCEAN_A) ||
                    planetType.equals(new Planet().US_OCEAN_B)) {
                
                // Other US Conditions
                if(isUS){
                    if(rand2() <= 0.025 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SUB_ICE_BEDROCK)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SUB_ICE_BEDROCK);
                    }

                    if(rand2() <= 0.025 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().PARASITIC_SPORES)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().PARASITIC_SPORES);
                    }

                    if(rand2() <= 0.025 &&
                            !planet[planetNumber].getMarket().hasCondition(
                                    new Unknown_Conditions().SHROOMS)) {
                        planet[planetNumber].getMarket().addCondition(
                                new Unknown_Conditions().SHROOMS);
                    }
                }
                
                if(rand2() <= 0.15 &&
                        !planet[planetNumber].getMarket().hasCondition(Conditions.INIMICAL_BIOSPHERE))
                    planet[planetNumber].getMarket().addCondition(Conditions.INIMICAL_BIOSPHERE);
                
                // Oceans
                if(!planet[planetNumber].getMarket().hasCondition(Conditions.WATER) &&
                        !planet[planetNumber].getMarket().hasCondition(Conditions.WATER_SURFACE)) {
                    planet[planetNumber].getMarket().addCondition(Conditions.WATER);
                    planet[planetNumber].getMarket().addCondition(Conditions.WATER_SURFACE);
                }
                if(rand2() <= 0.4 &&
                        !planet[planetNumber].getMarket().hasCondition(Conditions.VOLTURNIAN_LOBSTER_PENS)) {
                    planet[planetNumber].getMarket().addCondition(Conditions.VOLTURNIAN_LOBSTER_PENS);
                }
            }
            
            // Meteors
            if(rand2() <= 0.1 &&
                    !planet[planetNumber].getMarket().hasCondition(Conditions.METEOR_IMPACTS)) {
                planet[planetNumber].getMarket().addCondition(Conditions.METEOR_IMPACTS);
            }
            
            // Volatiles
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_ABUNDANT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_DIFFUSE) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_PLENTIFUL) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VOLATILES_TRACE)) {
                if(rand2() <= 0.1) {
                    if(rand2() <= 0.2) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_ABUNDANT);
                    } else if(rand2() > 0.2 && rand2() <= 0.4) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_DIFFUSE);
                    } else if(rand2() > 0.4 && rand2() <= 0.6) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_PLENTIFUL);
                    } else if(rand2() > 0.6 && rand2() <= 0.8) {
                        planet[planetNumber].getMarket().addCondition(Conditions.VOLATILES_TRACE);
                    } 
                }
            }    
        }     
        
        // Temperature
        for(int counter_2 = 0; counter_2 < star_count; counter_2++) {
            if(!planet[planetNumber].getMarket().hasCondition(Conditions.VERY_HOT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.HOT) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.COLD) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.VERY_COLD) ||
                    !planet[planetNumber].getMarket().hasCondition(Conditions.MILD_CLIMATE)) {
                    if(counter_2 < 0 || counter_2 >= star_count) break;
                    else if(planet[planetNumber].getCircularOrbitRadius() >= habitableZoneMin[counter_2] &&
                            planet[planetNumber].getCircularOrbitRadius() < habitableZoneMiddleMin[counter_2]) {

                        // Hot or Cold
                        if(!planet[planetNumber].getTypeId().equals(new Planet().LAVA) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().LAVA_MINOR) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().US_LAVA) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().US_VOLCANIC)) {
                            if(planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE)) { 
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else if(rand2() <= 0.4 && 
                                    planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE)) {
                                planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                            } else if(rand2() <= 0.4 && 
                                    planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else if(planetType.equals(new Planet().GAS_GIANT) ||
                                    planetType.equals(new Planet().ICE_GIANT) ||
                                    planetType.equals(new Planet().US_GAS_GIANT_A) ||
                                    planetType.equals(new Planet().US_GAS_GIANT_B)) {
                                if(rand2() <= 0.95) {
                                    planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                                }
                            } else {
                                if(rand2() <= 0.2) {
                                    planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                                } else {
                                    planet[planetNumber]. getMarket().addCondition(Conditions.HOT);
                                }
                            }
                        } else if(planetType.equals(new Planet().ARID) ||
                                planetType.equals(new Planet().DESERT_A) ||
                                planetType.equals(new Planet().DESERT_B) ||
                                planetType.equals(new Planet().JUNGLE) ||
                                planetType.equals(new Planet().OCEAN) ||
                                planetType.equals(new Planet().TERRAN) ||
                                planetType.equals(new Planet().TERRAN_ECCENTRIC) ||
                                planetType.equals(new Planet().TUNDRA) ||
                                planetType.equals(new Planet().US_ALKALI) ||
                                planetType.equals(new Planet().US_ARID) ||
                                planetType.equals(new Planet().US_ARID_LIFELESS) ||
                                planetType.equals(new Planet().US_AURIC) ||
                                planetType.equals(new Planet().US_AURIC_CLOUDY) ||
                                planetType.equals(new Planet().US_CRIMSON) ||
                                planetType.equals(new Planet().US_DESERT_A) ||
                                planetType.equals(new Planet().US_DESERT_B) ||
                                planetType.equals(new Planet().US_DESERT_C) ||
                                planetType.equals(new Planet().US_JUNGLE) ||
                                planetType.equals(new Planet().US_LIFELESS) ||
                                planetType.equals(new Planet().US_MAGNETIC) ||
                                planetType.equals(new Planet().US_METHANE) ||
                                planetType.equals(new Planet().US_OCEAN_A) ||
                                planetType.equals(new Planet().US_OCEAN_B) ||
                                planetType.equals(new Planet().US_RED) ||
                                planetType.equals(new Planet().US_RED_WIND) ||
                                planetType.equals(new Planet().US_STORM) ||
                                planetType.equals(new Planet().US_TERRAN)) {
                            if(rand2() <= 0.35) {
                                planet[planetNumber].getMarket().addCondition(Conditions.MILD_CLIMATE);
                            }
                        } else if(planet[planetNumber].getTypeId().equals(new Planet().LAVA) ||
                                planet[planetNumber].getTypeId().equals(new Planet().LAVA_MINOR) ||
                                planet[planetNumber].getTypeId().equals(new Planet().US_LAVA) ||
                                planet[planetNumber].getTypeId().equals(new Planet().US_VOLCANIC)) {
                            if(rand2() <= 0.85) {
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else {
                                planet[planetNumber]. getMarket().addCondition(Conditions.HOT);
                            }
                        }
                    } else if(planet[planetNumber].getCircularOrbitRadius() >= habitableZoneMiddleMin[counter_2] &&
                            planet[planetNumber].getCircularOrbitRadius() <= habitableZoneMiddleMax[counter_2]) {

                        // Hot or Cold
                        if(planetType.equals(new Planet().ARID) ||
                                planetType.equals(new Planet().DESERT_A) ||
                                planetType.equals(new Planet().DESERT_B) ||
                                planetType.equals(new Planet().JUNGLE) ||
                                planetType.equals(new Planet().OCEAN) ||
                                planetType.equals(new Planet().TERRAN) ||
                                planetType.equals(new Planet().TERRAN_ECCENTRIC) ||
                                planetType.equals(new Planet().TUNDRA) ||
                                planetType.equals(new Planet().US_ALKALI) ||
                                planetType.equals(new Planet().US_ARID) ||
                                planetType.equals(new Planet().US_ARID_LIFELESS) ||
                                planetType.equals(new Planet().US_AURIC) ||
                                planetType.equals(new Planet().US_AURIC_CLOUDY) ||
                                planetType.equals(new Planet().US_CRIMSON) ||
                                planetType.equals(new Planet().US_DESERT_A) ||
                                planetType.equals(new Planet().US_DESERT_B) ||
                                planetType.equals(new Planet().US_DESERT_C) ||
                                planetType.equals(new Planet().US_JUNGLE) ||
                                planetType.equals(new Planet().US_LIFELESS) ||
                                planetType.equals(new Planet().US_MAGNETIC) ||
                                planetType.equals(new Planet().US_METHANE) ||
                                planetType.equals(new Planet().US_OCEAN_A) ||
                                planetType.equals(new Planet().US_OCEAN_B) ||
                                planetType.equals(new Planet().US_RED) ||
                                planetType.equals(new Planet().US_RED_WIND) ||
                                planetType.equals(new Planet().US_STORM) ||
                                planetType.equals(new Planet().US_TERRAN)) {
                            if(rand2() <= 0.7) {
                                planet[planetNumber].getMarket().addCondition(Conditions.MILD_CLIMATE);
                            }
                        } else if(planet[planetNumber].getTypeId().equals(new Planet().LAVA) ||
                                planet[planetNumber].getTypeId().equals(new Planet().LAVA_MINOR) ||
                                planet[planetNumber].getTypeId().equals(new Planet().US_LAVA) ||
                                planet[planetNumber].getTypeId().equals(new Planet().US_VOLCANIC)) {
                            if(rand2() <= 0.85) {
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else {
                                planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                            }
                        } else if(!planet[planetNumber].getTypeId().equals(new Planet().LAVA) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().LAVA_MINOR) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().US_LAVA) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().US_VOLCANIC)) {
                            if(planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE)) { 
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else if(rand2() <= 0.4 && 
                                    planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE)) {
                                planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                            } else if(rand2() <= 0.4 && 
                                    planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else if(planetType.equals(new Planet().GAS_GIANT) ||
                                    planetType.equals(new Planet().ICE_GIANT) ||
                                    planetType.equals(new Planet().US_GAS_GIANT_A) ||
                                    planetType.equals(new Planet().US_GAS_GIANT_B)) {
                                if(rand2() <= 0.75) {
                                    planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                                }
                            } else {
                                if(rand2() <= 0.2) {
                                    planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                                } else {
                                    planet[planetNumber]. getMarket().addCondition(Conditions.HOT);
                                }
                            }
                        
                    } else if(planet[planetNumber].getCircularOrbitRadius() > habitableZoneMiddleMax[counter_2] &&
                            planet[planetNumber].getCircularOrbitRadius() <= habitableZoneMax[counter_2]) {

                        // Hot or Cold
                        if(!planet[planetNumber].getTypeId().equals(new Planet().LAVA) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().LAVA_MINOR) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().US_LAVA) ||
                                !planet[planetNumber].getTypeId().equals(new Planet().US_VOLCANIC)) {
                            if(planet[planetNumber].getMarket().hasCondition(Conditions.NO_ATMOSPHERE)) { 
                                planet[planetNumber].getMarket().addCondition(Conditions.COLD);
                            } else if(rand2() <= 0.4 && 
                                    planet[planetNumber].getMarket().hasCondition(Conditions.THIN_ATMOSPHERE)) {
                                planet[planetNumber].getMarket().addCondition(Conditions.COLD);
                            } else if(rand2() <= 0.4 && 
                                    planet[planetNumber].getMarket().hasCondition(Conditions.DENSE_ATMOSPHERE)) {
                                planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                            } else if(planetType.equals(new Planet().GAS_GIANT) ||
                                    planetType.equals(new Planet().ICE_GIANT) ||
                                    planetType.equals(new Planet().US_GAS_GIANT_A) ||
                                    planetType.equals(new Planet().US_GAS_GIANT_B)) {
                                if(rand2() <= 0.45) {
                                    planet[planetNumber].getMarket().addCondition(Conditions.HOT);
                                }
                            }
                        } else if(planetType.equals(new Planet().ARID) ||
                                planetType.equals(new Planet().DESERT_A) ||
                                planetType.equals(new Planet().DESERT_B) ||
                                planetType.equals(new Planet().JUNGLE) ||
                                planetType.equals(new Planet().OCEAN) ||
                                planetType.equals(new Planet().TERRAN) ||
                                planetType.equals(new Planet().TERRAN_ECCENTRIC) ||
                                planetType.equals(new Planet().TUNDRA) ||
                                planetType.equals(new Planet().US_ALKALI) ||
                                planetType.equals(new Planet().US_ARID) ||
                                planetType.equals(new Planet().US_ARID_LIFELESS) ||
                                planetType.equals(new Planet().US_AURIC) ||
                                planetType.equals(new Planet().US_AURIC_CLOUDY) ||
                                planetType.equals(new Planet().US_CRIMSON) ||
                                planetType.equals(new Planet().US_DESERT_A) ||
                                planetType.equals(new Planet().US_DESERT_B) ||
                                planetType.equals(new Planet().US_DESERT_C) ||
                                planetType.equals(new Planet().US_JUNGLE) ||
                                planetType.equals(new Planet().US_LIFELESS) ||
                                planetType.equals(new Planet().US_MAGNETIC) ||
                                planetType.equals(new Planet().US_METHANE) ||
                                planetType.equals(new Planet().US_OCEAN_A) ||
                                planetType.equals(new Planet().US_OCEAN_B) ||
                                planetType.equals(new Planet().US_RED) ||
                                planetType.equals(new Planet().US_RED_WIND) ||
                                planetType.equals(new Planet().US_STORM) ||
                                planetType.equals(new Planet().US_TERRAN)) {
                            if(rand2() <= 0.35) {
                                planet[planetNumber].getMarket().addCondition(Conditions.MILD_CLIMATE);
                            }
                        } else if(planet[planetNumber].getTypeId().equals(new Planet().LAVA) ||
                                planet[planetNumber].getTypeId().equals(new Planet().LAVA_MINOR) ||
                                planet[planetNumber].getTypeId().equals(new Planet().US_LAVA) ||
                                planet[planetNumber].getTypeId().equals(new Planet().US_VOLCANIC)) {
                            if(rand2() <= 0.85) {
                                planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                            } else {
                                planet[planetNumber]. getMarket().addCondition(Conditions.HOT);
                            }
                        }
                        
                    // Planets too close to its stars
                    } else if(planet[planetNumber].getCircularOrbitRadius() < habitableZoneMin[counter_2]) {
                        if(rand2() <= 0.975) {
                            planet[planetNumber].getMarket().addCondition(Conditions.VERY_HOT);
                        } else {
                            planet[planetNumber]. getMarket().addCondition(Conditions.HOT);
                        }
                        
                    // Planets too far from its stars
                    } else if(planet[planetNumber].getCircularOrbitRadius() > habitableZoneMax[counter_2] &&
                            planet[planetNumber].getCircularOrbitRadius() <= habitableZoneMax[counter_2] * 1.25) {
                        
                        // Hot or Cold
                        if(rand2() <= 0.9) {
                            planet[planetNumber].getMarket().addCondition(Conditions.COLD);
                        } else {
                            planet[planetNumber].getMarket().addCondition(Conditions.VERY_COLD);
                        }
                        
                    } else if(planet[planetNumber].getCircularOrbitRadius() > habitableZoneMax[counter_2] * 1.25 &&
                            planet[planetNumber].getCircularOrbitRadius() <= habitableZoneMax[counter_2] * 1.5) {
                        
                        // Hot or Cold
                        if(rand2() <= 0.6) {
                            planet[planetNumber].getMarket().addCondition(Conditions.COLD);
                        } else {
                            planet[planetNumber].getMarket().addCondition(Conditions.VERY_COLD);
                        }
                        
                    } else if(planet[planetNumber].getCircularOrbitRadius() > habitableZoneMax[counter_2] * 1.5 &&
                            planet[planetNumber].getCircularOrbitRadius() <= habitableZoneMax[counter_2] * 1.75) {
                        
                        // Hot or Cold
                        if(rand2() <= 0.3) {
                            planet[planetNumber].getMarket().addCondition(Conditions.COLD);
                        } else {
                            planet[planetNumber].getMarket().addCondition(Conditions.VERY_COLD);
                        }
                        
                    } else {
                        
                        // Hot or Cold
                        if(rand2() <= 0.1) {
                            planet[planetNumber].getMarket().addCondition(Conditions.COLD);
                        } else {
                            planet[planetNumber].getMarket().addCondition(Conditions.VERY_COLD);
                        }
                        
                    }
                }
            }
        }
    }
}
