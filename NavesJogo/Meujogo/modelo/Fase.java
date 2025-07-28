package Meujogo.modelo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Meujogo.GameOverPanel;
import Meujogo.modelo.Enemy2;
import Meujogo.modelo.Enemy3;
import Meujogo.modelo.Enemy4;

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
    private List<Enemy2> enemy2;
    private List<Enemy3> enemy3;
    private List<Enemy4> enemy4;
    private List<Stars> stars;
    private boolean emJogo;
    private GameOverPanel gameOverPanel;

    private void reiniciarJogo(){
        if (player != null) {
            player.stopTurboTimer();
        }
        player = new Player();
        inicializaInimigos();
        inicializaEstrelas();
        emJogo = true;
        requestFocusInWindow();
        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }
    }

    // Called by GameOverPanel to restart the game
    public void reiniciarJogoPublic() {
        reiniciarJogo();
    }

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

        setLayout(null);
        gameOverPanel = new GameOverPanel(this);
        gameOverPanel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(gameOverPanel);
    }

    public void inicializaInimigos(){
        int quantidade = 10;
        enemy1 = new ArrayList<>();
        enemy2 = new ArrayList<>();
        enemy3 = new ArrayList<>();
        enemy4 = new ArrayList<>();

        for(int i =0; i< quantidade; i++){
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x, y));
            x = (int)(Math.random() * 8000+1024);
            y = (int)(Math.random() * 650+30);
            enemy2.add(new Enemy2(x, y));
            x = (int)(Math.random() * 8000+1024);
            y = (int)(Math.random() * 650+30);
            enemy3.add(new Enemy3(x, y));
            x = (int)(Math.random() * 8000+1024);
            y = (int)(Math.random() * 650+30);
            enemy4.add(new Enemy4(x, y));
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




    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo == true) {
        graficos.drawImage(fundo, 0, 0, null);

        for(int p = 0; p < stars.size(); p++){
            Stars q = stars.get(p);
            graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
        }

        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
        graficos.setColor(Color.WHITE);
        graficos.drawString("Vida: " + player.getVida(), player.getX(), player.getY() - 10);

        List<Tiro> tiros = player.getTiros();

        for(int i = 0; i < tiros.size(); i++){
            Tiro m = tiros.get(i);
            graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
        }

        for(int o = 0; o < enemy1.size(); o++){
            Enemy1 in = enemy1.get(o);
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            int barWidth = 30;
            int barHeight = 5;
            int lifeWidth = (int)((in.getVida() / (float)Enemy1.getVidaInicial()) * barWidth);
            graficos.setColor(Color.RED);
            graficos.fillRect(in.getX(), in.getY() - 7, barWidth, barHeight);
            graficos.setColor(Color.GREEN);
            graficos.fillRect(in.getX(), in.getY() - 7, lifeWidth, barHeight);
            graficos.setColor(Color.WHITE);
        }

        for(int o = 0; o < enemy2.size(); o++){
            Enemy2 in = enemy2.get(o);
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            int barWidth = 30;
            int barHeight = 5;
            int lifeWidth = (int)((in.getVida() / (float)Enemy2.getVidaInicial()) * barWidth);
            graficos.setColor(Color.RED);
            graficos.fillRect(in.getX(), in.getY() - 7, barWidth, barHeight);
            graficos.setColor(Color.GREEN);
            graficos.fillRect(in.getX(), in.getY() - 7, lifeWidth, barHeight);
            graficos.setColor(Color.WHITE);
        }

        for(int o = 0; o < enemy3.size(); o++){
            Enemy3 in = enemy3.get(o);
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            int barWidth = 30;
            int barHeight = 5;
            int lifeWidth = (int)((in.getVida() / (float)Enemy3.getVidaInicial()) * barWidth);
            graficos.setColor(Color.RED);
            graficos.fillRect(in.getX(), in.getY() - 7, barWidth, barHeight);
            graficos.setColor(Color.GREEN);
            graficos.fillRect(in.getX(), in.getY() - 7, lifeWidth, barHeight);
            graficos.setColor(Color.WHITE);
        }

        for(int o = 0; o < enemy4.size(); o++){
            Enemy4 in = enemy4.get(o);
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            int barWidth = 30;
            int barHeight = 5;
            int lifeWidth = (int)((in.getVida() / (float)Enemy4.getVidaInicial()) * barWidth);
            graficos.setColor(Color.RED);
            graficos.fillRect(in.getX(), in.getY() - 7, barWidth, barHeight);
            graficos.setColor(Color.GREEN);
            graficos.fillRect(in.getX(), in.getY() - 7, lifeWidth, barHeight);
            graficos.setColor(Color.WHITE);
        }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        if (player.isTurbo() == true) {
            timer.setDelay(2);
        } else {
            timer.setDelay(5);
        }

        for (Iterator<Stars> it = stars.iterator(); it.hasNext();) {
            Stars on = it.next();
            if (on.isVisivel()) {
                on.update();
            } else {
                it.remove();
            }
        }

        List<Tiro> tiros = player.getTiros();
        for (Iterator<Tiro> it = tiros.iterator(); it.hasNext();) {
            Tiro m = it.next();
            if (m.isVisivel()) {
                m.update();
            } else {
                it.remove();
            }
        }

        List<Enemy1> novos = new ArrayList<>();
        List<Enemy2> novos2 = new ArrayList<>();
        List<Enemy3> novos3 = new ArrayList<>();
        List<Enemy4> novos4 = new ArrayList<>();
        for (Iterator<Enemy1> it = enemy1.iterator(); it.hasNext();) {
            Enemy1 in = it.next();
            if (in.isVisivel()) {
                in.update();
            } else {
                it.remove();
                if (Math.random() < 0.5) {
                    int x = SCREEN_WIDTH + (int) (Math.random() * 8000);
                    int y = (int) (Math.random() * 650 + 30);
                    novos.add(new Enemy1(x, y));
                }
            }
        }
        enemy1.addAll(novos);

        for (Iterator<Enemy2> it = enemy2.iterator(); it.hasNext();) {
            Enemy2 in = it.next();
            if (in.isVisivel()) {
                in.update();
            } else {
                it.remove();
                if (Math.random() < 0.5) {
                    int x = SCREEN_WIDTH + (int) (Math.random() * 8000);
                    int y = (int) (Math.random() * 650 + 30);
                    novos2.add(new Enemy2(x, y));
                }
            }
        }
        enemy2.addAll(novos2);

        for (Iterator<Enemy3> it = enemy3.iterator(); it.hasNext();) {
            Enemy3 in = it.next();
            if (in.isVisivel()) {
                in.update();
            } else {
                it.remove();
                if (Math.random() < 0.5) {
                    int x = SCREEN_WIDTH + (int) (Math.random() * 8000);
                    int y = (int) (Math.random() * 650 + 30);
                    novos3.add(new Enemy3(x, y));
                }
            }
        }
        enemy3.addAll(novos3);

        for (Iterator<Enemy4> it = enemy4.iterator(); it.hasNext();) {
            Enemy4 in = it.next();
            if (in.isVisivel()) {
                in.update();
            } else {
                it.remove();
                if (Math.random() < 0.5) {
                    int x = SCREEN_WIDTH + (int) (Math.random() * 8000);
                    int y = (int) (Math.random() * 650 + 30);
                    novos4.add(new Enemy4(x, y));
                }
            }
        }
        enemy4.addAll(novos4);

        checarColisoes();

        repaint();
    }

    public void checarColisoes() {
        Rectangle formaNava = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaEnemy2;
        Rectangle formaEnemy3;
        Rectangle formaEnemy4;
        Rectangle formaTiro;

        for(int j = 0; j < enemy1.size(); j++){
            Enemy1 tempEnemy1 = enemy1.get(j);
            formaEnemy1 = tempEnemy1.getBounds();
                if(formaNava.intersects(formaEnemy1)){
                    tempEnemy1.setVisivel(false);
                    player.setVida(player.getVida() - 1);
                    if(player.getVida() <= 0){
                        player.setVisivel(false);
                        emJogo = false;
                        gameOverPanel.setVisible(true);
                    }
                }
        }

        for(int j = 0; j < enemy2.size(); j++){
            Enemy2 tempEnemy = enemy2.get(j);
            formaEnemy2 = tempEnemy.getBounds();
            if(formaNava.intersects(formaEnemy2)){
                tempEnemy.setVisivel(false);
                player.setVida(player.getVida() - 1);
                if(player.getVida() <= 0){
                    player.setVisivel(false);
                    emJogo = false;
                    gameOverPanel.setVisible(true);
                }
            }
        }

        for(int j = 0; j < enemy3.size(); j++){
            Enemy3 tempEnemy = enemy3.get(j);
            formaEnemy3 = tempEnemy.getBounds();
            if(formaNava.intersects(formaEnemy3)){
                tempEnemy.setVisivel(false);
                player.setVida(player.getVida() - 1);
                if(player.getVida() <= 0){
                    player.setVisivel(false);
                    emJogo = false;
                    gameOverPanel.setVisible(true);
                }
            }
        }

        for(int j = 0; j < enemy4.size(); j++){
            Enemy4 tempEnemy = enemy4.get(j);
            formaEnemy4 = tempEnemy.getBounds();
            if(formaNava.intersects(formaEnemy4)){
                tempEnemy.setVisivel(false);
                player.setVida(player.getVida() - 1);
                if(player.getVida() <= 0){
                    player.setVisivel(false);
                    emJogo = false;
                    gameOverPanel.setVisible(true);
                }
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
                    tempEnemy1.setVida(tempEnemy1.getVida() - 1);
                    tempTiro.setVisivel(false);
                    if(tempEnemy1.getVida() <= 0){
                        tempEnemy1.setVisivel(false);
                    }
                }
            }
            for (int m = 0; m < enemy2.size(); m++){
                Enemy2 tempEnemy = enemy2.get(m);
                formaEnemy2 = tempEnemy.getBounds();
                if(formaTiro.intersects(formaEnemy2)){
                    tempEnemy.setVida(tempEnemy.getVida() - 1);
                    tempTiro.setVisivel(false);
                    if(tempEnemy.getVida() <= 0){
                        tempEnemy.setVisivel(false);
                    }
                }
            }
            for (int m = 0; m < enemy3.size(); m++){
                Enemy3 tempEnemy = enemy3.get(m);
                formaEnemy3 = tempEnemy.getBounds();
                if(formaTiro.intersects(formaEnemy3)){
                    tempEnemy.setVida(tempEnemy.getVida() - 1);
                    tempTiro.setVisivel(false);
                    if(tempEnemy.getVida() <= 0){
                        tempEnemy.setVisivel(false);
                    }
                }
            }
            for (int m = 0; m < enemy4.size(); m++){
                Enemy4 tempEnemy = enemy4.get(m);
                formaEnemy4 = tempEnemy.getBounds();
                if(formaTiro.intersects(formaEnemy4)){
                    tempEnemy.setVida(tempEnemy.getVida() - 1);
                    tempTiro.setVisivel(false);
                    if(tempEnemy.getVida() <= 0){
                        tempEnemy.setVisivel(false);
                    }
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
