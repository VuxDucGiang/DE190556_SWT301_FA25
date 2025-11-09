package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Enable JavaScript for DemoQA practice form (it requires JavaScript to work)
        // Comment out the line below if you need to disable JavaScript
        // Map<String, Object> prefs = new HashMap<>();
        // prefs.put("profile.managed_default_content_settings.javascript", 2);
        // options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }
}
