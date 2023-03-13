#!/bin/zsh

mvn gatling:test -Dgatling.simulationClass=com.senacor.func.spring.AmortizationLoadTest -DtargetUrl=https://senacor-spring-function-demo.azurewebsites.net