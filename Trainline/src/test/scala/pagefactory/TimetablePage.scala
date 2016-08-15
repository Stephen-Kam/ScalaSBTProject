package pagefactory

import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.support.FindBy

/**
  * Created by Stephen.Kam on 11/08/2016.
  */
class TimetablePage {

  //val homepage: Homepage = new Homepage

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

  def isTimetableVisible(): Unit =
    assert(timetableConfirm.isDisplayed, "Timetable is not visible")

  def checkTrainHour(homepage: Homepage): Unit = {
    println(homepage.getCurrentOutHour) //debugging
    isTimetableVisible()
    assert(timetableHour.getText contains homepage.getCurrentOutHour, "Present hour is not used")

  }
}
