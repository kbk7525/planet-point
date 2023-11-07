<<<<<<< HEAD
let naverLogin = new naver.LoginWithNaverId({
  clientId: "RVvWO7R3QM1UnNRS0fnv",
  callbackUrl: "https://planet-point.netlify.app/",
  isPopup: false,
  loginButton: { color: "green", type: 2, height: 30 },
});
=======
let naverLogin = new naver.LoginWithNaverId(
  {
    clientId: "RVvWO7R3QM1UnNRS0fnv",
    callbackUrl: "https://planet-point.netlify.app/",
    isPopup: false,
    loginButton: {color: "green", type: 2, height: 30}
  }
);
>>>>>>> 347208485c2ca7c8a5837a501ea23bdb9a6aa76e
naverLogin.init();

window.addEventListener("load", function () {
  naverLogin.getLoginStatus(function (status) {
    if (status) {
      let email = naverLogin.user.getEmail();
      let name = naverLogin.user.getName();
      let mobile = naverLogin.user.getMobile();
      let uid = naverLogin.user.getId();

      const data = {
        email: email,
        uid: uid,
        name: name,
        mobile: mobile,
      };
      fetch("https://planet-point.ap-northeast-2.elasticbeanstalk.com/save", {
        method: "post",
        headers: {
          "Content-Type": "application/json; charset=UTF-8;",
        },
        body: JSON.stringify(data),
      })
        .then((response) => response.text())
        .then((result) => {
          if (result === "회원가입 성공") {
          }
<<<<<<< HEAD
=======
          fetch('https://planet-point.shop/save', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8;',
            },
            body: JSON.stringify(data)
>>>>>>> 347208485c2ca7c8a5837a501ea23bdb9a6aa76e
        })
        .catch((error) => {
          alert("오류 발생", error);
        });

<<<<<<< HEAD
      //로그인 되었을 때 환영메시지와 로그아웃 버튼 추가
      const message_area = document.getElementById("message");
      message_area.innerHTML = `${naverLogin.user.name}님`;
      const button_area = document.getElementById("button_area");
      button_area.innerHTML = "<a id='btn_logout' href=''>로그아웃</a>";

      //로그아웃 버튼 누르면 로그아웃
      const logout = document.getElementById("btn_logout");
      logout.addEventListener("click", (e) => {
        naverLogin.logout();
        location.replace("https://planet-point.netlify.app/");
=======
            //로그아웃 버튼 누르면 로그아웃
            const logout = document.getElementById('btn_logout');
            logout.addEventListener('click', (e)=> {
              naverLogin.logout();
              location.replace("https://planet-point.netlify.app/");
            });
        }
>>>>>>> 347208485c2ca7c8a5837a501ea23bdb9a6aa76e
      });
    }
  });
});
