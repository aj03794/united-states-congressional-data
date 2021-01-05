package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GetAllRepublicanOnlyCongressionalBills extends GetCongressionalBillsQuery {

    @Override
    public int execute() {
        return (int) this.congressionalBills.stream().filter(CongressionalBill::isRepublican).count();
    }
}
