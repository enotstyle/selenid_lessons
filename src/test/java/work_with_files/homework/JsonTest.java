package work_with_files.homework;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import work_with_files.homework.model.Sample;

import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonTest {
    ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonFileTest() throws Exception {
        Gson gson = new Gson();

        try (InputStream is = cl.getResourceAsStream("sample2.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            Assertions.assertTrue(jsonObject.get("address")
                    .getAsJsonObject().get("streetAddress").getAsInt() == 101);
            Assertions.assertTrue(jsonObject.get("phoneNumbers")
                    .getAsJsonArray().get(0).getAsJsonObject().get("number").getAsLong() == 7349282382L);
        }

    }

    @Test
    void jsonFileTestWithClass() throws Exception {
        Gson gson = new Gson();

        try (InputStream is = cl.getResourceAsStream("sample2.json");
             InputStreamReader reader = new InputStreamReader(is)) {
            Sample jsonObject = gson.fromJson(reader, Sample.class);
            Assertions.assertTrue(jsonObject.gender.equals("male"));
            Assertions.assertTrue(jsonObject.phoneNumbers.get(0).number == 7349282382L);
        }

    }
}
