package drivemastersolution;



public class Sedan extends Vehicle {
    private String trunkSize; // L, S, M

    public Sedan(String carId, String vehicleType, String subType, int speed, double fuel,
                 int seats, int year, String drivetrain, double price, int quantity, String trunkSize) {
        super(carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
        this.trunkSize = trunkSize;
    }

    private String trunkFull() {
        switch (trunkSize.toUpperCase()) {
            case "L": return "Large/spacious Trunk";
            case "M": return "Moderate Trunk";
            case "S": return "Small Trunk";
            default: return trunkSize;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Car ID:\t\t%s%nVehicle Type:\t%s%nSub Type:\t%s%nSpeed:\t\t%d%nFuel:\t\t%.1f%nNumber of Seats:\t%d%nYear:\t\t%d%nDriveTrain:\t\t%s%nPrice:\t\t%.0f%nAvailable:\t\t%d%nTrunk Size:\t%s%n",
            carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, trunkFull()
        );
    }

    @Override
    public String toDataString() {
        return String.join(";",
            carId, vehicleType, subType, String.valueOf(speed), String.valueOf(fuel),
            String.valueOf(seats), String.valueOf(year), drivetrain, String.valueOf((int)price),
            String.valueOf(quantity), trunkSize
        );
    }
}
