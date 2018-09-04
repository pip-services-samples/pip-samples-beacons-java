package beacons.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.*;

import org.junit.Test;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.data.*;
import org.pipservices.commons.errors.ApplicationException;
import org.pipservices.commons.refer.*;

import beacons.data.version1.BeaconV1;
import beacons.logic.BeaconsController;
import beacons.persistence.BeaconsMemoryPersistence;
import beacons.data.version1.TestModel;


public class BeaconsControllerTest {

	private BeaconsController _controller;

    private BeaconsMemoryPersistence _persistence;
    
    public BeaconsControllerTest() throws ApplicationException {
    	IReferences references = new References();
        _controller = new BeaconsController();

        _persistence = new BeaconsMemoryPersistence(null);
        _persistence.configure(new ConfigParams());

        references.put(new Descriptor("pip-beacons", "persistence", "memory", "*", "1.0"), _persistence);
        references.put(new Descriptor("pip-beacons", "controller", "default", "*", "1.0"), _controller);

        _controller.setReferences(references);
    }
    
    @Test
    public void itShouldCreateBeacon() {

        BeaconV1 beacon = TestModel.createBeacon();

        BeaconV1 result = _controller.createBeacon(null, beacon);

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldUpdateBeacon() {
    	BeaconV1 beacon = _controller.createBeacon(null, TestModel.createBeacon());

        beacon.setLabel("Update Label");
        beacon.setType("Update Type");

        BeaconV1 result = _controller.updateBeacon(null, beacon);

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldDeleteBeacon() {
    	BeaconV1 beacon = _controller.createBeacon(null, TestModel.createBeacon());

    	BeaconV1 deletedBeacon = _controller.deleteBeaconById(null, beacon.getId());
    	BeaconV1 result = _controller.getBeaconsById(null, beacon.getId());

        TestModel.assertEqual(beacon, deletedBeacon);
        assertNull(result);
    }

    @Test
    public void itShouldGetBeaconById() {
    	BeaconV1 beacon = _controller.createBeacon(null, TestModel.createBeacon());

    	BeaconV1 result = _controller.getBeaconsById(null, beacon.getId());

        TestModel.assertEqual(beacon, result);

    }

    @Test
    public void itShouldGetBeaconByUdi() {
    	BeaconV1 beacon = _controller.createBeacon(null, TestModel.createBeacon());

    	BeaconV1 result = _controller.getBeaconsById(null, beacon.getUdi());

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

        
    	DataPage<BeaconV1> result = _controller.getBeaconsByFilter(null, filter, paging);
    	assertNotNull(result);
    	assertEquals(beacons.getTotal(), result.getTotal());
    }
}
