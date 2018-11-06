package beacons.clients;

import javax.ws.rs.core.GenericType;

import org.pipservices3.rpc.clients.CommandableHttpClient;
import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.run.Parameters;

import beacons.data.version1.*;

public class BeaconsHttpClientV1 extends CommandableHttpClient implements IBeaconsClientV1 {

	public BeaconsHttpClientV1() {
		super("v1/beacons");
	}

	@Override
	public DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging)
			throws ApplicationException {
		return callCommand(new GenericType<DataPage<BeaconV1>>() {
		}, "get_beacons", correlationId, Parameters.fromTuples("filter", filter, "paging", paging));
	}

	@Override
	public BeaconV1 getBeaconById(String correlationId, String id) throws ApplicationException {
		return callCommand(BeaconV1.class, "get_beacon_by_id", correlationId, Parameters.fromTuples("id", id));
	}

	@Override
	public BeaconV1 getBeaconByUdi(String correlationId, String udi) throws ApplicationException {
		return callCommand(BeaconV1.class, "get_beacon_by_udi", correlationId,
				Parameters.fromTuples("udi", udi));
	}

	// Todo
	@Override
	public CenterObjectV1 calculatePosition(String correlationId, String siteId, String[] udis)
			throws ApplicationException {
		return callCommand(CenterObjectV1.class, "calculate_position", correlationId,
				Parameters.fromTuples("site_id", siteId, "udis", udis));
	}

	@Override
	public BeaconV1 createBeacon(String correlationId, BeaconV1 item) throws ApplicationException {
		return callCommand(BeaconV1.class, "create_beacon", correlationId, Parameters.fromTuples("beacon", item));
	}

	@Override
	public BeaconV1 updateBeacon(String correlationId, BeaconV1 item) throws ApplicationException {
		return callCommand(BeaconV1.class, "update_beacon", correlationId, Parameters.fromTuples("beacon", item));
	}

	@Override
	public BeaconV1 deleteBeaconById(String correlationId, String id) throws ApplicationException {
		return callCommand(BeaconV1.class, "delete_beacon_by_id", correlationId, Parameters.fromTuples("id", id));
	}

}
