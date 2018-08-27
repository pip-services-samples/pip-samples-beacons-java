package step4.org.src.interfaces.data.version1;

public class CenterObject {
	
	public CenterObject(String type, int[] coordinates) {
		super();
		this.type = type;
		this.coordinates = coordinates;
	}

	private String type;
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	
	private int[] coordinates;
	public int[] getCoordinates() { return coordinates; }
	public void setCoordinates(int[] coordinates) { this.coordinates = coordinates; }
}
