package PMS.gui.com;

import javax.swing.*;
import java.awt.*;

public class DataInputDialog extends JDialog {

    // 数据存放属性，对子类也进行屏蔽，子类只能通过getInputData和setInputData访问
    private Object inputData = null; // 初始化为null

    // 在对话框结束前，子类需要在内部调用setInputData设置数据
    // 停止阻塞后，在调用者处，调用dialog.getInputData获取数据

    // 内部设置数据方法
    protected final void setInputData(Object inputData) {
        this.inputData = inputData;
    }
    // 公开获取数据方法
    public final Object getInputData() {
        return inputData;
    }

    // 自动生成的构造函数
    public DataInputDialog() {
    }

    public DataInputDialog(Frame owner) {
        super(owner);
    }

    public DataInputDialog(Frame owner, boolean modal) {
        super(owner, modal);
    }

    public DataInputDialog(Frame owner, String title) {
        super(owner, title);
    }

    public DataInputDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public DataInputDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public DataInputDialog(Dialog owner) {
        super(owner);
    }

    public DataInputDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    public DataInputDialog(Dialog owner, String title) {
        super(owner, title);
    }

    public DataInputDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public DataInputDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public DataInputDialog(Window owner) {
        super(owner);
    }

    public DataInputDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    public DataInputDialog(Window owner, String title) {
        super(owner, title);
    }

    public DataInputDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    public DataInputDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }
}
