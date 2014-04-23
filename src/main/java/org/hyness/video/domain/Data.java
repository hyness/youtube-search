package org.hyness.video.domain;

import java.util.Date;
import java.util.List;

import com.google.common.base.Objects;

public class Data {
	private int totalItems;

	private int startIndex;

	private int itemsPerPage;

	private Date updated;

	private List<Video> items;

	public int getTotalItems() {
		return totalItems;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public Date getUpdated() {
		return updated;
	}

	public List<Video> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("totalItems", totalItems)
				.add("statrtIndex", startIndex)
				.add("itemsPerPage", itemsPerPage).add("updated", updated)
				.add("items", items).toString();
	}
}
