package beacons.clients;

import java.util.ArrayList;

import org.pipservices3.commons.data.DataPage;
import org.pipservices3.commons.data.FilterParams;
import org.pipservices3.commons.data.IdGenerator;
import org.pipservices3.commons.data.PagingParams;

import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;

public class BeaconsNullClientV1 implements IBeaconsClientV1 {

	@Override
	public DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging) {
		return new DataPage<BeaconV1>(new ArrayList<BeaconV1>());
	}

	@Override
	public BeaconV1 getBeaconById(String correlationId, String id) {
		return new BeaconV1();
	}

	@Override
	public BeaconV1 getBeaconByUdi(String correlationId, String udi) {
		return new BeaconV1();
	}

	@Override
	public BeaconV1 createBeacon(String correlationId, BeaconV1 item) {
		item.setId(item.getId() != null ? item.getId() : IdGenerator.nextLong());
		return item;
	}

	@Override
	public BeaconV1 updateBeacon(String correlationId, BeaconV1 item) {
		return item;
	}

	@Override
	public BeaconV1 deleteBeaconById(String correlationId, String id) {
		return new BeaconV1();
	}

	@Override
	public CenterObjectV1 calculatePosition(String correlationId, String siteId, String[] udis) {
		return new CenterObjectV1();
	}

}
