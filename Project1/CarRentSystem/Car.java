package CarRentSystem;

public class Car {
    private String CarId;
    private String brand;
    private String Model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay, boolean isAvailable) {
        this.CarId = carId;
        this.brand = brand;
        this.Model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = isAvailable;
    }

    public String getCarId() {
        return CarId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return Model;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double CalculatePrice(int rentalDays){
        return basePricePerDay*rentalDays;
    }

    public void rent(){
        isAvailable= false;
    }

    public void returnCar(){
        isAvailable=true;
    }
}

