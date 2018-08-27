package step7.org.src.build;

import org.pipservices.components.build.Factory;

import step7.org.src.logic.BeaconsController;
import step7.org.src.persistence.*;
import step7.org.src.services.BeaconsHttpServiceV1;

import org.pipservices.commons.refer.Descriptor;

public class BeaconsServiceFactory extends Factory{

	public static Descriptor Descriptor = new Descriptor("pip-samples-beacons", "factory", "service", "default", "1.0");
    public static Descriptor MemoryPersistenceDescriptor = new Descriptor("pip-samples-beacons", "persistence", "memory", "*", "1.0");
    public static Descriptor MongoDbPersistenceDescriptor = new Descriptor("pip-samples-beacons", "persistence", "mongodb", "*", "1.0");
    public static Descriptor ControllerDescriptor = new Descriptor("pip-samples-beacons", "controller", "default", "*", "1.0");
    public static Descriptor HttpServiceDescriptor = new Descriptor("pip-samples-beacons", "service", "http", "*", "1.0");


    public BeaconsServiceFactory() {
        registerAsType(MemoryPersistenceDescriptor, BeaconsMemoryPersistence.class);
        registerAsType(MongoDbPersistenceDescriptor, BeaconsMongoDbPersistence.class);
        registerAsType(ControllerDescriptor, BeaconsController.class);
        registerAsType(HttpServiceDescriptor, BeaconsHttpServiceV1.class);            
    }
}
