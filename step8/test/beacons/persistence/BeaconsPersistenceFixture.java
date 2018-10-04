package beacons.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.pipservices.commons.data.DataPage;
import org.pipservices.commons.data.FilterParams;
import org.pipservices.commons.data.PagingParams;
import org.pipservices.commons.errors.ApplicationException;

import beacons.data.version1.BeaconTypeV1;
import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;
import beacons.persistence.IBeaconsPersistence;
import beacons.data.version1.TestModel;

public class BeaconsPersistenceFixture {

	private BeaconV1 BEACON1 = new BeaconV1("1", "1", BeaconTypeV1.AltBeacon, "00001", "TestBeacon1",
			new CenterObjectV1("Point", new double[] { 0, 0 }), 50);

	private BeaconV1 BEACON2 = new BeaconV1("2", "1", BeaconTypeV1.iBeacon, "00002", "TestBeacon2",
			new CenterObjectV1("Point", new double[] { 2, 2 }), 70);
	
	private IBeaconsPersistence _persistence;

    public BeaconsPersistenceFixture(IBeaconsPersistence persistence) {
        _persistence = persistence;
    }
    
	public void testCrudOperations() throws ApplicationException {
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

		// Get all beacons
		DataPage<BeaconV1> page = _persistence.getPageByFilter(null, new FilterParams(), new PagingParams());
		assertNotNull(page);
		assertEquals(2, page.getData().size());
		beacon1 = page.getData().get(0);

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
		// Filter by id
		DataPage<BeaconV1> page = _persistence.getPageByFilter(null, 
				FilterParams.fromTuples("id", "1"), new PagingParams());
		assertNotNull(page);
		assertEquals(1, page.getData().size());
		
		// Filter by udi
		page = _persistence.getPageByFilter(null, FilterParams.fromTuples("udi", "00002"), new PagingParams());
		assertNotNull(page);
		assertEquals(1, page.getData().size());
		
		// Filter by site_id
		page = _persistence.getPageByFilter(null, FilterParams.fromTuples("site_id", "1"), new PagingParams());
		assertNotNull(page);
		assertEquals(2, page.getData().size());
	}
}
