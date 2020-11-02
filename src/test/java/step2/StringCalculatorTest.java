package step2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("2단계: 문자열 계산기")
public class StringCalculatorTest {

    @Test
    @DisplayName("더하기 연산")
    public void executePlus() {
        Integer result = StringCalculator.execute("1 + 2");

        assertThat(result).isEqualTo(3);
    }


    @Test
    @DisplayName("빼기 연산")
    public void executeMinus() {
        Integer result = StringCalculator.execute("1 - 2");

        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("곱하기 연산")
    public void executeMultiply() {
        Integer result = StringCalculator.execute("1 * 2");

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("나누기 연산")
    public void executeDivide() {
        Integer result = StringCalculator.execute("2 / 1");

        assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest(name = "{0} = {1}")
    @DisplayName("사칙연산 골고루 사용한 계산")
    @CsvSource(value = {"2 + 3 * 4 / 2:10", "1 - 3 / 2 + 5 * 2:8"}, delimiter = ':')
    public void complexExpression(String input, Integer expectedResult) {
        Integer result = StringCalculator.execute(input);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력 값이 null이거나 empty일 경우 IllegalArgumentException throw")
    public void nullInput(String input) {
        assertThatThrownBy(() -> {
            StringCalculator.execute(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}