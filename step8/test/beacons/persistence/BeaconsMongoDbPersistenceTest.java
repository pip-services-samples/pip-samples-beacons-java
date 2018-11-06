package beacons.persistence;

import org.junit.Test;
import org.pipservices3.commons.convert.*;
import org.pipservices3.commons.config.*;
import org.pipservices3.commons.errors.*;

import beacons.persistence.BeaconsMongoDbPersistence;

public class BeaconsMongoDbPersistenceTest {
	public BeaconsMongoDbPersistence persistence;
    public BeaconsMongoDbPersistence getPersistence() { return persistence; }
	public void setPersistence(BeaconsMongoDbPersistence persistence) { this.persistence = persistence; }
	
	public BeaconsPersistenceFixture fixture;
	public BeaconsPersistenceFixture getFixture() { return fixture; }
	public void setFixture(BeaconsPersistenceFixture fixture) { this.fixture = fixture; }
	
	public BeaconsMongoDbPersistenceTest() throws ApplicationException {
		String mongoEnabled = System.getenv("MONGO_ENABLED") != null ? System.getenv("MONGO_ENABLED") : "true";
		String mongoUri = System.getenv("MONGO_SERVICE_URI");
        String mongoHost = System.getenv("MONGO_SERVICE_HOST") != null ? System.getenv("MONGO_SERVICE_HOST") : "localhost";
        String mongoPort = System.getenv("MONGO_SERVICE_PORT") != null ? System.getenv("MONGO_SERVICE_PORT") : "27017";
        String mongoDatabase = System.getenv("MONGO_DB") != null ? System.getenv("MONGO_DB") : "test";
        String mongoCollection = System.getenv("MONGO_COLLECTION") != null ? System.getenv("MONGO_COLLECTION") : "beacons";
		
        boolean enabled = BooleanConverter.toBoolean(mongoEnabled);
        if (enabled) {
            ConfigParams config = ConfigParams.fromTuples(
                "collection", mongoCollection,
                "connection.database", mongoDatabase,
                "connection.host", mongoHost,
                "connection.port", mongoPort,
                "connection.uri", mongoUri
            );

            persistence = new BeaconsMongoDbPersistence();
            persistence.configure(config);
            persistence.open(null);
            persistence.clear(null);
            fixture = new BeaconsPersistenceFixture(persistence);
        }
    }
	
    @Test
    public void testCrudOperations() throws ApplicationException {
        if (fixture != null)
           fixture.testCrudOperations();
    }

    @Test
    public void testGetWithFilter() throws ApplicationException {
        if (fixture != null)
            fixture.testGetWithFilter();
    }

}
