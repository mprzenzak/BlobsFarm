import Classes.Map.AMapField;
import Classes.Map.FoodField;
import Classes.Map.WorldMap;
import org.w3c.dom.ls.LSOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Services {
    private static int simulationLength;
    private static int initialBlobsAmount;
    private static int initialFoodAmount;
    private static int initialKillersNumber;
    private static int initialAltruistsNumber;
    private static int initialAgressorsNumber;
    public static AMapField[][] dupa= new AMapField[10][10];;

    public static void main(String[] args) throws IOException {
        System.out.println("Witaj w symulacji agentowej przedstawiającej kolonię blobków \nPodaj parametry wejściowe:");
        System.out.println("Początkowa ilość blobków:");
        Scanner initialParametersScanner = new Scanner(System.in);
        Scanner blobsProportionScanner = new Scanner(System.in);
        Scanner mapDimensionsScanner = new Scanner(System.in);
        //initialBlobsAmount = initialParametersScanner.nextInt();
        initialBlobsAmount = 40;//Integer.parseInt(args[0]);
        System.out.println("Ilość jedzenia:");
        //initialFoodAmount = initialParametersScanner.nextInt();
        initialFoodAmount = 20;//Integer.parseInt(args[1]);
        System.out.println("Czas trwania symulacji (dni):");
        //simulationLength = initialParametersScanner.nextInt();
        simulationLength = 10;//Integer.parseInt(args[2]);
        System.out.println("Stosunek liczby agresorów do altruistów, np. 60/40:");
        //String blobsProportionRatio = blobsProportionScanner.nextLine();
        String blobsProportionRatio = "4/6";//args[3];
        String[] blobsProportion = blobsProportionRatio.split("/");
        //killerow zawsze 5%
        initialKillersNumber = initialBlobsAmount / 20;
        initialAltruistsNumber = (initialBlobsAmount - initialKillersNumber) * Integer.parseInt(blobsProportion[0]) / Integer.parseInt(blobsProportion[1]);
        initialAgressorsNumber = initialBlobsAmount - initialAltruistsNumber - initialKillersNumber;
        System.out.println("Podaj wymiary mapy, np. 10x10");
        //String mapSize = mapDimensionsScanner.nextLine();
        String mapSize = "10x10";//args[4];
        String[] mapDimensions = mapSize.split("x");
        int mapWidth = Integer.parseInt(mapDimensions[0]);
        int mapLength = Integer.parseInt(mapDimensions[1]);
        //TODO chyba bez -1
        WorldMap map = new WorldMap(mapWidth - 1, mapLength - 1, initialFoodAmount, initialAltruistsNumber, initialAgressorsNumber, initialKillersNumber);

        dayIteration();
    }

    public static void dayIteration() throws IOException {
        for (int i = 1; i <= simulationLength; i++) {
            //System.out.println("Day " + i);

            FileWriter csvWriter = new FileWriter("population.csv", true);
            csvWriter.write("Day " + i + " " + initialBlobsAmount + "\n");
            csvWriter.flush();
            csvWriter.close();
            WorldMap.mapUpdate();
        }
    }
}


