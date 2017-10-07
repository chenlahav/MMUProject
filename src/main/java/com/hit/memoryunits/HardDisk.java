package com.hit.memoryunits;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;

import com.hit.util.MMULogger;

public class HardDisk {
	private static final int _SIZE = 500;
	private  static final String DEFAULT_FILE_NAME = "src/main/resources/com/hit/HardDisk/HardDisk_file";
	private static HardDisk instance = null;
	private HashMap<Long,Page<byte[]>> pagesInDisk;
	
	private HardDisk() {
		this.pagesInDisk = new HashMap<>();
		File file = new File(DEFAULT_FILE_NAME);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void readFromDisk() throws FileNotFoundException, IOException{
		File yourFile = new File(DEFAULT_FILE_NAME);
		if (yourFile.length() == 0)
			return;
		ObjectInputStream in= new ObjectInputStream(new FileInputStream(DEFAULT_FILE_NAME));
		try{
			this.pagesInDisk=(HashMap<Long,Page<byte[]>>)in.readObject();
		}catch (Exception e) {
			// TODO: handle exception
			MMULogger.getInstance().write("HD:can't read from disk", Level.SEVERE);
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
			MMULogger.getInstance().write("HD:can't write to disk", Level.SEVERE);
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
		MMULogger.getInstance().write("PF:"+id, Level.INFO);
		Page<byte[]> currPage= pagesInDisk.get(id);
		if((currPage == null)&&(this.pagesInDisk.size()<_SIZE)){
			currPage = new Page<byte[]>(id, null);
			pagesInDisk.put(id, currPage);
			writeToDisk();
		}
		
		return currPage;
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId) throws FileNotFoundException, IOException{
		readFromDisk();
		MMULogger.getInstance().write("PR:MTH "+moveToHdPage.getPageId()+" MTR "+moveToRamId, Level.INFO);
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
