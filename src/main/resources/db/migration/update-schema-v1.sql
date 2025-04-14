CREATE TABLE product
(
    id            BIGINT       NOT NULL,
    created_at    datetime     NULL,
    updated_at    datetime     NULL,
    state         SMALLINT     NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    is_prime      BIT(1)       NOT NULL,
    category_id   BIGINT       NULL,
    price         BIGINT       NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    id            BIGINT       NOT NULL,
    created_at    datetime     NULL,
    updated_at    datetime     NULL,
    state         SMALLINT     NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_productcategory PRIMARY KEY (id)
);

CREATE TABLE test_model
(
    id         BIGINT       NOT NULL,
    created_at datetime     NULL,
    updated_at datetime     NULL,
    state      SMALLINT     NULL,
    num_field  INT          NOT NULL,
    text_field VARCHAR(255) NULL,
    CONSTRAINT pk_testmodel PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES product_category (id);