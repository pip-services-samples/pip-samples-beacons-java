package beacons.persistence;

import org.junit.Test;
import org.pipservices.commons.config.ConfigParams;
import org.pipservices.commons.errors.ApplicationException;

import beacons.persistence.BeaconsMongoDbPersistence;

public class BeaconsMongoDbPersistenceTest {
	public BeaconsMongoDbPersistence persistence;
    public BeaconsMongoDbPersistence getPersistence() { return persistence; }
	public void setPersistence(BeaconsMongoDbPersistence persistence) { this.persistence = persistence; }
	
	public BeaconsPersistenceFixture fixture;
	public BeaconsPersistenceFixture getFixture() { return fixture; }
	public void setFixture(BeaconsPersistenceFixture fixture) { this.fixture = fixture; }
	
	public BeaconsMongoDbPersistenceTest() throws ApplicationException {
		String mongoUri = System.getenv("MONGO_URI");
        String mongoHost = System.getenv("MONGO_HOST") != null ? System.getenv("MONGO_HOST") : "localhost";
        String mongoPort = System.getenv("MONGO_PORT") != null ? System.getenv("MONGO_PORT") : "27017";
        String mongoDatabase = System.getenv("MONGO_DB") != null ? System.getenv("MONGO_DB") : "test";
        String mongoCollection = System.getenv("MONGO_COLLECTION") != null ? System.getenv("MONGO_COLLECTION") : "beacons";
		
//        ConfigParams config = ConfigParams.fromTuples(
//            "collection", "beacons",
//            "connection.uri", "mongodb://localhost:27017/test"
//            );
        
        ConfigParams config = ConfigParams.fromTuples(
        		"collection", mongoCollection,
                "connection.database", mongoDatabase,
                "connection.host", mongoHost,
                "connection.port", mongoPort,
                "connection.uri", mongoUri);

        persistence = new BeaconsMongoDbPersistence();
        persistence.configure(config);
        persistence.open(null);
        persistence.clear(null);
        fixture = new BeaconsPersistenceFixture(persistence);
    }
	
    @Test
    public void testCrudOperations() throws ApplicationException {
        fixture.testCrudOperations();
    }

    @Test
    public void testGetWithFilter() throws ApplicationException {
        fixture.testGetWithFilter();;
    }

}
