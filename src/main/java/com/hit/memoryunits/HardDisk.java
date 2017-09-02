package com.hit.memoryunits;
import java.io.*;
import java.util.HashMap;

public class HardDisk {
	private int _SIZE;
	private String DEFAULT_FILE_NAME;
	private static HardDisk instance = null;
	private HashMap<Long,Page<byte[]>> pagesInDisk;
	
	private HardDisk(int _SIZE, String DEFAULT_FILE_NAME) {
		this._SIZE = _SIZE;
		this.DEFAULT_FILE_NAME = DEFAULT_FILE_NAME;
	}
	
	private void readFromDisk() throws FileNotFoundException, IOException{
		ObjectInputStream in= new ObjectInputStream(new FileInputStream(DEFAULT_FILE_NAME));
		try{
			this.pagesInDisk=(HashMap<Long,Page<byte[]>>)in.readObject();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			in.close();
		}
	}
		
	private void writeToDisk() throws FileNotFoundException, IOException{
		ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(DEFAULT_FILE_NAME));
		try{
			out.writeObject(this.pagesInDisk);
			out.flush();
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			out.close();
		}
	}
		
	public static HardDisk getInstance(){
		if(instance==null){
			instance=new HardDisk(1000,"src/main/resources/HardDisk/HDPages");
		}
		return instance;
	}
	
	public Page<byte[]> pageFault(Long id) throws FileNotFoundException, IOException{	
		readFromDisk();
		Page<byte[]> currPage= pagesInDisk.get(id);
		
		return currPage;
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId) throws FileNotFoundException, IOException{
		readFromDisk();
		this.pagesInDisk.put(moveToHdPage.getPageId(), moveToHdPage);
		writeToDisk();
		Page<byte[]> currPage= pagesInDisk.get(moveToRamId);
		
		return currPage;
	}
		
}
