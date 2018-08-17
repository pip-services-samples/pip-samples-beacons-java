package org.samples.beacons.service.logic;

import org.pipservices.commons.commands.CommandSet;
import org.pipservices.commons.commands.ICommandable;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.data.DataPage;
import org.pipservices.commons.data.FilterParams;
import org.pipservices.commons.data.PagingParams;
import org.pipservices.commons.refer.DependencyResolver;
import org.pipservices.commons.refer.IReferences;
import org.pipservices.commons.refer.ReferenceException;
import org.samples.beacons.interfaces.data.BeaconV1;
import org.samples.beacons.interfaces.data.CenterObject;
import org.samples.beacons.interfaces.logic.IBeaconsController;
import org.samples.beacons.service.persistence.IBeaconsPersistence;

public class BeaconsController implements ICommandable, IBeaconsController {
	
	private IBeaconsPersistence _persistence;
    private BeaconsCommandSet _commandSet;
    protected DependencyResolver _dependencyResolver;

    public String component; 
    public String getComponent() {
		return "Trainings.Beacons";
	}

    public BeaconsController() {
        _dependencyResolver = new DependencyResolver(ConfigParams.fromTuples("dependencies.persistence", "samples-beacons:persistence:*:*:1.0"));
    }

    public void setReferences(IReferences references) throws ReferenceException {
    	_dependencyResolver.setReferences(references);
        _persistence = (IBeaconsPersistence) _dependencyResolver.getOneRequired("persistence");
    }


	@Override
	public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) {
		return _persistence.getPageByFilter(correlationId, filter, paging);
	}

	@Override
	public BeaconV1 getOneById(String correlationId, String id) {
		return _persistence.getOneById(correlationId, id);
	}

	@Override
	public BeaconV1 getOneByUdi(String correlationId, String udi) {
		return _persistence.getOneByUdi(correlationId, udi);
	}

	@Override
	public CenterObject calculatePosition(String correlationId, String siteId, String[] udis) {
		return _persistence.calculatePosition(correlationId, siteId, udis);
	}

	@Override
	public BeaconV1 create(String correlationId, BeaconV1 item) {
		return _persistence.create(correlationId, item);
	}

	@Override
	public BeaconV1 update(String correlationId, BeaconV1 item) {
		return _persistence.update(correlationId, item);
	}

	@Override
	public BeaconV1 deleteById(String correlationId, String id) {
		return _persistence.deleteById(correlationId, id);
	}

	@Override
	public CommandSet getCommandSet() {
		return null;
		//return _commandSet != null ? _commandSet : (_commandSet = new BeaconsCommandSet(this));
	}

}
