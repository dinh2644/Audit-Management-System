

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class EmployeeAuditTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EmployeeAuditTest
{
    private EmployeeAudit employee1;

    /**
     * Default constructor for test class EmployeeAuditTest
     */
    public EmployeeAuditTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        employee1 = new EmployeeAudit();
        employee1.sortInstructions();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
