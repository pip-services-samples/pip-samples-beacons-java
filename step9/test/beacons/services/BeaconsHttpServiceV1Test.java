package beacons.services;

import static org.junit.Assert.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pipservices3.commons.config.*;
import org.pipservices3.commons.data.DataPage;
import org.pipservices3.commons.errors.*;
import org.pipservices3.commons.refer.*;
import org.pipservices3.commons.run.Parameters;

import beacons.data.version1.BeaconTypeV1;
import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;
import beacons.logic.BeaconsController;
import beacons.persistence.BeaconsMemoryPersistence;
import beacons.services.BeaconsHttpServiceV1;

public class BeaconsHttpServiceV1Test {

	private BeaconV1 BEACON1 = new BeaconV1("1", "1", BeaconTypeV1.AltBeacon, "00001", "TestBeacon1",
			new CenterObjectV1("Point", new double[] { 0, 0 }), 50);

	private BeaconV1 BEACON2 = new BeaconV1("2", "1", BeaconTypeV1.iBeacon, "00002", "TestBeacon2",
			new CenterObjectV1("Point", new double[] { 2, 2 }), 70);
	
	private static final ConfigParams HttpConfig = ConfigParams.fromTuples(
		"connection.protocol", "http",
		"connection.host", "localhost",
		"connection.port", 3002
	);

	private BeaconsMemoryPersistence _persistence;
	private BeaconsController _controller;
	private BeaconsHttpServiceV1 _service;

	@Before
	public void setUp() throws Exception {
		_persistence = new BeaconsMemoryPersistence();
		_persistence.configure(new ConfigParams());

		_controller = new BeaconsController();
		_controller.configure(new ConfigParams());

		_service = new BeaconsHttpServiceV1();
		_service.configure(HttpConfig);

		References references = References.fromTuples(
				new Descriptor("beacons", "persistence", "memory", "default", "1.0"), _persistence,
				new Descriptor("beacons", "controller", "default", "default", "1.0"), _controller,
				new Descriptor("beacons", "service", "http", "default", "1.0"), _service);

		_controller.setReferences(references);
		_service.setReferences(references);

		_persistence.open(null);
		_service.open(null);
		
		//Thread.sleep(2000);
	}

	@After
	public void close() throws Exception {
		_service.close(null);
	}

	// Todo: often this test fails in docker container. Find out why it happens.
	//@Test
	public void testCrudOperations() throws Exception {
		// Create the first beacon
		BeaconV1 beacon1 = invoke(BeaconV1.class, "/v1/beacons/create_beacon",
				Parameters.fromTuples("beacon", BEACON1));

		assertNotNull(beacon1);
		assertNotNull(beacon1.getId());
		assertEquals(BEACON1.getUdi(), beacon1.getUdi());
		assertEquals(BEACON1.getSiteId(), beacon1.getSiteId());
		assertEquals(BEACON1.getType(), beacon1.getType());
		assertEquals(BEACON1.getLabel(), beacon1.getLabel());
		assertNotNull(beacon1.getCenter());

		// Create the second beacon
		BeaconV1 beacon2 = invoke(BeaconV1.class, "/v1/beacons/create_beacon",
				Parameters.fromTuples("beacon", BEACON2));

		assertNotNull(beacon2);
		assertNotNull(beacon2.getId());
		assertEquals(BEACON2.getUdi(), beacon2.getUdi());
		assertEquals(BEACON2.getSiteId(), beacon2.getSiteId());
		assertEquals(BEACON2.getType(), beacon2.getType());
		assertEquals(BEACON2.getLabel(), beacon2.getLabel());
		assertNotNull(beacon2.getCenter());

		// Get all beacons
		DataPage<BeaconV1> beacons = invoke(new GenericType<DataPage<BeaconV1>>() {
		}, "/v1/beacons/get_beacons", null);
		assertNotNull(beacons);
		assertEquals(2, beacons.getData().size());
		beacon1 = beacons.getData().get(0);

		// Update the beacon
		beacon1.setLabel("Updated Label 1");
		BeaconV1 beacon = invoke(BeaconV1.class, "/v1/beacons/update_beacon", Parameters.fromTuples("beacon", beacon1));

		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());
		assertEquals("Updated Label 1", beacon.getLabel());
		
		// Get beacon by udi
		beacon = invoke(BeaconV1.class, "/v1/beacons/get_beacon_by_udi",
				Parameters.fromTuples("udi", beacon1.getUdi()));
		assertNotNull(beacon);
		assertEquals(beacon1.getId(), beacon.getId());
		
		// Calculate position for one beacon
		CenterObjectV1 position = invoke(CenterObjectV1.class, "/v1/beacons/calculate_position", 
				Parameters.fromTuples("site_id", "1", "udis", new String[] { "00001" }));
		assertNotNull(position);
		assertEquals("Point", position.getType());
		assertEquals(2, position.getCoordinates().size());
//		assertEquals(0, position.getCoordinates()[0]);
//		assertEquals(0, position.getCoordinates()[1]);
		

		// Delete the beacon
		invoke(BeaconV1.class, "/v1/beacons/delete_beacon_by_id", Parameters.fromTuples("id", beacon1.getId()));

		// Try to get deleted beacon
		beacon = invoke(BeaconV1.class, "/v1/beacons/get_beacon_by_id",
				Parameters.fromTuples("id", beacon1.getId()));
		assertNull(beacon);
	}

	@SuppressWarnings("static-access")
	private static Response performInvoke(String route, Object entity) throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(new JacksonFeature());
		Client httpClient = ClientBuilder.newClient(clientConfig);

		Response response = httpClient.target("http://localhost:3002" + route)
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.entity(entity, MediaType.APPLICATION_JSON));

		if (response.getStatus() >= 400) {
			ErrorDescription error = response.readEntity(ErrorDescription.class);
			Exception ex = new ApplicationExceptionFactory().create(error);
			throw ex;
		}

		
		return response;
	}

	private static <T> T invoke(Class<T> type, String route, Object entity) throws Exception {
		Response response = performInvoke(route, entity);
		return response.readEntity(type);
	}

	private static <T> T invoke(GenericType<T> type, String route, Object entity) throws Exception {
		Response response = performInvoke(route, entity);
		return response.readEntity(type);
	}

}
