package com.learn.project.journal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,3,6"
    })
    public void passManyParams(Integer p1, Integer p2, Integer res) {
        assertEquals(p1 + p2, res);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "bcd"})
    public void passValueSource(String val) {
        assertEquals(3, val.length());
    }
}
