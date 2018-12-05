package beacons.clients;

import static org.junit.Assert.*;

import org.pipservices3.commons.data.DataPage;
import org.pipservices3.commons.data.FilterParams;
import org.pipservices3.commons.data.PagingParams;
import org.pipservices3.commons.errors.ApplicationException;

import beacons.clients.IBeaconsClientV1;
import beacons.data.version1.BeaconTypeV1;
import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;

public class BeaconsClientV1Fixture {

	private BeaconV1 BEACON1 = new BeaconV1("1", "1", BeaconTypeV1.AltBeacon, "00001", "TestBeacon1",
			new CenterObjectV1("Point", new double[] { 0, 0 }), 50);

	private BeaconV1 BEACON2 = new BeaconV1("2", "1", BeaconTypeV1.iBeacon, "00002", "TestBeacon2",
			new CenterObjectV1("Point", new double[] { 2, 2 }), 70);

	private IBeaconsClientV1 _client;

	public BeaconsClientV1Fixture(IBeaconsClientV1 client) {
		assertNotNull(client);
		_client = client;
	}

	public void testClientCrudOperations() throws ApplicationException {
		// Create the first beacon
		BeaconV1 beacon1 = _client.createBeacon(null, BEACON1);

		assertNotNull(beacon1);
		assertNotNull(beacon1.getId());
		assertEquals(BEACON1.getUdi(), beacon1.getUdi());
		assertEquals(BEACON1.getSiteId(), beacon1.getSiteId());
		assertEquals(BEACON1.getType(), beacon1.getType());
		assertEquals(BEACON1.getLabel(), beacon1.getLabel());
		assertNotNull(beacon1.getCenter());

		// Create the second beacon
		BeaconV1 beacon2 = _client.createBeacon(null, BEACON2);

		assertNotNull(beacon2);
		assertNotNull(beacon2.getId());
		assertEquals(BEACON2.getUdi(), beacon2.getUdi());
		assertEquals(BEACON2.getSiteId(), beacon2.getSiteId());
		assertEquals(BEACON2.getType(), beacon2.getType());
		assertEquals(BEACON2.getLabel(), beacon2.getLabel());
		assertNotNull(beacon2.getCenter());

		// Get all beacons
		DataPage<BeaconV1> page = _client.getBeaconsByFilter(null, new FilterParams(), new PagingParams());
		assertNotNull(page);
		assertEquals(2, page.getData().size());
		beacon1 = page.getData().get(0);

		// Update the beacon
		beacon1.setLabel("Updated Label 1");
		BeaconV1 beacon = _client.updateBeacon(null, beacon1);
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());
		assertEquals("Updated Label 1", beacon.getLabel());

		// Get beacon by udi
		beacon = _client.getBeaconByUdi(null, beacon1.getUdi());
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());

		// Delete the beacon
		_client.deleteBeaconById(null, beacon1.getId());

		// Try to get deleted beacon
		beacon = _client.getBeaconById(null, beacon1.getId());
		assertNull(beacon);
	}

	public void testCalculatePosition() throws ApplicationException {
		// Create the first beacon
		BeaconV1 beacon1 = _client.createBeacon(null, BEACON1);

		assertNotNull(beacon1);
		assertNotNull(beacon1.getId());
		assertEquals(BEACON1.getUdi(), beacon1.getUdi());
		assertEquals(BEACON1.getSiteId(), beacon1.getSiteId());
		assertEquals(BEACON1.getType(), beacon1.getType());
		assertEquals(BEACON1.getLabel(), beacon1.getLabel());
		assertNotNull(beacon1.getCenter());

		// Create the second beacon
		BeaconV1 beacon2 = _client.createBeacon(null, BEACON2);

		assertNotNull(beacon2);
		assertNotNull(beacon2.getId());
		assertEquals(BEACON2.getUdi(), beacon2.getUdi());
		assertEquals(BEACON2.getSiteId(), beacon2.getSiteId());
		assertEquals(BEACON2.getType(), beacon2.getType());
		assertEquals(BEACON2.getLabel(), beacon2.getLabel());
		assertNotNull(beacon2.getCenter());

		// Calculate position for one beacon
		CenterObjectV1 position = _client.calculatePosition(null, "1", new String[] { "00001" });
		assertNotNull(position);
		assertEquals("Point", position.getType());
		assertEquals(2, position.getCoordinates().size());
	}
}
