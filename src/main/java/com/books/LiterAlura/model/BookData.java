package com.books.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData (
        @JsonAlias("title") String title,
        @JsonAlias("Authors") List<AuthorData> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Long downloadCount,
        @JsonAlias("copyright") Boolean copyright
        ) {
}
