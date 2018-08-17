package org.samples.beacons.service.test.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.pipservices.commons.data.DataPage;
import org.pipservices.commons.data.FilterParams;
import org.samples.beacons.interfaces.data.BeaconV1;
import org.samples.beacons.service.persistence.IBeaconsPersistence;
import org.samples.beacons.service.test.data.TestModel;

public class BeaconsPersistenceFixture {

	private IBeaconsPersistence _persistence;

    public BeaconsPersistenceFixture(IBeaconsPersistence persistence) {
        _persistence = persistence;
    }
    
    public void testCreateBeacon() {

        BeaconV1 beacon = TestModel.createBeacon();

        BeaconV1 result = _persistence.create(null, beacon);

        TestModel.assertEqual(beacon, result);
    }
    
    public void testUpdateBeacon() {

    	BeaconV1 beacon = _persistence.create(null, TestModel.createBeacon());

        beacon.setLabel("Update Label");
        beacon.setType("Update Type");

        BeaconV1 result = _persistence.update(null, beacon);

        TestModel.assertEqual(beacon, result);
    }
    
    public void testDeleteBeacon() {
    	BeaconV1 beacon = _persistence.create(null, TestModel.createBeacon());

    	BeaconV1 deletedBeacon = _persistence.deleteById(null, beacon.getId());
    	BeaconV1 result = _persistence.getOneById(null, beacon.getId());

        TestModel.assertEqual(beacon, deletedBeacon);
        assertNull(result);
    }

    public void testGetBeaconById() {
    	BeaconV1 beacon = _persistence.create(null, TestModel.createBeacon());

    	BeaconV1 result = _persistence.getOneById(null, beacon.getId());

        TestModel.assertEqual(beacon, result);
    }

    public void testGetBeaconByUdi() {
    	BeaconV1 beacon = _persistence.create(null, TestModel.createBeacon());

    	BeaconV1 result = _persistence.getOneById(null, beacon.getUdi());

        TestModel.assertEqual(beacon, result);
    }
    
    public void testGetBeaconsByFilter()
    {
    	BeaconV1 beacon1 = _persistence.create(null, TestModel.createBeacon());
    	BeaconV1 beacon2 = _persistence.create(null, TestModel.createBeacon());

        FilterParams filter = FilterParams.fromTuples(
            "site_id", "{String.Join(",", beacon2.SiteId)}",
            "udi", beacon1.getUdi());

        DataPage<BeaconV1> result = _persistence.getPageByFilter(null, filter, null);

        assertNotNull(result);
        assertEquals(2, result.getData().size());        
        TestModel.assertEqual(beacon1, result.getData().get(0));
    }
}
