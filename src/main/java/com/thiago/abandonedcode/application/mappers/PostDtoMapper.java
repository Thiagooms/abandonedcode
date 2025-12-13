package com.thiago.abandonedcode.application.mappers;

import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.valueobjects.Content;
import com.thiago.abandonedcode.domain.valueobjects.Excerpt;
import com.thiago.abandonedcode.presentation.dto.PostResponse;

public class PostDtoMapper {

    public static PostResponse toResponse(Post post) {
        return new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getSlug().value(),
            post.getContent().value(),
            post.getExcerpt().value(),
            post.getViewCount().value(),
            post.getFeatureImage(),
            post.getStatus().name(),
            post.getCategory() != null ?
            CategoryDtoMapper.toResponse(post.getCategory()) : null,
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getPublishedAt()
        );
    }

    public static Content toContent(String contentStr) {
        return new Content(contentStr);
    }

    public static Excerpt toExcerpt(String excerptStr, Content content) {
        if(excerptStr != null && !excerptStr.isBlank()) {
            return new Excerpt(excerptStr);
        }
        return Excerpt.fromContent(content);
    }
}
