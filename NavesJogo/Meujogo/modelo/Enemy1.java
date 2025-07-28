package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {
    private Image imagem;
    private int x, y;
    private int largura, altura;
    private boolean isVisivel;
    
    //private static final int LARGURA = 938;
    private static final int VELOCIDADE = 4;

    public Enemy1 (int x, int y){
        this.x = x;
        this.y = y;
        isVisivel = true;
    }

    public void load(){
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/enemy1.png"));
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
    }
    
    public void update(){
        this.x -= VELOCIDADE;
       /*  if(this.x > LARGURA){
            isVisivel = false;
        }*/
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

    
}
