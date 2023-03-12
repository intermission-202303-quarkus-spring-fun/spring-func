package com.senacor.func.spring.amortisation;

import com.senacor.func.spring.amortisation.model.Loan;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Amortization implements Function<Loan, Loan.Amortization> {

   public Loan.Amortization apply(Loan loan) {
        return loan.amortization();
    }

}
