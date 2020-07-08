package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
  @FindBy(id = "inputUsername")
  private WebElement usernameInput;

  @FindBy(id = "inputPassword")
  private WebElement passwordInput;

  @FindBy(id = "submit-button")
  private WebElement submitButton;

  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
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

  public void loginJohnSmith() {
    enterUsername("johnsmith");
    enterPassword("password");
    submit();
  }
}
