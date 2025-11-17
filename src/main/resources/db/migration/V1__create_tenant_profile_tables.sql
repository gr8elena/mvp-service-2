CREATE TABLE IF NOT EXISTS user_profile_tenant1 (
                                                    id            BIGSERIAL PRIMARY KEY,
                                                    user_id       BIGINT    NOT NULL,
                                                    name          VARCHAR(255) NOT NULL,
                                                    surname       VARCHAR(255) NOT NULL,
                                                    education     TEXT,
                                                    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    CONSTRAINT uq_user_tenant1 UNIQUE (user_id)
);

CREATE INDEX IF NOT EXISTS idx_user_tenant1 ON user_profile_tenant1 (user_id);

CREATE TABLE IF NOT EXISTS user_profile_tenant2 (
                                                    id            BIGSERIAL PRIMARY KEY,
                                                    user_id       BIGINT    NOT NULL,
                                                    name          VARCHAR(255) NOT NULL,
                                                    surname       VARCHAR(255) NOT NULL,
                                                    education     TEXT,
                                                    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                    CONSTRAINT uq_user_tenant2 UNIQUE (user_id)
);

CREATE INDEX IF NOT EXISTS idx_user_tenant2 ON user_profile_tenant2 (user_id);