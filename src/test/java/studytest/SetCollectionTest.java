package studytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SetCollectionTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    @DisplayName("요구사항1: Set 크기 확인")
    void size(final int expect) {
        // when
        final int size = numbers.size();

        // then
        assertThat(size).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("요구사항2: contains로 1, 2, 3 존재 확인")
    void contains(final int input) {
        // when
        final boolean contain = numbers.contains(input);

        // then
        assertThat(contain).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
    @DisplayName("요구사항3: 4, 5를 넣으면 false 도 확인")
    void containsWithFalseCase(final int input, final boolean expect) {
        // when
        final boolean contain = numbers.contains(input);

        // then
        assertThat(contain).isSameAs(expect);
    }
}
