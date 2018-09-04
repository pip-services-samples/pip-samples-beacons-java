package beacons.clients;

import org.pipservices.commons.data.*;

import beacons.data.version1.*;

public interface IBeaconsClientV1 {
	DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging);
    BeaconV1 getBeaconById(String correlationId, String id);
    BeaconV1 getBeaconByUdi(String correlationId, String udi);
    BeaconV1 createBeacon(String correlationId, BeaconV1 item);
    BeaconV1 updateBeacon(String correlationId, BeaconV1 item);
    BeaconV1 deleteBeaconById(String correlationId, String id);
    CenterObject calculatePosition(String correlationId, String siteId, String[] udis);
}
