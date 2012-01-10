import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JFrame {
    private long pos;
    private WorldGraphic worldGraphic = new WorldGraphic(12*30 + 390, 400);

    public World() {
	this.pos = 12;

	this.setTitle("Khepera simulation");
	this.setSize(800, 500);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	this.setContentPane(worldGraphic);
	this.setVisible(true);
    }


    public long getPos() {
	return this.pos;
    }

    public void move(long move) {
	// TODO gerer acces concurent
	if(move > 0) {
	    for(int i = (int)this.pos * 30 + 390; i < (this.pos + move) * 30 + 390; ++i) {
		worldGraphic.setPosX(i);
		worldGraphic.repaint();
		try {
		    Thread.sleep(10);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	} else {
	    for(int i = (int)this.pos * 30 + 390; i > (this.pos + move) * 30 + 390; --i) {
		worldGraphic.setPosX(i);
		worldGraphic.repaint();
		try {
		    Thread.sleep(10);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}
	this.pos += move;
	System.out.println("pos = " + this.pos);
    }


    public String toString() {
	String res = "";

	System.out.println("[H[J");
	for(int i = -40; i < 40; i++) {
	    if(this.pos == i) {
		res += "0";
	    } else {
		res += "-";
	    }
	}

	return res;
    }
}
