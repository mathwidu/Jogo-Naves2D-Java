package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy4 {
    private Image imagem;
    private Image imagemNormal;
    private Image imagemHitmed;
    private Image imagemHit;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    private int vida;
    private int dy;

    private static final int VELOCIDADE = 7;
    private static final int VIDA_INICIAL = 4;

    public Enemy4(int x, int y) {
        this.x = x;
        this.y = y;
        this.dy = 0;
        isVisivel = true;
        vida = VIDA_INICIAL;
        load();
    }

    public void load() {
        imagemNormal = new ImageIcon(getClass().getResource("/res/Inimigos/enemy4.png")).getImage();
        imagemHitmed = new ImageIcon(getClass().getResource("/res/Inimigos/enemy4Hitmed.png")).getImage();
        imagemHit = new ImageIcon(getClass().getResource("/res/Inimigos/enemy4Hit.png")).getImage();
        imagem = imagemNormal;
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() {
        x -= VELOCIDADE;
        if (Math.random() < 0.05) {
            dy = (int) (Math.random() * 5 - 2);
        }
        y += dy;
        if (y < 0) {
            y = 0;
        } else if (y + altura > Fase.SCREEN_HEIGHT) {
            y = Fase.SCREEN_HEIGHT - altura;
        }
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
        } else if (vida <= VIDA_INICIAL - 2) {
            imagem = imagemHitmed;
        } else {
            imagem = imagemNormal;
        }
    }

    public static int getVidaInicial() {
        return VIDA_INICIAL;
    }
}
