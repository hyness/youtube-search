package org.hyness.video.domain;

import com.google.common.base.MoreObjects;

public class Result {
	private String apiVersion;

	private Data data;

	public String getApiVersion() {
		return apiVersion;
	}

	public Data getData() {
		return data;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("apiVersion", apiVersion)
				.add("data", data).toString();
	}
}
