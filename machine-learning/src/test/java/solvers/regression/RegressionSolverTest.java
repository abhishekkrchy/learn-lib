package solvers.regression;

import linear.algebra.matrices.dense.DenseMatrix;
import linear.algebra.statistics.errors.Errors;
import linear.algebra.util.constants.enums.ErrorType;
import linear.algebra.vectors.dense.DenseVector;
import models.Model;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solvers.Solver;
import util.Data;

import java.lang.reflect.Field;
import java.util.Collections;

import static linear.algebra.util.constants.enums.ErrorType.MSE;
import static util.constants.enums.OptimizerType.GRADIENT_DESCENT;
import static util.constants.enums.Regularizer.L2;

public class RegressionSolverTest {

    private RegressionSolver regressionSolver;

    private Data data;

    @Before
    public void setUp() throws Exception {
        regressionSolver = new LinearRegressionSolver();

        data = new Data(new DenseMatrix(new double[][]{
                {1, 2, 3, 111},
                {4, 5, 6, 332},
                {7, 8, 9, 246},
                {10, 11, 12, 432}
        }), 25);

        Field dataTestRows = data.getClass().getDeclaredField("testRows");
        dataTestRows.setAccessible(true);
        dataTestRows.set(data, Collections.singletonList(3));

        Field field = regressionSolver.getClass().getSuperclass().getDeclaredField("data");
        field.setAccessible(true);
        field.set(regressionSolver, data);
    }

    @Test
    public void test() {
        Model model = regressionSolver.solve();
        DenseVector predictions = model.predict(regressionSolver.getData().testingX());
        Assert.assertEquals(661.3740935296001, predictions.head(), 0.0001);
        Assert.assertEquals(52612.47478252576, Errors.type(ErrorType.MSE).apply(regressionSolver.getData().testingY(), predictions), 0.0001);
    }

    @After
    public void tearDown() throws Exception {
    }

    class LinearRegressionSolver extends RegressionSolver {

        public LinearRegressionSolver() {
            super(null, GRADIENT_DESCENT, L2, 5, 0.01, 0.03, 0.0001, 25.0, MSE, data);
        }

        @Override
        protected DenseVector entryPoint() {
            return new DenseVector(new double[]{1, 2, 3, 4});
        }

        /**
         * Load data set.
         */
        public Solver loadDataSet() {
            return this;
        }
    }
}