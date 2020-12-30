package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetBipartisanCongressionalBills extends GetCongressionalBillsQuery {

    @Inject
    public GetBipartisanCongressionalBills(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        super(loadCongressionalBillsPort);
    }

    @Override
    public int execute() {
        return (int) this.congressionalBills.stream().filter(CongressionalBill::isBipartisan).count();
    }

}
