package fivvy.backend_challenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendChallengeApplicationTests {

    // Test class added ONLY to cover main() invocation not covered by application tests.
    @Test
    public void mainTest() {
        BackendChallengeApplication.main(new String[] {});
    }
}
