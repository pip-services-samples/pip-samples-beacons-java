package beacons.persistence;

import org.pipservices.commons.data.*;

import beacons.data.version1.*;

public interface IBeaconsPersistence {
	DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging);
	BeaconV1 getOneById(String correlationId, String id);
    BeaconV1 getOneByUdi(String correlationId, String udi);
    BeaconV1 create(String correlationId, BeaconV1 item);
    BeaconV1 update(String correlationId, BeaconV1 item);
    BeaconV1 deleteById(String correlationId, String id);
}
