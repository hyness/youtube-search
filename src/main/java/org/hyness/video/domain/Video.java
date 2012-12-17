package org.hyness.video.domain;

import com.google.common.base.Objects;

/**
 * @author Hy Goldsher
 */
public class Video {
	private String id;

	private String title;

	private String description;

	private String category;

	private int viewCount;

	private int favoriteCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("title", title)
				.add("description", description).add("category", category)
				.add("viewCount", viewCount)
				.add("favoriteCount", favoriteCount).toString();
	}
}
