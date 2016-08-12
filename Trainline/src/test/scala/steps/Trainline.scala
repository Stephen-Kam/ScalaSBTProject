package steps

import utils.Support

/**
  * Created by Stephen.Kam on 03/08/2016.
  */
class Trainline extends Support {

  feature("As a user I want to be able to use the trainline website") {

    scenario("Enter in two stations and press submit") {
      Given("I am on the Trainline website")

      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I click on the submit button")
      homepage.clickSubmit

      Then("The timetable page will be present")
      timetablepage.isTimetableVisible

      info("////////////////END OF TEST/////////////////////////")
    }

    scenario("Enter in two stations, press next day, press submit") {
      Given("I am on the Trainline website")

      When("I enter in an origin and destination station")
      homepage.enterStations("London", "Brighton")

      When("I click on the tomorrow & next day buttons")
      homepage.clickTomorrowNextDay()

      When("I click on the submit button")
      homepage.clickSubmit

      Then("The timetable page will be present")
      timetablepage.isTimetableVisible

      info("////////////////END OF TEST/////////////////////////")

    }

    scenario("Ensure the station finder label is correct") {
      Given("I am on the Trainline website")

      When("I click on the station finder button")
      homepage.clickOnStationFinder

      Then("The label will be correct")
      homepage.isStationFinderLabelCorrect(driver)
    }

    scenario("Display all  of the dates from the calendar") {
      Given("I am on the Trainline website")

      When("I click on the out date then all of the dates will  be printed")
      homepage.iterateThroughDates

      info("////////////////END OF TEST/////////////////////////")
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

      info("////////////////END OF TEST/////////////////////////")
    }

    scenario("Select a random number of adults and check that the timetable page accurately reflects this") {
      Given("I'm on The Train Line main page")

      When("I enter the 'from' and 'to' locations")
      homepage.enterStations("London", "Brighton")

      And("I select a random number of adults")
      homepage.selectRandomNoOfAdults

      When("I press the submit button")
      click on "submitButton"

      Then("The timetable page will show the correct number of adults")
      homepage.isNoOfAdultsCorrect

      info("////////////////END OF TEST/////////////////////////")
    }
  }
}
