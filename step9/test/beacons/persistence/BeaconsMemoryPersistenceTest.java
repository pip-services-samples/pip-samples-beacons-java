package beacons.persistence;

import org.junit.Test;
import org.pipservices3.commons.config.*;
import org.pipservices3.commons.errors.*;

public class BeaconsMemoryPersistenceTest {
	
	public BeaconsMemoryPersistence persistence;
    public BeaconsMemoryPersistence getPersistence() { return persistence; }
	public void setPersistence(BeaconsMemoryPersistence persistence) { this.persistence = persistence; }
	
	public BeaconsPersistenceFixture fixture;
	public BeaconsPersistenceFixture getFixture() { return fixture; }
	public void setFixture(BeaconsPersistenceFixture fixture) { this.fixture = fixture; }
    
    public BeaconsMemoryPersistenceTest() throws ApplicationException {
        persistence = new BeaconsMemoryPersistence();
        persistence.configure(new ConfigParams());
        fixture = new BeaconsPersistenceFixture(persistence);
        persistence.open(null);
    }
    
    @Test
    public void testCrudOperations() throws ApplicationException {
        fixture.testCrudOperations();
    }

    @Test
    public void testGetWithFilter() throws ApplicationException {
        fixture.testGetWithFilter();
    }
}
