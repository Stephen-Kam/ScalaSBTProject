package steps

import org.openqa.selenium.{WebDriver, WebElement}
import org.scalatest.time.{Seconds, Span}
import utils.Support

/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class Trainline extends Support {


    def iterateThroughDates(dates: List[Element]): Unit = {
      val head = dates.head
      val tail = dates.tail
      println(head.underlying.getText)
      if (tail.isEmpty) println("End of List") else iterateThroughDates(tail)
    }

  feature("As a user I want to be able to use the trainline website") {

    scenario("Enter in two stations and press submit") {
      Given("I am on the Trainline website")

      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I click on the submit button")
      homepage.clickSubmit()

      Then("The timetable page will be present")
      assert(timetablepage.isTimetableVisible)

    }

    scenario("Enter in two stations, press next day, press submit") {
      Given("I am on the Trainline website")

      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I click on the tomorrow & next day buttons")
      homepage.clickTomorrowNextDay()

      When("I click on the submit button")
      homepage.clickSubmit()

      Then("The timetable page will be present")
      assert(timetablepage.isTimetableVisible)

    }

    scenario("Ensure the station finder label is correct") {
      Given("I am on the Trainline website")

      When("I click on the station finder button")
      homepage.clickOnStationFinder()

      Then("The label will be correct")
      assert(homepage.isStationFinderLabelCorrect(driver))

    }

    scenario("Display all  of the dates from the calendar") {
      Given("I am on the Trainline website")

      When("I click on the out date")
      homepage.clickOnOutDate()
      val calendars = findAll(xpath(".//*[@id='ui-datepicker-div']/*")).toList
      iterateThroughDates(calendars)
    }

    scenario("Ticket search with 'today' and 'next day'") {
      Given("I'm on The Train Line main page")

      When("I enter the 'from' and 'to' locations")
      homepage.enterStations("London", "Brighton")

      And("I select the 'today' and 'next day' button")
      homepage.clickTomorrowNextDay()

      And("I press the submit button")
      click on "submitButton"

      Then("the 'tomorrow' and 'next day' date displays")
      homepage.assertTodayDate()
    }
  }
}
