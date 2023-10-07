//후원금액 버튼중 클릭하는 버튼의 금액 추출
let amount;
let button = document.querySelectorAll('.price');
button.forEach(function(btn) {
    btn.addEventListener('click', function(event) {
        // 클릭된 버튼의 value 값을 가져옵니다.
        amount = parseFloat(event.target.value);
    });
});
function requestPayment() {
    // 입력된 이름 가져오기
    let orderName = document.getElementById("orderName").value;
    let email = document.getElementById('email').value;
    let name = document.getElementById("name").value;

    let baseUrl = "http://localhost:8081/v1/api/payments";

    let url = baseUrl + "?payType=카드&amount=" + encodeURIComponent(amount) + "&orderName=" + encodeURIComponent(orderName)
     + "&userEmail=" + encodeURIComponent(email) + "&userName=" + encodeURIComponent(name);

    // 새로운 URL로 리다이렉트 또는 사용
    //window.location.href = finalUrl;
    let postData = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // 요청 헤더 설정
        },
        body: JSON.stringify({}), // POST 요청 본문 데이터
    };
    fetch(url, postData)
    .then(response => {
        if(response.body != null) {
            return response.json();
        }
    })
    .then(res => {
        console.log("post success " + res.data.amount +", " + res.data.userEmail + ", " + res.data.successUrl);
            // ------ 클라이언트 키로 객체 초기화 ------
        var clientKey = 'test_ck_6BYq7GWPVv5DRddeYNn3NE5vbo1d'
        var tossPayments = TossPayments(clientKey)

        // ------ 결제창 띄우기 ------
        tossPayments.requestPayment('카드', {
            // 결제 정보 파라미터
            // https://docs.tosspayments.com/reference/js-sdk
            amount: res.data.amount, // 결제 금액
            orderId: res.data.orderId, // 주문 ID(주문 ID는 상점에서 직접 만들어주세요.)
            orderName: res.data.orderName, // 주문명
            customerName: res.data.userName, // 구매자 이름
            customerEmail: res.data.userEmail,
            successUrl: res.data.successUrl, // 결제 성공 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
            failUrl: res.data.failUrl // 결제 실패 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
        })
            // ------결제창을 띄울 수 없는 에러 처리 ------
            // 메서드 실행에 실패해서 reject 된 에러를 처리하는 블록입니다.
            // 결제창에서 발생할 수 있는 에러를 확인하세요. 
            // https://docs.tosspayments.com/reference/error-codes#결제창공통-sdk-에러
            .catch(function (error) {
                if (error.code === 'USER_CANCEL') {
                // 결제 고객이 결제창을 닫았을 때 에러 처리
                } else if (error.code === 'INVALID_CARD_COMPANY') {
                // 유효하지 않은 카드 코드에 대한 에러 처리
            }
        });
    })
}