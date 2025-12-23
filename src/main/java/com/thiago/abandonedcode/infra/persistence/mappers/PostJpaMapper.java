package com.thiago.abandonedcode.infra.persistence.mappers;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.valueobjects.Content;
import com.thiago.abandonedcode.domain.valueobjects.Excerpt;
import com.thiago.abandonedcode.domain.valueobjects.Slug;
import com.thiago.abandonedcode.domain.valueobjects.ViewCount;
import com.thiago.abandonedcode.infra.persistence.entities.CategoryJpaEntity;
import com.thiago.abandonedcode.infra.persistence.entities.PostJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class PostJpaMapper {

    private final CategoryJpaMapper categoryJpaMapper;

    public PostJpaMapper(CategoryJpaMapper categoryJpaMapper){
        this.categoryJpaMapper = categoryJpaMapper;
    }

    public Post toDomain(PostJpaEntity entity){
        if (entity == null) {
            return null;
        }

        Category category = null;
        if (entity.getCategory() != null) {
            category = categoryJpaMapper.toDomain(entity.getCategory());
        }
        Content content = new Content(entity.getContent());
        Excerpt excerpt = new Excerpt(entity.getExcerpt());

        Post post = new Post(
                entity.getTitle(),
                content,
                excerpt,
                category
        );

        post.setId(entity.getId());
        post.setSlug(new Slug(entity.getSlug()));
        post.setViewCount(new ViewCount(entity.getViewCount()));
        post.setStatus(entity.getStatus());
        post.setFeatureImage(entity.getFeatureImage());
        post.setCreatedAt(entity.getCreatedAt());
        post.setUpdatedAt(entity.getUpdatedAt());
        post.setPublishedAt(entity.getPublishedAt());

        return post;
    }

    public PostJpaEntity toJpaEntity(Post post){
        if (post == null) {
            return null;
        }

        PostJpaEntity entity = new PostJpaEntity();

        if (post.getId() != null) {
            entity.setId(post.getId());
        }
        entity.setTitle(post.getTitle());
        entity.setFeatureImage(post.getFeatureImage());
        entity.setStatus(post.getStatus());
        entity.setCreatedAt(post.getCreatedAt());
        entity.setUpdatedAt(post.getUpdatedAt());
        entity.setPublishedAt(post.getPublishedAt());
        entity.setContent(post.getContent().value());
        entity.setExcerpt(post.getExcerpt().value());
        entity.setViewCount(post.getViewCount().value());
        entity.setSlug(post.getSlug().value());

        if (post.getCategory() != null) {
            CategoryJpaEntity categoryEntity = categoryJpaMapper.toJpaEntity(post.getCategory());
            entity.setCategory(categoryEntity);
        }

        return entity;
    }
}
