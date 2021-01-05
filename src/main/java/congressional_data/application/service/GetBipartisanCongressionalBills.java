package congressional_data.application.service;

import congressional_data.domain.CongressionalBill;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Supplier;

@ApplicationScoped
public class GetBipartisanCongressionalBills extends GetCongressionalBillsQueryRefactor {

    @Override
    public Supplier<Boolean> createQuery(CongressionalBill congressionalBill) {
       return congressionalBill::isBipartisan;
    }

}
