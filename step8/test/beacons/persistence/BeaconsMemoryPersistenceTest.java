package beacons.persistence;

import org.junit.Test;

import beacons.persistence.BeaconsMemoryPersistence;

public class BeaconsMemoryPersistenceTest {
	
	public BeaconsMemoryPersistence persistence;
    public BeaconsMemoryPersistence getPersistence() { return persistence; }
	public void setPersistence(BeaconsMemoryPersistence persistence) { this.persistence = persistence; }
	
	public BeaconsPersistenceFixture fixture;
	public BeaconsPersistenceFixture getFixture() { return fixture; }
	public void setFixture(BeaconsPersistenceFixture fixture) { this.fixture = fixture; }
    
    public BeaconsMemoryPersistenceTest() {
        persistence = new BeaconsMemoryPersistence(null);
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
