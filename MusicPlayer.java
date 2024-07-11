import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class MusicPlayer extends JFrame{

    private static final ImageIcon play = new ImageIcon("assets/play.png");
    private static final ImageIcon pause = new ImageIcon("assets/pause.png");
    private static final ImageIcon settings = new ImageIcon("assets/settings.png");
    private static final ImageIcon reset = new ImageIcon("assets/reset.png");

    private static final Border border = BorderFactory.createLineBorder(new Color(0x000000)); 
    private static final Border uborder = BorderFactory.createLineBorder(new Color(0x00FF00)); 

    private static final Color bgc = new Color(0x004000);
    private static final Color acc = new Color(0x000000);

    private static boolean error = false;
    private static Clip clip;

    private static String file_name;

    MusicPlayer(){

    try{
        File file = new File("assets/A-Truly-Dazzling-Dream-National-Sweetheart.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audio);

        file_name = file.getName();

        } catch(IOException e) {error = true;
        } catch(UnsupportedAudioFileException e) {error = true;
        } catch(LineUnavailableException e) {error = true;}

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(700,300);
        this.setResizable(true);
        this.getContentPane().setBackground(bgc); 
        this.setResizable(false);
        uper();
        lower();
        centerPan();
        this.setVisible(true); 
    }

    public void centerPan(){
        JPanel pan = new JPanel();
        pan.setBorder(uborder);
        pan.setOpaque(true);
        pan.setBackground(bgc);
        JLabel name = new JLabel(file_name);
        name.setForeground(new Color(0x00FF00));
        pan.add(name,FlowLayout.LEFT);
        this.add(pan,BorderLayout.CENTER);
    }

    public void uper(){
        JLabel player = new JLabel("music player");

        JPanel l = new JPanel();
        l.setBounds(10,150,100,140);
        l.setOpaque(true);
        l.setPreferredSize(new Dimension(100,30));
        l.setBackground(acc);

        l.add(player);

        this.add(l,BorderLayout.NORTH);
    }

    public void lower(){
        JButton pauseB = new JButton();
        JButton playB = new JButton();
        playB.setIcon(play);
        playB.setBorder(border);
        playB.setBackground(null);
        playB.setFocusable(false);
        playB.setBounds(10,10,10,10);
        playB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("play"); 
                if(!error) {clip.start(); playB.setEnabled(false); pauseB.setEnabled(true);}    
            }
        });

        pauseB.setIcon(pause);
        pauseB.setBorder(border);
        pauseB.setBackground(null);
        pauseB.setFocusable(false);
        pauseB.setBounds(10,10,10,10);
        pauseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("stop");     
                if(!error) {clip.stop(); pauseB.setEnabled(false); playB.setEnabled(true);}  
            }
        });

        JButton resetB = new JButton();
        resetB.setIcon(reset);
        resetB.setBorder(border);
        resetB.setBackground(null);
        resetB.setFocusable(false);
        resetB.setBounds(10,10,10,10);
        resetB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("reseet");
                if(!error){clip.setMicrosecondPosition(0);} 
            }
        });

        JButton settingsB = new JButton();
        settingsB.setIcon(settings);
        settingsB.setBorder(border);
        settingsB.setBackground(null);
        settingsB.setFocusable(false);
        settingsB.setBounds(10,10,10,10);
        settingsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("settings open");     
            }
        });

        JPanel p = new JPanel();
        p.add(playB);
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
        p.setOpaque(true);
        p.setPreferredSize(new Dimension(100,75));
        p.setBackground(acc);
        p.add(playB);
        p.add(pauseB);
        p.add(resetB);
        p.add(settingsB);
        this.add(p,BorderLayout.SOUTH);
    }
}

