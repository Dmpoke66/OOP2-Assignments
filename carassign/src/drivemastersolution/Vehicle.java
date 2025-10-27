package drivemastersolution;



public abstract class Vehicle {
    protected String carId;
    protected String vehicleType;
    protected String subType;
    protected int speed;
    protected double fuel;
    protected int seats;
    protected int year;
    protected String drivetrain;
    protected double price;
    protected int quantity;

    public Vehicle(String carId, String vehicleType, String subType, int speed, double fuel,
                   int seats, int year, String drivetrain, double price, int quantity) {
        this.carId = carId;
        this.vehicleType = vehicleType;
        this.subType = subType;
        this.speed = speed;
        this.fuel = fuel;
        this.seats = seats;
        this.year = year;
        this.drivetrain = drivetrain;
        this.price = price;
        this.quantity = quantity;
    }

    public String getCarId() { return carId; }
    public String getVehicleType() { return vehicleType; }
    public String getSubType() { return subType; }
    public int getQuantity() { return quantity; }
    public void decrementQuantity() { if (quantity > 0) quantity--; }

    public abstract String toString(); // human readable vertical output
    public abstract String toDataString(); // original file format
}
