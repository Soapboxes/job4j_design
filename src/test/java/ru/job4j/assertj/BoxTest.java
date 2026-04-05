package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 4);
        String name = box.whatsThis();
        assertThat(name)
                .isNotNull()
                .isNotEmpty()
                .doesNotContain("Tetroid")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 6);
        String name = box.whatsThis();
        assertThat(name)
                .doesNotContain("cube")
                .isEqualTo("Cube");
    }

    @Test
    void getNumberOfVerticesCube() {
        Box box = new Box(8, 6);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotZero()
                .isPositive()
                .isEven()
                .isEqualTo(8);
    }

    @Test
    void getNumberOfVerticesAndWithZeroEdge() {
        Box box = new Box(4, 0);
        assertThat(box.getNumberOfVertices()).isNegative();
    }

    @Test
    void getNumberOfVerticesWithZero() {
        Box box = new Box(0, 6);
        assertThat(box.getNumberOfVertices()).isZero();
    }

    @Test
    void isExistShouldReturnTrueWhenVerticesAreValid() {
        Box box = new Box(8, 6);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void isExistShouldReturnTrueWhenCube() {
        Box box = new Box(8, 6);
        assertThat(box)
                .returns(8, Box::getNumberOfVertices)
                .returns(true, Box::isExist);
    }

    @Test
    void isExistShouldReturnFalseWhenVerticesAreValid() {
        Box box = new Box(4, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void shouldBeInvalidWhenEdgesAreZero() {
        Box box = new Box(4, 0);
        assertThat(box)
                .returns(-1, Box::getNumberOfVertices)
                .returns(false, Box::isExist);
    }

    @Test
    void getAreaCubeAndVerticalCube() {
        Box box = new Box(8, 6);
        assertThat(box)
                .returns(8, Box::getNumberOfVertices)
                .returns("Cube", Box::whatsThis);
        assertThat(box.getArea()).isEqualTo(216.0, withPrecision(0.01d));
    }

    @Test
    void getAllTestBoxMethodWithSphere() {
        Box box = new Box(0, 5);
        double expectedArea = 4 * Math.PI * (5 * 5);
        assertThat(box)
                .returns("Sphere", Box::whatsThis)
                .returns(0, Box::getNumberOfVertices)
                .returns(true, Box::isExist);
        assertThat(box.getArea())
                .isCloseTo(expectedArea, within(0.1d))
                .isPositive()
                .isGreaterThan(314);
    }

    @Test
    void unknownObjectTest() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
        assertThat(box.isExist()).isFalse();
    }

}