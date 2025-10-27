package drivemastersolution;


public class Hybrid extends Vehicle {
    private String powerTrain; // E, A, PHEV
    private int electricRange;

    public Hybrid(String carId, String vehicleType, String subType, int speed, double fuel,
                  int seats, int year, String drivetrain, double price, int quantity, String powerTrain, int electricRange) {
        super(carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
        this.powerTrain = powerTrain;
        this.electricRange = electricRange;
    }

    private String powerFull() {
        if (powerTrain.equalsIgnoreCase("E")) return "Series Hybrid";
        if (powerTrain.equalsIgnoreCase("A")) return "Parallel Hybrid";
        if (powerTrain.equalsIgnoreCase("PHEV")) return "Plug-in Hybrid";
        return powerTrain;
    }

    @Override
    public String toString() {
        return String.format(
            "Car ID:\t\t%s%nVehicle Type:\t%s%nSub Type:\t%s%nSpeed:\t\t%d%nFuel:\t\t%.1f%nNumber of Seats:\t%d%nYear:\t\t%d%nDriveTrain:\t\t%s%nPrice:\t\t%.0f%nAvailable:\t\t%d%nPower Train:\t%s%nElectric Range:\t%d%n",
            carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, powerFull(), electricRange
        );
    }

    @Override
    public String toDataString() {
        return String.join(";",
            carId, vehicleType, subType, String.valueOf(speed), String.valueOf(fuel),
            String.valueOf(seats), String.valueOf(year), drivetrain, String.valueOf((int)price),
            String.valueOf(quantity), powerTrain, String.valueOf(electricRange)
        );
    }
}
