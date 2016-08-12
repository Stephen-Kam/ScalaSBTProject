package pagefactory

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

/**
  * Created by Stephen.Kam on 11/08/2016.
  */
class TimetablePage {

  /////////////////////////////////////////////////
  ///////////////START OF VARIABLES////////////////
  /////////////////////////////////////////////////

  @FindBy(xpath = ".//*[@id='timetable']/div[2]")
  protected var timetableConfirm: WebElement = _


  /////////////////////////////////////////////////
  ///////////////START OF FUNCTIONS////////////////
  /////////////////////////////////////////////////

  def isTimetableVisible: Unit =
    assert(timetableConfirm.isDisplayed, "Timetable is not visible")

}
