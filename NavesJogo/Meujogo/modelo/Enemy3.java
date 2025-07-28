package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy3 {
    private Image imagem;
    private Image imagemNormal;
    private Image imagemHitmed;
    private Image imagemHit;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    private int vida;
    private int step;

    private static final int VELOCIDADE = 4;
    private static final int VIDA_INICIAL = 3;

    public Enemy3(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
        vida = VIDA_INICIAL;
        step = 0;
        load();
    }

    public void load() {
        imagemNormal = new ImageIcon(getClass().getResource("/res/Inimigos/enemy3.png")).getImage();
        imagemHitmed = new ImageIcon(getClass().getResource("/res/Inimigos/enemy3Hitmed.png")).getImage();
        imagemHit = new ImageIcon(getClass().getResource("/res/Inimigos/enemy3Hit.png")).getImage();
        imagem = imagemNormal;
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() {
        x -= VELOCIDADE;
        y += (int) (Math.sin(Math.toRadians(step)) * 3);
        step += 10;
        if (x + largura < 0) {
            isVisivel = false;
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

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean visivel) {
        this.isVisivel = visivel;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        updateImagem();
    }

    private void updateImagem() {
        if (vida <= 1) {
            imagem = imagemHit;
        } else if (vida <= VIDA_INICIAL - 1) {
            imagem = imagemHitmed;
        } else {
            imagem = imagemNormal;
        }
    }

    public static int getVidaInicial() {
        return VIDA_INICIAL;
    }
}
