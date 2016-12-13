-- 커피
DROP SCHEMA IF EXISTS coffee;

-- 커피
CREATE SCHEMA coffee;

-- 제품정보
CREATE TABLE coffee.info (
	code   VARCHAR(10) NULL     COMMENT '제품코드', -- 제품코드
	title  VARCHAR(10) NULL     COMMENT '제품명', -- 제품명
	price  INT(8)      NOT NULL COMMENT '제품단가', -- 제품단가
	amount INT(8)      NOT NULL COMMENT '판매수량', -- 판매수량
	profit INT(2)      NOT NULL COMMENT '마진율' -- 마진율
)
COMMENT '제품정보';

-- 기본정보
CREATE TABLE coffee.df_info (
	code  VARCHAR(10) NOT NULL COMMENT '제품코드', -- 제품코드
	title VARCHAR(10) NOT NULL COMMENT '제품명' -- 제품명
)
COMMENT '기본정보';

-- 기본정보
ALTER TABLE coffee.df_info
	ADD CONSTRAINT PK_df_info -- 기본정보 기본키
		PRIMARY KEY (
			code,  -- 제품코드
			title  -- 제품명
		);

-- 제품정보
ALTER TABLE coffee.info
	ADD CONSTRAINT FK_df_info_TO_info -- 기본정보 -> 제품정보
		FOREIGN KEY (
			code,  -- 제품코드
			title  -- 제품명
		)
		REFERENCES coffee.df_info ( -- 기본정보
			code,  -- 제품코드
			title  -- 제품명
		);

select * from df_info;

select * from info; 

select * from info i,df_info d where i.code=d.code;

insert into df_info values('A001','아메리카노');
insert into df_info values('A002','카푸치노'),('A003','헤이즐넛'),('A004','에스프레소'),('B001','딸기쉐이크'),('B002','후르츠와인'),('B003','팥빙수'),('B004','아이스초코');

insert into info values('A001','아메리카노',4500,1,10);
update info set amount=2 where code='A001'; 
delete from info where code='A002';
select * from info;
insert into info values('A001','아메리카노',4500,1,10);
select code, title, price, amount, profit from info;