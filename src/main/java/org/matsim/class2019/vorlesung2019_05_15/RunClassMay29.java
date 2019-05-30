package org.matsim.class2019.vorlesung2019_05_15;

import java.net.URL;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class RunClassMay29 {

	public static void main(String[] args) {


		Config config = ConfigUtils.loadConfig("./scenarios/equil/config.xml");
		
		config.controler().setOutputDirectory("./output3");
		
		Scenario scenario = ScenarioUtils.loadScenario(config);
		
		Controler controler = new Controler(scenario);
		
	
		controler.run();

	}

}
