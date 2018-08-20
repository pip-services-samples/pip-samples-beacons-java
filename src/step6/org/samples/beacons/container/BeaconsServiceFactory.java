package step6.org.samples.beacons.container;

import org.pipservices.components.build.Factory;
import step3.org.samples.beacons.persistence.*;
import step4.org.samples.beacons.logic.BeaconsController;
import step5.org.samples.beacons.services.BeaconsHttpServiceV1;
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
