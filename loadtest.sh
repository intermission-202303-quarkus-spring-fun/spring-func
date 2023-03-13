#!/bin/zsh

case "$1" in
  remote)
    echo "*** Testing against Azure Functions"
    mvn gatling:test -Dgatling.simulationClass=com.senacor.func.spring.AmortizationLoadTest -DtargetUrl=https://senacor-spring-function-demo.azurewebsites.net
    ;;
  *)
    echo "*** Testing against local deployment"
    mvn gatling:test -Dgatling.simulationClass=com.senacor.func.spring.AmortizationLoadTest -DtargetUrl=http://localhost:7071
      ;;
esac
