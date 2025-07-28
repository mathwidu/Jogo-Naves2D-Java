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
import Meujogo.modelo.Boss;
import Meujogo.modelo.Enemy2;
import Meujogo.modelo.TiroInimigo;
import Meujogo.modelo.Explosion;

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
    private List<Boss> bosses;
    private Image[] bossLife;
    private List<Stars> stars;
    private List<Explosion> explosions;
    private boolean emJogo;
    private GameOverPanel gameOverPanel;
    private int score;
    private long startTime;
    private int dificuldade;

    private void reiniciarJogo(){
        if (player != null) {
            player.stopTurboTimer();
        }
        player = new Player();
        inicializaInimigos();
        enemy2.clear();
        bosses.clear();
        explosions.clear();
        inicializaEstrelas();
        emJogo = true;
        score = 0;
        startTime = System.currentTimeMillis();
        dificuldade = 0;
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
        enemy2 = new ArrayList<Enemy2>();
        bosses = new ArrayList<Boss>();
        explosions = new ArrayList<Explosion>();
        bossLife = new Image[6];
        bossLife[0] = new ImageIcon(getClass().getResource("/res/Paineis/VidaBoss.png")).getImage();
        bossLife[1] = new ImageIcon(getClass().getResource("/res/Paineis/VidaBoss1Hit.png")).getImage();
        bossLife[2] = new ImageIcon(getClass().getResource("/res/Paineis/VidaBoss2Hit.png")).getImage();
        bossLife[3] = new ImageIcon(getClass().getResource("/res/Paineis/VidaBoss3Hit.png")).getImage();
        bossLife[4] = new ImageIcon(getClass().getResource("/res/Paineis/VidaBoss4Hit.png")).getImage();
        bossLife[5] = new ImageIcon(getClass().getResource("/res/Paineis/VidaBoss5Hit.png")).getImage();
        score = 0;
        startTime = System.currentTimeMillis();
        dificuldade = 0;

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
        int cordenadas [] = new int [40];
        enemy1 = new ArrayList<Enemy1>();

        for(int i =0; i< cordenadas.length; i++){
            int x = (int)(Math.random() * 8000+1024);
            int y = (int)(Math.random() * 650+30);
            enemy1.add(new Enemy1(x, y));
        }
    }

    public void inicializaEnemy2(){
        if(enemy2.isEmpty()){
            int coords [] = new int[10];
            for(int i=0;i<coords.length;i++){
                int x = SCREEN_WIDTH + (int)(Math.random()*5000);
                int y = (int)(Math.random()*650+30);
                enemy2.add(new Enemy2(x,y));
            }
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
        long tempo = (System.currentTimeMillis() - startTime) / 1000;
        graficos.drawString("Tempo: " + tempo + "s", 10, 20);
        graficos.drawString("Pontos: " + score, 10, 40);

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

        for(Boss b : bosses){
            graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
            int idx = (int)((Boss.getVidaInicial() - b.getVida()) * (bossLife.length - 1) / (float)Boss.getVidaInicial());
            graficos.drawImage(bossLife[idx], b.getX(), b.getY() - 20, this);
            for(TiroInimigo t : b.getTiros()){
                graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);
            }
        }

        for (Explosion ex : explosions) {
            graficos.drawImage(ex.getImagem(), ex.getX(), ex.getY(), this);
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

        long tempo = (System.currentTimeMillis() - startTime) / 1000;
        if (tempo / 20 > dificuldade) {
            dificuldade++;
            if (dificuldade >= 1) {
                inicializaEnemy2();
            }
            if (dificuldade % 3 == 0) {
                bosses.add(new Boss(SCREEN_WIDTH, 100));
            }
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

        List<Enemy2> novos2 = new ArrayList<>();
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

        for (Iterator<Boss> it = bosses.iterator(); it.hasNext();) {
            Boss b = it.next();
            if (b.isVisivel()) {
                b.update();
            } else {
                it.remove();
            }
        }

        for (Iterator<Explosion> it = explosions.iterator(); it.hasNext();) {
            Explosion ex = it.next();
            if (ex.isVisivel()) {
                ex.update();
            } else {
                it.remove();
            }
        }

        checarColisoes();

        repaint();
    }

    public void checarColisoes() {
        Rectangle formaNava = player.getBounds();
        Rectangle formaEnemy1;
        Rectangle formaEnemy2;
        Rectangle formaBoss;
        Rectangle formaTiro;
        Rectangle formaTiroInimigo;

        for(int j = 0; j < enemy1.size(); j++){
            Enemy1 tempEnemy1 = enemy1.get(j);
            formaEnemy1 = tempEnemy1.getBounds();
                if(formaNava.intersects(formaEnemy1)){
                    tempEnemy1.setVisivel(false);
                    explosions.add(new Explosion(tempEnemy1.getX(), tempEnemy1.getY()));
                    player.setVida(player.getVida() - 1);
                    explosions.add(new Explosion(player.getX(), player.getY()));
                    if(player.getVida() <= 0){
                        player.setVisivel(false);
                        emJogo = false;
                        gameOverPanel.setVisible(true);
                    }
                }
        }

        for(int j = 0; j < enemy2.size(); j++){
            Enemy2 tempEnemy2 = enemy2.get(j);
            formaEnemy2 = tempEnemy2.getBounds();
            if(formaNava.intersects(formaEnemy2)){
                tempEnemy2.setVisivel(false);
                explosions.add(new Explosion(tempEnemy2.getX(), tempEnemy2.getY()));
                player.setVida(player.getVida() - 1);
                explosions.add(new Explosion(player.getX(), player.getY()));
                if(player.getVida() <= 0){
                    player.setVisivel(false);
                    emJogo = false;
                    gameOverPanel.setVisible(true);
                }
            }
        }

        for(Boss b : bosses){
            formaBoss = b.getBounds();
            if(formaNava.intersects(formaBoss)){
                player.setVida(player.getVida() - 2);
                explosions.add(new Explosion(player.getX(), player.getY()));
                if(player.getVida() <= 0){
                    player.setVisivel(false);
                    emJogo = false;
                    gameOverPanel.setVisible(true);
                }
            }
            for(TiroInimigo t : b.getTiros()){
                formaTiroInimigo = t.getBounds();
                if(formaNava.intersects(formaTiroInimigo)){
                    t.setVisivel(false);
                    player.setVida(player.getVida() - 1);
                    explosions.add(new Explosion(player.getX(), player.getY()));
                    if(player.getVida() <= 0){
                        player.setVisivel(false);
                        emJogo = false;
                        gameOverPanel.setVisible(true);
                    }
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
                        explosions.add(new Explosion(tempEnemy1.getX(), tempEnemy1.getY()));
                        score += 10;
                    }
                }
            }

            for (int m = 0; m < enemy2.size(); m++){
                Enemy2 tempEnemy2 = enemy2.get(m);
                formaEnemy2 = tempEnemy2.getBounds();
                if(formaTiro.intersects(formaEnemy2)){
                    tempEnemy2.setVida(tempEnemy2.getVida() - 1);
                    tempTiro.setVisivel(false);
                    if(tempEnemy2.getVida() <= 0){
                        tempEnemy2.setVisivel(false);
                        explosions.add(new Explosion(tempEnemy2.getX(), tempEnemy2.getY()));
                        score += 20;
                    }
                }
            }

            for (int m = 0; m < bosses.size(); m++){
                Boss b = bosses.get(m);
                formaBoss = b.getBounds();
                if(formaTiro.intersects(formaBoss)){
                    b.damage(1);
                    tempTiro.setVisivel(false);
                    if(b.getVida() <= 0){
                        b.setVisivel(false);
                        explosions.add(new Explosion(b.getX(), b.getY()));
                        score += 100;
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
