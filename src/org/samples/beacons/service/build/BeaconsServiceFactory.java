package org.samples.beacons.service.build;

import org.pipservices.components.build.Factory;
import org.samples.beacons.service.logic.BeaconsController;
import org.samples.beacons.service.persistence.BeaconsMemoryPersistence;
import org.samples.beacons.service.persistence.BeaconsMongoDbPersistence;
import org.samples.beacons.service.services.BeaconsHttpServiceV1;
import org.pipservices.commons.refer.Descriptor;

public class BeaconsServiceFactory extends Factory{

	public static Descriptor Descriptor = new Descriptor("samples-beacons", "factory", "service", "default", "1.0");
    public static Descriptor MemoryPersistenceDescriptor = new Descriptor("samples-beacons", "persistence", "memory", "*", "1.0");
    public static Descriptor MongoDbPersistenceDescriptor = new Descriptor("samples-beacons", "persistence", "mongodb", "*", "1.0");
    public static Descriptor ControllerDescriptor = new Descriptor("samples-beacons", "controller", "default", "*", "1.0");
    public static Descriptor HttpServiceDescriptor = new Descriptor("samples-beacons", "service", "http", "*", "1.0");


    public BeaconsServiceFactory() {
        registerAsType(MemoryPersistenceDescriptor, BeaconsMemoryPersistence.class);
        registerAsType(MongoDbPersistenceDescriptor, BeaconsMongoDbPersistence.class);
        registerAsType(ControllerDescriptor, BeaconsController.class);
        registerAsType(HttpServiceDescriptor, BeaconsHttpServiceV1.class);            
    }
}
