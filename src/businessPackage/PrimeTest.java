package businessPackage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrimeTest {

    private Manager manager;

    @Before
    public void setUp() throws Exception {
        manager = new Manager();
    }

    @Test
    public void addPrime() throws Exception{
        Assert.assertEquals (1000., manager.addPrime(800.,200.), 0.001);
    }
}
