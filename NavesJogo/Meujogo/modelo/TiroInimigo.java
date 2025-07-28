package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class TiroInimigo {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean visivel;

    private static final int VELOCIDADE = 4;

    public TiroInimigo(int x, int y) {
        this.x = x;
        this.y = y;
        visivel = true;
        load();
    }

    private void load() {
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/Tiros/TiroInimigo.png"));
        imagem = referencia.getImage();
        largura = imagem.getWidth(null);
        altura = imagem.getHeight(null);
    }

    public void update() {
        this.x -= VELOCIDADE;
        if (this.x + largura < 0) {
            visivel = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
}
