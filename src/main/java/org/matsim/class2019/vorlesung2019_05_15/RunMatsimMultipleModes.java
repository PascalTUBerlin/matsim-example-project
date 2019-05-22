package org.matsim.class2019.vorlesung2019_05_15;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup.StrategySettings;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scenario.ScenarioUtils;


public class RunMatsimMultipleModes {
	
	public static void main (String[] args) {
		
		String configfile = "./scenarios/equil/config.xml";
		
		Config config = ConfigUtils.loadConfig(configfile);
		
		config.controler().setOutputDirectory("./output");
		OverwriteFileSetting  abc  = OverwriteFileSetting.deleteDirectoryIfExists;
		config.controler().setOverwriteFileSetting( abc );
		config.controler().setLastIteration(1);
		
		StrategyConfigGroup.StrategySettings stratSets = new StrategyConfigGroup.StrategySettings();
		stratSets.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ChangeSingleTripMode);
		double probability = 0.1;
		stratSets.setWeight(probability);
		config.strategy().addStrategySettings(stratSets);
		config.plansCalcRoute();
		
		String[] modes = {"car","scooter"};
		config.changeMode().setModes(modes);
		
		
		
		
		Scenario scenario = ScenarioUtils.loadScenario(config);
		
		Controler controler = new Controler(scenario);
		
		controler.run();
		
		
	}

}
