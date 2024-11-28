
Feature: Sauce Demo - Login Page

  Scenario: Navigation - Page Access Verified
    Given Go to "Sauce Demo"
    Then Verify the "Logo"

  Scenario: Login - Missing Username and Password
    Given Go to "Sauce Demo"
    When Click the "Login Button"
    Then Verify the "Error Message"

  Scenario: Login - Missing Password
    Given Go to "Sauce Demo"
    When Set the "Standard User" on the "Username"
    And Click the "Login Button"
    Then Verify the "Error Message"

  Scenario: Login - Missing Username
    Given Go to "Sauce Demo"
    When Set the "Password" on the "Password"
    And Click the "Login Button"
    Then Verify the "Error Message"

  Scenario: Login - Invalid Username and Password
    Given Go to "Sauce Demo"
    When Set the "Invalid User" on the "Username"
    When Set the "Invalid Password" on the "Password"
    And Click the "Login Button"
    Then Verify the "Error Message"

  Scenario: Login - Invalid Password
    Given Go to "Sauce Demo"
    When Set the "Standard User" on the "Username"
    When Set the "Invalid Password" on the "Password"
    And Click the "Login Button"
    Then Verify the "Error Message"

  Scenario: Login - Invalid Username
    Given Go to "Sauce Demo"
    When Set the "Invalid User" on the "Username"
    When Set the "Password" on the "Password"
    And Click the "Login Button"
    Then Verify the "Error Message"

  Scenario: Login - Valid Username and Password
    Given Go to "Sauce Demo"
    When Set the "Standard User" on the "Username"
    When Set the "Password" on the "Password"
    And Click the "Login Button"
    Then Verify the "Products"

  Scenario: Login - Both Username and Password Invalid (Fail Scenario)
    Given Go to "Sauce Demo"
    When Set the "Invalid User" on the "Username"
    When Set the "Invalid Password" on the "Password"
    And Click the "Login Button"
    Then Verify the "Products"