package automacaoCurriculo;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/Curriculo.feature",
glue = { "" }, monochrome = true, dryRun = false)

public class Runner {
	
}
