package Meujogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Explosion {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean visivel;
    private long startTime;
    private static final int DURATION = 500; // ms

    public Explosion(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        visivel = true;
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/Efeitos/explosion.gif"));
        imagem = referencia.getImage().getScaledInstance(largura, altura, Image.SCALE_DEFAULT);
        startTime = System.currentTimeMillis();
    }

    public void update() {
        if (System.currentTimeMillis() - startTime > DURATION) {
            visivel = false;
        }
    }

    public boolean isVisivel() {
        return visivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
