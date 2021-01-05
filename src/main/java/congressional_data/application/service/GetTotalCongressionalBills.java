package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.function.Function;

@ApplicationScoped
public class GetTotalCongressionalBills {

    GetCongressionalBillsQueryComposition getCongressionalBillsQueryComposition;

    public GetTotalCongressionalBills(GetCongressionalBillsQueryComposition getCongressionalBillsQueryComposition) {
        this.getCongressionalBillsQueryComposition = getCongressionalBillsQueryComposition;
    }

    public int execute() {
        Function<CongressionalBill, Boolean> query = congressionalBill -> true;
        return this.getCongressionalBillsQueryComposition.execute(query);
    }
}
