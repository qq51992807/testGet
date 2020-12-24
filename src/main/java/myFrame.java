import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class myFrame extends JFrame{
    JLabel label = new JLabel("商品id");

    //创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
    JTextField textField = new JTextField(16);
    JButton button = new JButton("开始监控");
    JButton button2 = new JButton("停止监控");
    JTextArea jTextArea=new JTextArea("查询结果",200,50);
    JScrollPane jsp = new JScrollPane(jTextArea);
    getSku getSku2=new getSku();
    Thread mThread1=new Thread(getSku2,"线程1");
    //构造函数
    public myFrame(String title)
    {
        //继承父类，
        super(title);

        //内容面板
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        //添加控件
        contentPane.add(label);
        contentPane.add(textField);
        contentPane.add(button);
       contentPane.add(button2);
       contentPane.add(jTextArea);

        //按钮点击处理 lambda表达式
        button.addActionListener((e) -> {
            onButtonOk();
        });
        button2.addActionListener((e) -> {
            mThread1.stop();
        });

    }

    //事件处理
    private void onButtonOk()
    {
        String str = textField.getText();//获取输入内容
        //判断是否输入了
        if(str.equals(""))
        {
            Object[] options = { "OK ", "CANCEL " };
            JOptionPane.showOptionDialog(null, "您还没有输入 ", "提示", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null, options, options[0]);
        }
        else{
            //            JOptionPane.showMessageDialog(this,"您输入了：" + str);

            mThread1.start();
            System.out.println(getSku.skuMessage);
            jTextArea.setText( getSku2.getSkuMessage());

        }



    }


}