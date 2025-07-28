package Meujogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Fase extends JPanel implements ActionListener{
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 728;
    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Enemy1> enemy1;
    private List<Stars> stars;
    private boolean emJogo;

    public Fase(){
        setFocusable(true);
        setDoubleBuffered(true);
        
        ImageIcon referencia = new ImageIcon(getClass().getResource("/res/Background.png"));
        fundo = referencia.getImage();
        player = new Player();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();

        inicializaInimigos();
        inicializaEstrelas();
        emJogo = true;
    }

    public void inicializaInimigos(){
        int cordenadas [] = new int [40];
        enemy1 = new ArrayList<Enemy1>();

        for(int i =0; i< cordenadas.length; i++){
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x, y));
        }
    }

    public void inicializaEstrelas(){
        int cordenadas [] = new int [100];
        stars = new ArrayList<Stars>();
        for(int i =0; i< cordenadas.length; i++){
            int x = (int)(Math.random() * SCREEN_WIDTH);
            int y = (int)(Math.random() * SCREEN_HEIGHT);
            stars.add(new Stars(x, y));
        }
    }




    public void paint( Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo == true) {
        graficos.drawImage(fundo, 0, 0, null);

        for(int p = 0; p < stars.size(); p++){
            Stars q = stars.get(p);
            graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
        }

        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

        List<Tiro> tiros = player.getTiros();

        for(int i = 0; i < tiros.size(); i++){
            Tiro m = tiros.get(i);
            graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
        }

        for(int o = 0; o < enemy1.size(); o++){
            Enemy1 in = enemy1.get(o);
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
        }

        } else{
            ImageIcon fimJogo = new ImageIcon(getClass().getResource("/res/fimdejogo.png"));
            graficos.drawImage(fimJogo.getImage(), 0, 0, null);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        if (player.isTurbo() == true) {
            timer.setDelay(2);
        } else {
            timer.setDelay(5);
        }

        for(int p = 0; p < stars.size(); p++){
            Stars on = stars.get(p);
                if(on.isVisivel()){
                    on.update();
                } else {
                    stars.remove(p);
                }
        }

        List<Tiro> tiros = player.getTiros();
        for(int i = 0; i < tiros.size(); i++){
            Tiro m = tiros.get(i);
            if(m.isVisivel()){
                m.update();
            } else{
                tiros.remove(i);
            }
        }

        for(int o = 0; o < enemy1.size(); o++){
            Enemy1 in = enemy1.get(o);
            if(in.isVisivel()){
                in.update();
            } else{
                enemy1.remove(o);
            }
        }

        checarColisoes();

        repaint();
    }

    public void checarColisoes() {
        Rectangle formaNava = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaTiro;

        for(int j = 0; j < enemy1.size(); j++){
            Enemy1 tempEnemy1 = enemy1.get(j);
            formaEnemy1 = tempEnemy1.getBounds();
                if(formaNava.intersects(formaEnemy1)){
                    player.setVisivel(false);
                    tempEnemy1.setVisivel(false);
                    emJogo = false;
                }
        }

        List<Tiro> tiros = player.getTiros();
        for(int p = 0; p < tiros.size(); p++){
            Tiro tempTiro = tiros.get(p);
            formaTiro = tempTiro.getBounds();
            for (int m = 0; m < enemy1.size(); m++){
                Enemy1 tempEnemy1 = enemy1.get(m);
                formaEnemy1 = tempEnemy1.getBounds();
                if(formaTiro.intersects(formaEnemy1)){
                    tempEnemy1.setVisivel(false);
                    tempTiro.setVisivel(false);
                }
            }
        } 
    }

    private class TecladoAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent tecla){
            player.keyRelease(tecla);
        }
    }
    
}
