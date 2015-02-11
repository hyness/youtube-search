package org.hyness.video.domain;

import lombok.Getter;
import lombok.ToString;

import org.hyness.video.domain.Video.Builder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Getter
@ToString
@JsonDeserialize(builder = Builder.class)
public class Video {
	private final String id;
	private final String title;
	private final String description;
	private final String category;
	private final int viewCount;
	private final int favoriteCount;
	
	private Video(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.description = builder.description;
		this.category = builder.category;
		this.viewCount = builder.viewCount;
		this.favoriteCount = builder.favoriteCount;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Builder {
		private String id;
		private String title;
		private String description;
		private String category;
		private int viewCount;
		private int favoriteCount;

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withCategory(String category) {
			this.category = category;
			return this;
		}

		public Builder withViewCount(int viewCount) {
			this.viewCount = viewCount;
			return this;
		}

		public Builder withFavoriteCount(int favoriteCount) {
			this.favoriteCount = favoriteCount;
			return this;
		}
		
		public Video build() {
			return new Video(this);
		}
	}
}
