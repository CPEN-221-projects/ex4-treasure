package pirates;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task4PublicTests {

    @Test
    public void testBalancedAllocation1() {
        Treasure[] treasures = new Treasure[] {
                new Treasure("One", 4),
                new Treasure("Two", 7),
                new Treasure("Three", 53)
        };

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));
        assertFalse(PirateManager.isBalanced(treasures, pirates, 0));
        pirates.remove(hb);
        pirates.remove(dj);
        // the only valid allocation is to give everything to Jack Sparrow
        assertTrue(PirateManager.isBalanced(treasures, pirates, 0));
    }

    @Test
    public void testBalancedAllocation2() { // actually works
        Treasure[] treasures = new Treasure[] {
                new Treasure("One", 1),
                new Treasure("Two", 1),
                new Treasure("Three", 1),
                new Treasure("Four", 4),
                new Treasure("Five", 1)
        };

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));
        pirates.remove(js);
        // Jack Sparrow gets nothing
        assertTrue(PirateManager.isBalanced(treasures, pirates, 0));
    }

    @Test
    public void testBalancedAllocation3() { // actually works
        Treasure[] treasures = new Treasure[] {
                new Treasure("One", 42),
                new Treasure("Two", 42),
                new Treasure("Three", 42)
        };

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));
        // equal distribution!
        assertTrue(PirateManager.isBalanced(treasures, pirates, 0));
    }

    @Test
    public void testBalancedAllocation4() {
        Treasure[] treasures = new Treasure[] {
                new Treasure("One", 1),
                new Treasure("Two", 2),
                new Treasure("Three", 3),
                new Treasure("Four", 4),
                new Treasure("Five", 5),
                new Treasure("Six", 6)
        };

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));

        int hbIdx = pirates.indexOf(hb);
        int djIdx = pirates.indexOf(dj);

        assertEquals(pirates.get(hbIdx).getTreasureValue(), 9);
        assertEquals(pirates.get(djIdx).getTreasureValue(), 9);
    }

    @Test
    public void testBalancedAllocation6() {
        Treasure[] treasures = new Treasure[] {
                new Treasure("One", 1),
                new Treasure("Two", 2),
                new Treasure("Three", 3),
        };

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));

        int hbIdx = pirates.indexOf(hb);
        int djIdx = pirates.indexOf(dj);

        assertEquals(pirates.get(hbIdx).getTreasureValue(), 3);
        assertEquals(pirates.get(djIdx).getTreasureValue(), 3);
    }

    @Test
    public void testBalancedAllocation7() {
        Treasure[] treasures = new Treasure[] {
                new Treasure("One", 1),
                new Treasure("Two", 2),
                new Treasure("Three", 3),
                new Treasure("Four", 4)
        };

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));

        int hbIdx = pirates.indexOf(hb);
        int djIdx = pirates.indexOf(dj);

        assertEquals(pirates.get(hbIdx).getTreasureValue(), 5);
        assertEquals(pirates.get(djIdx).getTreasureValue(), 5);
    }

    @Test
    public void testBalancedAllocation100() {
        int[] treasureVal = new int[] {1, 2, 4, 8, 16, 32, 1, 2, 4, 8, 16, 32};

        Treasure[] treasures = new Treasure[treasureVal.length];

        for (int i = 0; i < treasureVal.length; i++) {
            treasures[i] = new Treasure(Integer.toString(i), treasureVal[i]);
        }

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));

        int hbIdx = pirates.indexOf(hb);
        int djIdx = pirates.indexOf(dj);

        assertTrue(pirates.get(hbIdx).getTreasureValue() == 63);
        assertTrue(pirates.get(djIdx).getTreasureValue() == 63);

    }

    @Test
    public void testBalancedAllocation99999() {

        int[] treasureVal = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};

        Treasure[] treasures = new Treasure[treasureVal.length];

        for (int i = 0; i < treasureVal.length; i++) {
            treasures[i] = new Treasure(Integer.toString(i), treasureVal[i]);
        }

        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Pirate> pirates = PirateManager.balancedAllocation(treasures);

        assertEquals(3, pirates.size());
        assertTrue(pirates.contains(js));
        assertTrue(pirates.contains(hb));
        assertTrue(pirates.contains(dj));

        int hbIdx = pirates.indexOf(hb);
        int djIdx = pirates.indexOf(dj);

        assertTrue(pirates.get(hbIdx).getTreasureValue() == pirates.get(djIdx).getTreasureValue());
    }

}
