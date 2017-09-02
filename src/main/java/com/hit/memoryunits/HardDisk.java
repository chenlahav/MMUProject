package com.hit.memoryunits;
import java.io.*;

public class HardDisk {
	private int _SIZE;
	private String DEFAULT_FILE_NAME;
	private static HardDisk instance = null;
	
	private HardDisk(int _SIZE, String DEFAULT_FILE_NAME) {
		this._SIZE = _SIZE;
		this.DEFAULT_FILE_NAME = DEFAULT_FILE_NAME;
	}
		
	//TODO
	public static HardDisk getInstance(){
		if(instance==null){
			instance=new HardDisk(1000,"/filename.txt");
		}
		return instance;
	}
	
	//TODO
	public Page<byte[]> pageFault(Long id) throws FileNotFoundException, IOException{	
		return null;
	}
	
	//TODO
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId) throws FileNotFoundException, IOException{
		return null;
	}
		
}
