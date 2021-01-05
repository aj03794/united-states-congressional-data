package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@ApplicationScoped
public class GetCongressionalBillsQueryComposition {

    LoadCongressionalBillsPort loadCongressionalBillsPort;

    public GetCongressionalBillsQueryComposition(LoadCongressionalBillsPort loadCongressionalBillsPort) {
        this.loadCongressionalBillsPort = loadCongressionalBillsPort;
    }

    public int execute(Function<CongressionalBill, Boolean> createQuery) {
        return (int) this.loadCongressionalBillsPort
                .getAllCongressionalBills(List.of("116"))
                .stream()
                .filter(createQuery::apply).count();
    }
}
