package com.tinyjira.util;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.tinyjira.dto.Bug;
import com.tinyjira.dto.Story;

/**
 * Utility for get fetching fields with null values.
 * 
 */
public class BeanUtil {
	
	public static String[] getIgnoredPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    return Stream.of(wrappedSource.getPropertyDescriptors())
	            .map(FeatureDescriptor::getName)
	            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null ||
	            		propertyName.equals(Constant.STATUS) && wrappedSource.getPropertyValue(propertyName) == Bug.Status.NEW ||
	            		propertyName.equals(Constant.STATUS) && wrappedSource.getPropertyValue(propertyName) == Story.Status.NEW)
	            .toArray(String[]::new);
	}
}
