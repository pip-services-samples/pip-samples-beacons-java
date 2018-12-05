package beacons.persistence;

import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.*;

import beacons.data.version1.*;

public interface IBeaconsPersistence {
	DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) throws ApplicationException;
	BeaconV1 getOneById(String correlationId, String id) throws ApplicationException;
    BeaconV1 getOneByUdi(String correlationId, String udi) throws ApplicationException;
    BeaconV1 create(String correlationId, BeaconV1 item) throws ApplicationException;
    BeaconV1 update(String correlationId, BeaconV1 item) throws ApplicationException;
    BeaconV1 deleteById(String correlationId, String id) throws ApplicationException;
}
