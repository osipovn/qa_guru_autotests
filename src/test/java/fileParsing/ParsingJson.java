package fileParsing;


import com.fasterxml.jackson.databind.ObjectMapper;
import fileParsing.domain.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParsingJson {

    ClassLoader classLoader = ParsingJson.class.getClassLoader();

    @Test
    void jsonParser() throws IOException {
        InputStream file = classLoader.getResourceAsStream("TestJson.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(file, Person.class);
        assertThat(person.getName()).isEqualTo("Ivan");
        assertThat(person.getHobbys()).contains(true);
        assertThat(person.getHobbys()).contains(69);
        assertThat(person.getMother().getMotherName()).isEqualTo("Anastasya");
    }

}
