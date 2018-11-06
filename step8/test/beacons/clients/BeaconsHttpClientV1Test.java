package beacons.clients;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.refer.*;
import beacons.clients.BeaconsHttpClientV1;
import beacons.logic.BeaconsController;
import beacons.persistence.BeaconsMemoryPersistence;
import beacons.services.BeaconsHttpServiceV1;

public class BeaconsHttpClientV1Test {

	private static final ConfigParams HttpConfig = ConfigParams.fromTuples(
		"connection.protocol", "http",
		"connection.host", "localhost",
		"connection.port", 8080
	);

	private BeaconsMemoryPersistence _persistence;
	private BeaconsController _controller;
	private BeaconsHttpClientV1 _client;
	private BeaconsHttpServiceV1 _service;
	private BeaconsClientV1Fixture _fixture;

	@Before
	public void setUp() throws Exception {
		_persistence = new BeaconsMemoryPersistence();
		_controller = new BeaconsController();
		_client = new BeaconsHttpClientV1();
		_service = new BeaconsHttpServiceV1();

		IReferences references = References.fromTuples(
				new Descriptor("beacons", "persistence", "memory", "default", "1.0"), _persistence,
				new Descriptor("beacons", "controller", "default", "default", "1.0"), _controller,
				new Descriptor("beacons", "client", "http", "default", "1.0"), _client,
				new Descriptor("beacons", "service", "http", "default", "1.0"), _service);

		_controller.setReferences(references);

		_service.configure(HttpConfig);
		_service.setReferences(references);

		_client.configure(HttpConfig);
		_client.setReferences(references);

		_fixture = new BeaconsClientV1Fixture(_client);
		_persistence.open(null);
		
		_service.open(null);
		_client.open(null);
		
		
	}

	@After
	public void tearDown() throws Exception {
		_client.close(null);
		_service.close(null);
		_persistence.close(null);
	}

	@Test
	public void testHttpClientCrudOperations() throws ApplicationException {
		_fixture.testClientCrudOperations();
	}

	@Test
	public void testCalculatePosition() throws ApplicationException {
		_fixture.testCalculatePosition();
	}
}
