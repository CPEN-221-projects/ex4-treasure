package pirates;

import java.util.*;

public abstract class PirateManager {

    /**
     * Generate a list of pirates by assigning treasures to the pirate
     * using the name of the pirate in the pirate-to-treasure mapping
     *
     * @param treasures           the sequence of treasures, is not empty
     * @param pirateToTreasureMap pirateToTreasureMap[i] is the name of the pirate
     *                            that is given treasures[i], and is not empty
     * @return a list of pirates with each pirate being assigned the appropriate treasures
     * @throws IllegalArgumentException if treasures.length != pirateToTreasureMap.length
     */
    public static List<Pirate> buildPiratesWithTreasure(Treasure[] treasures, String[] pirateToTreasureMap) {
        if (treasures.length != pirateToTreasureMap.length) {
            throw new IllegalArgumentException();
        }

        List<Pirate> pirateList = new ArrayList<>();

        for (int i = 0; i < pirateToTreasureMap.length; i++) {
            Pirate pirate = new Pirate(pirateToTreasureMap[i]);
            int pirateIdx = pirateList.indexOf(pirate);
            if (pirateIdx != -1) {
                pirate = pirateList.get(pirateIdx);
            } else {
                pirateList.add(pirate);
            }
            pirate.addTreasure(treasures[i]);
        }

        return pirateList;
    }

    /**
     * Is the allocation of treasure to pirates balanced?
     *
     * @param treasures           the original list of treasures, is not null
     * @param pirates             the list of pirates after the treasures have been allocated
     * @param deviationPercentage the allowed variation from the mean
     *                            for the per-pirate treasure value,
     *                            is between 0 and 200
     * @return true if the allocation is balanced and false otherwise
     */
    public static boolean isBalanced(Treasure[] treasures, List<Pirate> pirates, int deviationPercentage) {
        Set<Treasure> pirateTreasures = new HashSet<>();

        for (Pirate pirate : pirates) {
            for (Treasure treasure : pirate.getTreasures()) {
                pirateTreasures.add(treasure);
            }
        }

        int totalTreasureValue = 0;
        for (Treasure treasure : treasures) {
            if (!setContainsTreasure(pirateTreasures, treasure)) {
                return false;
            }
            totalTreasureValue += treasure.value;
        }

        double valueMin = (float) totalTreasureValue / pirates.size() * (100 - deviationPercentage) / 100;
        double valueMax = (float) totalTreasureValue / pirates.size() * (100 + deviationPercentage) / 100;

        for (Pirate pirate : pirates) {
            if (pirate.getTreasureValue() < valueMin || pirate.getTreasureValue() > valueMax) {
                return false;
            }
        }
        return true;
    }

    private static boolean setContainsTreasure(Set<Treasure> pirateTreasures, Treasure treasure) {
        for (Treasure currentTreasure : pirateTreasures) {
            if (currentTreasure.name.equals(treasure.name) && currentTreasure.value == treasure.value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a list of pirates with a maximal balanced allocation
     * to Barbarossa and Jones
     * (see problem description)
     *
     * @param treasures the treasure to allocation, is not null
     * @return pirates with a maximal balanced allocation
     * to Barbarossa and Jones
     */
    public static List<Pirate> balancedAllocation(Treasure[] treasures) {
        Pirate js = new Pirate("Jack Sparrow");
        Pirate hb = new Pirate("Hector Barbarossa");
        Pirate dj = new Pirate("Davy Jones");

        List<Treasure> sparrowTreasures = new ArrayList<>();
        Treasure[][] splitTreasures = giveSparrow(sparrowTreasures, treasures);

        if (splitTreasures == null) {
            for (Treasure treasure : treasures) {
                js.addTreasure(treasure);
            }
        } else {
            for (Treasure treasure : splitTreasures[0]) {
                if (treasure != null) {
                    hb.addTreasure(treasure);
                }
            }

            for (Treasure treasure : splitTreasures[1]) {
                if (treasure != null) {
                    dj.addTreasure(treasure);
                }
            }

            for (Treasure treasure : treasures) {
                if (!(dj.getTreasures().contains(treasure) || hb.getTreasures().contains(treasure))) {
                    js.addTreasure(treasure);
                }
            }
        }

        List<Pirate> pirateList = new ArrayList<>();
        pirateList.add(js);
        pirateList.add(hb);
        pirateList.add(dj);

        return pirateList;
    }

    /**
     * @param sparrowTreasures treasures to give jack sparrow
     * @return a list, with two entries, each with treasure array of equal amount
     */
    private static Treasure[][] giveSparrow(List<Treasure> sparrowTreasures, Treasure[] allTreasures) {
        int allTreasuresValue = getTotalValue(allTreasures);

        Treasure[] sparrowArray = new Treasure[sparrowTreasures.size()];
        sparrowArray = sparrowTreasures.toArray(sparrowArray);
//        for (int i = 0; i < sparrowArray.length; i++) {
//            sparrowArray[i] = sparrowTreasures.get(i);
//        }

        int sparrowValue = getTotalValue(sparrowArray);

        Treasure[] nonSparrowTreasures = new Treasure[allTreasures.length - sparrowArray.length];
        Treasure[][] bestCombo = null;
        int bestValue = 0;

        int nonSparrowIndex = 0;
        for (Treasure treasure : allTreasures) {
            if (!sparrowTreasures.contains(treasure)) {
                nonSparrowTreasures[nonSparrowIndex] = treasure;
                nonSparrowIndex++;
            }
        }

        if ((allTreasuresValue - sparrowValue) % 2 == 0) {
            Treasure[][] currentDistribution = new Treasure[2][2];
            int currentValue = splitInTwo(nonSparrowTreasures, currentDistribution);

            bestValue = currentValue;
            if (currentDistribution[0] != null) {
                bestCombo = currentDistribution;
            }
        }

        for (Treasure treasure : nonSparrowTreasures) {
            List<Treasure> newSparrowTreasures = new ArrayList<>(sparrowTreasures);
            newSparrowTreasures.add(treasure);

            Treasure[][] possibleDistribution = giveSparrow(newSparrowTreasures, allTreasures);

            if (possibleDistribution != null) {
                int possibleValue = 0;
                for (Treasure distribTreasure: possibleDistribution[0]) {
                    if (distribTreasure != null) {
                        possibleValue += distribTreasure.value;
                    }
                }
                if (possibleValue > bestValue) {
                    bestValue = possibleValue;
                    bestCombo = possibleDistribution;
                }
            }

        }

        return bestCombo;
    }

    private static int getTotalValue(Treasure[] treasures) {
        int totalValue = 0;
        for (Treasure treasure : treasures) {
            if (treasure != null) {
                totalValue += treasure.value;
            }
        }
        return totalValue;
    }

    private static int splitInTwo(Treasure[] treasures, Treasure[][] distribution) {
        List<Treasure> list1 = new ArrayList<>();
        List<Treasure> list2 = new ArrayList<>();
        int list1Value = 0;
        int list2Value = 0;

        reverseOrderTreasure comparator = new reverseOrderTreasure();
        Arrays.sort(treasures, comparator);

        for (Treasure treasure : treasures) {
            if (list1Value > list2Value) {
                list2Value += treasure.value;
                list2.add(treasure);
            } else {
                list1Value += treasure.value;
                list1.add(treasure);
            }
        }

        if (list1Value == list2Value) {
            distribution[0] = list1.toArray(distribution[0]);
            distribution[1] = list2.toArray(distribution[1]);
            return list1Value;
        } else {
            return 0;
        }

    }

}
class reverseOrderTreasure implements Comparator<Treasure> {
    @Override
    public int compare(Treasure first, Treasure second) {
        return Integer.compare(second.value, first.value);
    }

}