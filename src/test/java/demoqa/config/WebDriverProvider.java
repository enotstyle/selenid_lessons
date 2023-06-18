package demoqa.config;

import com.codeborne.selenide.Configuration;
import demoqa.config.WebConfig;
import groovy.xml.StreamingDOMBuilder;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverProvider {

    private final WebConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebConfig.class, System.getProperties());
        createWebDriver();
    }

    public void createWebDriver() {
        switch (config.getBrowser().toLowerCase()) {
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "chrome":
            default:
                Configuration.browser = "chrome";
        }

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browserVersion = config.getBrowserVersion();
        // получение удаленной ссылки на selenoid
        Configuration.remote = config.getRemoteUrl();
        Configuration.pageLoadTimeout = 300000;

        //включение видео
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }
}
