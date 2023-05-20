package github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideRepositorySearch {

  @Test
  void shouldFindSelenideRepositoryAtTheTop() {
    // открыть главную страницу
    Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

    open("https://github.com/");
    $("[name=\"q\"]").setValue("Selenium");
    


  }

}
