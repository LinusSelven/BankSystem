package org.openjfx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openjfx.Model;


public class ModelTest {

    @Test
    void testingModelConstructor() {
        Model output = new Model();
        Assertions.assertNotNull(output);
    }

    //TODO Add a test for the getter


}