import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HBAppTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "Android");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
//        desiredCapabilities.setCapability("appium:udid", "R5CT20473PP");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability("appPackage", "com.poincenot.bpn.mobile.qa");
        desiredCapabilities.setCapability("appActivity", "com.poincenot.doit.controller.activity.SplashActivity");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("disableWindowAnimation", "true");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void loginTest() {


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.userInput));


        driver.findElement(LoginPage.userInput).sendKeys("rioshugo");
        driver.findElement(LoginPage.passwordInput).sendKeys("QAbpn2022");
        driver.findElement(LoginPage.submitButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(LoginPage.accept_check));
        driver.findElement(LoginPage.accept_check).click();
        driver.findElement(LoginPage.accept_button).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.txtWelcome));

        Assert.assertTrue(driver.findElement(LoginPage.txtWelcome).getText().contains("HUGO"));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

