

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StoreManagerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StoreManagerTest
{
    private StoreManager storeMan1;

    /**
     * Default constructor for test class StoreManagerTest
     */
    public StoreManagerTest()
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
        storeMan1 = new StoreManager("Scott", 200, 25, 10000, 7000);
        storeMan1.generateAuditFile();
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
