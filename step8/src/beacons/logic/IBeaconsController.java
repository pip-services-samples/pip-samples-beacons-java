package beacons.logic;

import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.ApplicationException;

import beacons.data.version1.*;

public interface IBeaconsController {
    DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging) throws ApplicationException;
    BeaconV1 getBeaconsById(String correlationId, String id) throws ApplicationException;
    BeaconV1 getBeaconsByUdi(String correlationId, String udi) throws ApplicationException;
    CenterObjectV1 calculatePosition(String correlationId, String siteId, String[] udis) throws ApplicationException;
    BeaconV1 createBeacon(String correlationId, BeaconV1 item) throws ApplicationException;
    BeaconV1 updateBeacon(String correlationId, BeaconV1 item) throws ApplicationException;
    BeaconV1 deleteBeaconById(String correlationId, String id) throws ApplicationException;
}
