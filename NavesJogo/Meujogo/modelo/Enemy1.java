package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    private int vida;
    
    //private static final int LARGURA = 938;
    private static final int VELOCIDADE = 4;
    private static final int VIDA_INICIAL = 3;

    public Enemy1 (int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = true;
        vida = VIDA_INICIAL;

        load();
    }

    public void load(){
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/enemy1.png"));
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }
    
    public void update(){
        this.x -= VELOCIDADE;

        if(this.x + largura < 0){
            isVisivel = false;
        }
    }

    public Rectangle getBounds(){
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
        return imagem;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public static int getVidaInicial() {
        return VIDA_INICIAL;
    }


}
