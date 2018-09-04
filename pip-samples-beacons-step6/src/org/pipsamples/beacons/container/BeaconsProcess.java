package org.pipsamples.beacons.container;

import org.pipsamples.beacons.build.BeaconsServiceFactory;
import org.pipservices.container.ProcessContainer;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new BeaconsServiceFactory());
	}

}
