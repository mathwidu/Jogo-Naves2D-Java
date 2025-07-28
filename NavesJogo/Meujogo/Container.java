package Meujogo;
import Meujogo.modelo.Fase;
import javax.swing.JFrame;

public class Container extends JFrame {
    private Fase fase;

    public Container() {
        setTitle("meu jogo");
        setSize(Fase.SCREEN_WIDTH, Fase.SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);

        showMenu();

        setVisible(true);
    }

    public void showMenu() {
        getContentPane().removeAll();
        add(new MainMenu(this));
        revalidate();
        repaint();
    }

    public void startGame() {
        getContentPane().removeAll();
        fase = new Fase();
        add(fase);
        revalidate();
        repaint();
        fase.requestFocusInWindow();
    }

    public void showSettings() {
        getContentPane().removeAll();
        add(new SettingsPanel(this));
        revalidate();
        repaint();
    }



    public static void main(String[] args) {
        new Container();
    }

}