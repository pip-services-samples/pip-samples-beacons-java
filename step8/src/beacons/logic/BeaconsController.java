package beacons.logic;

import java.util.List;

import org.pipservices.commons.commands.*;
import org.pipservices.commons.data.*;
import org.pipservices.commons.refer.*;

import beacons.data.version1.*;
import beacons.persistence.IBeaconsPersistence;
import beacons.services.BeaconsCommandSet;

public class BeaconsController implements IReferenceable, ICommandable, IBeaconsController {
	private IBeaconsPersistence _persistence;
	private BeaconsCommandSet _commandSet;

	public BeaconsController() {
	}

	public void setReferences(IReferences references) throws ReferenceException {
		_persistence = (IBeaconsPersistence) references.getOneRequired(
			new Descriptor("beacons", "persistence", "*", "*", "1.0")
		);
	}

	@Override
	public CommandSet getCommandSet() {
		return _commandSet != null ? _commandSet : new BeaconsCommandSet(this);
	}

	@Override
	public DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging) {
		return _persistence.getPageByFilter(correlationId, filter, paging);
	}

	@Override
	public BeaconV1 getBeaconsById(String correlationId, String id) {
		return _persistence.getOneById(correlationId, id);
	}

	@Override
	public BeaconV1 getBeaconsByUdi(String correlationId, String udi) {
		return _persistence.getOneByUdi(correlationId, udi);
	}

	@Override
	public BeaconV1 createBeacon(String correlationId, BeaconV1 item) {
		return _persistence.create(correlationId, item);
	}

	@Override
	public BeaconV1 updateBeacon(String correlationId, BeaconV1 item) {
		return _persistence.update(correlationId, item);
	}

	@Override
	public BeaconV1 deleteBeaconById(String correlationId, String id) {
		return _persistence.deleteById(correlationId, id);
	}

	@Override
	public CenterObject calculatePosition(String correlationId, String siteId, String[] udis) {
		if (udis == null || udis.length == 0)
			return null;

		DataPage<BeaconV1> result = getBeaconsByFilter(correlationId,
				FilterParams.fromTuples("site_id", siteId, "udis", udis), null);
		List<BeaconV1> beacons = result.getData();

		double lat = 0;
		double lng = 0;
		int count = 0;
		for (BeaconV1 beacon : beacons) {
			if (beacon.getCenter() != null) {
				double[] coordinates = beacon.getCenter().getCoordinates();

				if (coordinates != null && coordinates.length == 2) {
					lng += coordinates[0];
					lat += coordinates[1];
					count += 1;
				}
			}
		}

		if (count > 0) {
			CenterObject position = new CenterObject("Point", new double[] { lng / count, lat / count });
			return position;
		}

		return null;
	}

}
