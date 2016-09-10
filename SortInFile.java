package ParallelComputing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class SortInFile {
	
	public static void sort(File file) throws IOException{
		//String filename = file.getName();
		//String filepath = file.getAbsolutePath();
		//String outputPath = filepath.substring(0,filepath.lastIndexOf('\\')+1) + "out\\" + filename.substring(0, filename.lastIndexOf('.'))+"_sorted.txt";
		
		FileInputStream fin = new FileInputStream(file);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		//read data from file
		String line;
		
		while((line = reader.readLine()) != null){
			list.add(Integer.parseInt(line.trim()));
		}
		file.delete();
		reader.close();
		Collections.sort(list);
		//write data to the original file
		FileOutputStream fou = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fou));
		for(int i=0; i<list.size(); i++){
			writer.write(String.valueOf(list.get(i)));
			if(i != list.size()-1){
				writer.newLine();
			}
		}
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("H:\\TestData\\tmp");
		//File outfile = new File("H:\\TestData\\tmp\\nums.txt_part0_tmp.txt");
		File[] files = file.listFiles();
		for(int i=0; i<files.length; i++){
			if(files[i].isDirectory()){
				continue;
			}
			try {
				SortInFile.sort(files[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
