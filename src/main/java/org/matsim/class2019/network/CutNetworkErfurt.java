package org.matsim.class2019.network;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.core.network.io.NetworkWriter;

public class CutNetworkErfurt {
	
	public static void main(String[] args) {
		
		// Leipziger Str wird auf Kapazität 1 gesetzt
	
	Path inputNetwork = Paths.get("C:\\Users\\Pascal\\Desktop\\Multi agent transport simulation\\uebung02\\test-networkWithShapeFilter.xml.gz");
	Path outputNetwork = Paths.get("C:\\Users\\Pascal\\Desktop\\Multi agent transport simulation\\uebung02\\test-networkWithShapeFilterCuttedLeipzigerstr.xml.gz");
	Network network = NetworkUtils.createNetwork();
	new MatsimNetworkReader(network).readFile(inputNetwork.toString());
	network.getLinks().get(Id.createLinkId("16578")).setCapacity(0);
	network.getLinks().get(Id.createLinkId("16572")).setCapacity(0);
	new NetworkWriter(network).write(outputNetwork.toString());
	
	}
}
