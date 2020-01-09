package PMS;

import PMS.gui.LoginFrame;

import java.awt.*;
import javax.swing.*;

public class PMSLauncher {
    public static void main(String[] args)
            throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {

        // 设置UI风格
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        // 启动主窗口
        Frame f = new LoginFrame();
        f.setVisible(true);

    }
}



