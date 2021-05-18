@patient
Feature: Patient
  In order to maintain the patient record
  As a admin
  I want to create, update, delete patient records

  Scenario Outline: Add Patient
    Given I have "ff" browser with OpenEMR page
    When I enter username as "<username>"
    And I enter password as "<password>"
    And I select language as "<language>"
    And I click on login
    And I mouseover on patient-client
    And I click on patients
    And I click on add new patient
    And I fill the patient detail
      | firstname   | lastname   | dob   | gender   |
      | <firstname> | <lastname> | <dob> | <gender> |
    And I click on create new patient
    And I click on confirm create new patient
    And I handle alert
    And I close happy birthday pop when available
    Then I validate alert message receiver "Assessment: Tobacco"
    And I validate the added patient detail "<expectedvalue>"

    Examples: 
      | username | password | language         | firstname | lastname  | dob        | gender | expectedvalue                               |
      | admin    | pass     | English (Indian) | Balaji    | Dinakaran | 2021-05-17 | Male   | Medical Record Dashboard - Balaji Dinakaran |
      | admin    | pass     | English (Indian) | John      | Cena      | 2021-05-5  | Female | Medical Record Dashboard - John Cena        |
