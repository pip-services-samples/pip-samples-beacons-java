package step5.org.src.interfaces.data.version1;

import org.pipservices.commons.data.IStringIdentifiable;

import com.fasterxml.jackson.annotation.JsonProperty;
import step5.org.src.interfaces.data.version1.CenterObject;

public class BeaconV1 implements IStringIdentifiable {
	
	public BeaconV1(String id, String siteId, String type, String udi, String label, CenterObject center, double radius) {
		super();
		this.id = id;
		this.siteId = siteId;
		this.type = type;
		this.udi = udi;
		this.label = label;
		this.center = center;
		this.radius = radius;
	}
	
	public BeaconV1(BeaconV1 copy) {
		super();
		this.id = copy.id;
		this.siteId = copy.siteId;
		this.type = copy.type;
		this.udi = copy.udi;
		this.label = copy.label;
		this.center = copy.center;
		this.radius = copy.radius;
	}

	@JsonProperty("id")
	private String id;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	@JsonProperty("site_id")
	private String siteId;
	public String getSiteId() { return siteId; }
	public void setSiteId(String siteId) { this.siteId = siteId; }
	
	@JsonProperty("type")
	private String type;
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	@JsonProperty("udi")
	private String udi;
	public String getUdi() { return udi; }
	public void setUdi(String udi) { this.udi = udi; }

	@JsonProperty("label")
	private String label;
	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	@JsonProperty("center")
	private CenterObject center;
	public CenterObject getCenter() { return center; }
	public void setCenter(CenterObject center) { this.center = center; }

	@JsonProperty("radius")
	private double radius;
	public double getRadius() { return radius; }
	public void setRadius(double radius) { this.radius = radius; }
}