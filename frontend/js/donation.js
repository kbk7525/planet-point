let userEmail;
let isSeedInsufficient = false;
document.addEventListener("DOMContentLoaded", function () {
  printSeed();
  const data = localStorage.getItem("com.naver.nid.access_token");
  const token = data.split("bearer.")[1].split(".")[0];
  fetch("http://planet-point.ap-northeast-2.elasticbeanstalk.com/token", {
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
      decreaseSeed(userEmail);
    })
    .catch((error) => {
      console.log(error);
    });
});

function increaseSeed(elementId) {
  if (elementId == 1 || elementId == 2 || elementId == 3) {
    cnt = 10;
  } else {
    cnt = 100;
  }
  fetch(
    `http://planet-point.ap-northeast-2.elasticbeanstalk.com/donation/increase?elementId=${elementId}&cnt=${cnt}`,
    {
      method: "POST",
    }
  )
    .then((response) => {
      if (!response.ok) {
        throw new Error("오류발생");
      }
      return response.text();
    })
    .then((data) => {
      console.log("기부 완료");

      const progressValue = document.querySelector(".donation-progress");
      const currentProgress = progressValue.value;
      const maxProgress = progressValue.max;
      const newProgress = Math.min(currentProgress + cnt, maxProgress);
      progressValue.value = newProgress;
    })
    .catch((error) => {
      console.log(error);
    });
}

function decreaseSeed(userEmail) {
  const clickElements = document.querySelectorAll(".donation_img");
  const progressBars = document.querySelectorAll(".donation-progress");
  clickElements.forEach((clickElement, index) => {
    let cnt;
    clickElement.addEventListener("click", () => {
      if (
        clickElement.id == 1 ||
        clickElement.id == 2 ||
        clickElement.id == 3
      ) {
        cnt = 100;
      } else {
        cnt = 100;
      }
      fetch(
        `http://planet-point.ap-northeast-2.elasticbeanstalk.com/donation/decrease?email=${userEmail}&cnt=${cnt}`,
        {
          method: "POST",
        }
      )
        .then((response) => {
          if (!response.ok) {
            $("#exampleModal_fail").modal("show");
            isSeedInsufficient = true;
          }
          return response.text();
        })
        .then((message) => {
          if (!isSeedInsufficient) {
            if (clickElement.id == 4) {
              increaseSeed(clickElement.id);
              $("#exampleModal_buy").modal("show");
            }
            proBar(progressBars[index], cnt);
            increaseSeed(clickElement.id);
            $("#exampleModal").modal("show");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    });
  });
  function proBar(progressBar, cnt) {
    const currentProgress = parseInt(progressBar.value);
    const maxProgress = parseInt(progressBar.max);
    const newProgress = Math.min(currentProgress + cnt, maxProgress);
    progressBar.value = newProgress;
  }
}
function printSeed() {
  const elements = document.querySelectorAll(".donation-progress");
  elements.forEach((element) => {
    const elementId = element.id.replace("progress-", "");
    fetch(`http://planet-point.ap-northeast-2.elasticbeanstalk.com/donation/print/${elementId}`)
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        throw new Error("요소들의 씨앗을 가져오지 못했습니다.");
      })
      .then((data) => {
        const progressValue = document.getElementById(`progress-${elementId}`);
        console.log(progressValue);
        if (progressValue) {
          progressValue.value = data.seedCnt;
        }
      })
      .catch((error) => {
        console.log(error);
      });
  });
}
