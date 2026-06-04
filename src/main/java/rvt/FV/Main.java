package rvt.FV;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.initializeDatabase();
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=================================");
            System.out.println("1 - Pievienot kategoriju");
            System.out.println("2 - Pievienot produktu");
            System.out.println("3 - Parādīt visas kategorijas");
            System.out.println("4 - Parādīt visus produktus");
            System.out.println("5 - Meklēt produktus pēc kategorijas");
            System.out.println("0 - Iziet");
            System.out.println("=================================");
            System.out.print("Izvēlies darbību: ");

            String izvele = scanner.nextLine();

            if (izvele.equals("0")) {
                System.out.println("Programma beidz darbu. Atā!");
                break;
            }

            switch (izvele) {
                case "1":
                    System.out.print("Ievadi kategorijas nosaukumu: ");
                    String katNosaukums = scanner.nextLine();
                    Category.addCategory(katNosaukums);
                    break;

                case "2":
                    System.out.print("Ievadi produkta nosaukumu: ");
                    String prodNosaukums = scanner.nextLine();
                    
                    System.out.print("Ievadi cenu: ");
                    double cena = Double.parseDouble(scanner.nextLine());
                    
                    System.out.print("Ievadi kategorijas ID: ");
                    int katId = Integer.parseInt(scanner.nextLine());
                    
                    Product.addProduct(prodNosaukums, cena, katId);
                    break;

                case "3":
                    Category.printAllCategories();
                    break;

                case "4":
                    Product.printAllProducts();
                    break;

                case "5":
                    System.out.print("Ievadi kategorijas ID vai nosaukumu: ");
                    String meklet = scanner.nextLine();
                    Product.searchByCategory(meklet);
                    break;

                default:
                    System.out.println("Nepareiza komanda, mēģini vēlreiz!");
            }
        }
        scanner.close();
    }
}