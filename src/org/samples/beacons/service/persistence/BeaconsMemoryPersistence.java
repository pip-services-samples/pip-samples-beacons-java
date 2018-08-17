package org.samples.beacons.service.persistence;

import java.util.*;
import java.util.function.Predicate;

import org.pipservices.commons.data.DataPage;
import org.pipservices.commons.data.FilterParams;
import org.pipservices.commons.data.PagingParams;
import org.pipservices.data.memory.IdentifiableMemoryPersistence;
import org.samples.beacons.interfaces.data.BeaconV1;
import org.samples.beacons.interfaces.data.CenterObject;

public class BeaconsMemoryPersistence extends IdentifiableMemoryPersistence<BeaconV1, String> 
implements IBeaconsPersistence{

	private int maxPageSize = 100;
    private Object _lock = new Object();
    private Map<String, BeaconV1> _beacons = new HashMap<String, BeaconV1>();
	
	public BeaconsMemoryPersistence(Class<BeaconV1> type) {
		super(type);
		this.maxPageSize = 1000;
	}

	public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) {
		return super.getPageByFilter(correlationId, (Predicate<BeaconV1>) filter, paging, null);
	}

	// TO DO
	public BeaconV1 getOneByUdi(String correlationId, String udi) {
		BeaconV1 result = null;
		synchronized(_lock) {
			for(String key : _beacons.keySet()) {
				if(_beacons.get(key).getUdi() == udi) {
					result = _beacons.get(key);
				}
			}
		}
		return result;
	}

	public CenterObject calculatePosition(String correlationId, String siteId, String[] udis) {
		List <BeaconV1> beacons;
        CenterObject position = new CenterObject("", new int[] {});

        if (udis == null || udis.length == 0)
        {
            return null;
        }

        DataPage<BeaconV1> result = getPageByFilter(correlationId, FilterParams.fromTuples("site_id", siteId, "udis", udis), null);
        beacons = result.getData();
        int lat = 0;
        int lng = 0;
        int count = 0;
        int[] coordinates = new int[] {};
        for(BeaconV1 beacon : beacons) {
        	if(beacon.getCenter() != null && beacon.getCenter().getType() == "Point" &&
        			beacon.getCenter().getCoordinates() instanceof int[]) {
        		coordinates = beacon.getCenter().getCoordinates();
        		lng += coordinates[0];
                lat += coordinates[1];
                count += 1;
        	}
        	coordinates = new int[] {};
        }
        
        if (count > 0) {
        	position.setType("Point");
        	position.setCoordinates(new int[] {lng / count, lat / count});
        }     
		return position;
	}
	
	public BeaconV1 getOneById(String correlationId, String id) {
		return super.getOneById(correlationId, id);
	}
    public BeaconV1 create(String correlationId, BeaconV1 item) {
    	return super.create(correlationId, item);
    }
    public BeaconV1 update(String correlationId, BeaconV1 newItem) {
    	return super.update(correlationId, newItem);
    }
    public BeaconV1 deleteById(String correlationId, String id) {
    	return super.deleteById(correlationId, id);
    }

}
