package org.samples.beacons.client.clients;

import org.pipservices.commons.data.DataPage;
import org.pipservices.commons.data.FilterParams;
import org.pipservices.commons.data.PagingParams;
import org.samples.beacons.interfaces.data.BeaconV1;
import org.samples.beacons.interfaces.data.CenterObject;

public interface IBeaconsClientV1 {

	DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging);
    BeaconV1 getOneById(String correlationId, String id);
    BeaconV1 getOneByUdi(String correlationId, String udi);
    CenterObject calculatePosition(String correlationId, String siteId, String[] udis);
    BeaconV1 create(String correlationId, BeaconV1 item);
    BeaconV1 update(String correlationId, BeaconV1 item);
    BeaconV1 deleteById(String correlationId, String id);
}
