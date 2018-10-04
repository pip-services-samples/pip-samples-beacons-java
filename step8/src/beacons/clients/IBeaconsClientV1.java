package beacons.clients;

import org.pipservices.commons.data.*;
import org.pipservices.commons.errors.ApplicationException;

import beacons.data.version1.*;

public interface IBeaconsClientV1 {
	DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging) throws ApplicationException;
    BeaconV1 getBeaconById(String correlationId, String id) throws ApplicationException;
    BeaconV1 getBeaconByUdi(String correlationId, String udi) throws ApplicationException;
    BeaconV1 createBeacon(String correlationId, BeaconV1 item) throws ApplicationException;
    BeaconV1 updateBeacon(String correlationId, BeaconV1 item) throws ApplicationException;
    BeaconV1 deleteBeaconById(String correlationId, String id) throws ApplicationException;
    CenterObject calculatePosition(String correlationId, String siteId, String[] udis) throws ApplicationException;
}
