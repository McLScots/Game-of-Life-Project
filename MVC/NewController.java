import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class NewController {
    private MVCModel myModel;
    private MVCView  myView;
    public int counter = 0;
    private PauseListener pause;
    private ResetListener reset;
    private CellListener cell;
    private Cell ce;
    private boolean simOn;
    private Timer timer;

    public NewController(MVCModel model, MVCView view) {
        this.myModel = model;
        this.myView  = view;
        this.pause = new PauseListener();
        this.reset = new ResetListener();
        this.cell = new CellListener();
        this.simOn = false;
        timer = new Timer();
    }

    class PauseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pause/Start");
            if (!simOn){
                timer = new Timer();
                timer.schedule(new TimerTaskSimulate(),0,500);
                simOn = true;
                myView.simOn(true);
            }
            else {
                timer.cancel();
                simOn = false;
                myView.simOn(false);
            }
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset");
            myModel.resetCells();
            myView.updateCells();
            myView.resetSec();
        }
    }

    public class CellListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            Object obj = e.getSource();
            if (obj instanceof Cell) {
                ce = (Cell) obj;
                System.out.println("X: "+ce.x);
                System.out.println("Y: "+ce.y);
                myModel.toggleCell(ce.x,ce.y);
                myView.toggleCell(ce.x,ce.y);
            }
            //myModel.turnOn(ce);
            System.out.println("cell");
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class TimerTaskSimulate extends TimerTask {
        @Override
        public void run() {
            myModel.simStep();
            myView.updateCells();
        }
    }

    public ActionListener getPauseListener(){
        return this.pause;
    }

    public ActionListener getResetListener(){
        return this.reset;
    }

    public MouseListener getCellListener(){
        return this.cell;
    }

    public void addModel(MVCModel m){
        System.out.println("Controller: adding model");
        this.myModel = m;
    } //addModel()

    public void addView(MVCView v){
        System.out.println("Controller: adding view");
        this.myView = v;
    } //addView()

    public void initModel(int x){
        myModel.setValue(x);
    } //initModel()
}
