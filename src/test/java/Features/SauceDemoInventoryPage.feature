@run
Feature: Sauce Demo - Inventory Page

  Background: Login Background - Successful Authentication
    Given Go to "Sauce Demo"
    When Set the "Standard User" on the "Username"
    When Set the "Password" on the "Password"
    And Click the "Login Button"

  Scenario: Inventory Page - Successfully Add Product to Cart
    When Click the "Sauce Labs Backpack"
    And Wait for "3" seconds
    When Click the "Add To Cart"
    And Click the "Cart"
    Then Verify the "Cart Item"