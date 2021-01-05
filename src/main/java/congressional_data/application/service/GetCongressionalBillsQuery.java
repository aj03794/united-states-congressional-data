package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@NoArgsConstructor
@ApplicationScoped
public abstract class GetCongressionalBillsQuery {

    List<CongressionalBill> congressionalBills;

    @Inject
    LoadCongressionalBillsPort loadCongressionalBillsPort;
//
//    public GetCongressionalBillsQuery() {
//        this.congressionalBills = this.loadCongressionalBillsPort.getAllCongressionalBills(List.of("116"));
//    }

    @Inject
    public GetCongressionalBillsQuery(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        this.congressionalBills = loadCongressionalBillsPort.getAllCongressionalBills(List.of("116"));
    }

    // This is where the user of this baseclass should override and build their logic
    abstract int execute();
}
