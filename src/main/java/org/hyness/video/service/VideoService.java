/**
 * 
 */
package org.hyness.video.service;

import org.hyness.video.domain.Result;

/**
 * @author Hy Goldsher
 *
 */
public interface VideoService {

	Result search(String term);

	Result search(String term, int page);

	Result search(String term, boolean hd, int page);
	
	Result getMostPopular();

	Result getMostPopular(int page);

}