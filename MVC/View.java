//View.java
//(C) Joseph Mack 2011, jmack (at) wm7d (dot) net, released under GPL v3 (or any later version)

//inspired by Joseph Bergin's MVC gui at http://csis.pace.edu/~bergin/mvc/mvcgui.html

//View is an Observer

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;	//for CloseListener()
import java.awt.event.WindowAdapter;	//for CloseListener()
import java.lang.Integer;		//int from Model is passed as an Integer
import java.time.Clock;
import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()


class View implements MVCView {

	//attributes as must be visible within class
	private JButton Start, Stop, Reset;
	private JLabel Image, Tittle, LivingC, Clock, Time, CellN;
	private JPanel Container;
	private JScrollPane Scroll;
	private Cell[][] Cells;

	//private Model model;		//Joe: Model is hardwired in, 
					//needed only if view initialises model (which we aren't doing)
	
	View() {
		System.out.println("View()");

		//frame in constructor and not an attribute as doesn't need to be visible to whole class
		Frame frame 	= new Frame("Game of Life");
		Image 	 = new JLabel();
		Tittle	 = new JLabel("Game of Life");
		LivingC	 = new JLabel("Cells:");
		Clock	 = new JLabel("000");
		Time	 = new JLabel("Time:");
		CellN	 = new JLabel("0");
		Start	 = new JButton("Start");
		Stop	 = new JButton("Stop");
		Reset	 = new JButton("Reset");
		Container= new JPanel();
		Scroll	 = new JScrollPane(Container);

		//JLabels
		Image.setIcon(new ImageIcon("Life.jpg"));
		Image.setSize(1280,100);
		Image.setLocation(0,0);
		Tittle.setFont(new Font(Tittle.getName(), Font.PLAIN, 20));
		Tittle.setSize(200,150);
		Tittle.setLocation(15,5);
		LivingC.setFont(new Font(LivingC.getName(), Font.PLAIN, 25));
		LivingC.setSize(100,50);
		LivingC.setLocation(1100,17);
		Time.setFont(new Font(Clock.getName(), Font.PLAIN, 25));
		Time.setSize(100,50);
		Time.setLocation(1100,50);
		Clock.setFont(new Font(Clock.getName(), Font.PLAIN, 25));
		Clock.setSize(50,50);
		Clock.setLocation(1170,50);
		CellN.setFont(new Font(LivingC.getName(), Font.PLAIN, 25));
		CellN.setSize(50,50);
		CellN.setLocation(1170,17);
		//JButtons
		Start.setSize(100,50);
		Start.setLocation(490,630);
		Start.setForeground(Color.WHITE); //Text color
		Start.setBackground(Color.BLUE);  //Background color
		Stop.setSize(100,50);
		Stop.setLocation(600,630);
		Reset.setSize(100,50);
		Reset.setLocation(380,630);
		//Others
		Container.setLayout(new GridLayout(100, 200));
		Scroll.setSize(1270,520);
		Scroll.setLocation(6,100);
		Scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		createCells();

		Container.revalidate();
		Container.repaint();

		frame.setLayout(null);
		frame.add(Tittle);
		frame.add(LivingC);
		frame.add(CellN);
		frame.add(Clock);
		frame.add(Time);
		frame.add(Image);
		frame.add(Start);
		//frame.add(Stop);
		frame.add(Reset);
		frame.add(Scroll);


		frame.setBackground(Color.GRAY);
		frame.addWindowListener(new CloseListener());
		frame.setSize(1280,720);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

	} //View()

	@Override
	public void createCells() {
		Cells = new Cell[100][200];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < Cells.length; j++) {
				Cells[i][j] = new Cell();
				Container.add(Cells[i][j]);
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

	public void addPause(ActionListener e){
		Start.addActionListener(e);
	}

	public void addReset(ActionListener e){
		Reset.addActionListener(e);
	}

	public void addCell(MouseListener e){
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < Cells.length; j++) {
				Cells[i][j].addMouseListener(e);
			}
		}
	}


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
