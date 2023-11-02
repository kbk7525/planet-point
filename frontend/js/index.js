let userEmail;
document.addEventListener("DOMContentLoaded", function () {
  const data = localStorage.getItem('com.naver.nid.access_token');
  let isHidden = getCookie("badgeHidden");
  const badge = document.querySelector(".badges");
  if (isHidden === "true") {
    badge.style.display = "none";
  } else {
    badge.style.display = "block";
  }
  function setCookie(cname, cvalue, exdays) {
    let d = new Date();
    d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
    let expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
  }

  function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(";");
    for (var i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == " ") {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }
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

        badge.addEventListener('click', function (e) {
          if (!isLogIn) {
            e.preventDefault();
            alert('로그인이 필요한 서비스입니다.');
            window.location.href = 'index.html';
          } else {
            setCookie("badgeHidden", "true", 1);
            badge.style.display = "none";
            fetch(`http://planet-point.ap-northeast-2.elasticbeanstalk.com/increaseSeed?email=${userEmail}&cnt=100`, {
              method: 'POST',
            })
              .then(response => {
                if (response.ok) {
                  return response.text();
                }
                throw new Error('씨앗 증가 요청 실패');
              })
              .then(data => {
                console.log(data); 
              })
              .catch(error => {
                console.log(error);
              });
          }
        });
      })
      .catch(error => {
        console.log(error);
      })
  }
  else {
    const isLogIn = checkLogin(null);
    const links = document.querySelectorAll(".check_login");
    const badge = document.querySelector(".badges");
    links.forEach(link => {
      link.addEventListener('click', function (e) {
        if (!isLogIn) {
          e.preventDefault();
          alert('로그인이 필요한 서비스입니다.');
          window.location.href = 'index.html';
        }
      });
    });
    badge.addEventListener('click', function (e) {
      if (!isLogIn) {
        e.preventDefault();
        alert('로그인이 필요한 서비스입니다.');
        window.location.href = 'index.html';
      }
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