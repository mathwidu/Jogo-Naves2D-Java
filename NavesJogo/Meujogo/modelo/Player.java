package Meujogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {
    private int x,y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private boolean isVisivel, isTurbo;
    private int vida;
    private List <Tiro> tiros;
    private Timer timer;

    public Player(){
        this.x =100;
        this.y =100;
        isVisivel = true;
        isTurbo = false;
        vida = 3;

        tiros = new ArrayList<Tiro>();

        timer = new Timer(5000, this);
        timer.setRepeats(false);

        load();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isTurbo) {
            isTurbo = false;
            load();
        }
    }



    public void load(){
        String caminho;
        if (isTurbo) {
            caminho = "/res/naveturbo.png";
        } else if (vida <= 1) {
            caminho = "/res/Player Nave/spaceship2Hit.png";
        } else if (vida == 2) {
            caminho = "/res/Player Nave/spaceship2Hitmed.png";
        } else {
            caminho = "/res/spaceship2.png";
        }
        ImageIcon referencia = new ImageIcon(getClass().getResource(caminho));
        imagem = referencia.getImage();
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update(){
        x += dx;
        y += dy;

        if (x < 0) {
            x = 0;
        } else if (x + largura > Fase.SCREEN_WIDTH) {
            x = Fase.SCREEN_WIDTH - largura;
        }

        if (y < 0) {
            y = 0;
        } else if (y + altura > Fase.SCREEN_HEIGHT) {
            y = Fase.SCREEN_HEIGHT - altura;
        }
    }

    public void tiroSimples(){
        this.tiros.add(new Tiro(x+largura, y + (altura/2)));
    }

    public void turbo(){
        isTurbo = true;
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/naveturbo.png"));
        imagem = referencia.getImage();
        timer.restart();
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, largura, altura);
    }

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_T){
            turbo();
        }

        if(codigo == KeyEvent.VK_SPACE){
            tiroSimples();
        }

        if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W){
            dy = -3;
        }

        if(codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S){
            dy = 3;
        }

        if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A){
            dx = -3;
        }

        if(codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D){
            dx = 3;
        }
    }

    public void keyRelease(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
        if(codigo == KeyEvent.VK_UP || codigo == KeyEvent.VK_W){
            dy = 0;
        }

        if(codigo == KeyEvent.VK_DOWN || codigo == KeyEvent.VK_S){
            dy = 0;
        }

        if(codigo == KeyEvent.VK_LEFT || codigo == KeyEvent.VK_A){
            dx = 0;
        }

        if(codigo == KeyEvent.VK_RIGHT || codigo == KeyEvent.VK_D){
            dx = 0;
        }
    }

    

    public boolean isVisivel() {
        return isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
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

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        if (!isTurbo) {
            load();
        }
    }

    public List<Tiro> getTiros() {
        return tiros;
    }

    public boolean isTurbo() {
        return isTurbo;
    }

    public void stopTurboTimer() {
        timer.stop();
    }



}
