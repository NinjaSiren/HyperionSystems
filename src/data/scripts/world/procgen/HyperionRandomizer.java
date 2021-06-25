package data.scripts.world.procgen;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author NinjaSiren
 */
public class HyperionRandomizer {

    public int value;
    
    public HyperionRandomizer(int i) {
        value = Randomizer(i);
    }

    private int Randomizer(int size) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.isEmpty()) {
            int index = rand.nextInt(list.size());
            return list.remove(index);
        }
        return 0;
    }
}
