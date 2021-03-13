//View.java
//(C) Joseph Mack 2011, jmack (at) wm7d (dot) net, released under GPL v3 (or any later version)

//inspired by Joseph Bergin's MVC gui at http://csis.pace.edu/~bergin/mvc/mvcgui.html

//View is an Observer

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()


class View implements MVCView {

	//attributes as must be visible within class
	private Button Start, Stop, Reset;
	private JLabel Image, Tittle;
	private JPanel container;
	private JScrollPane scroll;
	private JLabel[][] cells;

	//private Model model;		//Joe: Model is hardwired in, 
					//needed only if view initialises model (which we aren't doing)
	
	View() {
		System.out.println("View()");

		//frame in constructor and not an attribute as doesn't need to be visible to whole class
		Frame frame 	= new Frame("Game of Life");
		Image 	 = new JLabel();
		Tittle	 = new JLabel("Game of Life");
		Start	 = new Button("Start");
		Stop	 = new Button("Stop");
		Reset	 = new Button("Reset");
		container= new JPanel();
		scroll	 = new JScrollPane(container);

		Image.setIcon(new ImageIcon("Life.jpg"));
		Image.setSize(1280,100);
		Image.setLocation(0,0);
		Tittle.setFont(new Font(Tittle.getName(), Font.PLAIN, 20));
		Tittle.setSize(200,150);
		Tittle.setLocation(15,5);
		Start.setSize(100,50);
		Start.setLocation(490,630);
		Start.setForeground(Color.WHITE); //Text color
		Start.setBackground(Color.BLUE);  //Background color
		Stop.setSize(100,50);
		Stop.setLocation(600,630);
		Reset.setSize(100,50);
		Reset.setLocation(380,630);
		container.setLayout(new GridLayout(100, 200));
		scroll.setSize(1270,520);
		scroll.setLocation(6,100);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		createCells();

		container.revalidate();
		container.repaint();

		frame.setLayout(null);
		frame.add(Tittle);
		frame.add(Image);
		frame.add(Start);
		frame.add(Stop);
		frame.add(Reset);
		frame.add(scroll);


		frame.setBackground(Color.GRAY);
		frame.addWindowListener(new CloseListener());
		frame.setSize(1280,720);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

	} //View()

	@Override
	public void createCells() {
		int i;
		cells = new JLabel[100][200];
		for (i = 0; i < 100; i++) {
			for (int j = 0; j < cells.length; j++) {
				//cells[i][j] = new JLabel(i+"");
				cells[i][j] = new JLabel();
				cells[i][j].setOpaque(true);
				cells[i][j].setBackground(Color.white);
				cells[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				cells[i][j].setPreferredSize(new Dimension(20, 20));
				container.add(cells[i][j]);
			}
		} // End for
	}

	// Called from the Model
    	@Override
		public void update(Observable obs, Object obj) {

		//who called us and what did they send?
		//System.out.println ("View      : Observable is " + obs.getClass() + ", object passed is " + obj.getClass());

		//model Pull 
		//ignore obj and ask model for value, 
		//to do this, the view has to know about the model (which I decided I didn't want to do)
		//uncomment next line to do Model Pull
    		//myTextField.setText("" + model.getValue());

		//model Push 
		//parse obj


    	} //update()

	@Override
	public void addController(ActionListener controller){
		System.out.println("View      : adding controller");
		Start.addActionListener(controller);	//need instance of controller before can add it as a listener
	} //addController()

	//to initialise TextField

    	
	//uncomment to allow controller to use view to initialise model	
	//public void addModel(Model m){
	//	System.out.println("View      : adding model");
	//	this.model = m;
	//} //addModel()

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		} //windowClosing()
	} //CloseListener

} //View
