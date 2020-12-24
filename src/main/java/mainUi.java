import javax.swing.*;
import java.awt.*;

public class mainUi  extends Frame {

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                createGUI();
            }


        });

    }
    private static void createGUI()
    {
        //创建一个窗口，创建一个窗口
        myFrame frame = new myFrame("监控");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口大小
        frame.setSize(600, 400);

        //显示窗口
        frame.setVisible(true);
    }


}
