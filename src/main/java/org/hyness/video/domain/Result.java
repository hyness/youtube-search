package org.hyness.video.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Hy Goldsher
 */
public class Result {
	private String apiVersion;
	
	private Data data;
	
	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
