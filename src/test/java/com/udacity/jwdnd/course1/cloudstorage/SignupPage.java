package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
  @FindBy(id = "inputFirstName")
  private WebElement firstNameInput;

  @FindBy(id = "inputLastName")
  private WebElement lastNameInput;

  @FindBy(id = "inputUsername")
  private WebElement usernameInput;

  @FindBy(id = "inputPassword")
  private WebElement passwordInput;

  @FindBy(id = "submit-button")
  private WebElement submitButton;

  @FindBy(id = "back-to-login")
  private WebElement backToLoginLink;

  public SignupPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void enterFirstname(String firstName) {
    firstNameInput.sendKeys(firstName);
  }
  public void enterLastname(String lastName) {
    lastNameInput.sendKeys(lastName);
  }
  public void enterUsername(String username) {
    usernameInput.sendKeys(username);
  }
  public void enterPassword(String password) {
    passwordInput.sendKeys(password);
  }

  public void submit() {
    submitButton.click();
  }

  public void goToLoginPage() {
    backToLoginLink.click();
  }

  public void registerJohnSmith() {
    enterFirstname("John");
    enterLastname("Smith");
    enterUsername("johnsmith");
    enterPassword("password");
    submit();
  }
}
