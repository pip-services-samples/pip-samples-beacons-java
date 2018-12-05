package beacons.build;

import org.pipservices3.components.build.Factory;

import beacons.logic.BeaconsController;
import beacons.persistence.*;
import beacons.services.*;

import org.pipservices3.commons.refer.Descriptor;

public class BeaconsServiceFactory extends Factory {

	public static Descriptor Descriptor = new Descriptor("beacons", "factory", "service", "default", "1.0");
	public static Descriptor MemoryPersistenceDescriptor = new Descriptor("beacons", "persistence", "memory", "*",
			"1.0");
	public static Descriptor MongoDbPersistenceDescriptor = new Descriptor("beacons", "persistence", "mongodb", "*",
			"1.0");
	public static Descriptor ControllerDescriptor = new Descriptor("beacons", "controller", "default", "*", "1.0");
	public static Descriptor HttpServiceDescriptor = new Descriptor("beacons", "service", "http", "*", "1.0");

	public BeaconsServiceFactory() {
		registerAsType(MemoryPersistenceDescriptor, BeaconsMemoryPersistence.class);
		registerAsType(MongoDbPersistenceDescriptor, BeaconsMongoDbPersistence.class);
		registerAsType(ControllerDescriptor, BeaconsController.class);
		registerAsType(HttpServiceDescriptor, BeaconsHttpServiceV1.class);
	}
}
