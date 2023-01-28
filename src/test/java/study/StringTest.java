package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }
    @Test
    void split() {
        String target = "1,2";
        String[] splitedTarget = target.split(",");
        assertThat(splitedTarget).contains("1","2");

        target = "1";
        splitedTarget = target.split(",");
        assertThat(splitedTarget).containsExactly("1");
    }

    @Test
    void substring(){
        String afterFunc = "(1,2)".substring(1,"(1,2)".length()-1);
        assertThat(afterFunc).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt 인덱스가 없을때 에러발생")
    void charAt() {
        assertThatThrownBy( () ->{
            int index = 10;
            char result = "abc".charAt(index);
        }).isInstanceOf(IndexOutOfBoundsException.class).hasMessageContaining("String index out of range: 10");

    }
}
