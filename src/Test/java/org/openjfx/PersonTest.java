package org.openjfx;

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

    @Test
    void testPersonSetters(){
        Person p1 = new Person("Emily", "Inns", 23);

        p1.setFirstName("Sana");
        p1.setLastName("Minatozaki");
        p1.setAge(22);

        Assertions.assertEquals("Sana", p1.getFirstName());
        Assertions.assertEquals("Minatozaki", p1.getLastName());
        Assertions.assertEquals(22, p1.getAge());

    }

    @Test
    void testPersonToString(){
        Person p1 = new Person("Emily", "Inns", 23);
        Assertions.assertEquals("Person{firstName=StringProperty [value: Emily], lastName=StringProperty [value: Inns], age=IntegerProperty [value: 23]}",p1.toString());
    }

    @Test
    void testPersonEquals(){
        Person p1 = new Person("Emily", "Inns", 23);
        Person p2 = new Person("Sana", "Minatozaki", 22);
        Person p3 = new Person("Emily", "Inns", 23);

        Assertions.assertEquals(true, p1.equals(p3));
        Assertions.assertEquals(false, p1.equals(p2));


    }

}
