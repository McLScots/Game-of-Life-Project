import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel {
    private boolean flag = false;
    public int x;
    public int y;

    public Cell (int x,int y){
        this.setPreferredSize(new Dimension(20, 20));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.setOpaque(true);
        this.x = x;
        this.y = y;
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
