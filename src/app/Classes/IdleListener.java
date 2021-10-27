package app.Classes;

import javax.swing.*;
    import java.awt.*;
    import java.awt.event.AWTEventListener;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;


    class IdleListener implements AWTEventListener, ActionListener {

        private Timer timer;
        private int timeoutSeconds;
        private int counter=0;

        IdleListener(int timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
            timer = new Timer(1000, this);
        }

        public void eventDispatched(AWTEvent event) {
            timer.restart();
            counter=0;
        }

        public void actionPerformed(ActionEvent e) {

            stopTimer();
            counter++;
            startTimer();

            if(counter== timeoutSeconds){
                stopTimer();
                JOptionPane.showMessageDialog(null,"Timeout ( Status--> Idle for 3 minutes )");
                System.exit(1);
            }
        }


        private void stopTimer() {
            timer.removeActionListener(this);
            timer.stop();
        }


        void startTimer() {
            timer = new Timer(1000, this);
            timer.start();

        }
}