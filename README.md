# Meeting Room Booking System

## 제약조건

- 30분단위로 예약 가능 : 30분을 한 Unit으로 처리
0: 00:00~00:30
1: 00:30~01:00
.
.
.
47: 23:30~24:00

- 한명이 동시에 여러개의 회의실을 예약할수는 있음
- 여러명이 하나의 회의실을 에약할 수는 없음


## 전략
- 특정일자, 특정사용자로 쿼리하기 유리하도록 설계