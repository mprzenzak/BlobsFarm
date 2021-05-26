import Classes.Map.Map;

import java.util.Scanner;

public class Services {
    public static void main(String[] args) {
        System.out.println("Witaj w symulacji agentowej przedstawiającej kolonię blobków \nPodaj parametry wejściowe:");
        System.out.println("Początkowa ilość blobków:");
        Scanner initialParametersScanner = new Scanner(System.in);
        Scanner blobsProportionScanner = new Scanner(System.in);
        Scanner mapDimensionsScanner = new Scanner(System.in);
        int initialBlobsAmount = initialParametersScanner.nextInt();
        System.out.println("Ilość jedzenia:");
        int initialFoodAmount = initialParametersScanner.nextInt();
        System.out.println("Czas trwania symulacji (dni):");
        int simulationLength = initialParametersScanner.nextInt();
        System.out.println("Stosunek liczby agresorów do altruistów, np. 60/40:");
        String blobsProportionRatio = blobsProportionScanner.nextLine();
        String[] blobsProportion = blobsProportionRatio.split("/");
        //killerow zawsze 5%
        int killersNumber = initialBlobsAmount / 20;
        int altruistsNumber = (initialBlobsAmount - killersNumber) * Integer.parseInt(blobsProportion[0]) / Integer.parseInt(blobsProportion[1]);
        int agressorsNumber = initialBlobsAmount - altruistsNumber - killersNumber;
        System.out.println("Podaj wymiary mapy, np. 10x10");
        String mapSize = mapDimensionsScanner.nextLine();
        String[] mapDimensions = mapSize.split("x");
        int mapX = Integer.parseInt(mapDimensions[0]);
        int mapY = Integer.parseInt(mapDimensions[1]);
        Map map = new Map(mapX, mapY, initialFoodAmount);
    }
}


