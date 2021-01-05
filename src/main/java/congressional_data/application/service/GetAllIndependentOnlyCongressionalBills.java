package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GetAllIndependentOnlyCongressionalBills extends GetCongressionalBillsQuery {

    public GetAllIndependentOnlyCongressionalBills(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        super(loadCongressionalBillsPort);
    }

    @Override
    public int execute() {
        return (int) this.congressionalBills.stream().filter(CongressionalBill::isIndependent).count();
    }
}
