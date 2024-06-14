package Solution.models.models;

import Solution.models.models.contracts.Car;
import Solution.models.utils.ValidationHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CarImpl extends VehicleBase implements Car {

    private static final int CAR_RENT_DAILY_COST = 20;

    private static final int CAR_RENT_DAILY_COST_DISCOUNT = 5;

    private static final double CAR_INSURANCE_COST_PER_DAY = 0.01;

    private static final double INSURANCE_COST_REDUCTION = 0.1;


    private int safetyRating;

    long actualRentDays = calculateActualRentDays();
    long unusedRentDays = getRentPeriod() - actualRentDays;

    public CarImpl(String vehicleBrand, String vehicleModel, String customerName, double vehicleValue,
                   int rentPeriod,
                   LocalDate returnDate, LocalDate startOfRentDate, LocalDate endOfRentDate,
                   int safetyRating) {
        super(vehicleBrand, vehicleModel, customerName, vehicleValue, rentPeriod, returnDate, startOfRentDate, endOfRentDate);
        setSafetyRating(safetyRating);
    }

    @Override
    public int getSafetyRating() {
        return safetyRating;
    }

    public void setSafetyRating(int safetyRating) {
        ValidationHelper.validateSafetyRatingValue(safetyRating);
        this.safetyRating = safetyRating;
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

    private double calculateRentCostPerDay() {
        int rentDays = getRentPeriod();
        if (rentDays > 7) {
            return CAR_RENT_DAILY_COST - CAR_RENT_DAILY_COST_DISCOUNT;
        } else
            return CAR_RENT_DAILY_COST;
    }

    private double calculateInitialInsurance() {
        return (getVehicleValue() * CAR_INSURANCE_COST_PER_DAY) * 10;
    }

    private double calculateInsurancePerDay() {
        if (safetyRating > 3) {
            return calculateInitialInsurance() - calculateInsuranceDiscount();
        } else
            return calculateInitialInsurance();
    }

    private double calculateInsuranceDiscount() {
        return calculateInitialInsurance() * INSURANCE_COST_REDUCTION;
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
        if (safetyRating > 3) {
            return String.format("Initial insurance per day: $%.2f%n" +
                            "Insurance discount per day: $%.2f%n" +
                            "Insurance per day: $%.2f%n", calculateInitialInsurance(), calculateInsuranceDiscount(),
                    calculateInsurancePerDay()
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
