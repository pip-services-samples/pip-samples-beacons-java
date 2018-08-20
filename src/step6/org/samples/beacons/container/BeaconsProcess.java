package step6.org.samples.beacons.container;

import org.pipservices.container.ProcessContainer;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new BeaconsServiceFactory());
	}

}
