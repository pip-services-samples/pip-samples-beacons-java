package beacons.logic;

import java.util.List;

import org.pipservices3.commons.commands.*;
import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.commons.config.IConfigurable;
import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.commons.errors.ConfigException;
import org.pipservices3.commons.refer.*;

import beacons.data.version1.*;
import beacons.persistence.IBeaconsPersistence;

public class BeaconsController implements IReferenceable, ICommandable, IBeaconsController, IConfigurable {
	private IBeaconsPersistence _persistence;
	private BeaconsCommandSet _commandSet;

	public BeaconsController() {
	}

	@Override
	public void configure(ConfigParams config) throws ConfigException {

	}

	public void setReferences(IReferences references) throws ReferenceException {
		_persistence = (IBeaconsPersistence) references
				.getOneRequired(new Descriptor("beacons", "persistence", "*", "*", "1.0"));
	}

	@Override
	public CommandSet getCommandSet() {
		return _commandSet != null ? _commandSet : new BeaconsCommandSet(this);
	}

	@Override
	public DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging)
			throws ApplicationException {
		return _persistence.getPageByFilter(correlationId, filter, paging);
	}

	@Override
	public BeaconV1 getBeaconsById(String correlationId, String id) throws ApplicationException {
		return _persistence.getOneById(correlationId, id);
	}

	@Override
	public BeaconV1 getBeaconsByUdi(String correlationId, String udi) throws ApplicationException {
		return _persistence.getOneByUdi(correlationId, udi);
	}

	@Override
	public CenterObjectV1 calculatePosition(String correlationId, String siteId, String[] udis)
			throws ApplicationException {
		if (udis == null || udis.length == 0)
			return null;

		DataPage<BeaconV1> result = _persistence.getPageByFilter(correlationId,
				FilterParams.fromTuples("site_id", siteId, "udis", udis), null);
		List<BeaconV1> beacons = result.getData();

		double lat = 0;
		double lng = 0;
		int count = 0;
		for (BeaconV1 beacon : beacons) {
			if (beacon.getCenter() != null
					&& beacon.getCenter().getType().equals("Point")
					&& beacon.getCenter().getCoordinates() != null
					&& beacon.getCenter().getCoordinates().size() > 1) {
				List<Double> coordinates = beacon.getCenter().getCoordinates();
				lng += coordinates.get(0);
				lat += coordinates.get(1);
				count += 1;
			}
		}

		if (count > 0) {
			CenterObjectV1 position = new CenterObjectV1("Point", new double[] { lng / count, lat / count });
			return position;
		}

		return null;
	}

	@Override
	public BeaconV1 createBeacon(String correlationId, BeaconV1 item) throws ApplicationException {
		return _persistence.create(correlationId, item);
	}

	@Override
	public BeaconV1 updateBeacon(String correlationId, BeaconV1 item) throws ApplicationException {
		return _persistence.update(correlationId, item);
	}

	@Override
	public BeaconV1 deleteBeaconById(String correlationId, String id) throws ApplicationException {
		return _persistence.deleteById(correlationId, id);
	}

}
