package beacons.container;

import org.pipservices.container.ProcessContainer;
import org.pipservices.rpc.build.DefaultRpcFactory;

import beacons.build.BeaconsServiceFactory;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new DefaultRpcFactory());
		_factories.add(new BeaconsServiceFactory());
	}

}
