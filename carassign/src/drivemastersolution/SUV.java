package drivemastersolution;


public class SUV extends Vehicle {
    public SUV(String carId, String vehicleType, String subType, int speed, double fuel,
               int seats, int year, String drivetrain, double price, int quantity) {
        super(carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity);
    }

    @Override
    public String toString() {
        return String.format(
            "Car ID:\t\t%s%nVehicle Type:\t%s%nSub Type:\t%s%nSpeed:\t\t%d%nFuel:\t\t%.1f%nNumber of Seats:\t%d%nYear:\t\t%d%nDriveTrain:\t\t%s%nPrice:\t\t%.0f%nAvailable:\t\t%d%nDriveTrain:\t%s%n",
            carId, vehicleType, subType, speed, fuel, seats, year, drivetrain, price, quantity, drivetrain
        );
    }

    @Override
    public String toDataString() {
        return String.join(";",
            carId, vehicleType, subType, String.valueOf(speed), String.valueOf(fuel),
            String.valueOf(seats), String.valueOf(year), drivetrain, String.valueOf((int)price),
            String.valueOf(quantity)
        );
    }
}
