# Retrofit2MVVM-Covid19
 how to retreive the covid19 information of South Korea  with retrpfit2 and MVVM model (XML format)
 
 이 샘플 프로젝트는 XML 기반의 API Call을 수행하는 Retrofit MVVM의 기본 형태를 공유하기 위해서 만들어졌다.
 한국의 시도별 코로나19 현황을 샘플로 하여 실제 데이터를 다루게 만들었다.
 
 이 샘플을 돌리기 위해서 한가지 전제 조건이 있다.
 data.go.kr 사이트에 가입해서, 
 "보건복지부_코로나19 시·도발생_현황"을 신청하면 
 서비스키를 발급받아야 한다. (수 시간내에 발급된다)
 
 발급된 서비스키를 클래스 Covid19의 다음 라인에 수정해야 한다.
 private String serviceKey = "you service key should be here";
 
 웹페이지에서 serviceKey는 urlencoded 되어서 보이므로 urldecode하는 사이트를 찾아서 변환해서 넣어주면 된다.
 예를들어 키의 끝쪽을 보면 "~~~~ Z2Q%3D%3D" 이런 식으로 되어 있다.
 변환 해서 "~~~~ Z2Q==" 이런 형태로 들어가야 한다.
 
 Good Luck 2 U Guys~
