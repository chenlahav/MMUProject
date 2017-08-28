package com.hit.memoryunits;

public class Page<T>{
	private T content;
	private Long id;
	
	public Page(Long id, T content){
		this.setContent(content);
		this.setId(id);
	}
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	//TODO
	public int hashCode(){
		return 1;
	}
	
	//TODO
	public boolean equals(Object obj){
		return false;
	}
	
	//TODO
	public String toString(){
		return "";
	}
	
}
