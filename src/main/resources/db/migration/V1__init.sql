DROP TABLE IF EXISTS books;

CREATE TABLE books (
    id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    price VARCHAR(50) NOT NULL
);

INSERT INTO books VALUES ('1', 'ワンピース', 'oda', 'ジャンプ', '300');
INSERT INTO books VALUES ('2', '進撃の巨人', 'higashi', 'ジャンプ', '400');
INSERT INTO books VALUES ('3', 'ハンターハンター', 'togashi', 'ジャンプ', '500');
INSERT INTO books VALUES ('4', 'NARUTO', 'kishi', 'ジャンプ', '600');
