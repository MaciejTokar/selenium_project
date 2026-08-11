pipeline {
   agent any
    parameters {
        string(
        name: 'cucumberTag',
        description: 'Description for parameters string with name tag cucumberTag. Default value of that field is "@smoke"',
        defaultValue: '@smoke'
        )

        booleanParam(
        name: 'headlessMode',
        description: 'Setting the headless mode based on true or false options. Default value of that field is "false"',
        defaultValue: false
        )

        choice(
        name: 'environment',
        choices: ['test', 'uat', 'prod'],
        description: 'Select the environment for tests'
        )

        string(
        name: 'branch',
        description: 'Enter name of the branch. Default value of that field is "main"',
        trim: true
        defaultValue: 'main',
        )

        choice(
        name: 'browser',
        choices: ['chrome', 'firefox', 'edge'],
        description: 'Select the browser for tests'
        )
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: params.branch,
                    url: 'https://github.com/MaciejTokar/selenium_project',
            }
        }
        stage('Run Tests') {
            steps {
                bat """
                      mvn clean test ^
                      -Dtest=CucumberRunnerTest ^
                      "-Dcucumber.filter.tags=${params.cucumberTag}" ^
                      "-Denvironment=${params.environment}" ^
                      "-Dbrowser=${params.browser}" ^
                      "-Dheadless=${params.headlessMode}"
                      "-Dcucumber.plugin=pretty,junit:target/cucumber-reports/Cucumber.xml,json:target/cucumber-reports/Cucumber.json,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" ^
                      "-Dallure.results.directory=target/allure-results"
                """
            }
        }
    }
    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}