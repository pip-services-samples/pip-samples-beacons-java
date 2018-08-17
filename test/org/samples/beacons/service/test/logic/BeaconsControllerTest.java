package org.samples.beacons.service.test.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.Test;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.data.*;
import org.pipservices.commons.errors.ApplicationException;
import org.pipservices.commons.refer.*;
import org.samples.beacons.interfaces.data.BeaconV1;
import org.samples.beacons.service.logic.BeaconsController;
import org.samples.beacons.service.persistence.*;
import org.samples.beacons.service.test.data.TestModel;

public class BeaconsControllerTest {

	private BeaconsController _controller;

    private BeaconsMemoryPersistence _persistence;
    
    public BeaconsControllerTest() throws ApplicationException {
    	IReferences references = new References();
        _controller = new BeaconsController();

        _persistence = new BeaconsMemoryPersistence(null);
        _persistence.configure(new ConfigParams());

        references.put(new Descriptor("samples-beacons", "persistence", "memory", "*", "1.0"), _persistence);
        references.put(new Descriptor("samples-beacons", "controller", "default", "*", "1.0"), _controller);

        _controller.setReferences(references);
    }
    
    @Test
    public void itShouldCreateBeacon() {

        BeaconV1 beacon = TestModel.createBeacon();

        BeaconV1 result = _controller.create(null, beacon);

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldUpdateBeacon() {
    	BeaconV1 beacon = _controller.create(null, TestModel.createBeacon());

        beacon.setLabel("Update Label");
        beacon.setType("Update Type");

        BeaconV1 result = _controller.update(null, beacon);

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldDeleteBeacon() {
    	BeaconV1 beacon = _controller.create(null, TestModel.createBeacon());

    	BeaconV1 deletedBeacon = _controller.deleteById(null, beacon.getId());
    	BeaconV1 result = _controller.getOneById(null, beacon.getId());

        TestModel.assertEqual(beacon, deletedBeacon);
        assertNull(result);
    }

    @Test
    public void itShouldGetBeaconById() {
    	BeaconV1 beacon = _controller.create(null, TestModel.createBeacon());

    	BeaconV1 result = _controller.getOneById(null, beacon.getId());

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldGetBeaconByUdi() {
    	BeaconV1 beacon = _controller.create(null, TestModel.createBeacon());

    	BeaconV1 result = _controller.getOneById(null, beacon.getUdi());

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldGetBeaconsByFilter() {
    	List<BeaconV1> data = new ArrayList<BeaconV1>();
    	for(int i = 0; i < 3; i++) {
    		data.add(i, TestModel.createBeacon());
    	}
    	DataPage<BeaconV1> beacons = new DataPage<BeaconV1>();
    	beacons.setData(data);
    	beacons.setTotal((long) data.size());
    	FilterParams filter = new FilterParams();
    	PagingParams paging = new PagingParams();

        
    	DataPage<BeaconV1> result = _controller.getPageByFilter(null, filter, paging);
    	assertNotNull(result);
    	assertEquals(beacons.getTotal(), result.getTotal());
    }
}
