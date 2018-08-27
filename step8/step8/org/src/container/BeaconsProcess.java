package step8.org.src.container;

import org.pipservices.container.ProcessContainer;

import step8.org.src.build.BeaconsServiceFactory;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new BeaconsServiceFactory());
	}

}
