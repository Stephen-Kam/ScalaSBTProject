package pagefactory

import java.text.SimpleDateFormat
import java.util.Calendar

import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.{ExpectedConditions, Select, WebDriverWait}
import org.scalatest.selenium.WebBrowser._


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

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[1]/div/div[1]/button[2]")
  protected var tomorrowButton: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[2]/div/div[1]/button[2]")
  protected var nextdayButton: WebElement = _

  @FindBy(id="outHour")
  var outHour: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[3]/div[1]/div/button")
  protected var railcardButton: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[3]/div[1]/div/div/button")
  protected var railcardButtonDone: WebElement = _

  @FindBy(xpath=".//*[@id='tickets']/div/div[1]/table/thead/tr[1]/th[2]/div/h3")
  protected var timetableOutdate: WebElement = _

  @FindBy(xpath=".//*[@id='timetable']/div[1]")
  protected var timetableHeader: WebElement = _

  protected var randomnumber = ""

  /////////////////////////////////////////////////
  ////////////START OF NEGATIVE VARIABLES//////////
  /////////////////////////////////////////////////

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[1]/div/div[1]/div/div/div[2]")
  protected var originErrorMessage: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[1]/div/div[2]/div/div/div[2]")
  protected var destinationErrorMessage: WebElement = _

  @FindBy(xpath=".//*[@id='extendedSearchForm']/div[2]/div[1]/div/div[2]/div[3]/div[2]")
  protected var outhourErrorMessage: WebElement = _

  protected var currentOutHour = ""

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

  def clickOnStationFinder(implicit driver: WebDriver): Unit = {
    click on "stationFinder"
  }

  def isStationFinderLabelCorrect(driver: WebDriver): Unit = {
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOf(stationFinderLabel))
    println(stationFinderLabel.getText) //debugging
    assert(stationFinderLabel.getText contentEquals "Find a station closest to", "Station Finder label was incorrect")
  }
  def enterStations(origin: String, destination: String): Unit = {
    originStation.sendKeys(origin)
    destinationStation.sendKeys(destination)
  }

    def clickTomorrowNextDay(): Unit = {
    tomorrowButton.click()
    nextdayButton.click()
  }

  def clickOnOutDate(implicit driver: WebDriver): Unit = {
    click on "outDate"
  }

  def clickSubmit(implicit driver: WebDriver): Unit = {
    click on "submitButton"
  }

  def assertTodayDate(): Unit = {
    val monthYear = new SimpleDateFormat("MMM y")
    val my = monthYear.format(Calendar.getInstance().getTime)
    var tomorrowDate: String = ""
    val now = Calendar.getInstance()
    val aDate = now.get(Calendar.DATE) + 1 //Get tomorrow's date
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

  def iterateThroughDates(implicit driver: WebDriver): Unit = {
    def iterateThroughTheDates(dates: List[Element]): Unit = {
      val head = dates.head
      val tail = dates.tail
      println(head.underlying.getText)
      if (tail.isEmpty) println("End of List") else iterateThroughTheDates(tail)
    }
    click on "outDate"
    val calendars = findAll(xpath(".//*[@id='ui-datepicker-div']/*")).toList
    iterateThroughTheDates(calendars)
  }

  def selectRandomNoOfAdults(implicit driver: WebDriver): Unit = {
    railcardButton.click()
    val rand = new scala.util.Random
    val range = 1 to 9
    randomnumber = range(rand.nextInt(range length)).toString
    singleSel("adults").value = randomnumber
    click on railcardButtonDone
  }

  def isNoOfAdultsCorrect(): Unit = {
    assert(timetableHeader.getText contains randomnumber, "The number of adults did not match")
  }

  /////////////////////////////////////////////////
  ///////////START OF NEGATIVE FUNCTIONS///////////
  /////////////////////////////////////////////////

  def getCurrentOutHour: String = {
    currentOutHour
  }

  def getCurrentOutHourFromPage(driver: WebDriver): Unit = {
    var select = new Select(driver.findElement(By.id("outHour")))
    currentOutHour = select.getFirstSelectedOption.getText
  }

  def selectPastOutHour(implicit driver: WebDriver): Unit = {
    getCurrentOutHourFromPage(driver)
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    singleSel("outHour").value = "0"
    wait.until(ExpectedConditions.visibilityOf(outhourErrorMessage))
  }

  def outHourErrorMessageCorrect(): Unit = {
    assert(outhourErrorMessage.getText contentEquals "Your outward journey is in the past", "OutHour Error message incorrect. " +
    "Expected: Your outward journey is in the past, Actual: " + outhourErrorMessage.getText)
  }

  def errorMessagesCorrect(driver:  WebDriver): Unit = {
    val expectedOriginMessage = "Please enter the station you will be travelling from"
    val expectedDestinationMessage = "Please enter the station you will be travelling to"
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOf(originErrorMessage))

    assert(originErrorMessage.getText contentEquals expectedOriginMessage, "Origin Error message incorrect." +
      "Expected: " + expectedOriginMessage + ", Actual: " + originErrorMessage.getText)

    assert(destinationErrorMessage.getText contentEquals expectedDestinationMessage, "Origin Error message incorrect." +
      "Expected: " + expectedDestinationMessage + ", Actual: " + originErrorMessage.getText)
  }




}
