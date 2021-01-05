package congressional_data.application.service;

import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Supplier;


@ApplicationScoped
public abstract class GetCongressionalBillsQueryRefactor {

    @Inject
    LoadCongressionalBillsPort loadCongressionalBillsPort;

    //Subclasses will pass the query here that they want to run
    abstract Supplier<Boolean> createQuery(CongressionalBill congressionalBill);

    public int execute() {
        return (int) this.loadCongressionalBillsPort
                .getAllCongressionalBills(List.of("116"))
                .stream()
                .filter(congressionalBill -> createQuery(congressionalBill).get()).count();
    }

}
