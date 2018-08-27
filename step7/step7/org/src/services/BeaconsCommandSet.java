package step7.org.src.services;

import org.pipservices.commons.commands.*;
import org.pipservices.commons.convert.TypeCode;
import org.pipservices.commons.data.*;
import org.pipservices.commons.validate.*;

import step7.org.src.interfaces.data.version1.*;
import step7.org.src.logic.IBeaconsController;

public class BeaconsCommandSet extends CommandSet{
	
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
        return new Command(
            "get_beacons",
            new ObjectSchema()
                .withOptionalProperty("filter", new FilterParamsSchema())
                .withOptionalProperty("paging", new PagingParamsSchema()),
            (correlationId, parameters) ->
            {
            	FilterParams filter = FilterParams.fromValue(parameters.get("filter"));
            	PagingParams paging = PagingParams.fromValue(parameters.get("paging"));
                return _controller.getPageByFilter(correlationId, filter, paging);
            });
    }
    
    private ICommand makeGetOneByIdBeaconsCommand() {
        return new Command(
            "get_beacon",
            new ObjectSchema()
                .withRequiredProperty("id", TypeCode.String),
            (correlationId, parameters) ->
            {
                String id = parameters.getAsString("id");
                return _controller.getOneById(correlationId, id);
            });
    }

    private ICommand makeGetBeaconByUdiCommand() {
        return new Command(
            "get_beacon_by_udi",
            new ObjectSchema()
                .withRequiredProperty("udi", TypeCode.String),
            (correlationId, parameters) ->
            {
                String udi = parameters.getAsString("udi");
                return _controller.getOneByUdi(correlationId, udi);
            });
    }
    
    private ICommand makeCalculatePositionCommand() {
        return new Command(
            "calculate_position",
            new ObjectSchema()
                .withRequiredProperty("site_id", new FilterParamsSchema())
                .withRequiredProperty("udis", new PagingParamsSchema()),
            (correlationId, parameters) ->
            {
                String siteId = parameters.getAsString("site_id");
                String udis = parameters.getAsString("udis");
                return _controller.calculatePosition(correlationId, siteId, new String[]{ udis });
            });
    }
    
    private ICommand makeCreateBeaconCommand() {
        return new Command(
            "create_beacon",
            new ObjectSchema()
                .withOptionalProperty("beacon", new BeaconV1Schema()),
            (correlationId, parameters) ->
            {
                BeaconV1 beacon = (BeaconV1)parameters.get("beacon");
                return _controller.create(correlationId, beacon);
            });
    }

    private ICommand makeUpdateBeaconCommand() {
        return new Command(
           "update_beacon",
           new ObjectSchema()
               .withOptionalProperty("beacon", new BeaconV1Schema()),
           (correlationId, parameters) ->
           {
               BeaconV1 beacon = (BeaconV1)parameters.get("beacon");
               return _controller.update(correlationId, beacon);
           });
    }

    private ICommand makeDeleteBeaconByIdCommand() {
        return new Command(
           "delete_beacon",
           new ObjectSchema()
               .withRequiredProperty("id", TypeCode.String),
           (correlationId, parameters) ->
           {
               String id = parameters.getAsString("id");
               return _controller.deleteById(correlationId, id);
           });
    }
}
