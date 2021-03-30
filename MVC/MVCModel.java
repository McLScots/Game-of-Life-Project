import javax.swing.*;

public abstract class MVCModel extends  java.util.Observable{
    //notifyObservers()
    //model sends notification to view because of RunMVC: myModel.addObserver(myView)
    //myView then runs update()
    //
    //model Push - send counter as part of the message
    abstract void setValue(int value) //setValue()
    ;

    abstract void incrementValue() //incrementValue()
    ;

    abstract void resetCells()
            ;

    abstract void simStep();
}
