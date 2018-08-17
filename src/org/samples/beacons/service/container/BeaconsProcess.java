package org.samples.beacons.service.container;

import org.pipservices.container.ProcessContainer;
import org.samples.beacons.service.build.BeaconsServiceFactory;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new BeaconsServiceFactory());
	}

}
