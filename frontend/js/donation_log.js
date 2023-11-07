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
      donationInfo(userEmail);
    })
    .catch((error) => {
      console.log(error);
    });
  function donationInfo(userEmail) {
<<<<<<< HEAD
    fetch(
      `https://planet-point.ap-northeast-2.elasticbeanstalk.com/v1/api/payments/all?email=${userEmail}`
    )
      .then((response) => {
=======
    fetch(`https://planet-point.shop/v1/api/payments/all?email=${userEmail}`)
      .then(response => {
>>>>>>> 347208485c2ca7c8a5837a501ea23bdb9a6aa76e
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then((data) => {
        makeTable(data.data);
      });
  }

  function makeTable(data) {
    let table = document.getElementById("donationTable");
    for (let i = data.length - 1; i >= 0; i--) {
      if (data[i].paySuccessYn === "Y") {
        const formattedNumber = addCommasToNumber(data[i].amount);
        let row = `<tr>
                  <td>${data[i].createDate}</td>
                  <td>${data[i].userName}</td>
                  <td>${data[i].payType}</td>
                  <td>${formattedNumber}</td>
                 </tr>`;
        table.innerHTML += row;
      }
    }
  }

  function addCommasToNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
});

function toggleBtn1() {
  // 토글 할 버튼 선택 (btn1)
  const btn1 = document.getElementById("btn1");

  // btn1 숨기기 (display: none)
  if (btn1.style.display !== "none") {
    btn1.style.display = "none";
  }
}
