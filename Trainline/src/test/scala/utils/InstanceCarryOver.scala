package utils

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

/**
  * Created by Stephen.Kam on 03/08/2016.
  */
abstract trait InstanceCarryOver {

  var driver: WebDriver =_

  def getDriver(): WebDriver = {
    if (driver == null) {
      System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Stephen.Kam\\\\Desktop\\\\drivers\\\\chromedriver.exe")
      implicit var driver: WebDriver = new ChromeDriver()
    }
    driver
  }

  def closeDriver(): WebDriver = {
    if (driver != null) {
      closeDriver()
    }
    driver
  }

}
