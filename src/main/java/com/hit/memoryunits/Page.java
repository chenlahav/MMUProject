package com.hit.memoryunits;

import java.io.Serializable;

public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = 1806150186064340976L;
	private T content;
	private Long id;
	
	public Page(Long id, T content){
		this.setContent(content);
		this.setPageId(id);
	}
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Long getPageId() {
		return this.id;
	}

	public void setPageId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Page<T> other = (Page<T>) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Page [content=" + content + ", id=" + id + "]";
	}
	
	
}