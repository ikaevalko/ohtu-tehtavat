Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given new user is selected
        When  valid username and password "salainen1" and matching password confirmation are entered
        Then  new user is created

	Scenario: creation fails with too short username and valid password
        Given new user is selected
        When  too short username and password "salainen1" and matching password confirmation are entered
        Then  user is not created and error "username should have at least 3 characters" is given

    Scenario: creation fails with valid username and too short password
        Given new user is selected
        When  valid username and too short password "sala1" and matching password confirmation are entered
        Then user is not created and error "password should have at least 8 characters" is given

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When  valid username and password "salainen1" does not match password confirmation
        Then  user is not created and error "password and password confirmation do not match" is given
