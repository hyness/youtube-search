package org.hyness.video.domain;

import static lombok.AccessLevel.NONE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter(NONE)
@ToString
public class Result {
	private String apiVersion;
	private Data data;
}
