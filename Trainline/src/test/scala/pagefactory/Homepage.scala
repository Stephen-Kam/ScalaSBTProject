package pagefactory

import java.text.SimpleDateFormat
import java.util.Calendar

import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser._
import utils.Pagefactory


/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class Homepage  {

  /////////////////////////////////////////////////
  ///////////////START OF VARIABLES////////////////
  /////////////////////////////////////////////////

  @FindBy(id="originStation")
  protected var originStation: WebElement = _

  @FindBy(id="destinationStation")
  protected var destinationStation: WebElement = _

  @FindBy(id="stationFinder")
  protected var stationFinder: WebElement = _

  @FindBy(xpath=".//*[@id='stationFinderOverlay']/div[2]/div/div[1]/form/div/label")
  protected var stationFinderLabel: WebElement = _

  @FindBy(id="sfLocation")
  protected var stationFinderLocation: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[1]/div/div[3]/label")
  protected var viaAvoid: WebElement = _

  @FindBy(xpath=".//*[@id='routeRestriction']")
  protected var routeRestrictionBtn: WebElement = _

  @FindBy(id="viaAvoidStation")
  protected var viaAvoidStation: WebElement = _

  @FindBy(id="submitButton")
  protected var submitButton: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[1]/div/div[1]/button[2]")
  protected var tomorrowButton: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[2]/div/div[1]/button[2]")
  protected var nextdayButton: WebElement = _

  @FindBy(id="outDate")
  protected var outDate: WebElement = _

  @FindBy(xpath=".//*[@id='tickets']/div/div[1]/table/thead/tr[1]/th[2]/div/h3")
  protected var timetableOutdate: WebElement = _

  /////////////////////////////////////////////////
  ///////////////START OF FUNCTIONS////////////////
  /////////////////////////////////////////////////

  def enterInSFLocation(location: String): Unit = {
    stationFinderLocation.sendKeys(location)
  }

  def navigateToWebPage(driver: WebDriver): Unit = {
    driver.manage.deleteAllCookies()
    driver.navigate().to("https://www.thetrainline.com")
    if (driver.getTitle contains "500 Internal Server Error") driver.navigate().refresh()
  }

  def clickOnStationFinder(): Unit = {
    stationFinder.click()
  }

  def isStationFinderLabelCorrect(driver: WebDriver): Boolean = {
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOf(stationFinderLabel))
    println(stationFinderLabel.getText) //debugging
    stationFinderLabel.getText contentEquals "Find a station closest to"
  }
  def enterStations(origin: String, destination: String): Unit = {
    originStation.sendKeys(origin)
    destinationStation.sendKeys(destination)
  }

    def clickTomorrowNextDay(): Unit = {
    tomorrowButton.click()
    nextdayButton.click()
  }

  def clickOnOutDate(): Unit = {
    click on outDate
  }

  def clickSubmit(): Unit = {
    click on submitButton
  }

  def assertTodayDate(): Unit = {
    val monthYear = new SimpleDateFormat("MMM y")
    val my = monthYear.format(Calendar.getInstance().getTime)
    var tomorrowDate: String = ""
    val now = Calendar.getInstance()
    val aDate = now.get(Calendar.DATE) + 1
    aDate match {
      case 1 | 21 | 31 => tomorrowDate = aDate + "st" + my
      case 2 | 22 => tomorrowDate = aDate + "nd " + my
      case 3 | 23 => tomorrowDate = aDate + "rd " + my
      case _ => tomorrowDate = aDate + "th " + my
    }
    println("Tomorrows's date is: " + tomorrowDate) //debugging
    println("OutDate is: " + timetableOutdate.getText) //debugging
    assert(timetableOutdate.getText contains tomorrowDate) //assert to ensure the result display the correct date
  }
}
