DROP TABLE IF EXISTS PRODUCTS;
CREATE TABLE PRODUCTS (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  name VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  image_url VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS WISH_PRODUCTS;
CREATE TABLE WISH_PRODUCTS (
    member_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (member_id) references MEMBER(id),
    FOREIGN KEY (product_id) references PRODUCTS(id),
    PRIMARY KEY (member_id, product_id)
)
