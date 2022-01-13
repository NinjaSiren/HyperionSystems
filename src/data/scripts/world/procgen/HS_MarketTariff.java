/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.scripts.world.procgen;

import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HS_MarketTariff {
    
    // Sets a random tariff value from 0% to 0.12%
    public float tariff() {
        Random rand = new Random();
        final float max = 0.12f;
        final float min = 0.0f;
        return min + rand.nextFloat() * (max - min);
    }
}
