import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SuperTest {
    private WebDriver driver;
    private String urlHomePage = "http://www.i.ua/";
    private String urlMailPage = "http://mbox2.i.ua/";
    public static final String LOGIN_FIELD = "/html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/ul/li[1]/p[2]/input";
    public static final String PASSWORD_FIELD = "/html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/ul/li[1]/input";
    public static final String LOGIN_BUTTON = "/html/body/div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/p/input";

    public static final String CREATE_LETTER_BUTTON = "/html/body/div[1]/div[5]/div[1]/div[1]/p/a";
    public static final String TO_FIELD = "//*[@id=\"to\"]";
    public static final String SUBJECT_FIELD = "/html/body/div[4]/div[5]/div[1]/div[1]/div[1]/div/form/div[5]/div[2]/span/input[1]";
    public static final String TEXT_FIELD = "//*[@id=\"text\"]";
    public static final String SEND_BUTTON = "/html/body/div[4]/div[5]/div[1]/div[1]/p[3]/input[1]";
    public static final String SUCCESSFUL_MESSAGE = "/html/body/div[1]/div[5]/div[2]/div/div/div[1]/div/div[5]";

    @BeforeTest
    public void precondition() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(urlHomePage);
    }

    @Test
    public void superTest() {
        //login
        driver.findElement(By.xpath(LOGIN_FIELD)).sendKeys("lp22");
        driver.findElement(By.xpath(PASSWORD_FIELD)).sendKeys("romashka");
        driver.findElement(By.xpath(LOGIN_BUTTON)).click();
        assertTrue(driver.getCurrentUrl().contains(urlMailPage));

        //sendLetter
        driver.findElement(By.xpath(CREATE_LETTER_BUTTON)).click();
        driver.findElement(By.xpath(TO_FIELD)).sendKeys("lp22@i.ua");
        driver.findElement(By.xpath(SUBJECT_FIELD)).sendKeys("Theme from IDEA :)");
        driver.findElement(By.xpath(TEXT_FIELD)).sendKeys("Hello!" + "\n" + "How are you?");
        driver.findElement(By.xpath(SEND_BUTTON)).click();
        assertTrue(driver.findElement(By.xpath(SUCCESSFUL_MESSAGE)).isDisplayed());
        assertTrue(driver.findElement(By.xpath(SUCCESSFUL_MESSAGE)).getText().equalsIgnoreCase("Лист успішно відправлено адресатам"));

    }

    @AfterTest
    public void post_condition() {
        driver.close();
    }
}
