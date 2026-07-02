pipeline {
   agent any
    parameters {
        string(
        name: 'cucumberTag',
        description: 'Description for parameters string with name tag cucumberTag'
        )
    }
    stages {
        stage('99999999999999999999999999999999999999999') {
            steps {
                bat '''
                    mvn clean test -Dtest=CucumberRunnerTest "-Dcucumber.filter.tags=${params.cucumberTag}" "-Dcucumber.plugin=pretty,junit:target/cucumber-reports/Cucumber.xml,json:target/cucumber-reports/Cucumber.json,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" "-Dallure.results.directory=target/allure-results"
                '''
            }

        }
    }
    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}