package beacons.container;

import org.pipservices3.container.ProcessContainer;
import org.pipservices3.rpc.build.DefaultRpcFactory;

import beacons.build.BeaconsServiceFactory;

public class BeaconsProcess extends ProcessContainer{

	public BeaconsProcess() {
		super("beacons", "Beacons microservice");
		_factories.add(new DefaultRpcFactory());
		_factories.add(new BeaconsServiceFactory());
	}

	public static void main(String[] args) {
		try {
			BeaconsProcess process = new BeaconsProcess();
			process.run(args);
		} catch (Throwable ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
}
