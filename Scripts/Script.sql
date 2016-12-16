-- 커피
DROP SCHEMA IF EXISTS coffee;

-- 커피
CREATE SCHEMA coffee;
drop table coffee.sale;
-- 제품정보
CREATE TABLE coffee.sale (
	code   VARCHAR(10) NULL     COMMENT '제품코드', -- 제품코드
	title  VARCHAR(10) NULL     COMMENT '제품명', -- 제품명
	price  INT(8)      NOT NULL COMMENT '제품단가', -- 제품단가
	amount INT(8)      NOT NULL COMMENT '판매수량', -- 판매수량
	margin INT(2)      NOT NULL COMMENT '마진율', -- 마진율 
	primary key (code)
)
COMMENT '제품정보';

-- 기본정보
CREATE TABLE coffee.product (
	code  VARCHAR(10) NOT NULL COMMENT '제품코드', -- 제품코드
	title VARCHAR(10) NOT NULL COMMENT '제품명' -- 제품명
)
COMMENT '기본정보';

-- 기본정보
ALTER TABLE coffee.product
	ADD CONSTRAINT PK_product -- 기본정보 기본키
		PRIMARY KEY (
			code,  -- 제품코드
			title  -- 제품명
		);

-- 제품정보
ALTER TABLE coffee.sale
	ADD CONSTRAINT FK_product_TO_sale -- 기본정보 -> 제품정보
		FOREIGN KEY (
			code,  -- 제품코드
			title  -- 제품명
		)
		REFERENCES coffee.product ( -- 기본정보
			code,  -- 제품코드
			title  -- 제품명
		);

select * from product;

select * from sale; 

select * from sale i,product d where i.code=d.code;

insert into product values('A001','아메리카노');
insert into product values('A002','카푸치노'),('A003','헤이즐넛'),('A004','에스프레소'),('B001','딸기쉐이크'),('B002','후르츠와인'),('B003','팥빙수'),('B004','아이스초코');

insert into sale values('A001','아메리카노',4500,1,10);
update sale set amount=2 where code='A001'; 
delete from sale where code='B002';
select * from output_t;
select * from sale;
insert into sale values('A001','아메리카노',4500,1,10);
select code, title, price, amount, margin from sale;
select * from sale;
alter table sale drop column profit;
alter table sale add column margin INT(2) NOT NULL COMMENT '마진율'; -- 마진율;
select code,title,price,amount,margin from sale;
select price*amount from sale;
desc sale;
select s.code,s.title,price,amount,margin from sale s,product p where s.code=p.code;
select p.code, p.title from product p;
select sum(price) , sum(amount) ,sum(margin) from sale;

select sum(supply_price) , sum(addTax) ,sum(sale_price),  sum(margin_price) from sale;

select code,title,price,amount,margin,(sale_price-addTax) supply_price,
										(sale_price/11) addTax,
										(price*amount) sale_price,
										(supply_price*margin) margin_price 
										from sale;

create view output_t as
select code,title,price,amount,margin, (price*amount) sale_price ,
										(price*amount)/11 addTax,
										(price*amount)-((price*amount)/11) supply_price,
										(((price*amount)-((price*amount)/11))*margin) margin_price
										from sale;
select * from output_t;

select code, title, sum(price) , sum(amount) ,margin,sum(sale_price),addTax,sum(supply_price),sum(margin_price) from output_t;

select (select count(*)+1 from output_t where sale_price > t.sale_price) as rank,code,title,price,amount,margin,sale_price,addTax,supply_price,margin_price 
from output_t t
order by rank;-- 판매금액순

select (select count(*)+1 from output_t where margin_price > o.margin_price) as rank, o.*
from output_o o
order by rank;-- 마진액순

create view output_o as
select * from output_t;

select * from output_o;
select * from output_t;

select (select count(*)+1 from output_t t where t.margin_price < margin_price) rank , s.code,s.title,price,amount,margin, (price*amount) sale_price ,
										(price*amount)/11 addTax,
										(price*amount)-((price*amount)/11) supply_price,
										(((price*amount)-((price*amount)/11))*margin) margin_price
										from sale s inner join product p on s.code = p.code order by (price*amount);
from sale inner join sale_detail s1 on sale.code = s1.code join product p on s1.code = p.code order by rank;


