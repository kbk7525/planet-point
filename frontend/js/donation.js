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
      donationInfo(userEmail);
    })
    .catch(error => {
      console.log(error);
    })
  function donationInfo(userEmail) {
    fetch(`http://localhost:8081/v1/api/payments/all?email=${userEmail}`)
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        makeTable(data.data);
      })
  }

  function makeTable(data) {
    let table = document.getElementById('donationTable');
    for (let i = 0; i < data.length; i++) {
      let row = `<tr>
                <td>${data[i].createDate}</td>
                <td>${data[i].userName}</td>
                <td>${data[i].payType}</td>
                <td>${data[i].amount}</td>
               </tr>`;
      table.innerHTML += row
    }
  }
});