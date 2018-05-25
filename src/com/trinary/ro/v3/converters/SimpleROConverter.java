package com.trinary.ro.v3.converters;

import java.lang.reflect.Field;

import javax.ws.rs.core.UriInfo;

import com.trinary.ro.exceptions.ConversionException;

public abstract class SimpleROConverter<RO, ENTITY> extends ROConverter<RO, ENTITY> {
	private Class<?> entityClass, roClass;
	
	public SimpleROConverter(UriInfo uriInfo, Class<?> roClass, Class<?> entityClass) {
		super(uriInfo);
		this.entityClass = entityClass;
		this.roClass = roClass;
	}

	@Override
	protected ENTITY _convertRO(RO ro) throws ConversionException {
		try {
			ENTITY entity = (ENTITY)entityClass.newInstance();
			for (Field entityField : entityClass.getDeclaredFields()) {
				try {
					Field roField = ro.getClass().getDeclaredField(entityField.getName());
					entityField.setAccessible(true);
					roField.setAccessible(true);
					entityField.set(entity, roField.get(ro));
				} catch (NoSuchFieldException e) {
					System.out.println("RO doesn't include field named: " + entityField.getName());
					continue;
				}
			}
			
			return entity;
		} catch (InstantiationException e) {
			throw new ConversionException("Unable to instantiate entity object", e);
		} catch (IllegalAccessException e) {
			throw new ConversionException("Unable to modify entity object reflectively", e);
		} catch (SecurityException e) {
			throw new ConversionException("Unable to modify entity object reflectively", e);
		}
	}

	@Override
	protected RO _convertEntity(ENTITY entity) throws ConversionException {
		try {
			RO ro = (RO)roClass.newInstance();
			for (Field roField : roClass.getDeclaredFields()) {
				try {
					Field entityField = entity.getClass().getDeclaredField(roField.getName());
					entityField.setAccessible(true);
					roField.setAccessible(true);
					roField.set(ro, entityField.get(entity));
				} catch (NoSuchFieldException e) {
					System.out.println("RO doesn't include field named: " + roField.getName());
					continue;
				}
			}
			
			return ro;
		} catch (InstantiationException e) {
			throw new ConversionException("Unable to instantiate ro object", e);
		} catch (IllegalAccessException e) {
			throw new ConversionException("Unable to modify ro object reflectively", e);
		} catch (SecurityException e) {
			throw new ConversionException("Unable to modify ro object reflectively", e);
		}
	}
}