import java.io.*;
import java.math.BigInteger;


public class FileTransfer {
	public String DoubleToBinary(String file) throws IOException{
		FileReader in = new FileReader(file);
	    BufferedReader br = new BufferedReader(in);
	    String out = file + "_Binary";
	    File output = new File(out);
	    if (!output.exists()) {
	    	output.createNewFile();
		}
	    FileWriter fw = new FileWriter(output.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
	    String line;
	    while((line = br.readLine()) != null){
	    	double d = Double.valueOf(line);
	    	String text = Long.toBinaryString(Double.doubleToRawLongBits(d));
	    	bw.write(text);
	    }
	    in.close();
	    bw.close();
		return out;		
	}
	
	// index of the number in raw file, start from 0
	public String BinaryToDouble(String file, int startIndex, int endIndex) throws IOException{
		FileReader in = new FileReader(file);
	    BufferedReader br = new BufferedReader(in);
	    String out = file + "_Normal";
	    File output = new File(out);
	    if (!output.exists()) {
	    	output.createNewFile();
		}
	    FileWriter fw = new FileWriter(output.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
	    int c;
	    int counter_bit = 0;
	    int counter_num = 0;
	    StringBuilder line = new StringBuilder();
	    while ((c = br.read()) != -1) {
	    	if (counter_bit < startIndex * 62) {
	    		counter_bit++;
	    		continue;
	    	} else if (counter_bit >= (endIndex + 1) * 62) {
	    		break;
	    	} 
	    	if(counter_num == 0) line = new StringBuilder();    	
	    	line.append( (char)c );
	    	if(counter_num == 61){
	    		String result = line.toString();
	    		double d = Double.longBitsToDouble(new BigInteger(result, 2).longValue());
		    	bw.write(Double.toString(d) + '\n');
	    		counter_num = -1;
	    	}
	    	counter_num ++;
	    	counter_bit ++;
	    }
	    in.close();
	    bw.close();
	    return out;
	}
	
	
	public static void main(String[] args) throws IOException{
//        double d = 1.0;
//        String text = Long.toBinaryString(Double.doubleToRawLongBits(d));
//        System.out.println(text.length());
//        System.out.println("0b "+ text);
//        double nd =  Double.longBitsToDouble(new BigInteger(text, 2).longValue());
//        System.out.println("double "+nd);
		int startIndex = 3;
		int endIndex = 5;
		 FileTransfer s = new FileTransfer();
         String input = "svd.test.data.txt";
         String binaryFile = s.DoubleToBinary(input);
         String normalFile = s.BinaryToDouble(binaryFile, startIndex, endIndex);
//        System.out.println(Double.doubleToRawLongBits(d));
    }
}
