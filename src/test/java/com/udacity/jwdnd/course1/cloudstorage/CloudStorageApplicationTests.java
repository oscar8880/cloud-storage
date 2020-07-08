package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private Integer port;

	private static WebDriver driver;
	private SignupPage signup;
	private LoginPage login;
	private HomePage home;
	private ResultPage result;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		signup = new SignupPage(driver);
		login = new LoginPage(driver);
		home = new HomePage(driver);
		result = new ResultPage(driver);
		driver.get("http://localhost:" + port + "/signup");
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	// 1.1: a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	public void testPageAuthorisation() {
		// check user can access signup
		driver.get("http://localhost:" + port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		// check user can access login
		signup.goToLoginPage();
		Assertions.assertEquals("Login", driver.getTitle());

		// check user can't access home and is rerouted to login
		driver.get("http://localhost:" + port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// 1.2: a test that signs up a new user, logs in, verifies that the home page is accessible,
	// logs out, and verifies that the home page is no longer accessible.

	@Test
	public void testLogin() throws InterruptedException {
		// register user
		signup.enterFirstname("Paul");
		signup.enterLastname("Smith");
		signup.enterUsername("paulsmith");
		signup.enterPassword("password");
		signup.submit();
		Thread.sleep(1000);

		// go to login page
		signup.goToLoginPage();
		Thread.sleep(1000);

		// log in user
		login.enterUsername("paulsmith");
		login.enterPassword("password");
		login.submit();
		Thread.sleep(1000);

		// check user has been directed to the homepage
		Assertions.assertEquals("Home", driver.getTitle());

		// logout user
		home.logout();

		// check user has been directed back to login page
		Assertions.assertEquals("Login", driver.getTitle());

		// attempt to go to homepage
		driver.get("http://localhost:" + port + "/home");

		// check user has been denied access and is still on login page
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// 2.1: a test that creates a note, and verifies it is displayed.
	// 2.2: a test that edits an existing note and verifies that the changes are displayed.
	// 2.3: a test that deletes a note and verifies that the note is no longer displayed.
	@Test
	public void testNotes() throws InterruptedException {
		// register and login user
		signup.registerJohnSmith();
		Thread.sleep(500);
		signup.goToLoginPage();
		Thread.sleep(500);
		login.loginJohnSmith();

		// create new note
		Thread.sleep(500);
		home.goToNotesTab();
		//driver.findElement(By.linkText("Notes")).click();
		Thread.sleep(500);
		home.clickNewNote();
		Thread.sleep(500);
		home.enterNoteTitle("Note");
		home.enterNoteDescription("Description");
		home.submitNewNote();
		Thread.sleep(500);

		// click continue to return to the homepage
		result.clickContinue();
		Thread.sleep(500);

		home.goToNotesTab();
		Thread.sleep(500);
		Assertions.assertEquals("Note", home.getFirstNoteTitle());

		home.clickEditFirstNote();
		Thread.sleep(500);
		home.enterNoteTitle("WithEdit");
		home.enterNoteDescription("Description");
		home.submitNewNote();
		Thread.sleep(500);

		// click continue to return to the homepage
		result.clickContinue();
		Thread.sleep(500);

		home.goToNotesTab();
		Thread.sleep(500);
		Assertions.assertEquals("NoteWithEdit", home.getFirstNoteTitle());

		// delete note
		home.clickDeleteFirstNote();
		Thread.sleep(500);

		// click continue to return to the homepage
		result.clickContinue();
		Thread.sleep(500);

		// check note no longer there
		Assertions.assertEquals(0, driver.findElements(By.id("note-1")).size());
	}

	// 3.1: a test that creates a set of credentials, verifies that they are displayed,
	// and verifies that the displayed password is encrypted.
	// 3.2: a test that views an existing set of credentials, verifies that the viewable
	// password is unencrypted, edits the credentials, and verifies that the changes are
	// displayed.
	// 3.3: a test that deletes an existing set of credentials and verifies that the
	// credentials are no longer displayed.

	@Test
	public void testCredentials() throws InterruptedException {
		// login user
		signup.goToLoginPage();
		Thread.sleep(500);
		login.loginJohnSmith();
		Thread.sleep(500);

		home.goToCredentialsTab();
		Thread.sleep(500);

		home.clickNewCredential();
		Thread.sleep(500);
		home.enterCredentialUrl("url");
		home.enterCredentialUsername("username");
		home.enterCredentialPassword("password");
		home.submitCredential();
		Thread.sleep(500);

		result.clickContinue();
		Thread.sleep(500);

		home.goToCredentialsTab();
		Thread.sleep(500);
		Assertions.assertEquals("username", home.getFirstCredentialUsername());
		Assertions.assertNotEquals("password", home.getFirstCredentialPassword());

		home.clickEditFirstCredential();
		Thread.sleep(500);

		Assertions.assertEquals("password", home.getPasswordInputContent());

		home.enterCredentialUsername("WithEdit");
		home.submitCredential();
		Thread.sleep(500);

		result.clickContinue();
		Thread.sleep(500);

		home.goToCredentialsTab();
		Thread.sleep(500);

		Assertions.assertEquals("usernameWithEdit", home.getFirstCredentialUsername());

		home.clickDeleteFirstCredential();
		Thread.sleep(500);

		result.clickContinue();
		Thread.sleep(500);

		home.goToCredentialsTab();
		Thread.sleep(500);

		Assertions.assertEquals(0, driver.findElements(By.id("credential-username-1")).size());
	}
}
