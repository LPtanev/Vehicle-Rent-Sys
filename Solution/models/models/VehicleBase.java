package Solution.models.models;

import Solution.models.models.contracts.Vehicle;
import Solution.models.utils.ValidationHelper;

import java.time.LocalDate;

public abstract class VehicleBase implements Vehicle {

    private String vehicleBrand;

    private String vehicleModel;

    private String customerName;

    private double vehicleValue;

    private int rentPeriod;

    private LocalDate returnDate;

    private LocalDate startOfRentDate;

    private LocalDate endOfRentDate;

    public VehicleBase(String vehicleBrand, String vehicleModel, String customerName,
                       double vehicleValue, int rentPeriod, LocalDate returnDate,
                       LocalDate startOfRentDate, LocalDate endOfRentDate) {
        setVehicleBrand(vehicleBrand);
        setVehicleModel(vehicleModel);
        setCustomerName(customerName);
        setVehicleValue(vehicleValue);
        setRentPeriod(rentPeriod);
        this.returnDate = returnDate;
        this.startOfRentDate = startOfRentDate;
        this.endOfRentDate = endOfRentDate;
    }

    @Override
    public String getVehicleBrand() {
        return vehicleBrand;
    }

    @Override
    public String getVehicleModel() {
        return vehicleModel;
    }

    @Override
    public double getVehicleValue() {
        return vehicleValue;
    }

    @Override
    public int getRentPeriod() {
        return rentPeriod;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public LocalDate getStartOfRentDate() {
        return startOfRentDate;
    }

    @Override
    public LocalDate getEndOfRentDate() {
        return endOfRentDate;
    }

    public void setVehicleBrand(String vehicleBrand) {
        ValidationHelper.validateStringMinLength(vehicleBrand);
        this.vehicleBrand = vehicleBrand;
    }

    public void setVehicleModel(String vehicleModel) {
        ValidationHelper.validateStringMinLength(vehicleModel);
        this.vehicleModel = vehicleModel;
    }

    public void setCustomerName(String customerName) {
        ValidationHelper.validateStringMinLength(customerName);
        this.customerName = customerName;
    }

    public void setVehicleValue(double vehicleValue) {
        ValidationHelper.validateGreaterThanZero(vehicleValue);
        this.vehicleValue = vehicleValue;
    }

    public void setRentPeriod(int rentPeriod) {
        ValidationHelper.validateGreaterThanZero(rentPeriod);
        this.rentPeriod = rentPeriod;
    }

    protected abstract String getPriceInfo();

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStartOfRentDate(LocalDate startOfRentDate) {
        this.startOfRentDate = startOfRentDate;
    }

    public void setEndOfRentDate(LocalDate endOfRentDate) {
        this.endOfRentDate = endOfRentDate;
    }

    @Override
    public String toString() {
        return String.format(
                "XXXXXXXXXX%n" +
                        "Date: %s%n" +
                        "Customer Name: %s%n" +
                        "Rented Vehicle: %s %s%n%n" +
                        "Reservation start date: %s%n" +
                        "Reservation end date: %s%n" +
                        "Reserved rental days: %s days%n%n" +
                        "Actual return date: %s" +
                        "%s",
                returnDate,
                customerName,
                vehicleBrand,
                vehicleModel,
                startOfRentDate,
                endOfRentDate,
                rentPeriod,
                endOfRentDate,
                getPriceInfo());
    }
}
