package org.pipsamples.beacons.clients;

import java.io.IOException;
import java.util.*;

import org.pipsamples.beacons.interfaces.data.version1.*;
import org.pipservices.clients.CommandableHttpClient;
import org.pipservices.commons.data.*;
import org.pipservices.commons.errors.ApplicationException;


public class BeaconsHttpClientV1 extends CommandableHttpClient implements IBeaconsClientV1{

	public BeaconsHttpClientV1() {
		super("v1/beacons");
	}

	@Override
	public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) {
		try {
			return callCommand("get_beacons", correlationId, filter, paging);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BeaconV1 getOneById(String correlationId, String id) {
		try {
			return callCommand("get_beacon", correlationId, id);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BeaconV1 getOneByUdi(String correlationId, String udi) {
		try {
			return callCommand("get_beacon_by_udi", correlationId, udi);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CenterObject calculatePosition(String correlationId, String siteId, String[] udis) {
		Map<String, String[]> entity = new HashMap<String, String[]>();
		entity.put(siteId, udis);
		try {
			return callCommand("calculate_position", correlationId, entity);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BeaconV1 create(String correlationId, BeaconV1 item) {
		try {
			return callCommand("create_beacon", correlationId, item);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BeaconV1 update(String correlationId, BeaconV1 item) {
		try {
			return callCommand("update_beacon", correlationId, item);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BeaconV1 deleteById(String correlationId, String id) {
		try {
			return callCommand("delete_beacon", correlationId, id);
		} catch (ApplicationException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
