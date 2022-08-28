package fileParsing;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ParsingZip {

    ClassLoader classLoader = ParsingZip.class.getClassLoader();

    @Test
    void parseCsvFromZip() throws Exception {
        InputStream file = classLoader.getResourceAsStream("Desktop.zip");
        ZipInputStream zip = new ZipInputStream(file);
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            if (entry.getName().contains("Test1.csv")) {
                CSVReader csvReader = new CSVReader(new InputStreamReader(zip, UTF_8));
                List<String[]> csv = csvReader.readAll();
                assertThat(csv).contains(
                        new String[]{"QuotaAmount", "StartDate", "OwnerName", "Username"},
                        new String[]{"150000", "2016-01-01", "Chris Riley", "trailhead9.ub20k5i9t8ou@example.com"},
                        new String[]{"150000", "2016-03-01", "Dennis Howard", "trailhead4.wfokpckfroxp@example.com"}
                );
            }
        }
    }

    @Test
    void parseXlsFromZip() throws Exception {
        InputStream file = classLoader.getResourceAsStream("Desktop.zip");
        ZipInputStream zip = new ZipInputStream(file);
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            if (entry.getName().contains("Test2.xlsx")) {
                XLS xls = new XLS(zip);
                assertThat(xls.excel.getSheetAt(0)
                        .getRow(15)
                        .getCell(1)
                        .getNumericCellValue()).isEqualTo(3);
            }
        }
    }

    @Test
    void parsePdfFromZip() throws Exception {
        InputStream file = classLoader.getResourceAsStream("Desktop.zip");
        ZipInputStream zip = new ZipInputStream(file);
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            if (entry.getName().contains("X4X3A59450J024854.pdf")) {
                PDF pdf = new PDF(zip);
                assertThat(pdf.text).contains("VIN: X4X3A59450J024854");
            }
        }
    }
}
