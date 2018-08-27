package step2.org.test.interfaces.data.version1;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.pipservices.commons.random.*;

import step2.org.src.interfaces.data.version1.*;


public class TestModel {

	public static BeaconV1 createBeacon() {
        String id = RandomString.nextString(10, 20);
        String siteId = RandomString.nextString(10, 20);
        String type = RandomText.word();
        String udi = RandomString.nextString(10, 20);
        String label = RandomText.word();
        CenterObject center = new CenterObject("Center", new int[] {RandomInteger.nextInteger(1, 5), RandomInteger.nextInteger(1, 5)});
        double radius = RandomDouble.nextDouble(100.0);
		
		return new BeaconV1(id, siteId, type, udi, label, center, radius);
    }

    public static List<BeaconV1> createBeacons() {
    	List<BeaconV1> result = new ArrayList<BeaconV1>();
        int count = RandomInteger.nextInteger(1, 5);
        for (int i = 0; i < count; i++) {
            result.add(createBeacon());
        }
        return result;
    }

    public static void assertEqual(BeaconV1 expectedBeacon, BeaconV1 actualBeacon) {
        assertEquals(expectedBeacon.getId(),     actualBeacon.getId());
        assertEquals(expectedBeacon.getSiteId(), actualBeacon.getSiteId());
        assertEquals(expectedBeacon.getType(),   actualBeacon.getType());
        assertEquals(expectedBeacon.getUdi(),    actualBeacon.getUdi());
        assertEquals(expectedBeacon.getLabel(),  actualBeacon.getLabel());
        assertEquals(expectedBeacon.getCenter(), actualBeacon.getCenter());
        assertEquals(expectedBeacon.getRadius(), actualBeacon.getRadius(), 0.0);
    }
}
