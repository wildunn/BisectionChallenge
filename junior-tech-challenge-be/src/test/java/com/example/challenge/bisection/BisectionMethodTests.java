package com.example.challenge.bisection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.DoubleUnaryOperator;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit :: Bisection :: Bisection Method")
class BisectionMethodTests {

    DoubleUnaryOperator f = (x) -> Math.pow(x, 3) - x - 2;
    double tolerance = 1e-6;
    int maxIterations = 100;

    @Test
    @DisplayName("Should find an acceptable root if interval is valid")
    void findRoot_validInterval_shouldFindAnAcceptableRoot() {
        var root = BisectionMethod.findRoot(f, 1, 2, tolerance, maxIterations);

        Assertions.assertThat(
            BigDecimal.valueOf(root)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue())
            .isEqualTo(1.521d);
    }
    
    @Test
    @DisplayName("Should return 0.00 as the conditions are not met")
    void findRoot_validInterval_bLargerThanA() {
        
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            BisectionMethod.findRoot(f, 2, 1, tolerance, maxIterations);
        });
        
        String expectedMessage = "No answer found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    @DisplayName("Should return 0.00 as the conditions are not met")
    void findRoot_validInterval_faAndFBLessThanZero() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            BisectionMethod.findRoot(f, -1, 1, tolerance, maxIterations);
        });
        
        String expectedMessage = "No answer found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    
    @Test
    @DisplayName("Should return 0.00 as the conditions are not met")
    void findRoot_validInterval_faAndFBMoreThanZero() {

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            BisectionMethod.findRoot(f, 2, 3, tolerance, maxIterations);
        });
        
        String expectedMessage = "No answer found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        
        
    }

}
