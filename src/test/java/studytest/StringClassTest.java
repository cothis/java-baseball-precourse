package studytest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringClassTest {

    @Test
    @DisplayName("요구사항1: Split")
    void splitTest() {
        // given
        final String input1 = "1,2";
        final String input2 = "1";

        // when
        final String[] result1 = input1.split(",");
        final String[] result2 = input2.split(",");

        // then
        assertThat(result1).containsExactly("1", "2");
        assertThat(result2).containsExactly("1");
    }

    @Test
    @DisplayName("요구사항2: Parenthesis removed split")
    void splitWithoutParenthesis() {
        // given
        final String input = "(1,2)";

        // when
        final String inputWithoutParenthesis = input.substring(1, input.length() - 1);
        final String[] result = inputWithoutParenthesis.split(",");

        // then
        assertThat(result).containsExactly("1", "2");
    }

    @Test
    @DisplayName("요구사항3: charAt 테스트")
    void charAt() {
        // given
        final String input = "abc";

        // when
        // then
        assertThat(input.charAt(0)).isEqualTo('a');
        assertThat(input.charAt(1)).isEqualTo('b');
        assertThat(input.charAt(2)).isEqualTo('c');
        assertThatThrownBy(() -> input.charAt(4))
                .isExactlyInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: 4");
    }
}
