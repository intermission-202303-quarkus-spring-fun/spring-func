package com.senacor.func.spring.amortisation;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Optional;

public class AmortizationHandler extends FunctionInvoker<Message<Loan>, Loan.Amortization> {

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
        var message = MessageBuilder.withPayload(request.getBody().get())
                .copyHeaders(request.getHeaders())
                .build();
        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(message, context))
                .header("Content-Type", "application/json")
                .build();
    }

}
