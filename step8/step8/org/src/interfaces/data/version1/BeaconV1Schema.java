package step8.org.src.interfaces.data.version1;

import org.pipservices.commons.convert.TypeCode;
import org.pipservices.commons.validate.ObjectSchema;

public class BeaconV1Schema extends ObjectSchema {
	public BeaconV1Schema()
    {
        this.withOptionalProperty("id", TypeCode.String);
        this.withRequiredProperty("site_id", TypeCode.String);
        this.withOptionalProperty("type", TypeCode.String);
        this.withRequiredProperty("udi", TypeCode.String);
        this.withOptionalProperty("label", TypeCode.String);
        this.withOptionalProperty("center", null);
        this.withOptionalProperty("radius", TypeCode.Double);
    }
}
