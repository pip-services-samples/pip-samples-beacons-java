package beacons.persistence;

import java.util.*;
import java.util.function.Predicate;

import org.pipservices.commons.data.*;
import org.pipservices.data.persistence.*;

import beacons.data.version1.BeaconV1;

public class BeaconsMemoryPersistence extends IdentifiableMemoryPersistence<BeaconV1, String>
	implements IBeaconsPersistence {

	public BeaconsMemoryPersistence(Class<BeaconV1> type) {
		super(type);
		_maxPageSize = 100;
	}

	private Predicate<BeaconV1> composeFilter(FilterParams filter) {
        filter = filter != null ? filter : new FilterParams();
        String id = filter.getAsNullableString("id");
        String siteId = filter.getAsNullableString("site_id");
        String label = filter.getAsNullableString("label");
        String udi = filter.getAsNullableString("udi");
        String udis = filter.getAsNullableString("udis");
        
        String[] udiArray = null;
        if (udis != null && udis.length() > 0) {
        	udiArray = udis.split(",");        	
        }
        List<String> udiList = udiArray != null ? Arrays.asList(udiArray) : null;
		
		return (v) -> {
			// Filter by key
            if (id != null && !id.equals(v.getId()))
                return false;
            if (siteId != null && !siteId.equals(v.getSiteId()))
                return false;
            if (label != null && !label.equals(v.getLabel()))
                return false;
            if (udi != null && !udi.equals(v.getUdi()))
                return false;
            if (udiList != null && !udiList.contains(v.getId()))
                return false;
            return true;
        };
	}	
	
    public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) {		
        return getPageByFilter(correlationId, composeFilter(filter), paging, null);
    }
	
	public BeaconV1 getOneByUdi(String correlationId, String udi)  {
		if (udi == null) return null;

		synchronized (_lock) {
			for (BeaconV1 item : _items) {
				if (udi.equals(item.getUdi()))
					return item;
			}
			
			return null;
		}
	}

}
