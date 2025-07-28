package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.swing.ImageIcon;

public class Boss {
    private Image imagem;
    private Image imagemHit;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    private int vida;
    private boolean hit;
    private long hitTime;
    private long lastShotTime;
    private List<TiroInimigo> tiros;

    private static final int VELOCIDADE = 2;
    private static final int VIDA_INICIAL = 20;
    private static final long SHOT_INTERVAL = 1500;
    private static final long HIT_DISPLAY = 200;

    public Boss(int x, int y) {
        this.x = x;
        this.y = y;
        isVisivel = true;
        vida = VIDA_INICIAL;
        hit = false;
        tiros = new ArrayList<TiroInimigo>();
        lastShotTime = System.currentTimeMillis();

        load();
    }

    public void load() {
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/Inimigos/Chefao.gif"));
        imagem = referencia.getImage();
        ImageIcon referenciaHit = new ImageIcon(getClass().getResource("/res/Inimigos/ChefaoHit.png"));
        imagemHit = referenciaHit.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }

    public void update() {
        if (x > Fase.SCREEN_WIDTH - largura - 50) {
            x -= VELOCIDADE;
        } else {
            if (System.currentTimeMillis() - lastShotTime > SHOT_INTERVAL) {
                tiros.add(new TiroInimigo(x, y + altura / 2));
                lastShotTime = System.currentTimeMillis();
            }
        }

        for (Iterator<TiroInimigo> it = tiros.iterator(); it.hasNext();) {
            TiroInimigo t = it.next();
            if (t.isVisivel()) {
                t.update();
            } else {
                it.remove();
            }
        }

        if (hit && System.currentTimeMillis() - hitTime > HIT_DISPLAY) {
            hit = false;
        }

        if (vida <= 0) {
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

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public static int getVelocidade() {
        return VELOCIDADE;
    }

    public Image getImagem() {
        return hit ? imagemHit : imagem;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        hit = true;
        hitTime = System.currentTimeMillis();
    }

    public List<TiroInimigo> getTiros() {
        return tiros;
    }

    public void damage(int amount) {
        vida -= amount;
        hit = true;
        hitTime = System.currentTimeMillis();
    }

    public static int getVidaInicial() {
        return VIDA_INICIAL;
    }
}