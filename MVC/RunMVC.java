//RunMVC.java
//(C) Joseph Mack 2011, jmack (at) wm7d (dot) net, released under GPL v3 (or any later version)

public class RunMVC {

	//The order of instantiating the objects below will be important for some pairs of commands.
	//I haven't explored this in any detail, beyond that the order below works.

	private int start_value = 10;	//initialise model, which in turn initialises view

	public RunMVC() {

		//create Model and View
		MVCModel myModel = new Model();
		MVCView myView 	= new View();

		myView.registerModel(myModel);

		//tell Model about View. 
		myModel.addObserver(myView);
		/*	
			init model after view is instantiated and can show the status of the model
			(I later decided that only the controller should talk to the model
			and moved initialisation to the controller (see below).)
		*/
		//uncomment to directly initialise Model
		//myModel.setValue(start_value);	

		//create Controller. tell it about Model and View, initialise model
		NewController myController = new NewController(myModel, myView);

		//tell View about Controller
		myModel.addObserver(myView);
		myView.addPause(myController.getPauseListener());
		myView.addReset(myController.getResetListener());
		myView.addCell(myController.getCellListener());
		//and Model, 
		//this was only needed when the view inits the model
		//myView.addModel(myModel);

	} //RunMVC()

} //RunMVC
