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