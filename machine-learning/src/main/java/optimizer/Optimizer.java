package optimizer;

import models.Model;

/**
 * The Optimizer interface.
 */
public interface Optimizer {
    /**
     * Optimize method
     * Any class implementing {@link Optimizer}
     * should override it.
     */
    Model optimize();
}
