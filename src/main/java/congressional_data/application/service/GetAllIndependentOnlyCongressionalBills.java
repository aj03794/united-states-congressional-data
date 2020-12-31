package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GetAllIndependentOnlyCongressionalBills extends GetCongressionalBillsQuery {

    public GetAllIndependentOnlyCongressionalBills(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        super(loadCongressionalBillsPort);
    }

    public int execute() {
        return (int) this.congressionalBills.stream().filter(CongressionalBill::isIndependent).count();
    }
}
