import java.awt.event.*;

public class NewController {
    private MVCModel myModel;
    private MVCView  myView;
    public int counter = 0;
    private PauseListener pause;
    private ResetListener reset;
    private CellListener cell;

    public NewController(MVCModel model, MVCView view) {
        this.myModel = model;
        this.myView  = view;
        this.pause = new PauseListener();
        this.reset = new ResetListener();
        this.cell = new CellListener();

        //view.PauseAction(new PauseListener());
        //view.ResetAction(new ResetListener());
        //view.CellAction(new CellListener());
    }

    class PauseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pause");
        }
    }

    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset");
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
                Cell cb = (Cell) obj;
            }
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
