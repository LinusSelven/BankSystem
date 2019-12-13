import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjfx.Human;
import org.openjfx.Person;

public class PersonTest {

    @Test
    void testPersonGetters(){
        Person p1 = new Person("Emily", "Inns", 23);
        Assertions.assertEquals("Emily", p1.getFirstName());
        Assertions.assertEquals("Inns", p1.getLastName());
        Assertions.assertEquals(23, p1.getAge());
    }
}
