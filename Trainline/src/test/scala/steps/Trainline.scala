package steps

import com.sun.org.apache.xalan.internal.utils.FeatureManager.Feature
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.PageFactory
import org.scalatest.selenium.WebBrowser
import org.scalatest.selenium.WebBrowser._
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}
import pagefactory.Homepage

/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class Trainline extends FeatureSpec with GivenWhenThen with BeforeAndAfter with WebBrowser{

  System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Stephen.Kam\\\\Desktop\\\\drivers\\\\chromedriver.exe")
  implicit val driver: WebDriver = new ChromeDriver()
  var homepage = PageFactory.initElements(driver, classOf[Homepage])

  before{
    go to "https://www.thetrainline.com"
  }

  after{
    close()
  }

  feature("As a user") {
    info("I want to be able to enter 2 stations")
    info("and be able to view the results")

    scenario("Enter in two stations and press submit") {
      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I click on the submit button")
      homepage.clickSubmit()
      implicitlyWait(Span(5, Seconds))

      Then("The timetable page will be present")
      assert(homepage.timetableConfirm.isDisplayed)
    }

    scenario("Enter in two stations, press next day, press submit") {
      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I click on the tomorrow & next day buttons")
      homepage.tomorrowButton.click()
      homepage.nextdayButton.click()

      When("I click on the submit button")
      homepage.clickSubmit()
      implicitlyWait(Span(5, Seconds))

      Then("The timetable page will be present")
      assert(homepage.timetableConfirm.isDisplayed)
    }
  }

}
