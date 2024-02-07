## 1. 기능
- 

## 2. 프로젝트 설계
### 1. ERD
<img src="https://github.com/Praise-Board-Project/Praise-Back-End/assets/113660954/3e2d863b-aa47-4812-92a8-890b7470b336" width="550" height="550">


### 2. UML 클래스 다이어그램



## 3. 분담
- 김세은
- 이민수
- 윤종욱
- 장유진 

## 4. 트러블슈팅
- 전체 트러블 슈팅
  
  |**문제**|**해결**|
  |:---|:---|
  |Board Table의 recieve_id와 send_id 가 모두 User Table의 id를 조회해야 해서 두 테이블 사이의 관계를 정의할 때 M:N과 1:M 사이에서 고민함|1:M 관계로 User Table의 id와 Board Table의 각 id를 따로 연결해줌|
  | |
- 회원관리 트러블 슈팅
  |**문제**|**해결**|
  |:---|:---|
  |로그인에 실패했을 때 해당 문구가 출력되게 했지만, <br> index를 호출할 때 마다 board List가 리다이렉트되기 때문에 '로그인 실패'문구가 고정되지 않고 사라짐|방법1) 로그인 메시지를 세션에 저장하여 호출함 <br> 방법2) header를 고정시키고 board list가 출력되는 부분만 변동되게끔 로직 수정 <br> --> 방법1 채택|
  |기존 회원의 탈퇴가 불가한 문제 = 외래 키 조회 문제|On Delete Cascade 를 적용하여 부모 행(User)을 삭제했을 때 자식 행(Board)도 삭제되게끔 수정|

## 5. 리뷰: KPT(Keep, Problem, Try)
- Keep: 잘 했기 때문에 유지하고 싶은 것
- Problem: 어려움을 느껴 개선하고 싶은 것
- Try: 구체적으로 시도할 내용

|**이름**|**Keep**|**Problem**|**Try**|
|:---:|:---:|:---:|:---:|
|세은||
|민수||||
|종욱|||
|유진||
