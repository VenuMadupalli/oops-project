// Source code is decompiled from a .class file using FernFlower decompiler.
package CarRentSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;
    private int CustomerCount = 1;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            rentals.add(new Rental(car, customer, days));
            car.rent();
        } else {
            System.out.println("Currently Car is not available.");
        }
    }

    public void returnCar(Car car) {
        Rental removeRental = null;
        for (Rental rent : rentals) {
            if (rent.getCar() == car) {
                removeRental = rent;
                break;
            }
        }

        if (removeRental != null) {
            rentals.remove(removeRental);
            car.returnCar();
            System.out.println("Car returned Successfully..");
        } else {
            System.out.println("Car was not returned..");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");
            System.out.println("Enter your choice..");

            int choice = sc.nextInt();
            sc.nextLine(); // to consume next Line

            switch (choice) {
                case 1: {
                    System.out.println("# Rent a car...");

                    System.out.println("Enter your name: ");
                    String customerName = sc.nextLine();

                    // System.out.println("Enter your Mobile nunber: ");
                    // String MobileNo= sc.nextLine();

                    String MobileNo;
                    while (true) {
                        System.out.print("Enter your Mobile number (10 digits): ");
                        MobileNo = sc.nextLine().trim();

                        // Check if the number is 10 digits and only contains digits
                        if (MobileNo.matches("\\d{10}")) {
                            break; // Valid input
                        } else {
                            System.out.println("Invalid mobile number! Please enter exactly 10 digits.");
                        }
                    }

                    System.out.println("Hello " + customerName + "! Welcome to My car rental application");
                    System.out.println("Available cars..");
                    for (Car car : cars) {
                        if (car.isAvailable()) {
                            System.out.println("Id: " + car.getCarId() + " - Model: " + car.getModel() + " - Brand: "
                                    + car.getBrand() + " - Price per day: " + car.getBasePricePerDay());
                        }
                    }
                    // System.out.println("Enter the car ID you wanna rent: ");
                    // String carId= sc.nextLine();

                    String carID;
                    boolean isPresent;
                    while (true) {
                        System.out.print("Enter the car ID you want to rent: ");
                        carID = sc.nextLine();
                        isPresent = false; // reset before each check
                        for (Car id : cars) {
                            if (id.getCarId().equals(carID) && id.isAvailable()) {
                                isPresent = true;
                                break; // Found a valid car, no need to continue loop
                            }
                        }
                        if (isPresent) {
                            break; // Exit the while loop if car ID is valid and available
                        } else {
                            System.out.println("Car ID mis-match or car is not available. Try again.");
                        }
                    }

                    System.out.println("Enter the number of days you want this car: ");
                    int rentDays = sc.nextInt();
                    sc.nextLine();

                    String CustomerId = "CUS" + CustomerCount++;

                    Customer newCustomer = new Customer(CustomerId, customerName, MobileNo);
                    addCustomer(newCustomer);

                    Car selectedCar = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(carID) && car.isAvailable()) {
                            selectedCar = car;
                            break;
                        }
                    }
                    if (selectedCar == null) {
                        System.out.println("Car is not available");
                    } else {
                        double totalPrice = selectedCar.CalculatePrice(rentDays);
                        System.out.println("=== Rental Information ===");
                        System.out.println("Customer Id: " + newCustomer.getCustomerId());
                        System.out.println("Customer Name: " + newCustomer.getName());
                        System.out.println("Customer Mobile number: " + newCustomer.getMobileNumber());
                        System.out.println("Car Id: " + selectedCar.getCarId());
                        System.out.println("Car Brand: " + selectedCar.getBrand());
                        System.out.println("Car Model: " + selectedCar.getModel());
                        System.out.println("Rental days: " + rentDays);
                        System.out.println("Total price(in Rupees): " + totalPrice);

                        System.out.println("\nConfirm your booking (YES/NO): ");
                        String confirm = sc.nextLine();

                        if (confirm.equals("YES")) {
                            rentCar(selectedCar, newCustomer, rentDays);
                            System.out.println("\n Your booking confirmed..");
                        } else {
                            System.out.println("Booking Failed.");
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("=== Return a Car.. ===");
                    sc.nextLine(); // in case previous input was nextInt()
                    System.out.print("Enter the car Id you wanna return: ");
                    String carId = sc.nextLine();
                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(carId) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }
                    if (carToReturn != null) {
                        Customer customer = null;
                        Rental rentalToRemove = null;

                        for (Rental rental : rentals) {
                            if (rental.getCar() == carToReturn) {
                                customer = rental.getCustomer();
                                rentalToRemove = rental;
                                break;
                            }
                        }

                        if (customer != null) {
                            returnCar(carToReturn); // mark car as available
                            rentals.remove(rentalToRemove); // optional: clean rental record
                            System.out.println("Car returned successfully by " + customer.getName());
                        } else {
                            System.out.println("Information not matched.\nTry again.");
                        }
                    } else {
                        System.out.println("No rented car found with that ID.");
                    }
                    break;
                }

                case 3: {
                    running = false;
                    System.out.println("Thank you for using the Car rental system");
                    break;
                }

                default: {
                    System.out.println("Invalid input please choose among the above options.");
                }
            }
        }
    }

}

// import java.io.PrintStream;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;
// import java.util.Scanner;

// import Car;

// class CarRentalSystem {
// private List<Car> cars = new ArrayList();
// private List<Customer> customers = new ArrayList();
// private List<Rental> rentals = new ArrayList();
// private int CustomerCount = 1;

// public CarRentalSystem() {
// }

// public void addCar(Car var1) {
// this.cars.add(var1);
// }

// public void addCustomer(Customer var1) {
// this.customers.add(var1);
// }

// public void rentCar(Car var1, Customer var2, int var3) {
// if (var1.isAvailable()) {
// this.rentals.add(new Rental(var1, var2, var3));
// var1.rent();
// } else {
// System.out.println("Currently Car is not available.");
// }

// }

// public void returnCar(Car var1) {
// Rental var2 = null;
// Iterator var3 = this.rentals.iterator();

// while(var3.hasNext()) {
// Rental var4 = (Rental)var3.next();
// if (var4.getCar() == var1) {
// var2 = var4;
// break;
// }
// }

// if (var2 != null) {
// this.rentals.remove(var2);
// var1.returnCar();
// System.out.println("Car returned Successfully..");
// } else {
// System.out.println("Car was not returned..");
// }

// }

// public void menu() {
// Scanner var1 = new Scanner(System.in);
// boolean var2 = true;

// while(true) {
// label103:
// while(var2) {
// System.out.println("===== Car Rental System =====");
// System.out.println("1. Rent a car");
// System.out.println("2. Return a car");
// System.out.println("3. Exit");
// System.out.println("Enter your choice..");
// int var3 = var1.nextInt();
// var1.nextLine();
// String var4;
// Iterator var6;
// Car var7;
// switch (var3) {
// case 1:
// System.out.println("# Rent a car...");
// System.out.println("Enter your name: ");
// var4 = var1.nextLine();

// while(true) {
// System.out.print("Enter your Mobile number (10 digits): ");
// String var15 = var1.nextLine().trim();
// if (var15.matches("\\d{10}")) {
// System.out.println();
// System.out.println("Hello " + var4 + "! Welcome to My car rental
// application\n");
// System.out.println("Available cars..");
// var6 = this.cars.iterator();

// while(var6.hasNext()) {
// var7 = (Car)var6.next();
// if (var7.isAvailable()) {
// PrintStream var10000 = System.out;
// String var10001 = var7.getCarId();
// var10000.println("Id: " + var10001 + " - Model: " + var7.getModel() + " -
// Brand: " + var7.getBrand() + " - Price per day: " +
// var7.getBasePricePerDay());
// }
// }

// Car var19 = null;

// while(true) {
// System.out.println("Enter the car ID you wanna rent: ");
// String var18 = var1.nextLine();
// boolean var20 = false;
// Iterator var21 = this.cars.iterator();

// while(var21.hasNext()) {
// Car var10 = (Car)var21.next();
// if (var10.getCarId().equals(var18) && var10.isAvailable()) {
// var20 = true;
// var19 = var10;
// break;
// }
// }

// if (var20) {
// System.out.println("Enter the number of days you want this car: ");
// int var22 = var1.nextInt();
// var1.nextLine();
// int var10002 = this.CustomerCount++;
// String var23 = "CUS" + var10002;
// Customer var11 = new Customer(var23, var4, var15);
// this.addCustomer(var11);
// double var12 = var19.CalculatePrice(var22);
// System.out.println("=== Rental Information ===");
// System.out.println("Customer Id: " + var11.getCustomerId());
// System.out.println("Customer Name: " + var11.getName());
// System.out.println("Customer Mobile Number: " + var11.getMobileNumber());
// System.out.println("Car ID: " + var19.getCarId());
// System.out.println("Car Barnd: " + var19.getBrand());
// System.out.println("Car Model: " + var19.getModel());
// System.out.println("Rental days: " + var22 + "days");
// System.out.println("Total price(in Rupees): " + var12);
// System.out.println("\nConfirm your booking (YES/NO): ");
// String var14 = var1.nextLine();
// if (var14.equals("YES")) {
// this.rentCar(var19, var11, var22);
// System.out.println("\n Your booking confirmed..\n");
// } else {
// System.out.println("Booking Failed.\n");
// }
// continue label103;
// }

// System.out.println("Car ID Mis-Match..");
// }
// }

// System.out.println("Invalid mobile number! Please enter exactly 10 digits.");
// }
// case 2:
// System.out.println("=== Return a Car ===");
// var1.nextLine();
// System.out.print("Enter the car Id you wanna return: ");
// var4 = var1.nextLine();
// Car var5 = null;
// var6 = this.cars.iterator();

// while(var6.hasNext()) {
// var7 = (Car)var6.next();
// if (var7.getCarId().equals(var4) && !var7.isAvailable()) {
// var5 = var7;
// break;
// }
// }

// if (var5 == null) {
// System.out.println("Car not found or already available.");
// } else {
// Customer var16 = null;
// Rental var17 = null;
// Iterator var8 = this.rentals.iterator();

// while(var8.hasNext()) {
// Rental var9 = (Rental)var8.next();
// if (var9.getCar().getCarId().equals(var5.getCarId())) {
// var16 = var9.getCustomer();
// var17 = var9;
// break;
// }
// }

// if (var16 != null) {
// this.returnCar(var5);
// this.rentals.remove(var17);
// System.out.println("Car returned successfully by " + var16.getName());
// } else {
// System.out.println("Rental info not matched.");
// }
// }
// break;
// case 3:
// var2 = false;
// System.out.println("Thank you for using the Car rental system");
// break;
// default:
// System.out.println("Invalid input please choose among the above options.");
// }
// }

// var1.close();
// return;
// }
// }
// }
