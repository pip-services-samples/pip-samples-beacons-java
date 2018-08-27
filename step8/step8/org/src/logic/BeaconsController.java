package step8.org.src.logic;

import org.pipservices.commons.commands.*;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.data.*;
import org.pipservices.commons.refer.*;

import step8.org.src.interfaces.data.version1.*;
import step8.org.src.persistence.IBeaconsPersistence;
import step8.org.src.services.BeaconsCommandSet;

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
		return _commandSet != null ? _commandSet : new BeaconsCommandSet(this);
	}

}
