import Classes.Map.WorldMap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Services {
    private static int simulationLength;
    private static int initialBlobsNumber;
    private static int initialFoodNumber;
    private static int initialBonusesNumber;
    private static int initialTrapsNumber;
    private static int initialKillersNumber;
    private static int initialAltruistsNumber;
    private static int initialAggressorsNumber;
    private static int currentBlobsNumber;
    private static int mapWidth;
    private static int mapLength;

    public static void main(String[] args) throws IOException {
        System.out.println("Witaj w symulacji agentowej przedstawiającej kolonię blobków \nPodaj parametry wejściowe.\nPamiętaj, że na każdym polu mapy mogą stać maksymalnie dwa blobki oraz, że liczba pól \nz jedzeniem nie może przekraczać 90% pól. Uwzględnij to podczas podawania parametrów");
        getParameters();
        WorldMap map = new WorldMap(mapWidth, mapLength, initialFoodNumber, initialBonusesNumber, initialTrapsNumber, initialAltruistsNumber, initialAggressorsNumber, initialKillersNumber);
        currentBlobsNumber = initialBlobsNumber;
        dayIteration();
    }

    private static void getParameters() {
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
        }
        System.out.println("Ilość pól z jedzeniem:");
        initialFoodNumber = initialParametersScanner.nextInt();
        if (mapWidth * mapLength * 0.9 < initialFoodNumber) {
            System.out.println("Jedzenie nie zmieści się na mapie. Spróbuj ponownie.");
            getParameters();
        }
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

    public static void dayIteration() throws IOException {
        for (int i = 1; i <= simulationLength; i++) {
            System.out.println("Day " + i);
            System.out.println(currentBlobsNumber);
            WorldMap.mapUpdate();
            currentBlobsNumber -= WorldMap.getDiedBlobs();
            if (currentBlobsNumber <= 0) {
                System.out.println("Niestety wszystkie blobki umarły");
                currentBlobsNumber = 0;
            }
            FileWriter csvWriter = new FileWriter("population.csv", true);
            csvWriter.write("Day " + i + " " + currentBlobsNumber + "\n");
            csvWriter.flush();
            csvWriter.close();
        }
    }
}


