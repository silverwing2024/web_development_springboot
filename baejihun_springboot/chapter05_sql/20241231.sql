-- 주석 처리 하는 방법
-- 1. SELECT : 보여달라/조회해라 
select "Hello, SQL!";
-- select : 보여달라 / Hello, SLQ! 이라는 str

select 12+7; -- SQL문을 통한 연산이 가능하다.

-- 결과 창의 첫 행에 각각 Find, Insight, with SQL을 3개의 칸에 
-- 걸쳐 순서대로 출력하는데, 컬럼명을 순서대로 First, Second, Third로 출력

select	"Find" as 'First',
		"Insight" as 'Second',
		"with SQL" as 'Third';
-- 이런 식으로 작성하는 이유는 모두가 사람이 알아보기 쉽게 하기 위해서이다. 
-- as : alias 알리아스라고 발음하는데, 데이터가 들어가는 부분에 대한 컬럼에 대한 별칭을 
-- 붙일 때 사용하는 구문
-- 쉼표(,)의 역할 : 나열할 때 사용
-- SQL문의 특징 : 큰따옴표, 작은따옴표, 따옴표없음 구분하지 않는다.

-- 연습문제
-- 1. SELECT를 이용하여 28 + 891의 결과를 표시하라
select 28  + 891;

-- 2. SELECT를 이용하여 19X27의 결과를 표시하라. 단 컬럼명은 Result로 표시하라.
select 19*27 as 'Result';

-- 3. SELECT를 이용하여 다음 세 가지 결과를 각각 다른 칼럼으로 결과 창에 표시하라. 
	-- 37 + 172(단, 컬럼명은 Plus)
	-- 25 X 78 (컬럼며은 Times)
	-- I love SQL(컬럼명은 Result) 
select 37+172 as 'Plus',
		27*78 as 'Times',
		'I love SQL' as 'Result';

-- 2. FROM : ~로 부터 + 테이블명
-- FROM은 데이터가 저장된 위치를 나타냄.

select *
	from users; 
-- users.csv 파일에 있었던 모든 테이블과 컬럼과 값이 출력됬음을 확인 가능
-- -- * : asterisk = all : 와일드카드 표시 // java
-- select * from users; : users 테이블에 있는 모든 컬럼의 값을 조회하라

-- 연습문제 2 : 제품 정보 테이블 products에 있는 모든 데이터를 출력하시오.
select * from products;
-- 한 줄로 쓰는 것도 가능 -> 긴 쿼리 문을 쓸 때는 가독성 때문에 개행을 하게 되는데, 
-- 차근차근 연습하기 위해서 한 줄로 쓸 때도 있고 나눌 때도 있을 예정
-- 원래 제가 쿼리문 명령어에 해당하는 부분은 가독성 때문에 대문자를 쓸 예정이었는데
-- sql은 대소문자를 구분하지 않습니다. 
-- LIMIT : 개수 제한을 거는 명령어(IDE에 따라 TOP인 경우도 있음)

select * from products p limit 10;
-- 이렇게 *를 사용해서 저체 정보를 조회하는 것을 full scan을 쓰는데, 빈도가 높지 않습니다.
-- 물론 시험에도 자주 나오지는 않음.

--개수 제한을 걸기 위한 LIMIT와 특정 컬럼을 조회하는 형태로 수업을 진행할 예정.

-- SELECT 컬럼명 1, 컬럼명 2 from 테이블 명;
-- 제품 정보 테이블인 products에서 제품 아이디(id), 제품명(name) 컬럼만 출력합니다. 
select id, name from products p ;

-- products에서 가격(price), 할인가(discount_price) 컬럼만 10개만 출력합니다.
select  price, discount_price 
	from products 
	limit 10;

-- SQL문의 경우에는 순서가 매우 명확하게 정해져 있는 편이므로
-- 읽기는 쉽지만 직접 작성하는 것이 까다로운 편

-- orderdetails의 모든 정보 표시하시오
SELECT * FROM orderdetails o ;

-- 회원 정보 테이블에서 users에서 상위 7건만 표시하세요. (전부 다)
SELECT * FROM users u LIMIT 7;

-- orders에서 id, user_id, order_date 컬럼의 데이터를 모두 표시
SELECT id, user_id, order_date FROM orders o ;

