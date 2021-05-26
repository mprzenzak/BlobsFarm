import java.util.Scanner;

public class Services {
    public static void main(String[] args) {
        System.out.println("Witaj w symulacji agentowej przedstawiającej kolonię blobków \nPodaj parametry wejściowe:");
        System.out.println("Początkowa ilość blobków:");
        Scanner initialParametersScanner = new Scanner(System.in);
        Scanner blobsProportionScanner = new Scanner(System.in);
        int initialBlobsAmount = initialParametersScanner.nextInt();
        System.out.println("Ilość jedzenia:");
        int initialFoodAmount = initialParametersScanner.nextInt();
        System.out.println("Czas trwania symulacji (dni):");
        int simulationLength = initialParametersScanner.nextInt();
        System.out.println("Stosunek liczby altruistow do agresorów, np. 60/40:");
        String blobsProportion = blobsProportionScanner.nextLine();
    }
}


