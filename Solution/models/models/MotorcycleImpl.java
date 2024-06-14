package Solution.models.models;

import Solution.models.models.contracts.Motorcycle;
import Solution.models.utils.ValidationHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MotorcycleImpl extends VehicleBase implements Motorcycle {
    private static final int MOTORCYCLE_RENT_DAILY_COST = 15;

    private static final int MOTORCYCLE_RENT_DAILY_COST_DISCOUNT = 5;

    private static final double MOTORCYCLE_INSURANCE_COST_PER_DAY = 0.02;

    private static final double INSURANCE_COST_INCREASE = 0.2;

    private int ridersAge;

    long actualRentDays = calculateActualRentDays();
    long unusedRentDays = getRentPeriod() - actualRentDays;

    public MotorcycleImpl(String vehicleBrand, String vehicleModel, String customerName, double vehicleValue,
                          int rentPeriod,
                          LocalDate returnDate, LocalDate startOfRentDate, LocalDate endOfRentDate,
                          int ridersAge) {
        super(vehicleBrand, vehicleModel, customerName, vehicleValue, rentPeriod,
                returnDate, startOfRentDate, endOfRentDate);
        setRidersAge(ridersAge);
    }

    @Override
    public int getRidersAge() {
        return ridersAge;
    }

    public void setRidersAge(int ridersAge) {
        ValidationHelper.validateAge(ridersAge);
        this.ridersAge = ridersAge;
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
        if (ridersAge < 25) {
            return calculateInitialInsurance() + calculateAdditionalInsurance();
        } else
            return calculateInitialInsurance();
    }

    private double calculateInitialInsurance() {
        return (getVehicleValue() * MOTORCYCLE_INSURANCE_COST_PER_DAY) * 10;
    }

    private double calculateAdditionalInsurance() {
            return calculateInitialInsurance() * INSURANCE_COST_INCREASE;
    }

    private double calculateRentCostPerDay() {
        int rentDays = getRentPeriod();
        if (rentDays > 7) {
            return MOTORCYCLE_RENT_DAILY_COST - MOTORCYCLE_RENT_DAILY_COST_DISCOUNT;
        } else
            return MOTORCYCLE_RENT_DAILY_COST;
    }


    public double calculateTotalRent() {
        long actualRentDays = getStartOfRentDate().until(getReturnDate(), ChronoUnit.DAYS);
        long unusedRentDays = getRentPeriod() - actualRentDays;
        if (actualRentDays != getRentPeriod()) {
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

    public String checkInsuranceDiscount() {
        if (ridersAge < 25) {
            return String.format("Initial insurance per day: $%.2f%n" +
                            "Insurance addition per day: $%.2f%n" +
                            "Insurance per day: $%.2f%n", calculateInitialInsurance(), calculateAdditionalInsurance(),
                    calculateInitialInsurance() + calculateAdditionalInsurance()
            );
        } else
            return String.format("Insurance per day: $%.2f", calculateInitialInsurance());
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
    public double getCalculateDiscountForRent() {
        return unusedRentDays * (calculateRentCostPerDay() / 2);
    }

    public double getCalculateDiscountForInsurance() {
        return unusedRentDays * calculateInsurancePerDay();
    }
}
