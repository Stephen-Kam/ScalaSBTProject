Simple SBT Scala Project w/ Selenium
=========================

This is a simple Scala SBT project using IntelliJ IDEA and Selenium WebDriver

The Trainline project is an automation project using the Trainline website as a testing ground

There are many features located in the trainline test files that took myself a while to research, 
so may be useful for others wanting to learn Scala & Selenium as there isn't that many resources out there

How to test project
===================

Download or clone the project

Import into IntelliJ the Trainline project, ensuring SBT is chosen

Under the steps folder,  open up the Trainline class and run the main

OR

Using SBT command line

Download or clone the project

Open up or navigate to the main Trainline folder whilst in command line

Launch SBT

> Test


Test Tagging
=============

Latest commit included the use of tagging tests.

See Utils folder, Tag object

To utilise, launch SBT, there are currently two tags: PositiveTest and NegativeTest

>test-only -- -n PositiveTest

Current problem I am encountering is that a second blank driver is launched, which I am trying to solve. 

Drivers, when not quit, remain in the processes so over time, many instances of the driver remain open

Other Issues Currently Attempting to Solve
==========================================

In Java, I was able to successfully and fairly easily able to iterate through the outdate calendar and select by linktext a date

However in Scala I am able to iterate through the dates and print each one out however, if I try and click on one, I get a cannot find element exception

Using the Marionette/Gecko driver for Firefox
=============================================

Managed to get the project working with Firefox version 46, version 0.10.0 Gecko driver and the latest Selenium build 3.0.0-Beta2

When I first implemented the driver, a vast majority of the tests would fail, upon investigation, the driver is progressing too quickly.

When I would click on the submit button, the driver would try and assert for an element to be visible before the page had even loaded.

So to solve the problem, I've added a bunch of wait for an element to be visible lines, which is annoying but hopefully with future versions of the driver, this may change.

Seemingly, tests which involves selecting an option from a list causes the test to fail. Maybe the driver is progressing too quickly that it can't even select an option.

To switch between the different drivers, open up the BrowserInitialisation trait and comment out the chrome lines, and uncomment the gecko lines