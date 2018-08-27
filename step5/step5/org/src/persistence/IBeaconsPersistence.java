package step5.org.src.persistence;

import org.pipservices.commons.data.*;

import step5.org.src.interfaces.data.version1.*;

public interface IBeaconsPersistence {
	DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging);
	<T> T getOneById(String correlationId, String id);
    BeaconV1 getOneByUdi(String correlationId, String udi);
    CenterObject calculatePosition(String correlationId, String siteId, String[] udis);
    BeaconV1 create(String correlationId, BeaconV1 item);
    BeaconV1 update(String correlationId, BeaconV1 newItem);
    <T> T deleteById(String correlationId, String id);
}
