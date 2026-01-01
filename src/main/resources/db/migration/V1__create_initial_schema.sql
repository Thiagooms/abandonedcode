CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    parent_id BIGINT REFERENCES categories(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT uk_category_slug_per_parent UNIQUE (parent_id, slug)
);

CREATE UNIQUE INDEX uk_category_root_slug ON categories(slug) WHERE parent_id IS NULL;

CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    excerpt TEXT,
    view_count BIGINT NOT NULL DEFAULT 0,
    slug VARCHAR(255) NOT NULL UNIQUE,
    feature_image VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    category_id BIGINT REFERENCES categories(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    published_at TIMESTAMP
);
