CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(100)
);

CREATE TABLE sites (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    status VARCHAR(100) NOT NULL,
    created_by BIGINT,
    CONSTRAINT fk_sites_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id)
);

CREATE TABLE installations (
    id BIGSERIAL PRIMARY KEY,
    site_id BIGINT NOT NULL,
    installation_type VARCHAR(255) NOT NULL,
    status VARCHAR(100) NOT NULL,
    technician_id BIGINT,
    start_date DATE,
    completion_date DATE,
    CONSTRAINT fk_installations_site
        FOREIGN KEY (site_id)
        REFERENCES sites(id),
    CONSTRAINT fk_installations_technician
        FOREIGN KEY (technician_id)
        REFERENCES users(id)
);

CREATE INDEX idx_sites_status
    ON sites(status);

CREATE INDEX idx_installations_status
    ON installations(status);

CREATE INDEX idx_installations_site_id
    ON installations(site_id);