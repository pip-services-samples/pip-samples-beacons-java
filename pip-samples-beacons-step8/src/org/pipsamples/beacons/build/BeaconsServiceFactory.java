package org.pipsamples.beacons.build;

import org.pipservices.components.build.Factory;
import org.pipsamples.beacons.logic.BeaconsController;
import org.pipsamples.beacons.persistence.*;
import org.pipsamples.beacons.services.BeaconsHttpServiceV1;
import org.pipservices.commons.refer.Descriptor;

public class BeaconsServiceFactory extends Factory{

	public static Descriptor Descriptor = new Descriptor("pip-beacons", "factory", "service", "default", "1.0");
    public static Descriptor MemoryPersistenceDescriptor = new Descriptor("pip-beacons", "persistence", "memory", "*", "1.0");
    public static Descriptor MongoDbPersistenceDescriptor = new Descriptor("pip-beacons", "persistence", "mongodb", "*", "1.0");
    public static Descriptor ControllerDescriptor = new Descriptor("pip-beacons", "controller", "default", "*", "1.0");
    public static Descriptor HttpServiceDescriptor = new Descriptor("pip-beacons", "service", "http", "*", "1.0");


    public BeaconsServiceFactory() {
        registerAsType(MemoryPersistenceDescriptor, BeaconsMemoryPersistence.class);
        registerAsType(MongoDbPersistenceDescriptor, BeaconsMongoDbPersistence.class);
        registerAsType(ControllerDescriptor, BeaconsController.class);
        registerAsType(HttpServiceDescriptor, BeaconsHttpServiceV1.class);            
    }
}
