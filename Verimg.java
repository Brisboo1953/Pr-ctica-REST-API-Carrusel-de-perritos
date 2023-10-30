import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Verimg extends JFrame {
    private URL imgURL;

    public Verimg(URL u){
        super();
        imgURL = u;
    }

    public Verimg(String u){
        super();
        try{
            imgURL = new URL(u);
        }catch(MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
        initUI();
    }

    private void initUI(){
        Panel dpnl = new Panel(imgURL);

        createLayout(dpnl);
        setTitle("Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));
        gl.setVerticalGroup(gl.createParallelGroup().addComponent(arg[0]));
        pack();

    }
}
