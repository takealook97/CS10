🎯CS10 프로세스 스케줄링 시각화
=

# 프로세스와 스레드

- 프로세스 : 컴퓨터에서 실행되고 있는 프로그램. CPU 스케줄링의 대상이 되는 작업(task)이다.
- 스레드 : 프로세스 내 작업의 흐름
- 프로그램이 메모리에 올라간다 → 프로세스가 되는 인스턴스화 → CPU 스케줄러에 따라 프로세스 실행

# 프로세스와 컴파일 과정

- 프로그램은 컴파일러가 컴파일 과정을 거쳐 컴퓨터가 이해할 수 잇는 기계어로 번역되어 실행될 수 있는 파일이 되는 것을 의미한다. 여기서의 프로그램은 C언어 기반의 프로그램을 의미하며 이는 별도의 컴파일 과정 없이 한 번에 한 줄씩 읽어들여서 실행하는 프로그램인 인터프리티어 언어(파이썬)로 된 프로그램과는 다르다.
- 프로그램의 컴파일 과정
    - 전처리 : 소스 코드의 주석을 제거하고 #include 등 헤더 파일을 병합하여 매크로를 치환한다.
    - 컴파일러 : 오류 처리, 코드 최적화 작업을 하며 어셈블리어로 변환한다.
    - 어셈블러 : 어셈블리어는 목적코드(object code)로 변환된다. 이 때 확장자는 운영체제마다 다르다.
    - 링커 : 프로그램 내에 있는 라이브러리 함수 또는 다른 파일들과 목적 코드를 결합하여 실행 파일을 만든다. → .exe , .out
        - 라이브러리
            - 정적 라이브러리 : 프로그램 빌드 시 라이브러리가 제공하는 모든 코드를 실행파일에 넣는 방식. 시스템 환경 등 외부 의존도가 낮고 코드 중복 등 메모리 효율성이 떨어지는 단점이 있다.
            - 동적 라이브러리 : 프로그램 실행 시 필요할 때만 DLL이라는 함수 정보를 통해 참조하는 방식.

              메모리 효율성에서의 장점과 외부 의존도가 높아진다는 단점이 있다.
# 프로세스의 상태

- 프로세스의 상태는 여러가지 상태 값을 갖는다.
- 생성 상태 (create)
    - 프로세스가 생성된 상태를 의미하며 `fork()` 또는 `exec()` 함수를 통해 생성한다. 이때 PCB가 할당된다.
    - `fork()` : 부모 프로세스의 주소 공간을 그대로 복사하며, 새로운 자식 프로세스를 생성하는 함수. 주소 공간만 복사할 뿐 부모 프로세스의 비동기 작업 등을 상속하지는 않는다.
    - `exec()` : 새롭게 프로세스를 생성하는 함수
- 대기 상태 (ready)
    - 메모리 공간이 충분하면 메모리를 할당받고 아니면 아닌 상태로 대기한다.
    - CPU 스케줄러로부터 CPU 소유권이 넘어오기를 기다리는 상태이다.
- 대기 중단 상태 (ready suspended) : 메모리 부족으로 일시 중단된 상태
- 실행 상태 (running) : CPU 소유권과 메모리를 할당받고 인스트럭션을 수행 중인 상태(= CPU burst 발생)
- 중단 상태 (blocked)
    - 어떤 이벤트가 발생한 이후 기다리며 프로세스가 차단된 상태이다.
    - I/O 디바이스에 의한 인터럽트로 이런 현상이 많이 발생하기도 한다.
- 일시 중단 상태 (blocked suspended)
    - 대기 중단과 유사하다
    - 중단된 상태에서 프로세스가 실행되려고 했지만 메모리 부족으로 일시 중단된 상태이다.
- 종료 상태 (terminated)
    - 메모리와 CPU 소유권을 모두 놓고 가는 상태이다.
    - 종료는 자연스럽게 종료되는 것도 있지만 부모 프로세스가 자식 프로세스를 강제시키는 비자발적 종료(abort)로 종료되는 것도 있다.
    - 자식 프로세스에 할당된 자원의 한계치를 넘어서거나 부모 프로세스가 종료되거나
      사용자가 process.kill 등 여러 명령어로 프로세스를 종료할 때 발생한다.

# 프로세스의 메모리 구조
- 스택은 위 주소부터 할당되고 힙은 아래 주소부터 할당된다.
- 스택
    - 지역변수, 매개변수, 함수가 저장된다. 컴파일 시에 크기가 결정되며 ‘동적’인 특징을 갖는다.
    - 함수가 함수를 재귀적으로 호출하면서 동적으로 크기가 늘어날 수 있다.

      → 힙과 스택의 메모리 영역이 겹치면 안되기 때문에 힙과 스택 사이의 공간을 비워 놓는다.

- 힙
    - 동적 할당할 때 사용되며 런타임 시 크기가 결정된다. ex) 벡터
    - ‘동적’인 특징을 갖는다.
- 데이터 영역
    - 전역변수, 정적변수가 저장되고 정적인 특징을 갖는 프로그램이 종료되면 사라지는 변수가 들어있는 영역
    - 데이터 영역은 BSS 영역과 Data 영역으로 나뉜다. BSS 영역은 초기화가 되지 않은 변수가 0으로 초기화되어 저장되며 Data 영역은 0이 아닌 다른 값으로 할당 된 변수들이 저장된다.
- 코드 영역
    - 프로그램에 내장되어 있는 소스 코드가 들어가는 영역이다
    - 수정 불가능한 기계어로 저장되어 있으며 정적인 특징을 가진다.

# PCB(Proccess Control Block)

- PCB : 운영체제에서 프로세스에 대한 메타데이터를 저장한 ‘데이터’를 말한다. (= 프로세스 제어 블럭)
  - 메타데이터 : 데이터에 관한 구조화된 데이터이자 데이터를 설명하는 작은 데이터. 대량의 정보 가운데서 찾고 있는 정보를 효율적으로 찾아서 이용하기 위해 일정한 규칙에 따라 콘텐츠에 대해 부여되는 데이터
- 프로세스가 생성되면 운영체제는 해당 PCB를 생성한다.
- 프로그램이 실행 → 프로세스가 생성 → 프로세스 주소 값들에 스택, 힙 등의 구조를 기반으로 메모리가 할당 → 이 프로세스의 메타데이터들이 PCB에 저장되어 관리
- 프로세스의 중요한 정보를 포함하고 있기 때문에 일반 사용자가 접근할 수 없도록 커널 스택의 가장 앞부분에서 관리된다.
- PCB의 구조
  - 프로세스 스케줄링 상태 : 준비, 일시중단 등 프로세스가 CPU에 대한 소유권을 얻은 이후의 상태
  - 프로세스 ID : 프로세스 ID, 해당 프로세스의 자식 프로스세스 ID
  - 프로세스 권한 : 컴퓨터 자원 또는 I/O 디바이스에 대한 권한 정보
  - 프로그램 카운터 : 프로세스에서 실행해야 할 다음 명령어의 주소에 대한 포인터
  - CPU 레지스터 : 프로세스를 실행하기 위해 저장해야 할 레지스터에 대한 정보
  - CPU 스케줄링 정보 : CPU 스케줄러에 의해 중단된 시간 등에 대한 정보
  - 계정 정보 : 프로세스 실행에 사용된 CPU 사용량, 실행한 유저의 정보
  - I/O 상태 정보 : 프로세스에 할당된 I/O 디바이스 목록
- 컨텍스트 스위칭
  - PCB를 교환하는 과정
  - 한 프로세스에 할당된 시간이 끝나거나 인터럽트에 의해 발생한다.
  - 컴퓨터는 많은 프로그램을 동시에 실행하는 것 처럼 보이지만 어떠한 시점에서 실행되고 있는 프로세스는 하나이다. → 아주 빠른속도의 컨텍스트 스위칭 (많은 프로세스가 동시에 구동되는 것 처럼 보인다)
  - 현대의 컴퓨터는 멀티코어의 CPU → 한 시점에 한개의 프로그램 X (싱글코어를 기준으로 설명할 뿐이다)
  - 비용 : 컨텍스트 스위칭 시 유휴시간 발생 + 캐시미스
    - 캐시미스 : 프로세스가 가지고 있는 메모리 주소가 그대로 있으면 잘못된 주소 변환이 생기므로 캐시클리어 → 캐시미스 발생
  - 스레드에서의 컨텍스트 스위칭
    - 스레드는 스택 영역을 제외한 모든 메모리를 공유
    - 스레드 컨텍스트 스위칭의 경우 비용이 더 적고 시간도 더 적게 걸린다.