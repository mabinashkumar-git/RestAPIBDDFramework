//package CucumberOptions;
//
//import org.junit.runner.RunWith;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import net.serenitybdd.cucumber.CucumberWithSerenity;
//
//@RunWith(CucumberWithSerenity.class)
//
//// Follow this for details : https://www.youtube.com/watch?v=ogiBRKqmM_M
//// provide package name in glue where step definations are written
//@CucumberOptions(
////		features="src/test/java/features", plugin ="json:target/jsonReports/cucumber-report.json", glue= {"stepDefinations"}, tags= {"@AddPlace"})
//		features="src/test/java/features", plugin ={"pretty", "html:target/cucumber-HTMLReports", 
//				"junit:target/cucumber-JunitReports/cucumber.xml", "json:target/jsonReports/cucumber-report.json"}, 
//		monochrome = true, glue= {"stepDefinations"}, tags= {"@AddWebAPI"})
////		features="src/test/java/features", plugin ={"pretty", "html:target/cucumber-HTMLReports","junit:target/cucumber-JunitReports/cucumber.xml", "json:target/jsonReports/cucumber-report.json"}, monochrome = true, glue= {"stepDefinations"}, tags= {"@AddBook"})
//		
//public class MySerenityTestRunner {
//
//}
//
///* Command to run by maven 
// * mvn test -Dcucumber.options="--tags @AddPlace"
// * Here "D" stands for parameter and the parameter passed here is cucumber.options 
// * Open the cmd -> navigate to folder having project : cd "path of project" -> press enter -> Provide above
//   maven command                */
//
//
///* plugin ="json:target/jsonReports/cucumber-report.json"  -> code to generate report and 
//	generated report is stored in "target" folder
// * https://github.com/damianszczepanik/maven-cucumber-reporting	-> Place the plugin in pom.xml
// * mvn test verify -> Command to execute tests and generate report */
