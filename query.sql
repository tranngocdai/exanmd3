CREATE database thi;
use thi;
create table Category(
                         categoryId int auto_increment primary key,
                         categoryName varchar(255) not null
);

CREATE table Product(
                        productId int auto_increment primary key,
                        productName varchar(255),
                        productPrice int not null,
                        productQuantity int not null,
                        productColor varchar(255) not null,
                        productDesc varchar(255) not null,
                        categoryId int not null,
                        foreign Key (categoryId) references Category(categoryId)
);