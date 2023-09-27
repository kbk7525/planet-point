let naverLogin = new naver.LoginWithNaverId(
  {
    clientId: "RVvWO7R3QM1UnNRS0fnv",
    callbackUrl: "http://127.0.0.1:8080",
    isPopup: false,
    loginButton: {color: "green", type: 2, height: 30}
  }
);
naverLogin.init();

let closing_window = false;

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
                if (result === 200) {
                    alert('성공');
                    window.location.replace('http://127.0.0.1:8080');
                }
            })
            .catch(error => {
                alert('오류 발생', error);
            });
        }
      });
});