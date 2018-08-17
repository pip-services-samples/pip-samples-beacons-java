package org.samples.beacons.process;

import org.samples.beacons.service.container.BeaconsProcess;

public class Program {
	static void main(String[] args)
    {
        try
        {
        	BeaconsProcess beacon = new BeaconsProcess();
        	beacon.runWithArgumentsOrConfigFile(null, args, null);
            beacon.wait();
        }
        catch (Exception ex)
        {
        	System.err.println(ex.toString());
        }
    }

}
