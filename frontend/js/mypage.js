    //access token 보내서 사용자의 이메일 값을 받는 코드
    let userEmail;
    document.addEventListener("DOMContentLoaded", function () {

      const data = localStorage.getItem('com.naver.nid.access_token');
      const token = data.split("bearer.")[1].split(".")[0];
      fetch('http://localhost:8081/token', {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain',
        },
        body: token,
      })
      .then(response => {
        if(response.ok) {
          return response.text();
        }
        throw new Error('통신 실패');
      })
      .then(data => {
        userEmail = data;
        findAndDisplayUserInfo(userEmail);
      })
      .catch(error => {
        console.log(error);
      })

      //페이지에 서버로부터 받아온 데이터를 뿌려주는 역할
      function findAndDisplayUserInfo(userEmail) {
        let userNameElement = document.getElementById("name");
        let userEmailElement = document.getElementById("email");
        let userMobileElement = document.getElementById("mobile");
        let userSeedElement = document.getElementById("seed");

        if (userNameElement && userEmailElement && userMobileElement && userSeedElement) {
          fetch(`http://localhost:8081/info?email=${userEmail}`)
            .then(response => {
              if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
              }
              return response.json();
            })
            .then(data => {
              // JSON 데이터를 HTML 요소에 할당
              userNameElement.textContent = data.name;
              userEmailElement.textContent = data.email;
              userMobileElement.textContent = data.mobile;
              userSeedElement.textContent = data.seed;
            })
            .catch(error => {
              console.error("Fetch error: " + error);
            });
        } else {
          setTimeout(findAndDisplayUserInfo, 100);
        }
      }
    });