package HW4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.Setup;

import static org.testng.Assert.*;


public class MainClassTest extends Setup {

    private WebElement loginForm;
    private WebElement passwordForm;
    private WebElement loginButton;
    private WebElement basicFolderTable;
    private WebElement subjectTitleField;
    private WebElement closeNewEmailDialogButton;

    private WebDriver driver;
    private WebDriverWait driverWait;

    @BeforeClass
    private void prepareDriver() throws Exception {
        driver = getDriver();
        driverWait = getDriverWait();
    }

    @Test
    public void checkAnEMailCreation() {
        // 1 open site by URL
        driver.navigate().to("https://mail.ru/");

        // 2 login
        loginForm = driver.findElement(By.xpath("//input[@id='mailbox:login']"));

        loginForm.click();
        loginForm.sendKeys("autotest_autotest");

        loginButton = driver.findElement(By.xpath("//input[@class='o-control']"));
        loginButton.click();

        passwordForm = driver.findElement(By.xpath("//input[@id='mailbox:password']"));

        driverWait.until(ExpectedConditions.visibilityOf(passwordForm));
        passwordForm.sendKeys("THISis!@#");
        loginButton.click();

        // 3 assert login is successful
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'portal-menu')]")));
        assertTrue(driver.getTitle().contains("Входящие"));

        // 4 create new e-mail
        driver.findElement(By.xpath("//span[@title='Написать письмо']")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'editable-container')]")));

        driver.findElement(By.xpath("//div[@data-type='to']//input[@type='text']")).sendKeys("autotest_autotest@mail.ru");

        subjectTitleField = driver.findElement(By.xpath("//input[@name='Subject']"));

        subjectTitleField.sendKeys("Tests e-mail");
        driver.findElement(By.xpath("//div[contains(@class, 'editable-container')]/div[1]")).sendKeys("Hello! This is a test e-mail.");

        // 5 save e-mail as a draft
        driver.findElement(By.xpath("//span[@title='Сохранить']")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='notify__message__text']")));
        driver.findElement(By.xpath("//button[@title='Закрыть']")).click();
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'dimmer')]")));

        // 6 verify that e-mail has been saved as a draft
        driver.findElement(By.xpath("//div[text()='Черновики']")).click();

        basicFolderTable = driver.findElement(By.xpath("//div[contains(@style, 'display: table;')]"));
        driverWait.until(ExpectedConditions.visibilityOf(basicFolderTable));

        assertTrue(driver.findElement(By.xpath("//*[text()='Tests e-mail']")).getText().contains("Tests e-mail"));
    }

    @Test
    public void checkContentOfEMailIsCorrect() {
        // 7 verify that content of e-mail is correct
        driver.findElement(By.xpath("//*[text()='Tests e-mail']")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'editable-container')]")));
        assertEquals(driver.findElement(By.xpath("//div[contains(@class, 'contactsContainer')]")).getText(), "autotest_autotest@mail.ru");

        subjectTitleField = driver.findElement(By.xpath("//input[@name='Subject']"));

        assertEquals(subjectTitleField.getAttribute("value"), "Tests e-mail");
        assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'editable-container')]")).getText().contains("Hello! This is a test e-mail."));
    }

    @Test
    public void checkEmailCanBeSent() {
        // 8 send the e-mail
        driver.findElement(By.xpath("//span[@title='Отправить']")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Закрыть']")));

        closeNewEmailDialogButton = driver.findElement(By.xpath("//span[@title='Закрыть']"));

        closeNewEmailDialogButton.click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title,'Почта Mail.ru')]")));

        // 9 verify that the e-mail disappeared from 'Drafts' folder
        driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class, 'dataset__items')]/a"), 0));
        assertFalse(basicFolderTable.getText().contains("Tests e-mail"));

        // 10 verify the the e-mail is in 'Sent' folder
        driver.findElement(By.xpath("//div[text()='Отправленные']")).click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@style, 'display: table;')]")));

        assertTrue(driver.findElement(By.xpath("//div[contains(@style, 'display: table;')]")).getText().contains("Tests e-mail"));

        // 11 log off
        driver.findElement(By.xpath("//a[@title='выход']")).click();
        driverWait.until(ExpectedConditions.titleIs("Почта Mail.ru - бесплатный и безопасный электронный почтовый ящик"));
    }
}
