package beacons.logic;

import java.io.IOException;

import org.pipservices.commons.commands.*;
import org.pipservices.commons.convert.*;
import org.pipservices.commons.data.*;
import org.pipservices.commons.validate.*;

import beacons.data.version1.*;

public class BeaconsCommandSet extends CommandSet {

	private IBeaconsController _controller;

	public BeaconsCommandSet(IBeaconsController controller) {
		_controller = controller;

		addCommand(makeGetBeaconsCommand());
		addCommand(makeGetOneByIdBeaconsCommand());
		addCommand(makeGetBeaconByUdiCommand());
		addCommand(makeCalculatePositionCommand());
		addCommand(makeCreateBeaconCommand());
		addCommand(makeUpdateBeaconCommand());
		addCommand(makeDeleteBeaconByIdCommand());
	}

	private ICommand makeGetBeaconsCommand() {
		return new Command("get_beacons", new ObjectSchema().withOptionalProperty("filter", new FilterParamsSchema())
				.withOptionalProperty("paging", new PagingParamsSchema()), (correlationId, parameters) -> {
					FilterParams filter = FilterParams.fromValue(parameters.get("filter"));
					PagingParams paging = PagingParams.fromValue(parameters.get("paging"));
					return _controller.getBeaconsByFilter(correlationId, filter, paging);
				});
	}

	private ICommand makeGetOneByIdBeaconsCommand() {
		return new Command("get_beacon_by_id", new ObjectSchema().withRequiredProperty("id", TypeCode.String),
				(correlationId, parameters) -> {
					String id = parameters.getAsString("id");
					return _controller.getBeaconsById(correlationId, id);
				});
	}

	private ICommand makeGetBeaconByUdiCommand() {
		return new Command("get_beacon_by_udi", new ObjectSchema().withRequiredProperty("udi", TypeCode.String),
				(correlationId, parameters) -> {
					String udi = parameters.getAsString("udi");
					return _controller.getBeaconsByUdi(correlationId, udi);
				});
	}

	private ICommand makeCalculatePositionCommand() {
		return new Command("calculate_position",
				new ObjectSchema().withRequiredProperty("site_id", TypeCode.String)
						.withRequiredProperty("udis", TypeCode.Array),
				(correlationId, parameters) -> {
					try {
						String siteId = parameters.getAsString("site_id");
						String[] udis = convertToStringList(parameters.getAsObject("udis"));
						return _controller.calculatePosition(correlationId, siteId, udis);
					} catch (IOException e) {
					}
					return null;
				});
	}

	private ICommand makeCreateBeaconCommand() {
		return new Command("create_beacon", new ObjectSchema().withOptionalProperty("beacon", new BeaconV1Schema()),
				(correlationId, parameters) -> {
					try {
						BeaconV1 beacon = convertToBeacon(parameters.getAsObject("beacon"));
						return _controller.createBeacon(correlationId, beacon);
					} catch (IOException e) {
					}
					return null;
				});
	}

	private ICommand makeUpdateBeaconCommand() {
		return new Command("update_beacon", new ObjectSchema().withOptionalProperty("beacon", new BeaconV1Schema()),
				(correlationId, parameters) -> {
					try {
						BeaconV1 beacon = convertToBeacon(parameters.getAsObject("beacon"));
						return _controller.updateBeacon(correlationId, beacon);
					} catch (IOException e) {
					}
					return null;
				});
	}

	private ICommand makeDeleteBeaconByIdCommand() {
		return new Command("delete_beacon_by_id", new ObjectSchema().withRequiredProperty("id", TypeCode.String),
				(correlationId, parameters) -> {
					String id = parameters.getAsString("id");
					return _controller.deleteBeaconById(correlationId, id);
				});
	}

	private BeaconV1 convertToBeacon(Object value) throws IOException {
		return JsonConverter.fromJson(BeaconV1.class, JsonConverter.toJson(value));
	}

	private String[] convertToStringList(Object value) throws IOException {
		return JsonConverter.fromJson(String[].class, JsonConverter.toJson(value));
	}
}
