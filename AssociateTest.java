

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AssociateTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AssociateTest
{
    private Associate associat1;

    /**
     * Default constructor for test class AssociateTest
     */
    public AssociateTest()
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
        associat1 = new Associate("Tu", 120, 16, 65);
        associat1.generateAuditFile();
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
