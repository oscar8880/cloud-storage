package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
  @FindBy(id = "logout-button")
  private WebElement logoutButton;

  @FindBy(id = "nav-notes-tab")
  private WebElement notesTabButton;

  @FindBy(id = "nav-files-tab")
  private WebElement filesTabButton;

  @FindBy(id = "nav-credentials-tab")
  private WebElement credentialsTabButton;


  @FindBy(id = "new-note-button")
  private WebElement newNoteButton;

  @FindBy(id = "note-title-input")
  private WebElement noteTitleInput;

  @FindBy(id = "note-description-input")
  private WebElement noteDescriptionInput;

  @FindBy(id = "note-save-changes-button")
  private WebElement noteSubmitButton;


  @FindBy(id = "note-1")
  private WebElement firstNoteTitle;

  @FindBy(id = "note-edit-button-1")
  private WebElement firstNoteEditButton;

  @FindBy(id = "note-delete-button-1")
  private WebElement firstNoteDeleteButton;


  @FindBy(id = "new-credential-button")
  private WebElement newCredentialButton;

  @FindBy(id = "credential-url-input")
  private WebElement credentialUrlInput;

  @FindBy(id = "credential-username-input")
  private WebElement credentialUsernameInput;

  @FindBy(id = "credential-password-input")
  private WebElement credentialPasswordInput;

  @FindBy(id = "credential-save-changes-button")
  private WebElement credentialSubmitButton;

  @FindBy(id = "credential-username-1")
  private WebElement firstCredentialUsername;

  @FindBy(id = "credential-password-1")
  private WebElement firstCredentialPassword;

  @FindBy(id = "credential-edit-button-1")
  private WebElement firstCredentialEditButton;

  @FindBy(id = "credential-delete-button-1")
  private WebElement firstCredentialDeleteButton;

  public HomePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void logout() {
    logoutButton.click();
  }

  public void goToNotesTab() {
    notesTabButton.click();
  }

  public void goToFilesTab() {
    filesTabButton.click();
  }

  public void goToCredentialsTab() {
    credentialsTabButton.click();
  }

  public void clickNewNote() {
    newNoteButton.click();
  }

  public void enterNoteTitle(String title) {
    noteTitleInput.sendKeys(title);
  }

  public void enterNoteDescription(String description) {
    noteDescriptionInput.sendKeys(description);
  }

  public void submitNewNote() {
    noteSubmitButton.click();
  }

  public String getFirstNoteTitle() {
    return firstNoteTitle.getText();
  }

  public void clickEditFirstNote() {
    firstNoteEditButton.click();
  }

  public void clickDeleteFirstNote() {
    firstNoteDeleteButton.click();
  }

  public void clickNewCredential() {
    newCredentialButton.click();
  }

  public void enterCredentialUrl(String url) {
    credentialUrlInput.sendKeys(url);
  }

  public void enterCredentialUsername(String username) {
    credentialUsernameInput.sendKeys(username);
  }

  public void enterCredentialPassword(String password) {
    credentialPasswordInput.sendKeys(password);
  }

  public void submitCredential() {
    credentialSubmitButton.click();
  }

  public String getFirstCredentialUsername() {
    return firstCredentialUsername.getText();
  }

  public String getFirstCredentialPassword() {
    return firstCredentialPassword.getText();
  }

  public void clickEditFirstCredential() {
    firstCredentialEditButton.click();
  }

  public void clickDeleteFirstCredential() {
    firstCredentialDeleteButton.click();
  }

  public String getPasswordInputContent() {
    return credentialPasswordInput.getAttribute("value");
  }
}
