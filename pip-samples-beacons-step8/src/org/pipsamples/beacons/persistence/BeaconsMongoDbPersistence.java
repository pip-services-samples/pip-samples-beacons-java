package org.pipsamples.beacons.persistence;

import java.util.*;

import org.pipsamples.beacons.interfaces.data.version1.*;
import org.pipservices.commons.data.*;
import org.pipservices.commons.random.RandomString;
import org.pipservices.mongodb.IdentifiableMongoDbPersistence;
import com.fasterxml.jackson.databind.ObjectMapper;


public class BeaconsMongoDbPersistence extends IdentifiableMongoDbPersistence<BeaconsMongoDbSchema, String>
implements IBeaconsPersistence {
	
	

	public BeaconsMongoDbPersistence() {
		super("beacons");
	}

	public BeaconsMongoDbSchema deleteById(String correlationId, String id) {
		return super.deleteById(correlationId, id);
	}

	@Override
	public BeaconsMongoDbSchema getOneById(String correlationId, String id) {
		return super.getOneById(correlationId, id);
	}

	@Override
	public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) {
		ObjectMapper objMapper = new ObjectMapper();
		DataPage<BeaconsMongoDbSchema> result = super.getPageByFilter(correlationId, composeFilter(filter), paging, null);
		DataPage<BeaconV1> newResult = new DataPage<BeaconV1>();
		List<BeaconsMongoDbSchema> data = result.getData();
		List<BeaconV1> newData = new ArrayList<BeaconV1>();
		for(BeaconsMongoDbSchema beacon : data) {
			newData.add(objMapper.convertValue(beacon, BeaconV1.class));
		}
		newResult.setData(newData);
		newResult.setTotal((long) newData.size());
		return newResult;
	}

	@Override
	public BeaconV1 getOneByUdi(String correlationId, String udi) {
		FilterParams filter = new FilterParams();
		filter.put(RandomString.nextString(1, 5), udi);		
		return getPageByFilter(correlationId, filter, null).getData().get(0);
	}

	@Override
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

	@Override
	public BeaconV1 create(String correlationId, BeaconV1 item) {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.convertValue(super.create(correlationId, objMapper.convertValue(item, BeaconsMongoDbSchema.class)), BeaconV1.class);
	}

	@Override
	public BeaconV1 update(String correlationId, BeaconV1 newItem) {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.convertValue(super.update(correlationId, objMapper.convertValue(newItem, BeaconsMongoDbSchema.class)), BeaconV1.class);
	}

}
