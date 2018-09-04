package beacons.clients;

import static org.junit.Assert.*;

import org.pipservices.commons.data.DataPage;

import beacons.clients.IBeaconsClientV1;
import beacons.data.version1.BeaconV1;
import beacons.data.version1.TestModel;

public class BeaconsClientV1Fixture {

	private IBeaconsClientV1 _client;

    public BeaconsClientV1Fixture(IBeaconsClientV1 client) {
        _client = client;
    }
    
    public void testClientCrudOperationsAsync() {
        BeaconV1 beacon1 = _client.createBeacon(null, TestModel.createBeacon());
        BeaconV1 beacon2 = _client.createBeacon(null, TestModel.createBeacon());

        DataPage<BeaconV1> page = _client.getBeaconsByFilter(null, null, null);
        assertEquals(2, page.getData().size());

        _client.deleteBeaconById(null, beacon1.getId());

        BeaconV1 result = _client.getBeaconById(null, beacon1.getId());
        assertNull(result);

        beacon2.setLabel("New Label");
        beacon2.setRadius(11.0);

        result = _client.updateBeacon(null, beacon2);
        TestModel.assertEqual(beacon2, result);

        _client.deleteBeaconById(null, beacon2.getId());
        page = _client.getBeaconsByFilter(null, null, null);
        assertTrue(!page.getData().isEmpty());
    }
}
