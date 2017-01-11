package optimizer.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by abhishek on 7/1/17.
 */
public class Derivative implements Function<BiFunction<Double, Double, Double>, BiFunction<Double, Double, Double>> {


  /*  BiFunction<Double, Double, Double> powerFunction = Math::pow;
    BiFunction<Double, Double, Double> multiplication = (aDouble, aDouble2) -> aDouble * aDouble2;
    BiFunction<Double, Double, Double> addition = (aDouble, aDouble2) -> aDouble + aDouble2;
    BiFunction<Double, Double, Double> subtraction = (aDouble, aDouble2) -> aDouble - aDouble2;
    //BiFunction<Double, Double, Double> division = (aDouble, aDouble2) -> aDouble / aDouble2;

    Function<BiFunction<Double, Double, Double>, BiFunction<Double, Double, Double>> derivative = function -> {
        if (function == powerFunction)
            return (aDouble, aDouble2) -> aDouble2 * (Math.pow(aDouble, aDouble2));
        else if (function == addition)
            return (aDouble, aDouble2) -> 1.0;
        else if (function == subtraction)
            return (aDouble, aDouble2) -> (-1.0);
        else if (function == multiplication)
            return (aDouble, aDouble2) -> aDouble;
        return null;
    };*/

    @Override
    public BiFunction<Double, Double, Double> apply(BiFunction<Double, Double, Double> doubleDoubleDoubleBiFunction) {
        return Functions.derivative.apply(doubleDoubleDoubleBiFunction);
    }
}