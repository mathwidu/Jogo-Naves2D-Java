package Meujogo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Meujogo.modelo.Fase;

public class GameOverPanel extends JPanel {
    private final Fase fase;
    private final Image imagem;

    public GameOverPanel(Fase fase) {
        this.fase = fase;
        setOpaque(false);
        setLayout(null);

        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/fimdejogo.png"));
        this.imagem = referencia.getImage();

        JButton reiniciar = new JButton("Reiniciar");
        reiniciar.setBounds(Fase.SCREEN_WIDTH / 2 - 60, Fase.SCREEN_HEIGHT / 2 + 40, 120, 30);
        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameOverPanel.this.setVisible(false);
                fase.reiniciarJogoPublic();
                fase.requestFocusInWindow();
            }
        });
        add(reiniciar);
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagem, 0, 0, null);
    }
}
