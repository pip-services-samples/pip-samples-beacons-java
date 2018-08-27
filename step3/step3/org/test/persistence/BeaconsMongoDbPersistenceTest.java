package step3.org.test.persistence;

import org.junit.Test;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.errors.ApplicationException;

import step3.org.src.persistence.BeaconsMongoDbPersistence;

public class BeaconsMongoDbPersistenceTest {
	public BeaconsMongoDbPersistence persistence;
    public BeaconsMongoDbPersistence getPersistence() { return persistence; }
	public void setPersistence(BeaconsMongoDbPersistence persistence) { this.persistence = persistence; }
	
	public BeaconsPersistenceFixture fixture;
	public BeaconsPersistenceFixture getFixture() { return fixture; }
	public void setFixture(BeaconsPersistenceFixture fixture) { this.fixture = fixture; }
	
	public BeaconsMongoDbPersistenceTest() throws ApplicationException {
        ConfigParams config = ConfigParams.fromTuples(
            "collection", "beacons",
            "connection.uri", "mongodb://localhost:27017/test"
            );

        persistence = new BeaconsMongoDbPersistence();
        persistence.configure(config);
        persistence.open(null);
        persistence.clear(null);
        fixture = new BeaconsPersistenceFixture(persistence);
    }
	
	@Test
    public void itShouldCreateBeacon() {
        fixture.testCreateBeacon();
    }

    @Test
    public void itShouldUpdateBeacon() {
        fixture.testUpdateBeacon();
    }

    @Test
    public void itShouldDeleteBeacon() {
        fixture.testDeleteBeacon();
    }

    @Test
    public void itShouldGetBeaconById() {
        fixture.testGetBeaconById();
    }

    @Test
    public void itShouldGetBeaconByUdi() {
        fixture.testGetBeaconByUdi();
    }

    @Test
    public void itShouldGetBeaconsByFilter() {
        fixture.testGetBeaconsByFilter();
    }

}
