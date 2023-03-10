package com.senacor.func.spring.amortisation;

import com.microsoft.azure.functions.ExecutionContext;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Amortization implements Function<Message<Loan>, Loan.Amortization> {

    public Loan.Amortization apply(Message<Loan> loanMessage) {
        var loan = loanMessage.getPayload();
        log(loanMessage, "Computing amortization of load " + loan);
        var result = loan.amortization();
        log(loanMessage, "Returning result " + result);
        return result;
    }

    private void log(Message<Loan> message, String logMessage) {
        var executionContext = message.getHeaders().get("executionContext", ExecutionContext.class);
        if (executionContext != null) {
            executionContext.getLogger().info(logMessage);
        }
    }
}
