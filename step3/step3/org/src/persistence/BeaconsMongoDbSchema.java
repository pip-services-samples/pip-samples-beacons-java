package step3.org.src.persistence;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.pipservices.commons.data.IStringIdentifiable;

import step3.org.src.interfaces.data.version1.CenterObject;

public class BeaconsMongoDbSchema implements IStringIdentifiable{
	@BsonProperty("id")
	private String id;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	@BsonProperty("site_id")
	private String siteId;
	public String getSiteId() { return siteId; }
	public void setSiteId(String siteId) { this.siteId = siteId; }
	
	@BsonProperty("type")
	private String type;
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	@BsonProperty("udi")
	private String udi;
	public String getUdi() { return udi; }
	public void setUdi(String udi) { this.udi = udi; }

	@BsonProperty("label")
	private String label;
	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	@BsonProperty("center")
	private CenterObject center;
	public CenterObject getCenter() { return center; }
	public void setCenter(CenterObject center) { this.center = center; }

	@BsonProperty("radius")
	private double radius;
	public double getRadius() { return radius; }
	public void setRadius(double radius) { this.radius = radius; }
}
