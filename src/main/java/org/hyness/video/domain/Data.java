package org.hyness.video.domain;

import static lombok.AccessLevel.NONE;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(NONE)
@ToString
public class Data {
	private int totalItems;
	private int startIndex;
	private int itemsPerPage;
	private Date updated;
	private List<Video> items;
}
