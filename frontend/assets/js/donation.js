// JavaScript 코드
document.addEventListener("DOMContentLoaded", function () {
  // 이미지와 프로그레스 바 요소를 가져옵니다.
  const donationImages = document.querySelectorAll(".donation_img");
  const progressBars = document.querySelectorAll(".donation-progress");

  // 클릭 이벤트를 추가합니다.
  for (let i = 0; i < donationImages.length; i++) {
    donationImages[i].addEventListener("click", function () {
      // value 값을 가져온 후 100 증가시킵니다.
      let currentValue = parseInt(progressBars[i].value);
      currentValue += 100;

      // 최대값을 초과하지 않도록 확인합니다.
      if (currentValue > parseInt(progressBars[i].max)) {
        currentValue = parseInt(progressBars[i].max);
      }

      // 진행도를 업데이트합니다.
      progressBars[i].value = currentValue;
    });
  }
});

// 모달이 나타났을 때 포커스를 설정
$("#exampleModal1").on("shown.bs.modal", function () {
  $("#exampleModal1").focus();
});
