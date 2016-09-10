package ParallelComputing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

class HeapItem{
	int val;
	int k;
	File file;
	public HeapItem(int val, int k, File file){
		this.val = val;
		this.k = k;
		this.file = file;
	}
}

public class MergeFiles {
	
	public String skipKLines(File file, int k) throws Exception{
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
		int i = 0;
		while(i<k && reader.readLine() != null){
			i++;
		}
		String res = reader.readLine();
		reader.close();
		return res;
	}
	
	public void merge(String inputPath, String outputFile){
		File[] files = new File(inputPath).listFiles();
		PriorityQueue<HeapItem> minHeap = new PriorityQueue<>(1,new Comparator<HeapItem>(){
			public int compare(HeapItem i1, HeapItem i2){
				return i1.val-i2.val;
			}
		});
		BufferedReader reader = null;
		//Load the first number in each file into heap
		for(int i=0; i<files.length; i++){
			try {
				reader = new BufferedReader(new FileReader(files[i]));
				String line = reader.readLine();
				if(line != null){
					int val = Integer.parseInt(line);
					minHeap.offer(new HeapItem(val,1,files[i]));
				}
				reader.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		//Create the new output file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(new File(outputFile)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Use the heap to sort and every time put the minimum number into the result file
		while(minHeap.size() != 0){
			HeapItem now = minHeap.poll();
			File curFile = now.file;
			int val = now.val;
			int k = now.k;
			writer.println(String.valueOf(val));
			try {
				String line = skipKLines(curFile, k);
				if(line != null){
					minHeap.offer(new HeapItem(Integer.parseInt(line),k+1,curFile));
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			writer.flush();
		}
		writer.close();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeFiles mf = new MergeFiles();
		String inputPath = "H:\\TestData\\tmp\\";
		String outputFile = "H:\\TestData\\tmp\\result.txt";

		mf.merge(inputPath, outputFile);
		System.out.println("Done!!!!");
	}

}
