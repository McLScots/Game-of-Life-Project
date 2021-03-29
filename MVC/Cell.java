import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel {
    private boolean flag = false;

    public Cell (){
        this.setPreferredSize(new Dimension(20, 20));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    public void changeState(){
        if (!flag){
            flag = true;
            this.setBackground(Color.YELLOW);
        }
        else {
            flag =false;
            this.setBackground(Color.WHITE);
        }
    }

    public boolean getState(){
        return flag;
    }
}
