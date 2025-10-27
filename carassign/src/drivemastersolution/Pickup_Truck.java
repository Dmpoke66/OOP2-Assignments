package drivemastersolution;


public class Pickup_Truck extends Vehicle {
    private String cargoBeds; // SB, EB, DB
    private int cargoCapacity;

    public Pickup_Truck(String carId, String vehicleType, String subType, int speed, double fuel,
                       int seats, int year, String drivetrain, double price, int quantity, String cargoBeds, int cargoCapacity) {
        super(carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
        this.cargoBeds = cargoBeds;
        this.cargoCapacity = cargoCapacity;
    }

    private String bedsFull() {
        switch (cargoBeds.toUpperCase()) {
            case "SB": return "Short Bed";
            case "EB": return "Extended Bed";
            case "DB": return "Dump Bed";
            default: return cargoBeds;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Car ID:\t\t%s%nVehicle Type:\t%s%nSub Type:\t%s%nSpeed:\t\t%d%nFuel:\t\t%.1f%nNumber of Seats:\t%d%nYear:\t\t%d%nDriveTrain:\t\t%s%nPrice:\t\t%.0f%nAvailable:\t\t%d%nCargo Capacity:\t%d%nCargo Beds:\t%s%n",
            carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, cargoCapacity, bedsFull()
        );
    }

    @Override
    public String toDataString() {
        return String.join(";",
            carId, vehicleType, subType, String.valueOf(speed), String.valueOf(fuel),
            String.valueOf(seats), String.valueOf(year), drivetrain, String.valueOf((int)price),
            String.valueOf(quantity), cargoBeds, String.valueOf(cargoCapacity)
        );
    }
}
