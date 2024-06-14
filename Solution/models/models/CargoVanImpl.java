package Solution.models.models;

import Solution.models.models.contracts.CargoVan;
import Solution.models.utils.ValidationHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CargoVanImpl extends VehicleBase implements CargoVan {

    private static final int CARGO_VAN_RENT_DAILY_COST = 50;

    private static final int CARGO_VAN_RENT_DAILY_COST_DISCOUNT = 10;

    private static final double CARGO_VAN_INSURANCE_COST_PER_DAY = 0.03;

    private static final double INSURANCE_COST_REDUCTION = 0.15;

    private int driverExperience;

    long actualRentDays = calculateActualRentDays();
    long unusedRentDays = getRentPeriod() - actualRentDays;

    public CargoVanImpl(String vehicleBrand, String vehicleModel, String customerName, double vehicleValue,
                        int rentPeriod,
                        LocalDate returnDate, LocalDate startOfRentDate, LocalDate endOfRentDate,
                        int driverExperience) {
        super(vehicleBrand, vehicleModel, customerName, vehicleValue, rentPeriod, returnDate, startOfRentDate, endOfRentDate);
        setDriverExperience(driverExperience);
    }

    @Override
    public int getDriverExperience() {
        return driverExperience;
    }

    public void setDriverExperience(int driverExperience) {
        ValidationHelper.validateGreaterThanZero(driverExperience);
        this.driverExperience = driverExperience;
    }

    @Override
    protected String getPriceInfo() {
            return String.format(
                    "%nActual rent days: %d days%n" +
                            "%nRental cost per day: $%.2f%n" +
                            "%s%n" +
                            "%s%n" +
                            "Total rent: $%.2f%n" +
                            "Total insurance: $%.2f%n" +
                            "Total: $%.2f" +
                            "%nXXXXXXXXXX",
                    calculateActualRentDays(),
                    calculateRentCostPerDay(),
                    checkInsuranceDiscount(),
                    checkDiscount(),
                    calculateTotalRent(),
                    calculateTotalInsurance(),
                    calculateTotalRent() + calculateTotalInsurance()
            );
    }

    private double calculateInsurancePerDay() {
        if (driverExperience > 5) {
            return calculateInitialInsurance() - calculateInsuranceDiscount();
        } else
            return calculateInitialInsurance();
    }


    private double calculateInitialInsurance() {
        return (getVehicleValue() * CARGO_VAN_INSURANCE_COST_PER_DAY) * 10;
    }

    private double calculateInsuranceDiscount() {
        return calculateInitialInsurance() * INSURANCE_COST_REDUCTION;
    }

    private double calculateRentCostPerDay() {
        int rentDays = getRentPeriod();
        if (rentDays > 7) {
            return CARGO_VAN_RENT_DAILY_COST - CARGO_VAN_RENT_DAILY_COST_DISCOUNT;
        } else
            return CARGO_VAN_RENT_DAILY_COST;
    }


    public double calculateTotalRent() {
        if (actualRentDays < getRentPeriod()) {
            return actualRentDays * calculateRentCostPerDay() + unusedRentDays * (calculateRentCostPerDay() / 2);
        } else
            return actualRentDays * calculateRentCostPerDay();
    }

    public double calculateTotalInsurance() {
        return actualRentDays * calculateInsurancePerDay();
    }

    public long calculateActualRentDays() {
        return getStartOfRentDate().until(getReturnDate(), ChronoUnit.DAYS);
    }

    public double getCalculateDiscountForRent() {
        return unusedRentDays * (calculateRentCostPerDay() / 2);
    }

    public double getCalculateDiscountForInsurance() {
        return unusedRentDays * calculateInsurancePerDay();
    }

    public String checkInsuranceDiscount() {
        if (driverExperience > 5) {
            return String.format("Initial insurance per day: $%.2f%n" +
                    "Insurance discount per day: $%.2f%n" +
                    "Insurance per day: $%.2f%n", calculateInitialInsurance(), calculateInsuranceDiscount(),
                    calculateInsurancePerDay()
            );
        } else
            return String.format("Insurance per day: $%.2f%n",calculateInitialInsurance());
    }

    public String checkDiscount() {
        if (calculateActualRentDays() < getRentPeriod()) {
            return String.format("Early return discount for rent: $%.2f%n" +
                            "Early return discount for insurance: $%.2f%n",
                    getCalculateDiscountForRent(),
                    getCalculateDiscountForInsurance());
        } else
            return "";
    }
}
