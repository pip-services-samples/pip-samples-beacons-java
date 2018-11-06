package beacons.clients;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.refer.Descriptor;
import org.pipservices3.commons.refer.IReferences;
import org.pipservices3.commons.refer.References;

import beacons.logic.BeaconsController;
import beacons.persistence.BeaconsMemoryPersistence;

public class BeaconsDirectClientV1Test {

	private BeaconsMemoryPersistence _persistence;
	private BeaconsController _controller;
	private BeaconsDirectClientV1 _client;
	private BeaconsClientV1Fixture _fixture;
	
	@Before
	public void setUp() throws Exception {
		_persistence = new BeaconsMemoryPersistence();
		_persistence.configure(new ConfigParams());
		
		_controller = new BeaconsController();
		_controller.configure(new ConfigParams());
		_client = new BeaconsDirectClientV1();
		
		IReferences references = References.fromTuples(
				new Descriptor("beacons", "persistence", "memory", "default", "1.0"), _persistence,
				new Descriptor("beacons", "controller", "default", "default", "1.0"), _controller,
				new Descriptor("beacons", "client", "http", "default", "1.0"), _client);
		
		_controller.setReferences(references);
		_client.setReferences(references);
		
		_fixture = new BeaconsClientV1Fixture(_client);
		
		_persistence.open(null);
	}
	
	
	@After
	public void tearDown() throws Exception {
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
