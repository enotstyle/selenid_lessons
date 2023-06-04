package work_with_files.homework;


import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PackingSomeFiles {

    List<String> names = new ArrayList<>(Arrays.asList("qa_guru.csv", "Frame 7607.png", "PDFtest.pdf"));
    ClassLoader cl = PackingSomeFiles.class.getClassLoader();

    @Test
    void checkFilesNamesInArchive() throws Exception {
        try (InputStream archName = cl.getResourceAsStream("arch.zip");
             ZipInputStream zis = new ZipInputStream(archName)) {

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                Assertions.assertTrue(names.contains(zipEntry.getName()));
            }
        }
    }

    @Test
    void checkPdfFileInArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("arch.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".pdf")) {
                    PDF content = new PDF(zis);
                    Assertions.assertTrue(content.text.contains("Инструкция по установке и настройке комплекта"));
                }
            }
        }
    }

    @Test
    void checkCsvFileInArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("arch.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    System.out.println(Arrays.toString(content.get(0)));
                    Assertions.assertTrue(content.get(0)[0].equals("Teacher"));
                }
            }
        }
    }

    @Test
    void checkXlsFileInArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("arch.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("Dulce");
                }
            }
        }
    }
}



