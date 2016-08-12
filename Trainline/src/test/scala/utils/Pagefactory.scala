package utils

import org.openqa.selenium.support.PageFactory
import pagefactory.{Homepage, TimetablePage}

/**
  * Created by Stephen.Kam on 11/08/2016.
  */
trait Pagefactory extends BrowserInitialisation{

  /*
  THIS PAGE INITIALISES ALL OF THE RELEVANT PAGE FACTORIES
   */

  val homepage = PageFactory.initElements(driver, classOf[Homepage])
  val timetablepage = PageFactory.initElements(driver, classOf[TimetablePage])

}
