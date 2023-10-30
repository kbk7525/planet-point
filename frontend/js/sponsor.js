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
      if (response.ok) {
        return response.text();
      }
      throw new Error('통신 실패');
    })
    .then(data => {
      userEmail = data;
      userInfo(userEmail);
    })
    .catch(error => {
      console.log(error);
    })
  function userInfo(userEmail) {
    let userNameElement = document.getElementById("name");
    let userEmailElement = document.getElementById("email");
    let userMobileElement = document.getElementById("phone");
    if (userNameElement && userEmailElement && userMobileElement) {
      fetch(`http://localhost:8081/info?email=${userEmail}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          // JSON 데이터를 HTML 요소에 할당
          userNameElement.value = data.name;
          userEmailElement.value = data.email;
          userMobileElement.value = data.mobile;
        })
        .catch(error => {
          console.error("Fetch error: " + error);
        });
    }
  }
});