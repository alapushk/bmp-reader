package bmpRead;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import bmpRead.Bmp;

public class DrawImage extends JPanel {
	public int[][] input; //array of pixel values
	boolean flag = false;
	int numPixels = 0; //number of samples
	int width = 0;
	int height = 0;
	
	//copy the data into DrawImage structure:
	public void setBuffer(Bmp image) {
		input = new int[image.numPixels][3];
		numPixels = image.numPixels;
		width = image.width;
		height = image.height;
		//copy the data:
		for(int i=0; i<numPixels; i++) {
			for(int j = 0; j < 3; j++) {
				 input[i][j]=image.data[i][j];
			}   
		}
		flag = true;
		repaint();
	}
	
	//paint the image:
	public void paintComponent(Graphics g) {
		
		//Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g); 
	
		//set the size of the screen:
		//int scwidth = getWidth(); //width of the screen
		//int scheight = getHeight(); //height of the screen
		int r = 0;
		int gr = 0;
		int b = 0;
		int pos = 0;
		int grey = 0;
		//g.fillRect(100, 100, 10,10);
		
		//byte value = 0;
		if(flag == true) {//the input has been copied
			//color
			for(int h = 0; h < (height); h++) {
				for(int w = 0; w < (width); w++) {
					pos = (h*width)+w;
					r = input[pos][0];
					gr = input[pos][1];
					b =input[pos][2];
					//System.out.println("r: " + r + "g: " +gr + "b: " + b);
					g.setColor(new Color(r, gr, b));
					g.fillRect(200-w, 300-h, 1,1);
				}
			}
			//grayscale
			for(int h = 0; h < (height); h++) {
				for(int w = 0; w < (width); w++) {
					pos = (h*width)+w;
					r = input[pos][0];
					gr = input[pos][1];
					b =input[pos][2];
					grey = (r+gr+b)/3;
					//System.out.println("r: " + r + "g: " +gr + "b: " + b);
					g.setColor(new Color(grey, grey, grey));
					g.fillRect(400-w, 300-h, 1,1);
				}
			}
			//bright
			for(int h = 0; h < (height); h++) {
				for(int w = 0; w < (width); w++) {
					pos = (h*width)+w;
					r = (int)((1.5)*(input[pos][0]));
					gr = (int)((1.5)*(input[pos][1]));
					b =(int)((1.5)*(input[pos][2]));
					if(r > 255) r = 255;
					if(gr > 255) gr = 255;
					if(b > 255) b = 255;
					//grey = (r+gr+b)/3;
					//System.out.println("r: " + r + "g: " +gr + "b: " + b);
					g.setColor(new Color(r, gr, b));
					g.fillRect(600-w, 300-h, 1,1);
				}
			}
			
			
			
		}
	}
}

