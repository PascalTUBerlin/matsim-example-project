package org.matsim.class2019.eventsprogramming;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Link;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunAnalysis {
	
	public static void main(String[] args) {
		
		Path events = Paths.get("C:\\Users\\Pascal\\Desktop\\Multi agent transport simulation\\UE\\uebung05\\policyCase\\output_events2.xml");
		Path events2 = Paths.get("C:\\Users\\Pascal\\Desktop\\Multi agent transport simulation\\UE\\uebung05\\baseCase\\output_events.xml");
		
		
		TravelTimeEventHandler handler = new TravelTimeEventHandler();
		EventsManager manager = EventsUtils.createEventsManager();
		manager.addHandler(handler);
		
		new MatsimEventsReader(manager).readFile(events.toString());
		
		double totalTravelTime = handler.computeOverallTravelTime();
		
		//
		
		TravelTimeEventHandler baseHandler = new TravelTimeEventHandler();
		EventsManager baseManager = EventsUtils.createEventsManager();
		baseManager.addHandler(baseHandler);
		
		new MatsimEventsReader(baseManager).readFile(events2.toString());
		
		double baseTotalTravelTime = baseHandler.computeOverallTravelTime();
		
		System.out.println("Total Traveltime Base: " + baseTotalTravelTime);	
		
		
		System.out.println("Total Traveltime Policy: " + totalTravelTime);
		
		System.out.println("Difference: " + (totalTravelTime - baseTotalTravelTime)/3600 + " hours");
		
		
		
		Set<Id<Link>> linksToWatch = new HashSet<>();
		
		linksToWatch.add(Id.createLinkId("77389"));
		linksToWatch.add(Id.createLinkId("77340"));
		
		
		AgentTravelledOnLinkEventHandler agentTravelledOnLinkEventHandler = new AgentTravelledOnLinkEventHandler(linksToWatch);
		
		agentTravelledOnLinkEventHandler.getPersonOnWatchedLinks().forEach(a->System.out.println(a));
		
//		CsvPrinter csvPrinter = new CsvPrinter(Files.createFile(Paths.g, attrs));
		
	}

}
