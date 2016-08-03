package stepdefs

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.PageFactory
import org.scalatest.{BeforeAndAfter, FeatureSpec, GivenWhenThen}
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.{Seconds, Span}
import pagefactory.GoogleHome

/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class Search extends FeatureSpec with GivenWhenThen with WebBrowser with BeforeAndAfter {

  //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\chromedriver.exe")
  //implicit var driver: WebDriver = new ChromeDriver()

  System.setProperty("webdriver.gecko.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\geckodriver.exe")
  var capabilities: DesiredCapabilities = DesiredCapabilities.firefox()
  capabilities.setCapability("marionette", true)
  implicit var driver: WebDriver = new FirefoxDriver(capabilities)

  val homepage = PageFactory.initElements(driver, classOf[GoogleHome])


  before {
    go to "http://www.google.co.uk"
  }

  after {
    driver.close()
  }

  feature("Google Search") {
    info("As a user")
    info("I want to be able to search by phrase")
    info("So that I can find web pages related to provided phrase")

    scenario("Google search") {
      When("I enter in the search phrase")
      homepage.search.sendKeys("google co uk")

      When("I click on the search button")
      homepage.clickButton()
      implicitlyWait(Span(5, Seconds)) //driver tries to assert before search results have appeared so make it wait for a little bit

      Then("The first link will be https://www.google.co.uk/")
      assert(homepage.firstLink.getText matches "https://www.google.co.uk/")
    }

    scenario("Another google search") {
      When("I emter in the other search phrase")
      homepage.search.sendKeys("hello world")

      When("I click on the search button")
      homepage.clickButton()
      implicitlyWait(Span(5, Seconds)) //driver tries to assert before search results have appeared so make it wait for a little bit

      Then("The first link will be correct")
      assert(homepage.firstLinkAlt.getText matches "https://en.wikipedia.org/wiki/%22Hello,_World!%22_program")
    }

  }

}