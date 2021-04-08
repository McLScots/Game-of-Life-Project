import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Observable;

public interface MVCView extends java.util.Observer {
    void createCells();

    // Called from the Model
    void update(Observable obs, Object obj) //update()
    ;

    void addPause(ActionListener pauseListener);

    void addReset(ActionListener resetListener);

    void addCell(MouseListener cellListener);

    void toggleCell(int x,int y);

    void registerModel(MVCModel model);

    void updateCells();

    void simOn(boolean status);

    void resetSec();
}
