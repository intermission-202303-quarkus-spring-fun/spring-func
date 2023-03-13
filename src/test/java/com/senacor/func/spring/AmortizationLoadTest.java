package com.senacor.func.spring;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

@SuppressWarnings("unused")
public class AmortizationLoadTest extends Simulation {

    private final HttpProtocolBuilder httpProtocolBuilder = http
            .baseUrl(System.getProperty("targetUrl"))
            .inferHtmlResources()
            .acceptHeader("*/*")
            .contentTypeHeader("application/json")
            .userAgentHeader("curl/7.79.1");

    private final ScenarioBuilder scenarioBuilder = scenario("RecordedSimulation")
            .exec(
                    http("request_0")
                            .post("/api/amortization")
                            .body(
                                    StringBody("""
                                            {
                                              "totalLoan": 100.0,
                                              "currentInterestRate": 0.01,
                                              "repaymentPerYear": 50.0
                                            }
                                            """
                                    )
                            )
            );

    {
        setUp(scenarioBuilder.injectOpen(
                rampUsersPerSec(0).to(50).during(60L),
                constantUsersPerSec(50).during(300L)
        )).protocols(httpProtocolBuilder);
    }
}
