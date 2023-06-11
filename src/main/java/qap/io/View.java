package qap.io;

import javax.swing.*;

public interface View {

    ClassLoader CLASS_LOADER = View.class.getClassLoader();
    String APP_NAME = "QAP";
    Icon ICON1 = new ImageIcon(CLASS_LOADER.getResource("icon/icon1.png"));
    Icon ICON2 = new ImageIcon(CLASS_LOADER.getResource("icon/icon2.png"));
}
