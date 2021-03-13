import java.awt.event.ActionListener;
import java.util.Observable;

public interface MVCView extends java.util.Observer {
    void createCells();

    // Called from the Model
    void update(Observable obs, Object obj) //update()
    ;

    void addController(ActionListener controller) //addController()
    ;
}
