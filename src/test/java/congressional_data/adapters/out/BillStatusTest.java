package congressional_data.adapters.out;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import congressional_data.domain.CongressionalBill;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@QuarkusTest
class BillStatusTest {

    @DisplayName("Should deserialize object properly from xml file")
    @Test
    public void t1() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        byte[] rawBillContents = Files.readAllBytes(Paths.get("src/test/resources/sample.xml"));

        String readContent = new String(rawBillContents);

        CongressionalBill deserializedData = xmlMapper.readValue(readContent, CongressionalBill.class);
        System.out.println(deserializedData.toString());
    }

}