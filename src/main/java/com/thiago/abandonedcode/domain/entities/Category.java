package com.thiago.abandonedcode.domain.entities;

import com.thiago.abandonedcode.domain.valueobjects.Slug;

import java.time.LocalDateTime;

public class Category {
    private Long id;
    private String name;
    private Slug slug;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Category(String name) {
        this.name = name;
        this.slug = Slug.fromTitle(name);
        this.parentId = null;
        this.createdAt = LocalDateTime.now();
    }

    public Category(String name, Long parentId) {
        this.name = name;
        this.slug = Slug.fromTitle(name);
        this.parentId = parentId;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isRoot() {
        return this.parentId == null;
    }

    public void changeParent(Long newParentId){
        if(newParentId != null && newParentId.equals(this.id)){
            throw new IllegalStateException("Categoria não pode ser pai de sí mesma");
        }
        this.parentId = newParentId;
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String name){
        this.name = name;
        this.slug = Slug.fromTitle(name);
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Slug getSlug() {
        return slug;
    }

    public Long getParentId() {
        return parentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
