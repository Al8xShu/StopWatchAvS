package com.avs.stopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatch implements ActionListener {

    JFrame aFrame = new JFrame();
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JLabel timeLabel = new JLabel();

    int elapseTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;

    boolean started = false;

    String secondsString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);
    String hoursString = String.format("%02d", hours);

    Timer aTimer = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            elapseTime += 1000;
            hours = elapseTime / 3600000;
            minutes = (elapseTime / 60000) % 60;
            seconds = (elapseTime / 1000) % 60;

            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            hoursString = String.format("%02d", hours);

            timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);

        }
    });

    StopWatch(){

        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);
        timeLabel.setBounds(100, 100, 200, 100);
        timeLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 38));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        aFrame.add(startButton);
        aFrame.add(resetButton);
        aFrame.add(timeLabel);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setSize(380, 380);
        aFrame.setLayout(null);
        aFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton){

            if (started == false)
            {
                started = true;
                startButton.setText("Stop");
                start();
            }
            else
            {
                started = false;
                startButton.setText("Start");
                stop();
            }

        }

        if (e.getSource() == resetButton)
        {
            started = false;
            startButton.setText("Start");
            reset();
        }

    }

    void start(){
        aTimer.start();
    }

    void stop(){
        aTimer.stop();
    }

    void reset(){

        aTimer.stop();
        elapseTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        secondsString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        hoursString = String.format("%02d", hours);

        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString);

    }
}
