import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ValidadorTest {
    PasswordValidator validador;

    @Nested
    class TestWithBlacklist {
        @BeforeEach
        public void init() {
            validador = new PasswordValidator("blacklist.txt");
        }

        @Test
        public void passwordTooShort() {
            Assertions.assertFalse(validador.validate("lal"));
        }

        @Test
        public void passwordIsInBlacklist() {
            Assertions.assertFalse(validador.validate("password"));
        }

        @Test
        public void validPassword() {
            Assertions.assertTrue(validador.validate("AguanteLaUTNLoco"));
        }

    }

    @Nested
    class TestWithoutBlacklist {

        @BeforeEach
        public void init() {
            validador = new PasswordValidator(null);
        }

        @Test
        public void passwordTooShort() {
            Assertions.assertFalse(validador.validate("lal"));
        }

        @Test
        public void passwordIsInBlacklist() {
            // This is a valid password, since no blacklist was specified
            Assertions.assertTrue(validador.validate("password"));
        }

        @Test
        public void validPassword() {
            Assertions.assertTrue(validador.validate("AguanteLaUTNLoco"));
        }

    }


}
