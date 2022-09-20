import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
//    private AndroidDriver driver;

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "Android");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability("appPackage", "com.poincenot.bpn.mobile.qa");
        desiredCapabilities.setCapability("appActivity", "com.poincenot.doit.controller.activity.SplashActivity");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

    }

    @Test
    public void sampleTest() {


        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.userInput));

        driver.findElement(LoginPage.userInput).sendKeys("test");
        driver.findElement(LoginPage.passwordInput).sendKeys("test");
        driver.findElement(LoginPage.submitButton).click();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

