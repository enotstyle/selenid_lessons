package work_with_files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import work_with_files.model.Glossary;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


public class FileParsingTest {

    //получаем ClassLoader
    ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide");
        File pdfFile = $("a[href=\"junit-user-guide-5.9.3.pdf\"]").download();
        PDF content = new PDF(pdfFile);
        assertThat(content.author).contains("Sam Brannen");
    }

    @Test
    void xlsParseTest() throws Exception {
        //обращаемся через ClassLoader для получения потока из папки resources
        try (InputStream resourceAsStream = cl.getResourceAsStream("sample-xlsx-file.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("Dulce");
        }
    }

    @Test
    void csvParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("qa_guru.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(resourceAsStream))) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(0)[1]).contains("lesson");
        }
    }

    @Test
    void zipParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("frame.zip");
             ZipInputStream zis = new ZipInputStream(resourceAsStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("Frame 7607.png");
            }
        }
    }

    @Test
    void jsonParseTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream resourceAsStream = cl.getResourceAsStream("glossary.json");
             InputStreamReader isr = new InputStreamReader(resourceAsStream)) {
            JsonObject jsonObject = gson.fromJson(isr, JsonObject.class);
            assertThat(jsonObject.get("title").getAsString()).isEqualTo("example glossary");
            assertThat(jsonObject.get("gloss_div").getAsJsonObject().get("title").getAsString()).isEqualTo("S");
            assertThat(jsonObject.get("gloss_div").getAsJsonObject().get("flag").getAsBoolean()).isTrue();
        }
    }

    @Test
    void jsonParseImprovedTest() throws Exception {
        Gson gson = new Gson();
        try (InputStream resourceAsStream = cl.getResourceAsStream("glossary.json");
             InputStreamReader isr = new InputStreamReader(resourceAsStream)) {

            Glossary jsonObject = gson.fromJson(isr, Glossary.class);
            System.out.println(jsonObject.title);
            assertThat(jsonObject.title).isEqualTo("example glossary");
            assertThat(jsonObject.glossDiv.title).isEqualTo("S");
            assertThat(jsonObject.glossDiv.flag).isTrue();
        }
    }
}
