package beacons.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.refer.*;

import beacons.data.version1.BeaconTypeV1;
import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;
import beacons.logic.BeaconsController;
import beacons.persistence.BeaconsMemoryPersistence;

public class BeaconsControllerTest {

	private BeaconV1 BEACON1 = new BeaconV1("1", "1", BeaconTypeV1.AltBeacon, "00001", "TestBeacon1",
			new CenterObjectV1("Point", new double[] { 0, 0 }), 50);

	private BeaconV1 BEACON2 = new BeaconV1("2", "1", BeaconTypeV1.iBeacon, "00002", "TestBeacon2",
			new CenterObjectV1("Point", new double[] { 2, 2 }), 70);

	private BeaconsController _controller;

	private BeaconsMemoryPersistence _persistence;

	public BeaconsControllerTest() throws ApplicationException {
		IReferences references = new References();
		_controller = new BeaconsController();

		_persistence = new BeaconsMemoryPersistence();
		_persistence.configure(new ConfigParams());

		references.put(new Descriptor("beacons", "persistence", "memory", "*", "1.0"), _persistence);
		references.put(new Descriptor("beacons", "controller", "default", "*", "1.0"), _controller);

		_controller.setReferences(references);
		_persistence.open(null);
	}

	@Test
	public void testClientCrudOperations() throws ApplicationException {
		// Create the first beacon
		BeaconV1 beacon1 = _controller.createBeacon(null, BEACON1);

		assertNotNull(beacon1);
		assertNotNull(beacon1.getId());
		assertEquals(BEACON1.getUdi(), beacon1.getUdi());
		assertEquals(BEACON1.getSiteId(), beacon1.getSiteId());
		assertEquals(BEACON1.getType(), beacon1.getType());
		assertEquals(BEACON1.getLabel(), beacon1.getLabel());
		assertNotNull(beacon1.getCenter());

		// Create the second beacon
		BeaconV1 beacon2 = _controller.createBeacon(null, BEACON2);

		assertNotNull(beacon2);
		assertNotNull(beacon2.getId());
		assertEquals(BEACON2.getUdi(), beacon2.getUdi());
		assertEquals(BEACON2.getSiteId(), beacon2.getSiteId());
		assertEquals(BEACON2.getType(), beacon2.getType());
		assertEquals(BEACON2.getLabel(), beacon2.getLabel());
		assertNotNull(beacon2.getCenter());

		// Get all beacons
		DataPage<BeaconV1> page = _controller.getBeaconsByFilter(null, new FilterParams(), new PagingParams());
		assertNotNull(page);
		assertEquals(2, page.getData().size());
		beacon1 = page.getData().get(0);

		// Update the beacon
		beacon1.setLabel("Updated Label 1");
		BeaconV1 beacon = _controller.updateBeacon(null, beacon1);
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());
		assertEquals("Updated Label 1", beacon.getLabel());

		// Get beacon by udi
		beacon = _controller.getBeaconsByUdi(null, beacon1.getUdi());
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());

		// Delete the beacon
		_controller.deleteBeaconById(null, beacon1.getId());

		// Try to get deleted beacon
		beacon = _controller.getBeaconsById(null, beacon1.getId());
		assertNull(beacon);
	}

	@Test
	public void testCalculatePosition() throws ApplicationException {
		// Create the first beacon
		BeaconV1 beacon1 = _controller.createBeacon(null, BEACON1);

		assertNotNull(beacon1);
		assertNotNull(beacon1.getId());
		assertEquals(BEACON1.getUdi(), beacon1.getUdi());
		assertEquals(BEACON1.getSiteId(), beacon1.getSiteId());
		assertEquals(BEACON1.getType(), beacon1.getType());
		assertEquals(BEACON1.getLabel(), beacon1.getLabel());
		assertNotNull(beacon1.getCenter());

		// Create the second beacon
		BeaconV1 beacon2 = _controller.createBeacon(null, BEACON2);

		assertNotNull(beacon2);
		assertNotNull(beacon2.getId());
		assertEquals(BEACON2.getUdi(), beacon2.getUdi());
		assertEquals(BEACON2.getSiteId(), beacon2.getSiteId());
		assertEquals(BEACON2.getType(), beacon2.getType());
		assertEquals(BEACON2.getLabel(), beacon2.getLabel());
		assertNotNull(beacon2.getCenter());

		// Calculate position for one beacon
		CenterObjectV1 position = _controller.calculatePosition(null, "1", new String[] { "00001" });
		assertNotNull(position);
		assertEquals("Point", position.getType());
		assertEquals(2, position.getCoordinates().size());
	}

}
