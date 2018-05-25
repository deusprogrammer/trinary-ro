package com.trinary.ro.v2;

import java.util.ArrayList;
import java.util.List;

public class RepresentationObject {
	protected List<Link> links = new ArrayList<Link>();

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String rel, String href) {
		Link link = new Link();
		link.setRel(rel);
		link.setHref(href);
		links.add(link);
	}
	
	public void addLink(Link link) {
		links.add(link);
	}
}