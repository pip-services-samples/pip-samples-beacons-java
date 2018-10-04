package beacons.data.version1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CenterObject {
	private String _type;
	private double[] _coordinates;
	
	public CenterObject() {}

	public CenterObject(String type, double[] coordinates) {
		_type = type;
		_coordinates = coordinates;
	}

	@JsonProperty("type")
	public String getType() { return _type; }
	public void setType(String value) { _type = value; }
	
	@JsonProperty("coordinates")
	public double[] getCoordinates() { return _coordinates; }
	public void setCoordinates(double[] value) { _coordinates = value; }
}
