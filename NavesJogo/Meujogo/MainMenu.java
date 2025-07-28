package Meujogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Meujogo.modelo.Fase;

public class MainMenu extends JPanel {

    public MainMenu(Container container) {
        setLayout(null);

        int largura = 200;
        int altura = 40;
        int x = Fase.SCREEN_WIDTH / 2 - largura / 2;
        int y = Fase.SCREEN_HEIGHT / 2 - 60;

        JButton iniciar = new JButton("Iniciar Jogo");
        iniciar.setBounds(x, y, largura, altura);
        add(iniciar);

        JButton configuracoes = new JButton("Configura\u00e7\u00f5es");
        configuracoes.setBounds(x, y + 50, largura, altura);
        add(configuracoes);

        JButton sair = new JButton("Sair");
        sair.setBounds(x, y + 100, largura, altura);
        add(sair);

        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.startGame();
            }
        });

        configuracoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.showSettings();
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
