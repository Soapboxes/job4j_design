package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void shouldThrowExceptionWhenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkWhenContainInvalidFormatSymbol() {
        NameLoad nameLoad = new NameLoad();
        String key = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(key))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(key)
                .hasMessageContaining("=");
    }

    @Test
    void checkWhenArgumentExceptionValue() {
        NameLoad nameLoad = new NameLoad();
        String value = "key =";
        assertThatThrownBy(() -> nameLoad.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(value)
                .hasMessageContaining("does not contain a value");
    }

    @Test
    void checkWhenArgumentExceptionKey() {
        NameLoad nameLoad = new NameLoad();
        String key = " = value";
        assertThatThrownBy(() -> nameLoad.parse(key))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(key)
                .hasMessageContaining("does not contain a key");
    }
}