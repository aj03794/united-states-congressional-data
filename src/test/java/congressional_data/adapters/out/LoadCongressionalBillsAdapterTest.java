package congressional_data.adapters.out;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.Bill;
import congressional_data.domain.CongressionalBill;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class LoadCongressionalBillsAdapterTest {

    @Mock
    Bill bill;

    @Inject
    LoadCongressionalBillsPort loadCongressionalBillsPort;

    @Inject
    LoadCongressionalBillsAdapter loadCongressionalBillsAdapter;

    @DisplayName("Should unzip directory properly")
    @Test
    public void t1() {
        loadCongressionalBillsPort.getAllCongressionalBills(List.of("115", "116"));
    }

    @DisplayName("Testing out download zips with parallel stream")
    @Test
    public void t2() {
        var res = loadCongressionalBillsAdapter.getAllCongressionalBills(List.of("108", "109", "110", "111", "116"));
    }

}