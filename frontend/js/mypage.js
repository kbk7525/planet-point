//access token 보내서 사용자의 이메일 값을 받는 코드
let userEmail;
document.addEventListener("DOMContentLoaded", function () {
  const data = localStorage.getItem("com.naver.nid.access_token");
  const token = data.split("bearer.")[1].split(".")[0];
  fetch("https://planet-point.ap-northeast-2.elasticbeanstalk.com/token", {
    method: "POST",
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
      findAndDisplayUserInfo(userEmail);
      donationInfo(userEmail);
    })
    .catch((error) => {
      console.log(error);
    });

  //페이지에 서버로부터 받아온 데이터를 뿌려주는 역할
  function findAndDisplayUserInfo(userEmail) {
    let userNameElement = document.getElementById("name");
    let userEmailElement = document.getElementById("email");
    let userMobileElement = document.getElementById("mobile");
    let userSeedElement = document.getElementById("seed");

    if (
      userNameElement &&
      userEmailElement &&
      userMobileElement &&
      userSeedElement
    ) {
      fetch(
        `https://planet-point.ap-northeast-2.elasticbeanstalk.com/info?email=${userEmail}`
      )
        .then((response) => {
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
          return response.json();
        })
        .then((data) => {
          // JSON 데이터를 HTML 요소에 할당
          userNameElement.textContent = data.name;
          userEmailElement.textContent = data.email;
          userMobileElement.textContent = data.mobile;
          userSeedElement.textContent = data.seed + "개";
        })
        .catch((error) => {
          console.error("Fetch error: " + error);
        });
    } else {
      setTimeout(findAndDisplayUserInfo, 100);
    }
  }

  function donationInfo(userEmail) {
    fetch(
      `https://planet-point.ap-northeast-2.elasticbeanstalk.com/v1/api/payments/all?email=${userEmail}`
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        insertMoney(data.data);
      });
  }

  function insertMoney(data) {
    let money = document.getElementById("money");
    let total = 0;
    for (let i = 0; i < data.length; i++) {
      if (data[i].paySuccessYn === "Y") {
        total += parseInt(data[i].amount);
      }
    }
    const totalMoney = addCommasToNumber(total);
    money.textContent = totalMoney + "원";
  }

  function addCommasToNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
});

function showPopup() {
  console.log("버튼 클릭됨!"); // 이 로그가 콘솔에 나타나는지 확인
  window.open(
    "donation_log.html",
    "a",
    "width=1000, height=600, left=100, top=50"
  );
}
