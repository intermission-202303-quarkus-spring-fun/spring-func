package com.senacor.func.spring;

import com.microsoft.azure.functions.ExecutionContext;
import com.senacor.func.spring.amortisation.Amortization;
import com.senacor.func.spring.amortisation.AmortizationHandler;
import com.senacor.func.spring.amortisation.Loan;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

class AmortizationTest {

    private static final Loan LOAN = new Loan(10_000.00, 0.031, 1_000.00);

    @Test
    void testInvokeCalculation() {
        // given
        var fixture = new Amortization();
        // when
        var result = fixture.apply(MessageBuilder.withPayload(LOAN).build());
        // then
        assertThat(result.years()).isEqualTo(13);
        assertThat(result.lastRate()).isCloseTo(156.38, Percentage.withPercentage(3.0));
        assertThat(result.totalPaymentAmount()).isCloseTo(12_000.00, Percentage.withPercentage(3.0));
    }

    @Test
    void testHandler() {
        // given
        var invoker = new FunctionInvoker<Message<Loan>, Loan.Amortization>(Amortization.class);
        // when
        var result = invoker.handleRequest(MessageBuilder.withPayload(LOAN).build(),
                new ExecutionContext() {
                    @Override
                    public Logger getLogger() {
                        return Logger.getLogger(AmortizationTest.class.getName());
                    }

                    @Override
                    public String getInvocationId() {
                        return "id1";
                    }

                    @Override
                    public String getFunctionName() {
                        return "hello";
                    }
                });
        invoker.close();
        // then
        assertThat(result.years()).isEqualTo(13);
    }
}
