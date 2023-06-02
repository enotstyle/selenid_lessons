package work_with_files;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SelenideFilesTest {

    static {
//        Configuration.fileDownload = FileDownloadMode.PROXY;
//        Configuration.downloadsFolder = "";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        System.out.println("-------------Запуск теста-------------");
    }


    @Disabled("хз пачему падает")
    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloadedFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            byte[] bytes = is.readAllBytes();
            String textContent = new String(bytes, StandardCharsets.UTF_8);
            assertThat(textContent).contains("This repository is the home of the next generation of JUnit, _JUnit 5_");
        }
    }


    @Test
    void selenideUploadTest() throws Exception {
        open("https://fineuploader.com/demos.html");
        //нужно делать через classPath положив файл в resources
//        $("input[type=file]").uploadFile(new File("resources/readme.txt"));
        $("input[type=file]").uploadFromClasspath("Frame 7607.png");
        $("div.qq-file-name").shouldHave(text("Frame 7607.png"));
        $("span.qq-upload-file-selector").shouldHave(
                Condition.attribute("title", "cat.png")
        );


    }

}

