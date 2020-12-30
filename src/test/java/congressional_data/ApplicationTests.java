package congressional_data;

import congressional_data.application.service.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class ApplicationTests {

    @Inject
    GetBipartisanCongressionalBills getBipartisanCongressionalBills;

    @Inject
    GetTotalCongressionalBills getTotalCongressionalBills;

    @Inject
    GetAllRepublicanOnlyCongressionalBills getAllRepublicanOnlyCongressionalBills;

    @Inject
    GetAllDemocratOnlyCongressionalBills getAllDemocratOnlyCongressionalBills;

    @Inject
    GetAllIndependentOnlyCongressionalBills getAllIndependentOnlyCongressionalBills;

    @DisplayName("Integration test")
    @Test
    public void t1() {
        int bipartisanBills = getBipartisanCongressionalBills.execute();
        int bills = getTotalCongressionalBills.execute();
        int republicanOnlyBills = getAllRepublicanOnlyCongressionalBills.execute();
        int democratOnlyBills = getAllDemocratOnlyCongressionalBills.execute();
        int independentOnlyBills = getAllIndependentOnlyCongressionalBills.execute();
        System.out.println("Total bills: " + bills);
        System.out.println("Total Bipartisan Bills: " + bipartisanBills);
        System.out.println("Total Republican Bills: " + republicanOnlyBills);
        System.out.println("Total Democrat Bills: " + democratOnlyBills);
        System.out.println("Total Independent Bills: " + independentOnlyBills);
    }
}
