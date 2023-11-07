let userEmail;
document.addEventListener("DOMContentLoaded", function () {
  const data = localStorage.getItem("com.naver.nid.access_token");
  const token = data.split("bearer.")[1].split(".")[0];
<<<<<<< HEAD
  fetch("https://planet-point.ap-northeast-2.elasticbeanstalk.com/token", {
    method: "POST",
=======
  fetch('https://planet-point.shop/token', {
    method: 'POST',
>>>>>>> 347208485c2ca7c8a5837a501ea23bdb9a6aa76e
    headers: {
      "Content-Type": "text/plain",
    },
    body: token,
  })
    .then((response) => {
      if (response.ok) {
        return response.text();
      }
      throw new Error("통신 실패");
    })
    .then((data) => {
      userEmail = data;
      userInfo(userEmail);
    })
    .catch((error) => {
      console.log(error);
    });
  function userInfo(userEmail) {
    let userNameElement = document.getElementById("name");
    let userEmailElement = document.getElementById("email");
    let userMobileElement = document.getElementById("phone");
    if (userNameElement && userEmailElement && userMobileElement) {
<<<<<<< HEAD
      fetch(
        `https://planet-point.ap-northeast-2.elasticbeanstalk.com/info?email=${userEmail}`
      )
        .then((response) => {
=======
      fetch(`https://planet-point.shop/info?email=${userEmail}`)
        .then(response => {
>>>>>>> 347208485c2ca7c8a5837a501ea23bdb9a6aa76e
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then((data) => {
          // JSON 데이터를 HTML 요소에 할당
          userNameElement.value = data.name;
          userEmailElement.value = data.email;
          userMobileElement.value = data.mobile;
        })
        .catch((error) => {
          console.error("Fetch error: " + error);
        });
    }
  }
});
