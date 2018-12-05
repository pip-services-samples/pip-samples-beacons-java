package beacons.persistence;

import java.util.*;
import java.util.function.Predicate;

import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.ApplicationException;
import org.pipservices3.data.persistence.*;

import beacons.data.version1.BeaconV1;

public class BeaconsMemoryPersistence extends IdentifiableMemoryPersistence<BeaconV1, String>
	implements IBeaconsPersistence {

	public BeaconsMemoryPersistence() {
		super(BeaconV1.class);
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
            if (udiList != null && !udiList.contains(v.getUdi()))
                return false;
            return true;
        };
	}	
	
    public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) throws ApplicationException {		
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

	public BeaconV1 getOneById(String correlationId, String id) throws ApplicationException {
		return super.getOneById(correlationId, id);
	}
	
	public BeaconV1 create(String correlationId, BeaconV1 item) throws ApplicationException {
    	return super.create(correlationId, item);
    }
    public BeaconV1 update(String correlationId, BeaconV1 newItem) throws ApplicationException {
    	return super.update(correlationId, newItem);
    }
    public BeaconV1 deleteById(String correlationId, String id) throws ApplicationException {
    	return super.deleteById(correlationId, id);
    }
	
}
