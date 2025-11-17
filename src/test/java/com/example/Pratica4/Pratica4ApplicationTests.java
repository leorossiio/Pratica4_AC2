// src/test/java/com/example/Pratica4/Pratica4ApplicationTests.java
package com.example.Pratica4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Pratica4ApplicationTests {

    @Test
    void contextLoads() {

    }
    @Test
    void main() {
        // Este teste apenas executa o método main para fins de cobertura
        Pratica4Application.main(new String[]{});
    }
}