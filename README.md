## 1. 기능
이 프로젝트는 Spring 기반의 칭찬 게시판 시스템으로, 사용자 관리, 포인트 부여 및 좋아요 기능을 통해 서로 칭찬하고 긍정적으로 소통할 수 있도록 도와줍니다.

- 게시글 작성, 조회, 수정, 삭제 기능: 사용자는 게시판에 칭찬 글을 작성할 수 있고, 글을 조회하거나 수정, 삭제할 수 있습니다. 이 과정에서 글 작성자와 칭찬 받은 사람에게는 포인트가 부여되며, 조회수와 좋아요 수도 관리됩니다.

- 사용자 관리 기능: 사용자의 회원가입, 로그인, 회원 탈퇴 및 마이페이지에서 비밀번호 변경 등의 기능을 제공합니다. 사용자 정보를 관리하며, 사용자가 작성한 게시글 목록을 확인할 수 있습니다.

- 포인트 시스템과 좋아요 기능: 게시글 작성, 칭찬 받기, 좋아요 클릭 시 사용자에게 포인트를 부여합니다. 좋아요는 세션을 통해 관리되며, 게시글에 좋아요를 누르거나 취소할 때마다 포인트가 조정되고, 좋아요 수가 업데이트됩니다.


## 2. 프로젝트 설계
### 1. ERD
<img src="https://github.com/Praise-Board-Project/Praise-Back-End/assets/113660954/3e2d863b-aa47-4812-92a8-890b7470b336" width="550" height="550">


### 2. UML 클래스 다이어그램



## 3. 분담
- 김세은 : 회원가입, 로그인/로그아웃, 마이페이지 등 사용자 관리 기능 구현
- 이민수 :
- 윤종욱 :
- 장유진 : 회원가입, 로그인/로그아웃, 마이페이지 등 사용자 관리 기능 구현

## 4. 트러블슈팅
- 전체 트러블 슈팅
  |**문제**|**해결**|
  |:---|:---|
  |Board Table의 recieve_id와 send_id 가 모두 User Table의 id를 조회해야 해서 두 테이블 사이의 관계를 정의할 때 M:N과 1:M 사이에서 고민함|1:M 관계로 User Table의 id와 Board Table의 receive_id, send_id를 각각 연결함|
  | |
- 회원관리 트러블 슈팅
  |**문제**|**해결**|
  |:---|:---|
  |로그인에 실패했을 때 오류 메시지가 출력되게 했지만, <br> index를 호출할 때 마다 Board List가 리다이렉트되기 때문에 '로그인 실패'문구가 고정되지 않고 사라짐|방법1) 로그인 메시지를 세션에 저장하여 호출함 <br> 방법2) 헤더를 고정시키고 Board list가 출력되는 부분만 변동되게끔 로직 수정 <br> --> 방법1 채택|
  |기존 회원의 탈퇴가 불가한 문제 = 외래 키 조회 문제|"On Delete Cascade" 를 이용하여 부모 행(User)을 삭제했을 때 자식 행(Board)도 삭제되게끔 수정|
- 타입 미스매치 오류(Type Mismatch Error)
  |**문제**|**해결**|
  |:---|:---|
  |fetch로 uri에 데이터값을 달아 서버로 데이터를 전송 할 때 Controller에서 받는 값과 uri로 보낸 자료형이 달라 타입 미스매치 오류가 발생하였다.|서버로 데이터를 전송할 때 URI에 포함된 값의 자료형을 서버가 처리할 수 있는 형태로 정확하게 변환해야한다. 따라서 uri에 값을 보낼 떄에는 String으로 처리한 다음 Controller에서 후처리를 하는 것이 좋다.|
  | |

## 5. 리뷰: KPT(Keep, Problem, Try)
- Keep: 잘 했기 때문에 유지하고 싶은 것
- Problem: 어려움을 느껴 개선하고 싶은 것
- Try: 구체적으로 시도할 내용

|**이름**|**Keep**|**Problem**|**Try**|
|:---:|:---:|:---:|:---:|
|세은||
|민수| 1. "MVC (Model-View-Controller)" 아키텍처에 맞춰서 개발을 진행하였다.<br> 2. 협업을 위해 노션, 피그마, 그리고 깃허브와 같은 도구들을 적극적으로 활용하여 개발 프로세스를 진행하였다.|1. 좋아요 기능은 현재 세션을 통해 구현되어 있으나, 데이터베이스를 활용하여 세션이 만료되어도 사용자가 한 번만 좋아요를 누를 수 있도록 개선하고 싶다. <br> 2. 현재는 게시판 활성화를 목적으로 게시글 삭제 기능을 제한하고 있으나, 관리자 권한을 통해 게시글을 관리할 수 있는 삭제 기능을 추가하는 것이 바람직한 것 같다.|1. 새로운 좋아요 기능을 위해, 각 좋아요가 발생할 때마다 보드 ID와 유저 ID를 기록하고, 좋아요 상태를 나타내는 0(좋아요 안 함) 또는 1(좋아요 함) 값을 가지는 컬럼을 포함하는 데이터베이스 테이블을 생성한다.<br> 2. Spring Security와 같은 보안 프레임워크를 사용한다. |
|종욱|| 1. 보드 작성 시 유저 목록에 자기 자신도 포함된다.<br> 2. detail 글에서 내용이 textarea로 보이는데, 자바스크립트로 익숙한 에디터를 사용할 수 있다.<br> 3. 이동 페이지가 지금은 숫자를 입력해야만 넘어가는데, 페이지네이션으로 구현하고 싶다.<br>|1. registboard.jsp에서 if 조건문을 추가한다.<br>2. Wysiwyg 웹 편집기 종류를 이용한다.
|유진|1. 제한된 시간 안에 프로젝트를 성공적으로 마무리했다.<br>2. JPA를 사용해 객체지향적으로 데이터를 관리하였다.<br>|1. 개발의 편의성을 위해 Entity와 동일한 DTO를 사용하였으나, 이로 인해 Controller에서 불필요한 데이터를 함께 전달하게 되었다.|1. 각 요청 별 DTO를 분리하여 불필요한 데이터를 노출하지 않고 각 DTO의 역할을 명확히한다.|
