package beacons.container;

import org.pipservices.container.ProcessContainer;

import beacons.build.BeaconsServiceFactory;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new BeaconsServiceFactory());
	}

}
