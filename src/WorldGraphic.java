import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
 
public class WorldGraphic extends JPanel {
    int pos_x;
    int pos_y;

    public WorldGraphic(int pos_x, int pos_y) {
	super();

	this.pos_x = pos_x;
	this.pos_y = pos_y;
    }

    public void paintComponent(Graphics g){
	g.setColor(Color.white);
	g.fillRect(0, 0, this.getWidth(), this.getHeight());

	g.setColor(Color.gray);
	g.fillOval(375, 20, 50, 50);
	g.fillOval(this.pos_x, this.pos_y, 50, 50);

	g.setColor(Color.red);
	g.fillOval(405, 415, 20, 20);
    }

    public void setPosX(int pos_x) {
	this.pos_x = pos_x;
    }

    public void setPosY(int pos_y) {
	this.pos_y = pos_y;
    }

    public int getPosX() {
	return this.pos_x;
    }

    public int getPosY() {
	return this.pos_y;
    }

}
