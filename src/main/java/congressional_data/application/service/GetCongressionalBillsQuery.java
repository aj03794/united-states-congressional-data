package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.util.List;


@NoArgsConstructor
public abstract class GetCongressionalBillsQuery {

    List<CongressionalBill> congressionalBills;

    @Inject
    public GetCongressionalBillsQuery(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        this.congressionalBills = loadCongressionalBillsPort.getAllCongressionalBills();
    }

    // This is where the user of this baseclass should override and build their logic
    abstract int execute();
}
