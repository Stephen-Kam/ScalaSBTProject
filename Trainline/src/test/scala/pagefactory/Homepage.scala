package pagefactory

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import cucumber.api.java.en_lol.WEN
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.support.FindBy
import org.scalatest.selenium.WebBrowser._


/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class Homepage {

  @FindBy(id="originStation")
  var originStation: WebElement = _

  @FindBy(id="destinationStation")
  var destinationStation: WebElement = _

  @FindBy(id="submitButton")
  var submitButton: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[1]/div/div[1]/button[2]")
  var tomorrowButton: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[2]/div/div[1]/button[2]")
  var nextdayButton: WebElement = _

  @FindBy(xpath=".//*[@id='timetable']/div[2]")
  var timetableConfirm: WebElement = _


  def enterStations(origin: String, destination: String): Unit = {
    originStation.sendKeys(origin)
    destinationStation.sendKeys(destination)
  }

  def clickSubmit(): Unit = {
    click on submitButton
  }


}
