package step7.org.samples.beacons.clients;

import static org.junit.Assert.*;

import org.pipservices.commons.data.DataPage;
import step2.org.samples.beacons.interfaces.*;

public class BeaconsClientV1Fixture {

	private IBeaconsClientV1 _client;

    public BeaconsClientV1Fixture(IBeaconsClientV1 client) {
        _client = client;
    }
    
    public void testClientCrudOperationsAsync() {
        BeaconV1 beacon1 = _client.create(null, TestModel.createBeacon());
        BeaconV1 beacon2 = _client.create(null, TestModel.createBeacon());

        DataPage<BeaconV1> page = _client.getPageByFilter(null, null, null);
        assertEquals(2, page.getData().size());

        _client.deleteById(null, beacon1.getId());

        BeaconV1 result = _client.getOneById(null, beacon1.getId());
        assertNull(result);

        beacon2.setLabel("New Label");
        beacon2.setRadius(11.0);

        result = _client.update(null, beacon2);
        TestModel.assertEqual(beacon2, result);

        _client.deleteById(null, beacon2.getId());
        page = _client.getPageByFilter(null, null, null);
        assertTrue(!page.getData().isEmpty());
    }
}
