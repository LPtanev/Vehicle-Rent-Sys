package Solution.models.models.contracts;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Date;

public interface Vehicle {

    String getVehicleBrand();

    String getVehicleModel();

    double getVehicleValue();

    int getRentPeriod();

    String getCustomerName();

    Temporal getReturnDate();

    Temporal getStartOfRentDate();

    LocalDate getEndOfRentDate();

}
