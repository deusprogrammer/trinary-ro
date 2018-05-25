package com.trinary.ro.v2;

public class Link {
	protected String rel;
	protected String href;
	
	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}
	/**
	 * @param rel the rel to set
	 */
	public Link setRel(String rel) {
		this.rel = rel;
		return this;
	}
	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param href the href to set
	 */
	public Link setHref(String href) {
		this.href = href;
		return this;
	}
}