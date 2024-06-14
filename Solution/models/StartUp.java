package Solution.models;

import Solution.models.models.CarImpl;
import Solution.models.models.CargoVanImpl;
import Solution.models.models.MotorcycleImpl;

import java.time.LocalDate;

public class StartUp {
    public static void main(String[] args) {
        try {
            CarImpl car = new CarImpl("Mitsubishi", "Mirage", "John Doe",
                    15.000, 10,
                    LocalDate.of(2024, 6, 13),
                    LocalDate.of(2024, 6, 3),
                    LocalDate.of(2024, 6, 13),
                    3);
            System.out.println(car);
            MotorcycleImpl motorcycle = new MotorcycleImpl("Triumph", "Tiger Sport 660",
                    "Mary Johnson", 10.000, 10,
                    LocalDate.of(2024, 6, 13),
                    LocalDate.of(2024, 6, 3),
                    LocalDate.of(2024, 6, 13),
                    20);
            System.out.println(motorcycle);
            CargoVanImpl cargoVan = new CargoVanImpl("Citroen", "Jumper", "John Markson",
                    20.000, 15,
                    LocalDate.of(2024, 6, 13),
                    LocalDate.of(2024, 6, 3),
                    LocalDate.of(2024, 6, 18),
                    8);
            System.out.println(cargoVan);
        } catch (Exception ex) {
            if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                System.out.println(ex.getMessage());
            } else {
                System.out.println(ex);
            }
        }
    }
}
