package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import main.Utils;

public class GraphicsUtils {
	// Turn the flat world into a 3D masterpiece. // 
	// NOT COMPLETELY FINISHED //
	public static BufferedImage perspectivize(BufferedImage source){
		BufferedImage source2 = new BufferedImage(2100,2100,source.getType());
		
		double[][] rotationMatrix = new double[][]{{1,0,0,0},{0,Math.cos(Math.PI/4),-Math.sin(Math.PI/4),0},{0,Math.sin(Math.PI/4),Math.cos(Math.PI/4),0},{0,0,0,1}};
		//double[][] clipMatrix = new double[][]{{fov/2,0,0,0},{0,fov/2,0,0},{0,0,-((far+near)/(far-near)),(2*near*far)/(near-far)},{0,0,-1,0}};
		
		//int[][] pixelData = getPixels(source);
		//System.out.println("Pixel Data: " + pixelData[0].length + "x" + pixelData.length);
		for(int x = 0; x<source.getWidth();x++){
			for(int y = 0; y<source.getHeight();y++){
				double[][] points = (Utils.matrixMultiply(new double[][]{{x,y,0,1}},rotationMatrix));
				try{

				source2.setRGB((int)(-points[0][0]*2/points[0][2]),(int)(-points[0][1]*2/points[0][2]),source.getRGB(x, y));
				}
				catch(ArrayIndexOutOfBoundsException e){
					System.out.println("EEK! Out of Bounds! [" + (int)points[0][1] + ", " + (int)(points[0][0]) + "]");
				}
			}
		}
		return source2;
	}
	
	
	// Get pixel values from a buffered image //
	// STILL NEEDS TO BE OPTIMIZED //
	public static int[][] getPixels(BufferedImage image) {
	      final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	      byte[] pixelData = new byte[pixels.length];
	      for (int i=0; i<pixels.length; i++){
	      pixelData[i] = Byte.valueOf(pixelData[i]);
	      }
	      
	      final int width = image.getWidth();
	      final int height = image.getHeight();
	      final boolean hasAlphaChannel = image.getAlphaRaster() != null;

	      int[][] result = new int[height][width];
	      if (hasAlphaChannel) {
	         final int pixelLength = 4;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += (((int) pixelData[pixel] & 0xff) << 24); // alpha
	            argb += ((int) pixelData[pixel + 1] & 0xff); // blue
	            argb += (((int) pixelData[pixel + 2] & 0xff) << 8); // green
	            argb += (((int) pixelData[pixel + 3] & 0xff) << 16); // red
	            result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	      } else {
	         final int pixelLength = 3;
	         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            try{
	            argb += -16777216; // 255 alpha
	            argb += ((int) pixelData[pixel] & 0xff); // blue
	            argb += (((int) pixelData[pixel + 1] & 0xff) << 8); // green
	            argb += (((int) pixelData[pixel + 2] & 0xff) << 16); // red
	            result[row][col] = argb;
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	            }
	            catch(ArrayIndexOutOfBoundsException e){
	            	argb = -16777216;
	            }
	            System.out.println("RGB: " + argb);
	            System.out.println("RGB2: " + image.getRGB(row, col));
	         }
	      }

	      return result;
	   }
}
