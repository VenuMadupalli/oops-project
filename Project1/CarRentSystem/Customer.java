package CarRentSystem;

public class Customer {

    private String Name;
    private String MobileNumber;
    private String CustomerId;

    public Customer(String customerId, String name, String mobileNumber) {
        this.Name = name;
        this.MobileNumber = mobileNumber;
        this.CustomerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getCustomerId() {
        return CustomerId;
    }
}
