//Model.java
//(C) Joseph Mack 2011, jmack (at) wm7d (dot) net, released under GPL v3 (or any later version)

//inspired by Joseph Bergin's MVC gui at http://csis.pace.edu/~bergin/mvc/mvcgui.html

//Model holds an int counter (that's all it is).
//Model is an Observable
//Model doesn't know about View or Controller

import javax.swing.*;

public class Model extends MVCModel {
	
	private int counter;	//primitive, automatically initialised to 0
	// array for storing cell states
	public boolean arr[][] = new boolean[100][200];
	long startTime = System.currentTimeMillis();

	public Model(){

		System.out.println("Model()");

		resetCells();

		/**
		Problem initialising both model and view:

		On a car you set the speedometer (view) to 0 when the car (model) is stationary.
		In some circles, this is called calibrating the readout instrument.
		In this MVC example, you would need two separate pieces of initialisation code,
			in the model and in the view. If you changed the initialisation value in one
			you'd have to remember (or know) to change the initialisation value in the other.
			A recipe for disaster.

		Alternately, when instantiating model, you could run  

		setValue(0);

		as part of the constructor, sending a message to the view. 
		This requires the view to be instantiated before the model,
		otherwise the message will be send to null (the unitialised value for view).
		This isn't a particularly onerous requirement, and is possibly a reasonable approach.

		Alternately, have RunMVC tell the view to intialise the model.
		The requires the view to have a reference to the model.
		This seemed an unneccesary complication.

		I decided instead in RunMVC, to instantiate model, view and controller, make all the connections,
		then since the Controller already has a reference to the model (which it uses to alter the status of the model),
		to initialise the model from the controller and have the model automatically update the view.
		*/



	} //Model()

	//uncomment this if View is using Model Pull to get the counter
	//not needed if getting counter from notifyObservers()
	//public int getValue(){return counter;}

	//notifyObservers()
	//model sends notification to view because of RunMVC: myModel.addObserver(myView)
	//myView then runs update()
	//
	//model Push - send counter as part of the message

	// loop simStep
	public void gameLoop(){
		long lastStepTime = System.currentTimeMillis();
		while (true) {
			if ((System.currentTimeMillis()-lastStepTime)/1000 >= 1){
				simStep();
				lastStepTime = System.currentTimeMillis();
			}
		}
	}

	//one step in the simulation
	//game rules & stuff go here
	public void simStep(){
		int neighborCount[][] = new int[100][200];

		//loop over each cell and count its alive neighbors
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++){
				int aliveCount = 0;

				//instead of writing 8 if conditions, loop through the neighbors
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						// if x & y are 1 then it is itself
						if (x != 1 && y != 1 && arr[row+y-1][col+x-1]) {
							aliveCount += 1;
						}
					}
				}

				neighborCount[row][col] = aliveCount;
			}
		}

		//loop over each cell and update its status based on the rules & alive neighbors
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				//if alive neighbors less than 2, then dead
				if (neighborCount[row][col] < 2) {
					arr[row][col] = false;
				}
				//if alive neighbors 2 or 3, then alive
				else if (neighborCount[row][col] == 2 || neighborCount[row][col] == 3){
					arr[row][col] = true;
				}
				//if alive neighbors more than 3, then dead
				else {
					arr[row][col] = false;
				}
			}
		}
	}

	public void fillCell(int x, int y) {
		if (arr[x][y] == false) {
			arr[x][y] = true;
		}
		else {
			arr[x][y] = false;
		}
	}

	public void resetCells() {
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) arr[row][col] = false;
		}

	}

	public long getDuration(){
		long elapsedTime = (System.currentTimeMillis() - startTime)/1000;
		return elapsedTime;
	}

	@Override
	public void setValue(int value) {

		this.counter = value;
		System.out.println("Model init: counter = " + counter);
		setChanged();
		//model Push - send counter as part of the message
		notifyObservers(counter);
		//if using Model Pull, then can use notifyObservers()
		//notifyObservers()

	} //setValue()
	
	@Override
	public void incrementValue() {

		++counter;
		System.out.println("Model     : counter = " + counter);
		setChanged();
		//model Push - send counter as part of the message
		notifyObservers(counter);
		//if using Model Pull, then can use notifyObservers()
		//notifyObservers()

	} //incrementValue()

} //Model
