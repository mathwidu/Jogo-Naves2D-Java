package Meujogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Meujogo.modelo.Fase;

public class SettingsPanel extends JPanel {
    public SettingsPanel(Container container) {
        setLayout(null);

        int largura = 200;
        int altura = 40;
        int x = Fase.SCREEN_WIDTH / 2 - largura / 2;
        int y = Fase.SCREEN_HEIGHT / 2 - 100;

        JLabel volumeLabel = new JLabel("Volume:");
        volumeLabel.setBounds(x, y, largura, altura);
        add(volumeLabel);

        JSlider volume = new JSlider(0, 100, 50);
        volume.setBounds(x, y + 30, largura, altura);
        add(volume);

        JLabel difficultyLabel = new JLabel("Dificuldade:");
        difficultyLabel.setBounds(x, y + 70, largura, altura);
        add(difficultyLabel);

        String[] diffLevels = {"F\u00e1cil", "Normal", "Dif\u00edcil"};
        JComboBox<String> difficulty = new JComboBox<>(diffLevels);
        difficulty.setBounds(x, y + 100, largura, altura);
        add(difficulty);

        JButton keysButton = new JButton("Teclas");
        keysButton.setBounds(x, y + 150, largura, altura);
        keysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(container,
                        "Setas ou WASD: mover\nEspa\u00e7o: atirar\nT: turbo");
            }
        });
        add(keysButton);

        JButton voltar = new JButton("Voltar");
        voltar.setBounds(x, y + 200, largura, altura);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.showMenu();
            }
        });
        add(voltar);
    }
}
