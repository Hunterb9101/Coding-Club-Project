package graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import main.Utils;

public class GraphicsUtils {
	// Turn the flat world into a 3D masterpiece. // 
	// NOT COMPLETELY FINISHED //
	public static BufferedImage perspectivize(BufferedImage source){
		BufferedImage source2 = new BufferedImage(2100,2100,source.getType());
		
		// Strategy to get closer to ideal (Multiply both numerator and denominator by angle, subtract one from numerator)
		// Then, change Y and Z coordinates
		int[] cameraPos = new int[]{400,600,-200};
		double[][] R = new double[][]{{1,0,0},{0,Math.cos(7*Math.PI/6),-Math.sin(7*Math.PI/6)},{0,Math.sin(7*Math.PI/6),Math.cos(7*Math.PI/6)}};
		
		//int[][] pixelData = getPixels(source);
		//System.out.println("Pixel Data: " + pixelData[0].length + "x" + pixelData.length);
		for(int x = 0; x<source.getWidth();x++){
			for(int y = 0; y<source.getHeight();y++){
				double[][] newPoints = Utils.matrixMultiply(Utils.transposeMatrix(R),new double[][]{{x-cameraPos[0]},{y-cameraPos[1]},{0-cameraPos[2]}});
				double[] newpoints2 = new double[]{newPoints[0][0]/newPoints[2][0],newPoints[1][0]/newPoints[2][0]};
				
				try{
				source2.setRGB((int)(newpoints2[0]*100)+400,(int)(newpoints2[1]*100)+400,source.getRGB(x, y));
				}
				catch(ArrayIndexOutOfBoundsException e){
					//System.out.println("EEK! Out of Bounds!");
					//System.out.println(newpoints2[0]*100 + ", " + newpoints2[1]*100);
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
