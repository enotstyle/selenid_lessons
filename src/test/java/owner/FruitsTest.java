package owner;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.FruitsConfig;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class FruitsTest {

    @Test
    public void testArray() {
        System.setProperty("array", "banana,apple");

        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsArray())
                .containsExactly("banana", "apple");
    }

    @Test
    public void testList() {
        System.setProperty("list", "apple,orange");

        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsList())
                .containsExactly("apple", "orange");
    }

    @Test
    public void testArrayWithDefaultValues() {
        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsArrayWithDefaultValues())
                .containsExactly("orange", "apple");
    }

    @Test
    public void testArrayWithSeparator() {
        FruitsConfig config = ConfigFactory.create(FruitsConfig.class, System.getProperties());
        assertThat(config.getFruitsListWithSeparator())
                .containsExactly("orange", "banana");
    }

}
