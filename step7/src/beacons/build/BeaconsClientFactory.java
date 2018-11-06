package beacons.build;

import org.pipservices3.commons.refer.Descriptor;
import org.pipservices3.components.build.Factory;

import beacons.clients.BeaconsDirectClientV1;
import beacons.clients.BeaconsHttpClientV1;
import beacons.clients.BeaconsNullClientV1;

public class BeaconsClientFactory extends Factory {

	public static Descriptor NullClientDescriptor = new Descriptor("beacons", "client", "null", "*", "1.0");
	public static Descriptor DirectClientDescriptor = new Descriptor("beacons", "client", "direct", "*", "1.0");
	public static Descriptor HttpClientDescriptor = new Descriptor("beacons", "client", "http", "*", "1.0");

	public BeaconsClientFactory() {
		registerAsType(BeaconsClientFactory.NullClientDescriptor, BeaconsNullClientV1.class);
		registerAsType(BeaconsClientFactory.DirectClientDescriptor, BeaconsDirectClientV1.class);
		registerAsType(BeaconsClientFactory.HttpClientDescriptor, BeaconsHttpClientV1.class);
	}
}
