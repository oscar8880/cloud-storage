package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
  @FindBy(id = "continue-link-success")
  private WebElement linkToContinue;

  public ResultPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void clickContinue() {
    linkToContinue.click();
  }
}
