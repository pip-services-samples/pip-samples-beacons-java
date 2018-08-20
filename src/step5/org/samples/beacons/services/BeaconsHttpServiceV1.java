package step5.org.samples.beacons.services;

import org.pipservices.commons.refer.Descriptor;
import org.pipservices.rest.CommandableHttpService;

public class BeaconsHttpServiceV1 extends CommandableHttpService{

	public BeaconsHttpServiceV1() {
		super("api/v1/beacons");
		_dependencyResolver.put("controller", new Descriptor("samples-beacons", "controller", "default", "*", "1.0"));
	}
	

}
