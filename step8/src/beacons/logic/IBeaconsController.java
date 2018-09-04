package beacons.logic;

import org.pipservices.commons.data.*;

import beacons.data.version1.*;

public interface IBeaconsController {
    DataPage<BeaconV1> getBeaconsByFilter(String correlationId, FilterParams filter, PagingParams paging);
    BeaconV1 getBeaconsById(String correlationId, String id);
    BeaconV1 getBeaconsByUdi(String correlationId, String udi);
    BeaconV1 createBeacon(String correlationId, BeaconV1 item);
    BeaconV1 updateBeacon(String correlationId, BeaconV1 item);
    BeaconV1 deleteBeaconById(String correlationId, String id);
    CenterObject calculatePosition(String correlationId, String siteId, String[] udis);
}
