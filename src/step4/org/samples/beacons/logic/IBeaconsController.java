package step4.org.samples.beacons.logic;

import org.pipservices.commons.data.*;
import step2.org.samples.beacons.interfaces.*;

public interface IBeaconsController {
    DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging);
    BeaconV1 getOneById(String correlationId, String id);
    BeaconV1 getOneByUdi(String correlationId, String udi);
    CenterObject calculatePosition(String correlationId, String siteId, String[] udis);
    BeaconV1 create(String correlationId, BeaconV1 item);
    BeaconV1 update(String correlationId, BeaconV1 item);
    BeaconV1 deleteById(String correlationId, String id);
}
