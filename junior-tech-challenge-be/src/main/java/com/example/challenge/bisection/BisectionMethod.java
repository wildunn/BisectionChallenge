package com.example.challenge.bisection;

import java.util.function.DoubleUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BisectionMethod {

    private static final Logger log = LoggerFactory.getLogger(BisectionMethod.class);

    private BisectionMethod() {
        throw new IllegalStateException("Class should not be instantiated");
    }

    public static double findRoot(
        DoubleUnaryOperator f,
        double a,
        double b,
        double tolerance,
        int maxIterations
    ) {
        
        int n = 0;
        double c;
        double fa = f.applyAsDouble(a);
        double fb = f.applyAsDouble(b);
        double fc;
        
        //Conditions
        if( (a<b) && (fa < 0 && fb > 0 || fa > 0 && fb < 0) ){

            while (n <= maxIterations){  
                c = (a + b)/2; // new midpoint
                if (c == 0 || ((b - a) / 2) < tolerance){
                    log.info("Result of findRoot is:" + c);
                    return c;
                }

                n++; 

                fc = f.applyAsDouble(c);
                if (Math.signum(fc) == Math.signum(f.applyAsDouble(a)) ) a = c;                
                else b = c;
            }
            
        }
        log.info("Result of findRoot is: 0.00");
        throw new UnsupportedOperationException("No answer found");
        
    }
    
}
