package beacons.data.version1;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

public class CenterObjectV1 {
	private String _type;
	// MongoDB Pojo codec doesn't support arrays 
	private List<Double> _coordinates;
	
	public CenterObjectV1() {}

	public CenterObjectV1(String type, double[] coordinates) {
		_type = type;
		
		if (coordinates != null) {			
			_coordinates = new ArrayList<Double>();
			for (double coordinate : coordinates)
				_coordinates.add(new Double(coordinate));
		}
	}

	public CenterObjectV1(String type, List<Double> coordinates) {
		_type = type;
		_coordinates = coordinates;
	}
	
	@JsonProperty("type")
	public String getType() { return _type; }
	public void setType(String value) { _type = value; }
	
	@JsonProperty("coordinates")
	public List<Double> getCoordinates() { return _coordinates; }
	public void setCoordinates(List<Double> value) { _coordinates = value; }
}
