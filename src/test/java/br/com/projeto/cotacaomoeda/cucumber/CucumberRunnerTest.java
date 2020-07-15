package br.com.projeto.cotacaomoeda.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = {"json:target/cucumber-report/cucumber.json"})
public class CucumberRunnerTest {

}
