package org.hyness.video.domain;

import com.google.common.base.Objects;

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
		return Objects.toStringHelper(this).add("apiVersion", apiVersion)
				.add("data", data).toString();
	}
}
