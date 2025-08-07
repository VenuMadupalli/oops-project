package CarRentSystem;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem= new CarRentalSystem();

        Car car1= new Car("C9144", "Mahindra", "Thar", 2300, true);
        Car car2 = new Car("C5467", "Tata", "CURV", 2400, true);
        Car car3 = new Car("C2345", "Hyundai", "Creta", 3000, true);
        Car car4 = new Car("C8765", "Honda", "City", 2500, true);
        Car car5 = new Car("C4321", "Toyota", "Glanza", 2200, true);
        Car car6 = new Car("C9987", "Mahindra", "XUV300", 2800, true);
        Car car7 = new Car("C1122", "Kia", "Seltos", 3100, true);
        Car car8 = new Car("C3344", "Renault", "Kiger", 1900, true);
        Car car9 = new Car("C5566", "Ford", "EcoSport", 2700, true);
        Car car10 = new Car("C7788", "Volkswagen", "Polo", 2100, true);

        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);
        rentalSystem.addCar(car7);
        rentalSystem.addCar(car8);
        rentalSystem.addCar(car9);
        rentalSystem.addCar(car10);

        rentalSystem.menu();

    }
}
