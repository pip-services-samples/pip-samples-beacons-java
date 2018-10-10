package beacons.services;

import org.pipservices.commons.refer.Descriptor;
import org.pipservices.rpc.services.CommandableHttpService;;

public class BeaconsHttpServiceV1 extends CommandableHttpService{

	public BeaconsHttpServiceV1() {
		super("v1/beacons");
		_dependencyResolver.put("controller", new Descriptor("beacons", "controller", "default", "*", "1.0"));
	}
	
}
