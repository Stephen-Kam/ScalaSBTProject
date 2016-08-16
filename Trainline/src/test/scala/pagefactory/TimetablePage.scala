package pagefactory

import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser._

/**
  * Created by Stephen.Kam on 11/08/2016.
  */
class TimetablePage {


  /////////////////////////////////////////////////
  ///////////////START OF VARIABLES////////////////
  /////////////////////////////////////////////////

  @FindBy(xpath = ".//*[@id='timetable']/div[2]")
  protected var timetableConfirm: WebElement = _

  @FindBy(xpath=".//*[@id='matrixLength1']")
  protected var timetableHour: WebElement = _



  /////////////////////////////////////////////////
  ///////////////START OF FUNCTIONS////////////////
  /////////////////////////////////////////////////

  def isTimetableVisible(implicit driver: WebDriver): Unit = {
    val wait: WebDriverWait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOf(timetableConfirm))
    //pageloaded before proceed
    //implicit waits
    assert(timetableConfirm.isDisplayed, "Timetable is not visible" + captureTo("TimetableNotVisible"))
  }

  def checkTrainHour(implicit driver: WebDriver, homepage: Homepage): Unit = {
    println(homepage.getCurrentOutHour) //debugging
    isTimetableVisible(driver)
    assert(timetableHour.getText contains homepage.getCurrentOutHour, "Present hour is not used" + captureTo("PresentHourIsNotUsed"))

  }
}
