



import drivemastersolution.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DriveMasterApp {
    private static final String DATA_FILE = "res/vehicles.txt";
    private List<Vehicle> vehicles = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new DriveMasterApp().run();
    }

    private void run() {
        loadVehicles();
        boolean done = false;
        while (!done) {
            printMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": purchaseVehicle(); break;
                case "2": displayByType(); break;
                case "3": displayBySubtype(); break;
                case "4": randomList(); break;
                case "5": saveAndExit(); done = true; break;
                default: System.out.println("Invalid option."); break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\nWelcome to DriveMasters\n");
        System.out.println("Please choose any option below\n");
        System.out.println("---------------------------------");
        System.out.println("1\tPurchase Vehicle");
        System.out.println("2\tDisplay Vehicles by Type");
        System.out.println("3\tDisplay Vehicles by Subtype");
        System.out.println("4\tProduce a Random List of Vehicles");
        System.out.println("5\tSave & Exit\n");
        System.out.print("Enter option: ");
    }

    private void loadVehicles() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] tokens = line.split(";");
                String carId = tokens[0];
                char typeDigit = carId.charAt(0);
                String vtype = tokens[1];
                String subtype = tokens[2];
                int speed = Integer.parseInt(tokens[3]);
                double fuel = Double.parseDouble(tokens[4]);
                int seats = Integer.parseInt(tokens[5]);
                int year = Integer.parseInt(tokens[6]);
                String drivetrain = tokens[7];
                double price = Double.parseDouble(tokens[8]);
                int quantity = Integer.parseInt(tokens[9]);

                switch (typeDigit) {
                    case '1': 
                        String trunk = tokens[10];
                        vehicles.add(new Sedan(carId, vtype, subtype, speed, fuel, seats, year, drivetrain, price, quantity, trunk));
                        break;
                    case '2': 
                        String hatch = tokens[10];
                        vehicles.add(new Hatchback(carId, vtype, subtype, speed, fuel, seats, year, drivetrain, price, quantity, hatch));
                        break;
                    case '3': 
                        vehicles.add(new SUV(carId, vtype, subtype, speed, fuel, seats, year, drivetrain, price, quantity));
                        break;
                    case '4': case '5': 
                        String power = tokens[10];
                        int eRange = Integer.parseInt(tokens[11]);
                        vehicles.add(new Hybrid(carId, vtype, subtype, speed, fuel, seats, year, drivetrain, price, quantity, power, eRange));
                        break;
                    case '6': 
                        String beds = tokens[10];
                        int cap = Integer.parseInt(tokens[11]);
                        vehicles.add(new Pickup_Truck(carId, vtype, subtype, speed, fuel, seats, year, drivetrain, price, quantity, beds, cap));
                        break;
                    default:
                      
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read " + DATA_FILE + " (" + e.getMessage() + "). Starting with empty list.");
        }
    }

    private void purchaseVehicle() {
        System.out.print("\nEnter CarId: ");
        String id = sc.nextLine().trim();
        Vehicle v = findByCarId(id);
        if (v == null) {
            System.out.println("\nCarID not found.");
            return;
        }
        if (v.getQuantity() <= 0) {
            System.out.println("\nVehicle not available.");
            return;
        }
        v.decrementQuantity();
        System.out.printf("\nThe Vehicle \"%s %s\" has been checked out.%n", v.getVehicleType(), v.getSubType());
    }

    private Vehicle findByCarId(String id) {
        for (Vehicle v : vehicles) if (v.getCarId().equals(id)) return v;
        return null;
    }

    private void displayByType() {
        System.out.print("\nEnter vehicle type to search for: (Sedan, SUV, Hatchback, Pickup Truck and Hybrid car) ");
        String typeIn = sc.nextLine().trim().toLowerCase();
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getVehicleType().toLowerCase().equals(typeIn)) matches.add(v);
        }
        if (matches.isEmpty()) {
            System.out.println("\nNo matching vehicles.");
            return;
        }
        System.out.println("\nMatching vehicles:\n");
        for (Vehicle v : matches) {
            System.out.println(v.toString());
            System.out.println();
        }
    }

    private void displayBySubtype() {
        System.out.println("\n#\tSub Type\n1\tSedan\n2\tHatchBack\n3\tSUV\n4\tHybrid\n5\tPickup Truck\n");
        System.out.print("Enter type of vehicle: ");
        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.print("Enter a format (L for Large/Spacious trunk, S for Small Trunk, or M for Moderate Trunk): ");
                String t = sc.nextLine().trim().toUpperCase();
                printMatches(v -> v instanceof Sedan && ((Sedan)v).toDataString().endsWith(t));
                break;
            case "2":
                System.out.print("Enter HatchType (S for Standard Liftgate, T for Split Liftgate, P for Power Liftgate): ");
                String h = sc.nextLine().trim().toUpperCase();
                printMatches(v -> v instanceof Hatchback && ((Hatchback)v).toDataString().endsWith(h));
                break;
            case "3":
                System.out.print("Enter Drivetrain (AWD for All Wheel Drive, 4WD for Four Wheel Drive): ");
                String d = sc.nextLine().trim().toUpperCase();
                printMatches(v -> v instanceof SUV && v.getVehicleType().equalsIgnoreCase("SUV") && v.toString().toUpperCase().contains("DRIVETRAIN:\t\t" + d));
                break;
            case "4":
                System.out.print("Enter a PowerTrain (E for Series Hybrid , A for Parallel Hybrid, PHEV for Plug-in Hybrid): ");
                String p = sc.nextLine().trim().toUpperCase();
                printMatches(v -> v instanceof Hybrid && ((Hybrid)v).toDataString().contains(";" + p + ";"));
                break;
            case "5":
                System.out.print("Enter CargoBeds (SB for Short Beds , EB for Extended Beds, DB for Dump Beds): ");
                String cb = sc.nextLine().trim().toUpperCase();
                printMatches(v -> v instanceof Pickup_Truck && ((Pickup_Truck)v).toDataString().contains(";" + cb + ";"));
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    private void printMatches(java.util.function.Predicate<Vehicle> pred) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : vehicles) if (pred.test(v)) matches.add(v);
        if (matches.isEmpty()) {
            System.out.println("\nNo matching vehicles.");
            return;
        }
        System.out.println("\nMatching Vehicles:\n");
        for (Vehicle v : matches) {
            System.out.println(v.toString());
            System.out.println();
        }
    }

    private void randomList() {
        System.out.print("\nEnter number of vehicles to display: ");
        int n;
        try { n = Integer.parseInt(sc.nextLine().trim()); }
        catch (Exception e) { System.out.println("Invalid number."); return; }
        if (n <= 0) { System.out.println("Number must be > 0."); return; }
        Random r = new Random();
        System.out.println("\nRandom Vehicles:\n");
        if (vehicles.isEmpty()) { System.out.println("No vehicles available."); return; }
        for (int i = 0; i < n; i++) {
            Vehicle v = vehicles.get(r.nextInt(vehicles.size()));
            System.out.println(v.toString());
            System.out.println("----------------------------------\n");
        }
    }

    private void saveAndExit() {
        System.out.print("\nSaving Vehicles... ");
        List<String> lines = new ArrayList<>();
        for (Vehicle v : vehicles) lines.add(v.toDataString());
        try {
            Files.write(Paths.get(DATA_FILE), lines);
            System.out.println("Done!");
            System.out.println("\nGoodbye!");
        } catch (IOException e) {
            System.out.println("Failed (" + e.getMessage() + ")");
        }
    }
}
