package beacons.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.pipservices3.commons.data.DataPage;
import org.pipservices3.commons.data.FilterParams;
import org.pipservices3.commons.data.PagingParams;
import org.pipservices3.commons.errors.ApplicationException;

import beacons.data.version1.BeaconTypeV1;
import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;
import beacons.persistence.IBeaconsPersistence;

public class BeaconsPersistenceFixture {

	private BeaconV1 BEACON1 = new BeaconV1("1", "1", BeaconTypeV1.AltBeacon, "00001", "TestBeacon1",
			new CenterObjectV1("Point", new double[] { 0, 0 }), 50);

	private BeaconV1 BEACON2 = new BeaconV1("2", "1", BeaconTypeV1.iBeacon, "00002", "TestBeacon2",
			new CenterObjectV1("Point", new double[] { 2, 2 }), 70);

	private BeaconV1 BEACON3 = new BeaconV1("3", "2", BeaconTypeV1.AltBeacon, "00003", "TestBeacon3",
			new CenterObjectV1("Point", new double[] { 10, 10 }), 50);
	
	private IBeaconsPersistence _persistence;

    public BeaconsPersistenceFixture(IBeaconsPersistence persistence) {
        _persistence = persistence;
    }

	public void testCreateBeacons() throws ApplicationException {
		// Create the first beacon
		BeaconV1 beacon1 = _persistence.create(null, BEACON1);

		assertNotNull(beacon1);
		assertNotNull(beacon1.getId());
		assertEquals(BEACON1.getUdi(), beacon1.getUdi());
		assertEquals(BEACON1.getSiteId(), beacon1.getSiteId());
		assertEquals(BEACON1.getType(), beacon1.getType());
		assertEquals(BEACON1.getLabel(), beacon1.getLabel());
		assertNotNull(beacon1.getCenter());

		// Create the second beacon
		BeaconV1 beacon2 = _persistence.create(null, BEACON2);

		assertNotNull(beacon2);
		assertNotNull(beacon2.getId());
		assertEquals(BEACON2.getUdi(), beacon2.getUdi());
		assertEquals(BEACON2.getSiteId(), beacon2.getSiteId());
		assertEquals(BEACON2.getType(), beacon2.getType());
		assertEquals(BEACON2.getLabel(), beacon2.getLabel());
		assertNotNull(beacon2.getCenter());

		// Create the third beacon
		BeaconV1 beacon3 = _persistence.create(null, BEACON3);

		assertNotNull(beacon3);
		assertNotNull(beacon3.getId());
		assertEquals(BEACON3.getUdi(), beacon3.getUdi());
		assertEquals(BEACON3.getSiteId(), beacon3.getSiteId());
		assertEquals(BEACON3.getType(), beacon3.getType());
		assertEquals(BEACON3.getLabel(), beacon3.getLabel());
		assertNotNull(beacon3.getCenter());
	}
    
	public void testCrudOperations() throws ApplicationException {
		// Create 3 beacons
		testCreateBeacons();
		
		// Get all beacons
		DataPage<BeaconV1> page = _persistence.getPageByFilter(null, new FilterParams(), new PagingParams());
		assertNotNull(page);
		assertEquals(3, page.getData().size());
		BeaconV1 beacon1 = page.getData().get(0);

		// Update the beacon
		beacon1.setLabel("Updated Label 1");
		BeaconV1 beacon = _persistence.update(null, beacon1);
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());
		assertEquals("Updated Label 1", beacon.getLabel());

		// Get beacon by udi
		beacon = _persistence.getOneByUdi(null, beacon1.getUdi());
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());

		// Delete the beacon
		_persistence.deleteById(null, beacon1.getId());

		// Try to get deleted beacon
		beacon = _persistence.getOneById(null, beacon1.getId());
		assertNull(beacon);
	}
    
	public void testGetWithFilter() throws ApplicationException {
		// Create 3 beacons
		testCreateBeacons();

		// Filter by id
		DataPage<BeaconV1> page = _persistence.getPageByFilter(null, 
				FilterParams.fromTuples("id", "1"), new PagingParams());
		assertNotNull(page);
		assertEquals(1, page.getData().size());
		
		// Filter by udi
		page = _persistence.getPageByFilter(null, FilterParams.fromTuples("udi", "00002"), new PagingParams());
		assertNotNull(page);
		assertEquals(1, page.getData().size());

		// Filter by udis
		page = _persistence.getPageByFilter(null, FilterParams.fromTuples("udis", "00001,00003"), new PagingParams());
		assertNotNull(page);
		assertEquals(2, page.getData().size());
		
		// Filter by site_id
		page = _persistence.getPageByFilter(null, FilterParams.fromTuples("site_id", "1"), new PagingParams());
		assertNotNull(page);
		assertEquals(2, page.getData().size());
	}
}
