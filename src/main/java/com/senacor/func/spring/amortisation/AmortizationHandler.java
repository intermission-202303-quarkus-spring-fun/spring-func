package com.senacor.func.spring.amortisation;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.senacor.func.spring.amortisation.model.Loan;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

@SuppressWarnings("unused")
public class AmortizationHandler extends FunctionInvoker<Loan, Loan.Amortization> {

    @FunctionName("amortization")
    public HttpResponseMessage execute(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST, HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS
            )
            HttpRequestMessage<Optional<Loan>> request,
            ExecutionContext context
    ) {
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(request.getBody().get(), context))
                .header("Content-Type", "application/json")
                .build();
    }

}
