package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", null, "three");
        assertThat(list).isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .contains(null, Index.atIndex(1))
                .containsExactly("first", null, "three");
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "first");
        assertThat(set).isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("zero", "first", "second", "first");
        assertThat(map).isNotNull()
                .hasSize(3)
                .containsKeys("first", "second", "zero")
                .doesNotContainKey("three")
                .doesNotContainValue(3)
                .containsEntry("zero", 0);
    }
}