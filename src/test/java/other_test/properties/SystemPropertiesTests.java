package other_test.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    void simplePropertyTest() {
        String browserName = System.getProperty("browser");
        System.out.println(browserName);  //null
    }

    @Test
    void simplePropertyTest1() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName);  //opera
    }


    @Test
    void simplePropertyTest2() {
        System.setProperty("browser", "firefox");
        //если не было setProperty с файрфокосм, то вернет оперу
        String browserName = System.getProperty("browser", "opera");
        System.out.println(browserName);  //opera
    }


    @Test
    @Tag("one_property")
    void simplePropertyTest3() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);
        //gradle clean one_property_test
        //firefox

        //gradle clean one_property_test -Dbrowser=safari
        //safari
    }

    @Test
    @Tag("hello_test")
    void simplePropertyTest4() {
        System.out.println("Hello " + System.getProperty("user_name", "unknown student"));

        /*
        gradle clean hello_test
        Hello, unknown student

        gradle clean hello_test -Duser_name=ivan
        Hello, ivan
         */

    }
}
