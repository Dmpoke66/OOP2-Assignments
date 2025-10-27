package drivemastersolution;



public class Hatchback extends Vehicle {
    private String hatchType; // S, T, P

    public Hatchback(String carId, String vehicleType, String subType, int speed, double fuel,
                     int seats, int year, String drivetrain, double price, int quantity, String hatchType) {
        super(carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
        this.hatchType = hatchType;
    }

    private String hatchFull() {
        switch (hatchType.toUpperCase()) {
            case "S": return "Standard Liftgate";
            case "T": return "Split Liftgate";
            case "P": return "Power Liftgate";
            default: return hatchType;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Car ID:\t\t%s%nVehicle Type:\t%s%nSub Type:\t%s%nSpeed:\t\t%d%nFuel:\t\t%.1f%nNumber of Seats:\t%d%nYear:\t\t%d%nDriveTrain:\t\t%s%nPrice:\t\t%.0f%nAvailable:\t\t%d%nHatch Type:\t%s%n",
            carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, hatchFull()
        );
    }

    @Override
    public String toDataString() {
        return String.join(";",
            carId, vehicleType, subType, String.valueOf(speed), String.valueOf(fuel),
            String.valueOf(seats), String.valueOf(year), drivetrain, String.valueOf((int)price),
            String.valueOf(quantity), hatchType
        );
    }
}
