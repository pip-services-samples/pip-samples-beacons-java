package step8.org.test.clients;

import org.junit.Test;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.errors.ApplicationException;
import org.pipservices.commons.refer.*;

import step8.org.src.clients.BeaconsHttpClientV1;
import step8.org.src.logic.BeaconsController;
import step8.org.src.persistence.BeaconsMemoryPersistence;
import step8.org.src.services.BeaconsHttpServiceV1;

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

        public BeaconsHttpClientV1Test() throws ApplicationException
        {
            _persistence = new BeaconsMemoryPersistence(null);
            _controller = new BeaconsController();
            _client = new BeaconsHttpClientV1();
            _service = new BeaconsHttpServiceV1();

            IReferences references = References.fromTuples(
                new Descriptor("pip-samples-beacons", "persistence", "memory", "default", "1.0"), _persistence,
                new Descriptor("pip-samples-beacons", "controller", "default", "default", "1.0"), _controller,
                new Descriptor("pip-samples-beacons", "client", "http", "default", "1.0"), _client,
                new Descriptor("pip-samples-beacons", "service", "http", "default", "1.0"), _service
            );

            _controller.setReferences(references);

            _service.configure(HttpConfig);
            _service.setReferences(references);

            _client.configure(HttpConfig);
            _client.setReferences(references);

            _fixture = new BeaconsClientV1Fixture(_client);

            _service.open(null);
            _client.open(null);
        }

        @Test
        public void testHttpClientCrudOperationsAsync() {
            _fixture.testClientCrudOperationsAsync();
        }
}
