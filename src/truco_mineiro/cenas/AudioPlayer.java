/*
 * Licenciado a Carlos Henrique ;)
 */
package truco_mineiro.cenas;

import java.io.File;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class AudioPlayer {

    String[] aceitar = {"src/audios/aceitar1.mp3", "src/audios/aceitar10.mp3", "src/audios/aceitar11.mp3", "src/audios/aceitar12.mp3", "src/audios/aceitar13.mp3", "src/audios/aceitar14.mp3", "src/audios/aceitar15.mp3", "src/audios/aceitar2.mp3", "src/audios/aceitar3.mp3", "src/audios/aceitar4.mp3", "src/audios/aceitar5.mp3", "src/audios/aceitar6.mp3", "src/audios/aceitar7.mp3", "src/audios/aceitar8.mp3", "src/audios/aceitar9.mp3"};
    String[] correr = {"src/audios/correr1.mp3", "src/audios/correr10.mp3", "src/audios/correr11.mp3", "src/audios/correr13.mp3", "src/audios/correr14.mp3", "src/audios/correr15.mp3", "src/audios/correr16.mp3", "src/audios/correr17.mp3", "src/audios/correr18.mp3", "src/audios/correr19.mp3", "src/audios/correr2.mp3", "src/audios/correr20.mp3", "src/audios/correr21.mp3", "src/audios/correr22.mp3", "src/audios/correr3.mp3", "src/audios/correr4.mp3", "src/audios/correr5.mp3", "src/audios/correr6.mp3", "src/audios/correr7.mp3", "src/audios/correr8.mp3", "src/audios/correr9.mp3"};
    String[] truco = {"src/audios/truco1.mp3", "src/audios/truco10.mp3", "src/audios/truco11.mp3", "src/audios/truco12.mp3", "src/audios/truco13.mp3", "src/audios/truco14.mp3", "src/audios/truco15.mp3", "src/audios/truco16.mp3", "src/audios/truco17.mp3", "src/audios/truco18.mp3", "src/audios/truco2.mp3", "src/audios/truco3.mp3", "src/audios/truco4.mp3", "src/audios/truco5.mp3", "src/audios/truco6.mp3", "src/audios/truco7.mp3", "src/audios/truco8.mp3", "src/audios/truco9.mp3"};
    String[] seis = {"src/audios/seis1.mp3", "src/audios/seis10.mp3", "src/audios/seis11.mp3", "src/audios/seis12.mp3", "src/audios/seis13.mp3", "src/audios/seis14.mp3", "src/audios/seis2.mp3", "src/audios/seis3.mp3", "src/audios/seis4.mp3", "src/audios/seis5.mp3", "src/audios/seis6.mp3", "src/audios/seis7.mp3", "src/audios/seis8.mp3", "src/audios/seis9.mp3"};
    String[] nove = {"src/audios/nove1.mp3", "src/audios/nove10.mp3", "src/audios/nove11.mp3", "src/audios/nove12.mp3", "src/audios/nove13.mp3", "src/audios/nove2.mp3", "src/audios/nove3.mp3", "src/audios/nove4.mp3", "src/audios/nove5.mp3", "src/audios/nove6.mp3", "src/audios/nove7.mp3", "src/audios/nove8.mp3", "src/audios/nove9.mp3"};
    String[] doze = {"src/audios/doze2.mp3", "src/audios/doze3.mp3", "src/audios/doze4.mp3", "src/audios/doze5.mp3", "src/audios/doze6.mp3", "src/audios/doze7.mp3", "src/audios/doze8.mp3", "src/audios/doze9.mp3"};
    String[] vencer = {"src/audios/ganhou.mp3", "src/audios/vitoria1.mp3", "src/audios/vitoria2.mp3"};
    
    Media media;
    MediaPlayer mediaPlayer;
    
    Random rdm = new Random();
    
    public void gritarCorrer(){
        int i = rdm.nextInt(correr.length);
        File f = new File(correr[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void gritarAceitar(){
        int i = rdm.nextInt(aceitar.length);
        File f = new File(aceitar[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void gritarSeis(){
        int i = rdm.nextInt(seis.length);
        File f = new File(seis[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void gritarTruco(){
        int i = rdm.nextInt(truco.length);
        File f = new File(truco[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void gritarNove(){
        int i = rdm.nextInt(nove.length);
        File f = new File(nove[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void gritarDoze(){
        int i = rdm.nextInt(doze.length);
        File f = new File(doze[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    public void comemorarVitoria(){
        int i = rdm.nextInt(vencer.length);
        File f = new File(vencer[i]);
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
}

