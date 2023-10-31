let naverLogin = new naver.LoginWithNaverId(
  {
    clientId: "RVvWO7R3QM1UnNRS0fnv",
    callbackUrl: "http://planet-point.netlify.app/",
    isPopup: false,
    loginButton: {color: "green", type: 2, height: 30}
  }
);
naverLogin.init();

window.addEventListener('load', function() {
    naverLogin.getLoginStatus(function(status) {
        if(status) {
          let email = naverLogin.user.getEmail();
          let name = naverLogin.user.getName();
          let mobile = naverLogin.user.getMobile();
          let uid = naverLogin.user.getId();

          const data = {
            email: email,
            uid: uid,
            name: name,
            mobile: mobile
          }
          fetch('http://localhost:8081/save', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8;',
            },
            body: JSON.stringify(data)
        })
            .then(response => response.text())
            .then(result => {
                if (result === '회원가입 성공') {
                }
            })
            .catch(error => {
                alert('오류 발생', error);
            });
            
            //로그인 되었을 때 환영메시지와 로그아웃 버튼 추가
            const message_area = document.getElementById('message');
            message_area.innerHTML = `${naverLogin.user.name}님`;
            const button_area = document.getElementById('button_area');
            button_area.innerHTML="<a id='btn_logout' href=''>로그아웃</a>";

            //로그아웃 버튼 누르면 로그아웃
            const logout = document.getElementById('btn_logout');
            logout.addEventListener('click', (e)=> {
              naverLogin.logout();
              location.replace("http://planet-point.netlify.app/");
            });
        }
      });
});