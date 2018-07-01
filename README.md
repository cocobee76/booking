# Meeting Room Booking System

## 문제해결 전략

### 기술셋
- Backend : SpringBoot, JPA 
- FrontEnd : Mustache Template Engine, JQuery, Moment.js

###  DB 설계
- H2 사용(스프링부트 기동시 실행 됨)
- 주요테이블

RESERVATION: 예약 정보가 저장되는 테이블, 예약날짜(date), 회의실(room_no), 슬롯 번호(slot_no)를 Composit Key로 사용하여 중첩 예약을 차단함. 
30분 단위로 예약이 가능하기 때문에 24시간을 48개의 슬롯으로 나누어 슬롯 번호를 사용하여 관리함.
```
date date not null,
room_no integer not null,
slot_no integer not null,
emp_no integer not null,
created_at timestamp,
modified_at timestamp,
primary key (date, room_no, slot_no)
```

ROOM: 미팅룸 정보

```
id bigint generated by default as identity,
name varchar(20),
primary key (id)
```

EMP: 사용자 정보
```
emp_no bigint not null,
name varchar(20),
primary key (emp_no)
```

## 주요 클래스 및 리소스
- ReservationController: 컨트롤러 클래스(조회, 예약 생성)
- ReservationService: 주요 비즈니스 로직
- ReservationMapper: Entity -> Dto 변환 매퍼
- reservation.html: 주요 정적 html 파일 
- reservation.js: FE 스크립트

## 화면설명
##### 메인 화면

![2018-07-02 01 40 39](https://user-images.githubusercontent.com/30816986/42136572-0091117a-7d99-11e8-9495-21c0af216ae2.PNG)
- Make a Reservation 버튼: 예약 레이어 표시 버튼
- Pick a date: 하단 그리드에 표시할 날짜를 선택하는 DatePicker
- 하단 그리드: 가로열은 예약할 회의실 세로열은 예약할 시간 슬롯을 나타내며 각 셀에 해당 회의실과 시간대의 예약자가 표시된다. 



##### 예약 화면

![2018-07-02 01 47 34](https://user-images.githubusercontent.com/30816986/42136616-edbd6dcc-7d99-11e8-952a-0227fd065967.PNG)
- User: 회의실을 예약할 유저
- Date: 날짜 선택 DatePicker
- Room: 회의실 선택
- Time: 예약 시간 선택
- 반복예악: 체크시 반복 예약 횟수를 입력할 수 있는 Input이 활성화. 입력한 횟수대로 선택한 날짜를 포함하여 향후 동일한 요일에 해당 횟수만큼 예약 됨 

## 빌드 및 실행 방법

- 빌드
```
gradlew clean compileJava

```

- 실행
```
gradlew bootRun

```

- 페이지 접속
```
localhost:8080
```


 
