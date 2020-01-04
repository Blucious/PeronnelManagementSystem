package PMS;

import PMS.gui.LoginFrame;

import java.awt.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args)
            throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // 设置Windows界面风格
//        UIManager.setLookAndFeel(
//                UIManager.getSystemLookAndFeelClassName());
        // 设置Nimbus界面风格
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        Frame f = new LoginFrame();
        f.setVisible(true);

    }
}



