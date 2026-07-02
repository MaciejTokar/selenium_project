pipeline {
   agent any

    stages {
        stage('99999999999999999999999999999999999999999') {
            steps {
                    mvn clean test -Dtest=CucumberRunnerTest "-Dcucumber.filter.tags=@smoke" "-Dcucumber.plugin=pretty,junit:target/cucumber-reports/Cucumber.xml,json:target/cucumber-reports/Cucumber.json,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" "-Dallure.results.directory=target/allure-results"
            }

        }
    }
}