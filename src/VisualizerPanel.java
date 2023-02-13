import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class VisualizerPanel extends JPanel {
    JTextField textField;
    JButton button;
    String textFieldData;
    RBT rbt = new RBT();
    int nodeRadius = 30;
    int distancex = getWidth() / 2;
    int distancey = getWidth() / 4;

    Boolean go = false;
    
   

    public VisualizerPanel() {
        setLayout(null);

        textField = new JTextField();
        button = new JButton("Insert");

        textField.setBounds(50, 50, 50, 20);
        button.setBounds(110, 50, 70, 20);

        add(textField);
        add(button);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldData = textField.getText();
                rbt.insert(Integer.parseInt(textFieldData));
                textField.setText("");
                System.out.println(textFieldData);
                go = true;
                repaint();
            }
        });        
        

    }

    //write the paint method
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        textField.repaint();
        button.repaint();
        drawBST(g,rbt.getCurrentNode(), getWidth() / 2, 50, getWidth() / 3,1);

    }

    public void drawBST(Graphics g, Node node, int x, int y, int distance, int level) {
        if (node == null) {
            return;
        }
        //draw lines connecting the nodes black
        g.setColor(Color.BLACK);
        if (node.left != null) {
        g.drawLine(x + nodeRadius, y, x - (distance/(level+1)) + nodeRadius, y + 50);
        }
        if (node.right != null) {
        g.drawLine(x - nodeRadius, y, x + (distance/(level+1)) - nodeRadius, y + 50);
        }
        if(node.color == 0){
            g.setColor(Color.RED);
        }
        g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        //make the text white
        g.setColor(Color.WHITE);
        //get the size of the text
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(String.valueOf(node.data));
        int stringHeight = fm.getAscent();
        //center the text inside the oval
        g.drawString(String.valueOf(node.data), x - stringWidth / 2, y + stringHeight / 4);
        drawBST(g, node.left, x - (distance/(level+1)), y + 50, distance, level+1);
        drawBST(g, node.right, x + (distance/(level+1)), y + 50, distance, level+1);
        

    }
    

    
}
