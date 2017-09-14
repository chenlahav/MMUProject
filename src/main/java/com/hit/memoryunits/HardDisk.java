package com.hit.memoryunits;
import java.io.*;
import java.util.HashMap;

public class HardDisk {
	private static final int _SIZE = 10;
	private  static final String DEFAULT_FILE_NAME = "src/main/resources/com/hit/HardDisk/HardDisk_file";
	private static HardDisk instance = null;
	private HashMap<Long,Page<byte[]>> pagesInDisk;
	
	private HardDisk() {
	}
	
	@SuppressWarnings("unchecked")
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
			instance=new HardDisk();
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
		if(this.pagesInDisk.containsKey(moveToHdPage.getPageId())){
			this.pagesInDisk.put(moveToHdPage.getPageId(), moveToHdPage);
		}else{
			if(this.pagesInDisk.size()<_SIZE){
				this.pagesInDisk.put(moveToHdPage.getPageId(), moveToHdPage);
			}
			else{
				//TODO handle exception
			}
		}
		
		writeToDisk();
		Page<byte[]> currPage= pagesInDisk.get(moveToRamId);
		
		return currPage;
	}
		
}
