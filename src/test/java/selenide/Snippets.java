package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

// this is not a full list, just the most common
public class Snippets {

  void browser_command_examples() {
    //установка дефолтного url
    Configuration.baseUrl="https://google.com";
    //открыть страницу по абсолютному url
    open("https://google.com");
    //открыть страницу относительно baseUrl
    open("/customer/orders");     // -Dselenide.baseUrl=http://123.23.23.1
    //переход по оносительному url и базовая авторизация
    open("/", AuthenticationType.BASIC,
            new BasicAuthCredentials("", "user", "password"));


    //возврат на предыдущую страницу браузера
    Selenide.back();
    //обновление страницы браузера
    Selenide.refresh();

    //удаление всех cookie-файлов текущего домена из браузера
    Selenide.clearBrowserCookies();
    //удаляет все данные из локального хранилища браузера
    Selenide.clearBrowserLocalStorage();
    //позволяет выполнить JavaScript-код на странице
    executeJavaScript("sessionStorage.clear();"); // no Selenide command for this yet

    // подтверждает аллерт
    Selenide.confirm(); // OK in alert dialogs
    // нажать отмена в аллерте
    Selenide.dismiss(); // Cancel in alert dialogs

    //закрыть активную вкладку
    Selenide.closeWindow(); // close active tab
    //закрыть браузер
    Selenide.closeWebDriver(); // close browser completely

    //переключение на ферйм (iframe)
    Selenide.switchTo().frame("new");
    //переключение на основной контент страницы
    Selenide.switchTo().defaultContent(); // return from frame back to the main DOM
    //
    Selenide.switchTo().window("The Internet");

    // создание куки
    var cookie = new Cookie("foo", "bar");
    //установить куки для текущего сеанса браузера
    WebDriverRunner.getWebDriver().manage().addCookie(cookie);


  }

  void selectors_examples() {
    //find $ element - одно и то же
    //кликает на первый элемент по селектору
    $("div").click();
    //кликает на первый элемент по селектору
    element("div").click();

    //кликает на третий элемент с заданным селектором
    $("div", 2).click(); // the third div

    //кликает на первый элемент интерфейса на странице, соответствующий XPath
    $x("//h1/div").click();
    //кликает на первый элемент интерфейса на странице, соответствующий XPath
    $(byXpath("//h1/div")).click();

    //ищет элемент, который точно содержит строку в теге
    $(byText("full text")).click();
    //ищет элемент, который содержит часть строки в теге
    $(withText("ull tex")).click();

    //ищет элементы, которые имеют тег "div" и точно содержат строку
    $(byTagAndText("div", "full text"));
    //ищет элементы, которые имеют тег "div" и содержат часть строки
    $(withTagAndText("div", "ull text"));

    //возвращает родительский элемент найденного элемента
    $("").parent();
    //возвращает соседний элемент по указанному индексу относительно текущего элемента (брат ищет вниз)
    $("").sibling(1);
    //возвращает предшествующий элемент по указанному индексу относительно текущего элемента (брат ищет вверх)
    $("").preceding(1);
    //находит ближайший элемент, продвигаясь вверх по иерархии DOM-элементов
    $("").closest("div");
    $("").ancestor("div"); // the same as closest
    //возвращает все последние div'ы
    $("div:last-child");

    //ищет внутри первого div'а и первого h1 элемент с текстом
    $("div").$("h1").find(byText("abc")).click();

    //ищет элемент, содержащий указанный атрибут с заданным значением
    $(byAttribute("abc", "x")).click();
    //ищет элемент, содержащий указанный атрибут с заданным значением
    $("[abc=x]").click();

    //ищет элемент, содержащий указанный id
    $(byId("mytext")).click();
    //ищет элемент, содержащий указанный id
    $("#mytext").click();

    //ищет элемент, содержащий указанный класс
    $(byClassName("red")).click();
    //ищет элемент, содержащий указанный класс
    $(".red").click();

    //получает текущий url
    String url = url();
  }

  void actions_examples() {
    //клик
    $("").click();
    //двойной клик
    $("").doubleClick();
    //клик правой кнопкой
    $("").contextClick();

    //неведение без клика
    $("").hover();

    //удаляет все что было в поле и вводит
    $("").setValue("text");
    //добавляет значение в поле
    $("").append("text");
    //очищает поле
    $("").clear();
    $("").setValue(""); // clear

    //выбирает элемент и эмулирует нажаие клавиши
    $("div").sendKeys("c"); // hotkey c on element
    //эмулирует нажатие клавиши без привязки к элементу
    actions().sendKeys("c").perform(); //hotkey c on whole application
    //эмулирует нажатие комбинации клавиш без привязки к элементу
    actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
    $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

    //нажатие ентер
    $("").pressEnter();
    //нажатие эскейп
    $("").pressEscape();
    //нажатие таб
    $("").pressTab();


    // complex actions with keybord and mouse, example
    // пододвинуть мыш к элементу div, нажать лкм и не отпускать, передвинуть и отпустить
    actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

    // old html actions don't work with many modern frameworks
    //выбрать элемент и выбрать пункт дропдауна, только если тэг select, а внутри него option
    $("").selectOption("dropdown_option");
    //выбрать элемент и выбрать пункт радиобатона
    $("").selectRadio("radio_options");

  }

  void assertions_examples() {
    //проверка условия, все они одинаковые, нужны для красисвой связки в предложения
    $("").shouldBe(visible);
    $("").shouldNotBe(visible);
    $("").shouldHave(text("abc"));
    $("").shouldNotHave(text("abc"));
    $("").should(appear);
    $("").shouldNot(appear);


    //longer timeouts
    //задать вручную таймаут
    $("").shouldBe(visible, Duration.ofSeconds(30));

  }

  void conditions_examples() {
    //виден ли элемент
    $("").shouldBe(visible);
    //спрятан ли элемент в течении 4 сек
    $("").shouldBe(hidden);

    //есть ли часть текста
    $("").shouldHave(text("abc"));
    //есть ли полное совпадение текста
    $("").shouldHave(exactText("abc"));
    //есть ли часть текста учитывая кейс написания
    $("").shouldHave(textCaseSensitive("abc"));
    //есть ли полное совпадение текста учитывая кейс написания
    $("").shouldHave(exactTextCaseSensitive("abc"));
    //проверка регуляркой
    $("").should(matchText("[0-9]abc$"));

    //проверят есть ли класс у элемента
    $("").shouldHave(cssClass("red"));
    //проверят есть ли ccs значение у элемента
    $("").shouldHave(cssValue("font-size", "12"));

    //проверяет значение атрибута "value" на часть текста
    $("").shouldHave(value("25"));
    //проверяет значение атрибута "value" на весь текст
    $("").shouldHave(exactValue("25"));
    //не содержит дочерних элементов и не имеет текстового содержимого
    $("").shouldBe(empty);

    //проверка существования атрибута
    $("").shouldHave(attribute("disabled"));
    //проверка существования атрибута со значением
    $("").shouldHave(attribute("name", "example"));
    //проверка существования атрибута с использованием регулярки
    $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

    //проверка что чекбокс проставлен
    $("").shouldBe(checked); // for checkboxes

    //существует ли элемент на странице даже если он не виден
    // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
    $("").should(exist);

    //проверить значение атрибута disabled (активность кнопки)
    // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
    $("").shouldBe(disabled);
    $("").shouldBe(enabled);
  }

  void collections_examples() {

    //поиск коллекции элементов
    $$("div"); // does nothing!
    //поиск коллекции элементов по xpath
    $$x("//div"); // by XPath

    // selections
    //ищет элементы которые удовлетворяют условию
    $$("div").filterBy(text("123")).shouldHave(size(1));
    //ищет элементы которые НЕ удовлетворяют условию
    $$("div").excludeWith(text("123")).shouldHave(size(1));

    //получить первый элемент из коллекции
    $$("div").first().click();
    elements("div").first().click();
    // $("div").click();
    //получить последний элемент из коллекции
    $$("div").last().click();
    //получить элемент по индексу коллекции
    $$("div").get(1).click(); // the second! (start with 0)
    $("div", 1).click(); // same as previous
    //комбинация filterBy и first, ищет по фильтру первый элемент
    $$("div").findBy(text("123")).click(); //  finds first

    // assertions
    //проверяет размер коллекции
    $$("").shouldHave(size(0));
    $$("").shouldBe(CollectionCondition.empty); // the same

    //проверяет что коолекция содержит 3 элемента и вхождения этих строк
    $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
    //проверяет что коолекция содержит 3 элемента и точное совпадение текста
    $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

    //проверяет что коолекция содержит 3 элемента и вхождения этих строк, порядок не важен
    $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
    //проверяет что коолекция содержит 3 элемента, точное совпадение, включая регистр в любом порядке
    $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

    //один или несколько элементов этой коллекции имеют точно заданный текст
    $$("").shouldHave(itemWithText("Gamma")); // only one text

    //коллекция больше нуля
    $$("").shouldHave(sizeGreaterThan(0));
    //коллекция больше или равна 1
    $$("").shouldHave(sizeGreaterThanOrEqual(1));
    //коллекция меньше 3
    $$("").shouldHave(sizeLessThan(3));
    //коллекция меньше или равна 2
    $$("").shouldHave(sizeLessThanOrEqual(2));


  }

  void file_operation_examples() throws FileNotFoundException {

    //загрузить файл
    File file1 = $("a.fileLink").download(); // only for <a href=".."> links
    File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

    //создание файла
    File file = new File("src/test/resources/readme.txt");
    //загрузка файла
    $("#file-upload").uploadFile(file);
    $("#file-upload").uploadFromClasspath("readme.txt");
    // don't forget to submit!
    $("uploadButton").click();
  }

  void javascript_examples() {
    //выполнить js скрипт
    executeJavaScript("alert('selenide')");
    //выполнить js скрипт с аргументами
    executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
    //выполнить js скрипт с аргементами и возращаемымм значением
    long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

  }
}

