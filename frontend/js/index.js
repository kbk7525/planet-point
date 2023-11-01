let userEmail;
document.addEventListener("DOMContentLoaded", function () {
  const data = localStorage.getItem('com.naver.nid.access_token');
  if (data) {
    const token = data.split("bearer.")[1].split(".")[0];
    fetch('http://planet-point.ap-northeast-2.elasticbeanstalk.com/token', {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain',
      },
      body: token,
    })
      .then(response => {
        if (response.ok) {
          return response.text();
        }
        throw new Error('통신 실패');
      })
      .then(data => {
        userEmail = data;
        const isLogIn = checkLogin(userEmail);
        const links = document.querySelectorAll(".check_login");
        links.forEach(link => {
          link.addEventListener('click', function (e) {
            if (!isLogIn) {
              e.preventDefault();
              alert('로그인이 필요한 서비스입니다.');
              window.location.href = 'index.html';
            }
          });
        });
      })
      .catch(error => {
        console.log(error);
      })
  }
  else {
    const isLogIn = checkLogin(null);
    const links = document.querySelectorAll(".check_login");
    links.forEach(link => {
      link.addEventListener('click', function (e) {
        if (!isLogIn) {
          e.preventDefault();
          alert('로그인이 필요한 서비스입니다.');
          window.location.href = 'index.html';
        }
      });
    });
  }
});

function checkLogin(userEmail) {
  let flag = false;
  if (userEmail != null) {
    flag = true;
    return flag;
  }
  else {
    return flag;
  }
}