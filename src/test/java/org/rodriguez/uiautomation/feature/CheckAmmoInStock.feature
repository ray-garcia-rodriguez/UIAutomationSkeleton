@UI @Cart
Feature: Check Ammo in Stock
  @Test
  Scenario: User check which ammo is in stock
    Given User is on Basspro landing page
    And User navigates to handgun ammo from Navbar
    When User filters "CARTRIDGE_OR_GAUGE" section with "LUGER_9MM,LUGGER_9MM_P"
    Then Some results should be in stock