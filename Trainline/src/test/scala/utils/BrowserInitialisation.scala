package utils

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.scalatest.selenium.WebBrowser._

import scala.util.Try



/**
  * Created by Stephen.Kam on 03/08/2016.
  */
trait BrowserInitialisation {

  /*
  THIS PAGE INITIALISES THE DRIVER FOR ALL TESTS

  UNCOMMENT/COMMENT OUT THE REQUIRED DRIVER
   */

  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\chromedriver.exe")
  implicit val driver: WebDriver = new ChromeDriver()
  setCaptureDir("C:\\CapGemini\\Git\\ScalaSBTProject\\Trainline\\src\\test\\scala\\screenshots")


  //System.setProperty("webdriver.edge.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\MicrosoftWebDriver.exe")
  //implicit val driver: WebDriver = new EdgeDriver()

/*
  System.setProperty("webdriver.gecko.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\geckodriver.exe")
  var capabilities: DesiredCapabilities = DesiredCapabilities.firefox()
  capabilities.setCapability("marionette", true)
  implicit val driver: WebDriver = new FirefoxDriver(capabilities)





  def getDriver(whichDriver: String): WebDriver = whichDriver match {
    case "Chrome" => System.setProperty("webdriver.chrome.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\chromedriver.exe")
      implicit var driver: WebDriver = new ChromeDriver()
      driver
    case "Firefox" => System.setProperty("webdriver.gecko.driver", "C:\\Users\\Stephen.Kam\\Desktop\\drivers\\geckodriver.exe")
      var capabilities: DesiredCapabilities = DesiredCapabilities.firefox()
      capabilities.setCapability("marionette", true)
      implicit var driver: WebDriver = new FirefoxDriver(capabilities)
      driver
  }*/
}