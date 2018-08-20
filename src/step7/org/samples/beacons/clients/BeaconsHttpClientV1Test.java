package step7.org.samples.beacons.clients;

import org.junit.Test;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.errors.ApplicationException;
import org.pipservices.commons.refer.*;
import step4.org.samples.beacons.logic.BeaconsController;
import step3.org.samples.beacons.persistence.BeaconsMemoryPersistence;
import step5.org.samples.beacons.services.BeaconsHttpServiceV1;

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
                new Descriptor("trainings-beacons", "persistence", "memory", "default", "1.0"), _persistence,
                new Descriptor("trainings-beacons", "controller", "default", "default", "1.0"), _controller,
                new Descriptor("trainings-beacons", "client", "http", "default", "1.0"), _client,
                new Descriptor("trainings-beacons", "service", "http", "default", "1.0"), _service
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
