package org.matsim.class2019.vorlesung2019_05_15;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup.ModeParams;
import org.matsim.core.config.groups.PlansCalcRouteConfigGroup.ModeRoutingParams;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.config.groups.StrategyConfigGroup.StrategySettings;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule;
import org.matsim.core.replanning.strategies.DefaultPlanStrategiesModule.DefaultStrategy;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.scenario.ScenarioUtils;


public class RunMatsimMultipleModes {
	
	public static void main (String[] args) {
		
		String configfile = "./scenarios/equil/config.xml";
		
		Config config = ConfigUtils.loadConfig(configfile);
		
		config.controler().setOutputDirectory("./output");
		OverwriteFileSetting  abc  = OverwriteFileSetting.deleteDirectoryIfExists;
		config.controler().setOverwriteFileSetting( abc );
		config.controler().setLastIteration(10);
		
		{
		StrategyConfigGroup.StrategySettings stratSets = new StrategyConfigGroup.StrategySettings();
		stratSets.setStrategyName(DefaultPlanStrategiesModule.DefaultStrategy.ChangeSingleTripMode);
		double probability = 1.0;
		stratSets.setWeight(probability);
		config.strategy().addStrategySettings(stratSets);
//		config.plansCalcRoute();
		
		String[] modes = {"car","scooter"};
		config.changeMode().setModes(modes);
		}	
		
		{
		ModeRoutingParams pars = new ModeRoutingParams();
		String mode = "scooter";
		pars.setMode(mode);
		Double teleportedModeFreespeedFactor = 1.;
		pars.setTeleportedModeFreespeedFactor(teleportedModeFreespeedFactor);
		config.plansCalcRoute().addModeRoutingParams(pars );
		}
		
		{
			ModeParams params = new ModeParams("scooter");
			params.setMarginalUtilityOfTraveling(0.);
			config.planCalcScore().addModeParams(params);
		}
		
		
		
		Scenario scenario = ScenarioUtils.loadScenario(config);
		
		Controler controler = new Controler(scenario);
		
		controler.run();
		
		
	}

}
