package com.trinary.ro.v3.converters;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.trinary.ro.exceptions.ConversionException;

public abstract class ROConverter<RO, ENTITY> {
	protected UriInfo uriInfo;
	
	protected abstract ENTITY _convertRO(RO ro) throws ConversionException;
	protected abstract RO _convertEntity(ENTITY entity) throws ConversionException;
	protected abstract RO _addLinks(RO object);
	
	public ROConverter(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	public RO convertEntity(ENTITY entity) throws ConversionException {
		if (entity == null) {
			return null;
		}
		
		RO ro = _convertEntity(entity);
		RO roWithLinks = _addLinks(ro);
		
		if (roWithLinks == null) {
			return ro;
		}
		
		return roWithLinks;
	}
	
	public ENTITY convertRO(RO ro) throws ConversionException {
		if (ro == null) {
			return null;
		}
		
		return _convertRO(ro);
	}
	
	public List<RO> convertEntityList(List<ENTITY> entityList) throws ConversionException {
		if (entityList == null) {
			return null;
		}
		
		List<RO> roList = new ArrayList<RO>();
		for (ENTITY entity : entityList) {
			roList.add(convertEntity(entity));
		}
		return roList;
	}
	
	public List<ENTITY> convertROList(List<RO> roList) throws ConversionException {
		if (roList == null) {
			return null;
		}
		
		List<ENTITY> entityList = new ArrayList<ENTITY>();
		for (RO ro : roList) {
			entityList.add(convertRO(ro));
		}
		return entityList;
	}
	
	public UriInfo getUriInfo() {
		return uriInfo;
	}
	
}