package com.example.challenge.controller;

import java.util.Optional;
import com.example.challenge.bisection.BisectionMethod;
import java.util.function.DoubleUnaryOperator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wildu
 */

@RestController
public class ChallengeController {
    
    @GetMapping("/")
    public String index() {
        return "Hello, there!";
    }
    
    
    //Example - http://localhost:8080/bisection?a=1&b=2
    @GetMapping("/bisection")
    public String BisectionResults(@RequestParam(required=true,defaultValue="1") Double a, @RequestParam(required=true,defaultValue="2") Double b ) {
        
        DoubleUnaryOperator f = (x) -> Math.pow(x, 3) - x - 2;
        double tolerance = 1e-6;
        int maxIterations = 100;

        try{
            double result = BisectionMethod.findRoot(f, a, b, tolerance, maxIterations);
            String output = String.format("The results of 'findRoot' using a=%f & b=%f resulted in %f", a,b,result);
            return output;
        }
        catch(UnsupportedOperationException exception){
            String output = String.format("The results of 'findRoot' using a=%f & b=%f resulted in an error. Please try again using a different value for a or b.", a,b);
            return output;
        }
    }  
    
}
