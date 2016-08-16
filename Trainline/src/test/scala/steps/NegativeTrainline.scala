package steps

import org.openqa.selenium.support.ui.Select
import utils.{NegativeTest, Support}

/**
  * Created by Stephen.Kam on 15/08/2016.
  */
class NegativeTrainline extends Support{

  feature("As a Web Administrator") {

    scenario("All of the error labels should be correct", NegativeTest) {
      Given("I am on the Trainline website")

      When("I click on the submit button w/out entering any other details")
      homepage.clickSubmit

      Then("Two messages will appear telling myself to enter in an origin and destination station")
      homepage.errorMessagesCorrect(driver)

      info("////////////////END OF TEST/////////////////////////")
    }

    scenario("When an out time is set in the past, an appropriate error message should be displayed", NegativeTest) {
      //AT PRESENT THIS TEST DOES NOT WORK WHEN USING THE GECKO/FIREFOX DRIVER
      Given("I am on the Trainline website")

      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I change the out time to the past")
      homepage.selectPastOutHour(driver)

      Then("An error message will be displayed alerting me that the out time is in the past")
      homepage.outHourErrorMessageCorrect(driver)

      When("I click the submit button")
      homepage.clickSubmit

      Then("The timetable will appear showing journeys from the present time")
      timetablepage.checkTrainHour(driver, homepage)

      info("////////////////END OF TEST/////////////////////////")
    }
  }

}
