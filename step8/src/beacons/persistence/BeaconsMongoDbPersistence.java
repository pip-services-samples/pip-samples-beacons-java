package beacons.persistence;

import java.util.*;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.conversions.Bson;
import org.pipservices3.commons.data.*;
import org.pipservices3.commons.errors.*;
import org.pipservices3.mongodb.persistence.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;

import beacons.data.version1.*;

public class BeaconsMongoDbPersistence extends IdentifiableMongoDbPersistence<BeaconV1, String>
		implements IBeaconsPersistence {

	public BeaconsMongoDbPersistence() {
		super("beacons", BeaconV1.class);
	}

	protected Bson composeFilter(FilterParams filterParams) {
		filterParams = filterParams != null ? filterParams : new FilterParams();

		ArrayList<Bson> filters = new ArrayList<Bson>();

		String id = filterParams.getAsNullableString("id");
		if (id != null)
			filters.add(Filters.eq("_id", id));

		String siteId = filterParams.getAsNullableString("site_id");
		if (siteId != null)
			filters.add(Filters.eq("site_id", siteId));

		String label = filterParams.getAsNullableString("label");
		if (label != null)
			filters.add(Filters.eq("label", label));
		
		String udi = filterParams.getAsNullableString("udi");
		if (udi != null)
			filters.add(Filters.eq("udi", udi));
		
		String udis = filterParams.getAsNullableString("udis");
		if (udis != null) {
			String[] tokens = udis.split(",");
			if (tokens.length > 0)
				filters.add(Filters.in("udi", tokens));
		}

		// MongoDB doesn't like empty list of $and
		return filters.size() > 0 ? Filters.and(filters) : null;
	}

	protected Bson composeSort() {
		BsonDocument sort = new BsonDocument();
		sort.append("_id", new BsonInt32(1));
		return sort;
	}
	
	@Override
	public DataPage<BeaconV1> getPageByFilter(String correlationId, FilterParams filter, PagingParams paging) throws ApplicationException {
		return super.getPageByFilter(correlationId, composeFilter(filter), paging, composeSort());
	}

	@Override
	public BeaconV1 getOneByUdi(String correlationId, String udi) {
		FindIterable<BeaconV1> result = _collection.find(Filters.eq("udi", udi));
		BeaconV1 item = result != null ? result.first() : null;

        if (item == null)
            _logger.trace(correlationId, "Nothing found from %s with udi = %s", _collectionName, udi);
        else 
        	_logger.trace(correlationId, "Retrieved from %s with udi = %s", _collectionName, udi);

        return item;
	}

	@Override
	public BeaconV1 create(String correlationId, BeaconV1 item) throws ApplicationException {
		return super.сreate(correlationId, item);
	}

	@Override
	public BeaconV1 update(String correlationId, BeaconV1 newItem) throws ApplicationException {
		return super.update(correlationId, newItem);
	}
	@Override
	public BeaconV1 deleteById(String correlationId, String id) throws ApplicationException {
		return super.deleteById(correlationId, id);
	}

	@Override
	public BeaconV1 getOneById(String correlationId, String id) throws ApplicationException {
		return super.getOneById(correlationId, id);
	}
}
