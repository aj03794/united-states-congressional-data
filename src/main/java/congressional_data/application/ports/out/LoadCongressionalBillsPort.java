package congressional_data.application.ports.out;

import congressional_data.domain.CongressionalBill;

import java.util.List;

public interface LoadCongressionalBillsPort {
    List<CongressionalBill> getAllCongressionalBills();
}
