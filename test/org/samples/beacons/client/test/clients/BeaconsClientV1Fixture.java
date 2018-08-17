package org.samples.beacons.client.test.clients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.pipservices.commons.data.DataPage;
import org.samples.beacons.client.clients.IBeaconsClientV1;
import org.samples.beacons.interfaces.data.BeaconV1;
import org.samples.beacons.service.test.data.TestModel;

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
