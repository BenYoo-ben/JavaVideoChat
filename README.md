### Java Video Chat  
![image](https://user-images.githubusercontent.com/57353430/207296291-d3653fa4-1b50-4459-a82e-213363ea6005.png)


[KR]  
Java를 통해 작성한 영상 채팅 프로그램입니다.  
Client와 Server 코드로 나누어 작성되어 있습니다.  
Client는 Linux 또는 Windows 환경에서 실행이 가능하며 Server는 Linux 환경이 필요합니다.  


#### 실행 절차  
  
- 소스 코드 내려받기
ex) git clone "https://github.com/BenYoo-ben/JavaVideoChat"  
  
  
**[Client - Linux]**
1.  cd client
2.  make
3.  ./run.sh
  
  
**[Client - Windows]**
1. cd client
2. make -f Makefile_windows
3. run_windows.bat
  
  
**[Server - Linux]**
1. cd server
2. make
3. ./run.sh (default port: 55551)
  
(Webcam Driver 출처: github.com/sarxos)  
_21-11-24: 기본 기능이 구현되어 있으며 추후에 보완할 계획입니다._
