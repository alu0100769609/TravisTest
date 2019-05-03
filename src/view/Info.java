/**
 * 
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * @author alu0100769609
 *
 */
public class Info extends JPanel {
    private static final long serialVersionUID = 1L;

    // ATTRIBUTES
    private JLabel lblText;
    
    // CONSTRUCTOR
    public Info() {
        // Create attributes
        lblText = new JLabel("", JLabel.CENTER);
        
        // Add the label to the panel
        setLayout(new BorderLayout());
        add(lblText, BorderLayout.CENTER);
        
    }
    
    // GETTERS AND SETTERS
    public JLabel getLblText() {
        return lblText;
    }
    
    public void setLblText(JLabel lblText) {
        this.lblText = lblText;
    }
}
