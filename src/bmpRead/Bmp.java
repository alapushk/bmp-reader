package bmpRead;
import java.io.*;

public class Bmp {
	
	public int numPixels; 
	
	//BUFFERS;
	public byte[] input; //buffer for the 3 bytes pixel value
	public byte[] smallinput; //buffer for the 3 bytes input
	
	
	//HEADER:
	public int width;
	public int height;
	public int depth = 24;
	public int dataSize; //41-44
	
	//DATA:
	public int[][] data; //2d array to hold the colour values 
	
	//constructor:
	public Bmp(){
		numPixels = 0;
		input = new byte[4]; //buffer for 4 bytes
		smallinput = new byte[3]; //buffer for 2 bytes
	}
	
	//methods:
	public void readBmp(String filename) throws IOException {
		
		File file = new File(filename);
		FileInputStream fileInput =  null;
		BufferedInputStream buffer = null;
	
		try{
			fileInput = new FileInputStream(file);
			buffer = new BufferedInputStream(fileInput, 405504); //buffer for the image data
			//HEADER INFO
			//width
			buffer.skip(18); //skip irrelevant header info
			buffer.read(input, 0, 4); //store width in the input array
			width = (
					(((int) input[0] & 0xFF) )
					| (((int) input[1] & 0xFF) << 8)
					| (((int) input[2] & 0xFF) << 16)
					| ((int) input[3] & 0xFF << 24)
					);
			System.out.println("width: " + width);
			//height:
			input = new byte[4]; //reinitialize buffer for 4 bytes
			buffer.read(input, 0, 4); //store width in the input array
			height = (
					(((int) input[0] & 0xFF))
					| (((int) input[1] & 0xFF) << 8)
					| (((int) input[2] & 0xFF) << 16)
					| ((int) input[3] & 0xFF << 24)
					);
			System.out.println("height: " + height);
			
			//SAMPLES DATA
			buffer.skip(28);
			numPixels = width*height;
			System.out.println("number of pixels: " + numPixels);
			data = new int[numPixels+1][3]; //create an 2d array of size numPixels x 3
			
			int b = 0; //blue
			int g = 0; //green
			int r = 0; //red
			
			//int value = 0; //tmp for holding converted sample
			int i = 0;
			
			
			while(buffer.available() > 0) { //check each sample
				//read 2 bytes:
				b = buffer.read(); 
				g = buffer.read(); 
				r = buffer.read();
				
				data[i][0] = r; //store red value in the data array
				data[i][1] = g; //store green value in the data array
				data[i][2] = b; //store blue value in the data array
				
				i++;
			}
			System.out.println("The data was read successfully!");
			
		}catch(IOException e) {
			//do something
			e.printStackTrace();
		}finally {
			System.out.println("closing file...");
			if(fileInput!=null) fileInput.close();
			if(buffer!=null) buffer.close();
			}
	}

}


