package pagefactory

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.scalatest.selenium.WebBrowser._

/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class GoogleHome {

  @FindBy(id="lst-ib")
  var search: WebElement =_

  @FindBy(id="sblsbb")
  var searchButton: WebElement =_

  @FindBy(xpath = ".//*[@id='rso']/div[1]/div/div/div/div/div/cite")
  var firstLink: WebElement =_

  @FindBy(xpath = ".//*[@id='rso']/div[1]/div/div/div/div[1]/cite")
  var firstLinkAlt: WebElement =_

  def clickButton() : Unit = {
    click on searchButton
  }
}
