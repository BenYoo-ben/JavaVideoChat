### Java Video Chat  

[KR]  
Java를 통해 작성한 영상 채팅 프로그램입니다.  
Client와 Server 코드로 나누어 작성되어 있습니다.  
Client는 Linux 또는 Windows 환경에서 실행이 가능하며 Server는 Linux 환경이 필요합니다.  


#### 실행 절차  
  
- 소스 코드 내려받기
ex) git clone "https://github.com/BenYoo-ben/JavaVideoChat"  
  
  
[Client - Linux]
1.  cd client
2.  make
3.  ./run.sh
  
  
[Client - Windows]
1. cd client
2. make -f Makefile_windows
3. run_windows.bat
  
  
[Server - Linux]
1. cd server
2. make
3. ./run.sh (default port: 55551)
  
(Webcam Driver 출처: github.com/sarxos)
