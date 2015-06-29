package org.hyness.video.domain;

import static lombok.AccessLevel.NONE;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String etag;
    private String prevPageToken;
    private String nextPageToken;
    @Getter(NONE) private PageInfo pageInfo = new PageInfo();
    private List<Item> items;
    
    public int getTotalResults() {
        return pageInfo.totalResults;
    }
    
    public int getResultsPerPage() {
        return pageInfo.resultsPerPage;
    }

    @Setter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PageInfo {
        private int totalResults;
        private int resultsPerPage;
    }

    @Setter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        @Getter
        private String etag;
        private Id id = new Id();
        private Snippet snippet = new Snippet();
        
        public String getId() {
            return id.videoId;
        }
        
        public String getPublishedAt() {
            return snippet.publishedAt;
        }

        public String getTitle() {
            return snippet.title;
        }

        public String getDescription() {
            return snippet.description;
        }

        @Setter
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class Id {
            private String videoId;
        }
    }
    
    @Setter
    @ToString
    private static class Snippet {
        private String publishedAt;
        private String title;
        private String description;
    }
}
