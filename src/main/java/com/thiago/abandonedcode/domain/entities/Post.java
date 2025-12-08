package com.thiago.abandonedcode.domain.entities;

import com.thiago.abandonedcode.domain.enums.PostStatus;
import com.thiago.abandonedcode.domain.valueobjects.Content;
import com.thiago.abandonedcode.domain.valueobjects.Slug;
import com.thiago.abandonedcode.domain.valueobjects.ViewCount;

import java.time.LocalDateTime;

public class Post {

    private Long id;
    private String title;
    private Content content;
    private String excerpt;
    private ViewCount viewCount;
    private Slug slug;
    private String featureImage;
    private PostStatus status;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    public Post(String title, Content content, String excerpt, Category category) {
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.slug = Slug.fromTitle(title);
        this.viewCount = ViewCount.zero();
        this.status = PostStatus.DRAFT;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    public void publish() {
        if (this.status == PostStatus.PUBLISHED) {
            throw new IllegalStateException("Post já foi publicado");
        }
        this.status = PostStatus.PUBLISHED;
        this.publishedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void incrementViews() {
        this.viewCount = this.viewCount.increment();
    }

    public void update(String title, Content content, String excerpt) {
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.slug = Slug.fromTitle(title);
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public ViewCount getViewCount() {
        return viewCount;
    }

    public void setViewCount(ViewCount viewCount) {
        this.viewCount = viewCount;
    }

    public Slug getSlug() {
        return slug;
    }

    public void setSlug(Slug slug) {
        this.slug = slug;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}
