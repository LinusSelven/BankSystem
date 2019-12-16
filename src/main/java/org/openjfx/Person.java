package org.openjfx;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;


public class Person implements Human {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleIntegerProperty age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleIntegerProperty(age);
    }


    @Override
    public String getFirstName() {
        return firstName.get();
    }

    @Override
    public String getLastName() {
        return lastName.get();
    }

    @Override
    public int getAge() {
        return age.get();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Override
    public void setAge(int age) {
        this.age.set(age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }
}
