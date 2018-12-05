package beacons.data.version1;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.pipservices3.commons.data.IStringIdentifiable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeaconV1 implements IStringIdentifiable {
	private String _id;
	private String _siteId;
	private String _type;
	private String _udi;
	private String _label;
	private CenterObjectV1 _center;
	private double _radius;
	
	public BeaconV1() {}
	
	public BeaconV1(String id, String siteId, String type, String udi, String label, CenterObjectV1 center, double radius) {
		this._id = id;
		this._siteId = siteId;
		this._type = type;
		this._udi = udi;
		this._label = label;
		this._center = center;
		this._radius = radius;
	}
	
	@JsonProperty("id")
	@BsonProperty("_id")
	public String getId() { return _id; }
	public void setId(String value) { _id = value; }
	
	@JsonProperty("site_id")
	@BsonProperty("site_id")
	public String getSiteId() { return _siteId; }
	public void setSiteId(String value) { _siteId = value; }
	
	@JsonProperty("type")
	@BsonProperty("type")
	public String getType() { return _type; }
	public void setType(String value) { _type = value; }

	@JsonProperty("udi")
	@BsonProperty("udi")
	public String getUdi() { return _udi; }
	public void setUdi(String value) { _udi = value; }

	@JsonProperty("label")
	@BsonProperty("label")
	public String getLabel() { return _label; }
	public void setLabel(String value) { _label = value; }

	@JsonProperty("center")
	@BsonProperty("center")
	public CenterObjectV1 getCenter() { return _center; }
	public void setCenter(CenterObjectV1 value) { _center = value; }

	@JsonProperty("radius")
	@BsonProperty("redius")
	public double getRadius() { return _radius; }
	public void setRadius(double value) { _radius = value; }
}