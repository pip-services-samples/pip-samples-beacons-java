//package beacons.services;
//
//import static org.junit.Assert.*;
//import java.io.IOException;
//import java.util.*;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientRequest;
//import org.junit.Test;
//import org.pipservices.commons.config.*;
//import org.pipservices.commons.convert.JsonConverter;
//import org.pipservices.commons.data.DataPage;
//import org.pipservices.commons.errors.ApplicationException;
//import org.pipservices.commons.refer.*;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.sun.jersey.api.json.JSONConfiguration;
//
//import beacons.data.version1.BeaconV1;
//import beacons.logic.BeaconsController;
//import beacons.persistence.BeaconsMemoryPersistence;
//import beacons.services.BeaconsHttpServiceV1;
//import beacons.data.version1.TestModel;
//
//public class BeaconsHttpServiceV1Test {
//
//	private static final ConfigParams HttpConfig = ConfigParams.fromTuples("connection.protocol", "http",
//			"connection.host", "localhost", "connection.port", "8080");
//
//	private BeaconsMemoryPersistence _persistence;
//	private BeaconsController _controller;
//	private BeaconsHttpServiceV1 _service;
//	
//    public BeaconsHttpServiceV1Test() throws ApplicationException {
//        _persistence = new BeaconsMemoryPersistence(null);
//        _controller = new BeaconsController();
//        _service = new BeaconsHttpServiceV1();
//
//        IReferences references = References.fromTuples(
//            new Descriptor("pip-beacons", "persistence", "memory", "default", "1.0"), _persistence,
//            new Descriptor("pip-beacons", "controller", "default", "default", "1.0"), _controller,
//            new Descriptor("pip-beacons", "service", "http", "default", "1.0"), _service
//        );
//
//        _controller.setReferences(references);
//
//        _service.configure(HttpConfig);
//        _service.setReferences(references);
//        _service.open(null);
//    }
//    
//    @Test
//    public void itShouldTestCrudOperations() throws JsonMappingException, JsonParseException, IOException {
//        BeaconV1 expectedBeacon1 = TestModel.createBeacon();
//        BeaconV1 beacon1 = invoke("create_beacon", new BeaconV1(expectedBeacon1));
//        TestModel.assertEqual(expectedBeacon1, beacon1);
//
//        BeaconV1 expectedBeacon2 = TestModel.createBeacon();
//        BeaconV1 beacon2 = invoke("create_beacon", new BeaconV1(expectedBeacon2));
//        TestModel.assertEqual(expectedBeacon2, beacon2);
//
//        DataPage<BeaconV1> expectedPage = new DataPage<BeaconV1>();
//        List<BeaconV1> data = new ArrayList<BeaconV1>();
//        data.add(TestModel.createBeacon());
//        data.add(TestModel.createBeacon());
//        expectedPage.setData(data);
//        expectedPage.setTotal((long) data.size());
//        DataPage<BeaconV1> page = invoke("get_beacons", expectedPage);
//        assertNotNull(page);
//        assertEquals(2, page.getData().size());
//
//        beacon1.setRadius(25.0);
//        beacon2.setRadius(50.0);
//
//        BeaconV1 beacon = invoke("update_beacon", beacon1);
//        TestModel.assertEqual(beacon1, beacon);
//
//        // TO DO 
//        beacon = invoke("delete_beacon", beacon1.getId() == beacon.getId() ? beacon1 : null);
//        assertNotNull(beacon);
//        assertEquals(beacon1.getId(), beacon.getId());
//
//        beacon = invoke("get_beacon", beacon1.getId() == beacon.getId() ? beacon1 : null);
//        assertNull(beacon);
//
//        beacon = invoke("delete_beacon", beacon2.getId() == beacon.getId() ? beacon1 : null);
//        assertNotNull(beacon);
//        assertEquals(beacon2.getId(), beacon.getId());
//
//    }
//
//    private <T> T invoke(String route, T entity) throws JsonMappingException, JsonParseException, IOException {
//    	ClientConfig clientConfig = new ClientConfig();
//        clientConfig.getProperties().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
//        Client _client = ClientBuilder.newClient(clientConfig); 
//        ClientRequest request = new ClientRequest(null);
//        request.setEntity(entity);
//        
//        //String requestValue = JsonConverter.toJson(request);
//        Response response = _client.target(request.getUri()).request(request.getMediaType())
//                .post( Entity.entity("http://localhost:27017/v1/beacons/" + route, MediaType.APPLICATION_JSON));
//        String responseValue = response.getEntity().toString();
//        return JsonConverter.fromJson(null, responseValue);
//    }
//}
