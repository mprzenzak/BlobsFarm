import Classes.Map.WorldMap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Services is main class that will be used to get parameters from the user and to refresh map.
 *
 * @author Mikołaj Przenzak 259066@student.pwr.edu.pl
 */

public class Services {
    /**
     * The number of iterations of simulation
     */
    private static int simulationLength;
    /**
     * Number of blobs which are spawned on map
     */
    private static int initialBlobsNumber;
    /**
     * Number of food fields which are generated on map
     */
    private static int initialFoodNumber;
    /**
     * Number of bonus fields which are generated on map
     */
    private static int initialBonusesNumber;
    /**
     * Number of trap fields which are generated on map
     */
    private static int initialTrapsNumber;
    /**
     * Number of killers that are spawned on map. Is equal to 5% of all blobs rounded to int plus one.
     */
    private static int initialKillersNumber;
    /**
     * Number of altruists that are spawned on map. Counted on the basis of the altruists to aggressors number ratio.
     */
    private static int initialAltruistsNumber;
    /**
     * Number of aggressors that are spawned on map. Counted on the basis of the altruists to aggressors number ratio.
     */
    private static int initialAggressorsNumber;
    /**
     * Number of blobs that is updated and displayed in every day iteration and saved to statistics (csv)
     */
    private static int currentBlobsNumber;
    /**
     * Number of fields horizontally
     */
    private static int mapWidth;
    /**
     * Number of fields vertically
     */
    private static int mapLength;

    /**
     * Main method called when simulation starts.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Witaj w symulacji agentowej przedstawiającej kolonię blobków \nPodaj parametry wejściowe.\nPamiętaj, że na każdym polu mapy mogą stać maksymalnie dwa blobki oraz, że liczba pól \nz jedzeniem nie może przekraczać 90% pól. Uwzględnij to podczas podawania parametrów");
        Services services = new Services();
        services.getParameters();
        WorldMap map = new WorldMap(mapWidth, mapLength, initialFoodNumber, initialBonusesNumber, initialTrapsNumber, initialAltruistsNumber, initialAggressorsNumber, initialKillersNumber);
        currentBlobsNumber = initialBlobsNumber;
        services.dayIteration(map);
    }

    /**
     * Gets initial parameters from the user.
     */
    private void getParameters() {
        Scanner initialParametersScanner = new Scanner(System.in);
        Scanner blobsProportionScanner = new Scanner(System.in);
        Scanner mapDimensionsScanner = new Scanner(System.in);
        System.out.println("Podaj wymiary mapy, np. 10x10");
        String mapSize = mapDimensionsScanner.nextLine();
        String[] mapDimensions = mapSize.split("x");
        mapWidth = Integer.parseInt(mapDimensions[0]);
        mapLength = Integer.parseInt(mapDimensions[1]);
        System.out.println("Początkowa ilość blobków:");
        initialBlobsNumber = initialParametersScanner.nextInt();
        if (mapWidth * mapLength * 2 < initialBlobsNumber) {
            System.out.println("Blobki nie zmieszczą się na mapie. Spróbuj ponownie");
            getParameters();
        } else {
            System.out.println("Ilość pól z jedzeniem:");
            initialFoodNumber = initialParametersScanner.nextInt();
            if (mapWidth * mapLength * 0.9 < initialFoodNumber) {
                System.out.println("Jedzenie nie zmieści się na mapie. Spróbuj ponownie.");
                getParameters();
            } else {
                System.out.println("Czas trwania symulacji (dni):");
                simulationLength = initialParametersScanner.nextInt();
                System.out.println("Stosunek liczby agresorów do altruistów, np. 60/40:");
                String blobsProportionRatio = blobsProportionScanner.nextLine();
                String[] blobsProportion = blobsProportionRatio.split("/");
                initialBonusesNumber = ((mapWidth * mapLength) - initialFoodNumber) / 2;
                initialTrapsNumber = (mapWidth * mapLength) - initialFoodNumber - initialBonusesNumber;
                initialKillersNumber = (initialBlobsNumber / 20) + 1;
                initialAggressorsNumber = (initialBlobsNumber * Integer.parseInt(blobsProportion[0])) / (Integer.parseInt(blobsProportion[0]) + Integer.parseInt(blobsProportion[1]));
                initialAltruistsNumber = initialBlobsNumber - initialKillersNumber - initialAggressorsNumber;
            }
        }
    }

    /**
     * Refreshed map, displays current characters number and saves statistics to .csv file.
     *
     * @param map is <code>WorldMap</code> class object
     * @throws IOException
     */
    public void dayIteration(WorldMap map) throws IOException {
        for (int i = 1; i <= simulationLength; i++) {
            System.out.println("Dzień " + i + ", Liczba blobków, które przeżyły:");
            map.mapUpdate(mapWidth, mapLength, map);
            currentBlobsNumber -= map.getDiedBlobs();
            if (currentBlobsNumber <= 0) {
                System.out.println("Niestety wszystkie blobki umarły");
                currentBlobsNumber = 0;
            }
            System.out.println(currentBlobsNumber);
            FileWriter csvWriter = new FileWriter("population.csv", true);
            csvWriter.write("Dzień " + i + " " + currentBlobsNumber + "\n");
            csvWriter.flush();
            csvWriter.close();
        }
    }
}


