package org.matsim.class2019.eventsprogramming;

import java.util.HashSet;
import java.util.Set;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Person;
import org.matsim.vehicles.Vehicle;

public class LinkAggregator implements LinkEnterEventHandler {
	
	private Set<Id<Link>> monitoredLinkIds;
	private Set<Id<Vehicle>> monitoredVehicles = new HashSet<>();

	
	public LinkAggregator() {
		this.monitoredLinkIds = new HashSet<>();
		monitoredLinkIds.add(Id.createLinkId(123));
		monitoredLinkIds.add(Id.createLinkId("1243"));
	}
	
	
	@Override
	public void handleEvent(LinkEnterEvent event) {
		if (monitoredLinkIds.contains(event.getLinkId())) {
			monitoredVehicles.add(event.getVehicleId());
		}
		
	}
	

}
