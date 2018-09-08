package numbertospeech;

import numbertospeech.service.FileLuncher;
import numbertospeech.service.Manager;
import numbertospeech.view.JFrameHome;

/**
 * riyadnoapara@gmail.com
 * https://github.com/riyad-ctg/Number-to-speech
 * +8801829443600
 * 
 * @author RIYAD
 */

public class NumberTeller {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * The is the main method where execution starts
         * Set the Nimbus look and feel
         * 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /**
         * Create and display the form
         * This is how we show a view
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileLuncher fileLuncher = new FileLuncher(); //Reducing Dependencies
                Manager manager = new Manager();        //Reducing Dependencies
                
                JFrameHome jFrameHome = new JFrameHome(manager,fileLuncher); //This is the main view
                jFrameHome.setVisible(true);
            }
        });
    }
    
}
