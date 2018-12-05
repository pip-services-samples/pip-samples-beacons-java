package beacons.clients;

import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.refer.Descriptor;
import org.pipservices3.rpc.clients.DirectClient;

import beacons.data.version1.BeaconV1;
import beacons.data.version1.CenterObjectV1;
import beacons.logic.IBeaconsController;

public class BeaconsDirectClientV1 extends DirectClient<IBeaconsController> implements IBeaconsClientV1 {

	public BeaconsDirectClientV1() {
		super();
		_dependencyResolver.put("controller", new Descriptor("beacons", "controller", "*", "*", "1.0"));
	}
	
	@Override
	public DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging) throws ApplicationException {
		filter = filter != null ? filter : new FilterParams();
		paging = paging != null ? paging : new PagingParams();

        //Timing timing = instrument(correlationId, "dummy.get_page_by_filter");
		
		return _controller.getBeaconsByFilter(correlationId, filter, paging);
	}

	@Override
	public BeaconV1 getBeaconById(String correlationId, String id) throws ApplicationException {
		return _controller.getBeaconsById(correlationId, id);
	}

	@Override
	public BeaconV1 getBeaconByUdi(String correlationId, String udi) throws ApplicationException {
		return _controller.getBeaconsByUdi(correlationId, udi);
	}


	@Override
	public CenterObjectV1 calculatePosition(String correlationId, String siteId, String[] udis) throws ApplicationException {
		return _controller.calculatePosition(correlationId, siteId, udis);
	}
	
	@Override
	public BeaconV1 createBeacon(String correlationId, BeaconV1 item) throws ApplicationException {
		return _controller.createBeacon(correlationId, item);
	}

	@Override
	public BeaconV1 updateBeacon(String correlationId, BeaconV1 item) throws ApplicationException {
		return _controller.updateBeacon(correlationId, item);
	}

	@Override
	public BeaconV1 deleteBeaconById(String correlationId, String id) throws ApplicationException {
		return _controller.deleteBeaconById(correlationId, id);
	}


}
