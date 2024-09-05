package Meujogo;
import Meujogo.modelo.Fase;
import javax.swing.JFrame; 

public class Container extends JFrame {

    public Container() {
        add(new Fase());
        setTitle("meu jogo");
        setSize(1024, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    

    public static void main(String[] args) {
        new Container();
    }

}