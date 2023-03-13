package com.senacor.func.spring.amortisation;

import com.senacor.func.spring.amortisation.model.Loan;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class Amortization implements Function<Mono<Loan>, Mono<Loan.Amortization>> {

   public Mono<Loan.Amortization> apply(Mono<Loan> loanMono) {

       return loanMono.map(Loan::amortization);
    }

}
