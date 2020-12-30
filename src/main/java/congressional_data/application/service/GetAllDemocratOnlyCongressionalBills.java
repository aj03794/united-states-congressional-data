package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetAllDemocratOnlyCongressionalBills {

    private final LoadCongressionalBillsPort loadCongressionalBillsPort;

    @Inject
    public GetAllDemocratOnlyCongressionalBills(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        this.loadCongressionalBillsPort = loadCongressionalBillsPort;
    }

    public Integer execute() {
        List<CongressionalBill> congressionalBills = loadCongressionalBillsPort.getAllCongressionalBills();
        return (int) congressionalBills.stream().filter(CongressionalBill::isDemocrat).count();
    }
}
