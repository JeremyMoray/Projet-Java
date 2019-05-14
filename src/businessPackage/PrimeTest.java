package businessPackage;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class PrimeTest {

    private Manager manager;

    @Before
    public void setUp() throws Exception {
        manager = new Manager();
    }

    @Test
    public void addPrime() {
        Assert.assertEquals (1000., manager.addPrime(800.,200.), 0.001);
    }
}
