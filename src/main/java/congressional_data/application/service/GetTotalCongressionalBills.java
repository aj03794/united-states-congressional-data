package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetTotalCongressionalBills extends GetCongressionalBillsQuery {

    @Inject
    public GetTotalCongressionalBills(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        super(loadCongressionalBillsPort);
    }

    @Override
    public int execute() {
        return this.congressionalBills.size();
    }
}
