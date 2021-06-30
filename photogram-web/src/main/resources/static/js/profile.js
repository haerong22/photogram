/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 구독하기, 구독취소
  (2) 구독자 정보 모달 보기
  (3) 유저 프로필 사진 변경
  (4) 사용자 정보 메뉴 열기 닫기
  (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
  (7) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(toUserId, obj) {
	if ($(obj).text() === "구독취소") {

		$.ajax({
			type: "DELETE",
			url: "/api/subscribe/" + toUserId,
			dataType: "json"
		}).done(res => {
			$(obj).text("구독하기");
			$(obj).toggleClass("blue");
		}).fail(err => {
			alert("구독취소 실패");
		});

	} else {
		$.ajax({
			type: "POST",
			url: "/api/subscribe/" + toUserId,
			dataType: "json"
		}).done(res => {
			$(obj).text("구독취소");
			$(obj).toggleClass("blue");
		}).fail(err => {
			alert("구독하기 실패");
		});

	}
}

// (2) 구독자 정보  모달 보기
function subscribeInfoModalOpen(pageUserId) {
	$(".modal-subscribe").css("display", "flex");

	$.ajax({
		url: `/api/user/${pageUserId}/subscribe`
	}).done(res => {
		console.log(res.data);
		res.data.forEach(user => {
			let item = getSubscribeModalItem(user);
			$("#subscribeModalList").append(item);
		});
	}).fail(err => {
		console.log("구독정보 불러오기 실패", err);
	});
}

function getSubscribeModalItem(user) {
	let item = `
		<div class="subscribe__item" id="subscribeModalItem-${user.id}">
			<div class="subscribe__img">
				<img src="/upload/${user.profileImageFile}" onerror="this.src='/images/person.jpeg'"/>
		</div>
			<div class="subscribe__text">
				<h2>${user.username}</h2>
			</div>
			<div class="subscribe__btn">`;
			if(!user.equalUserState) {
				if (user.subscribeState) {
					item += `<button class="cta blue" onClick="toggleSubscribe(${user.id}, this)">구독취소</button>`;
				} else {
					item += `<button class="cta blue" onClick="toggleSubscribe(${user.id}, this)">구독하기</button>`;
				}
			}
			item += `
			</div>
		</div>
	`;
	return item;
}

// (3) 유저 프로파일 사진 변경
function profileImageUpload(pageUserId, principalId) {
	if (pageUserId != principalId) {
		return;
	}
	$("#userProfileImageInput").click();

	$("#userProfileImageInput").on("change", (e) => {
		let f = e.target.files[0];

		if (!f.type.match("image.*")) {
			alert("이미지를 등록해야 합니다.");
			return;
		}

		// 서버에 이미지 전송
		let profileImageForm = $("#userProfileImageForm")[0];

		let formData = new FormData(profileImageForm);

		$.ajax({
			type: "PUT",
			url: `/api/user/${principalId}/profileImageUrl`,
			data: formData,
			contentType: false,
			processData: false,
			enctype: "multipart/form-data",
			dataType: "json"
		}).done(res => {
			// 사진 전송 성공시 이미지 변경
			let reader = new FileReader();
			reader.onload = (e) => {
				$("#userProfileImage").attr("src", e.target.result);
			}
			reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
		}).fail(err => {
			console.log("fail => ", err)
		});
	});
}


// (4) 사용자 정보 메뉴 열기 닫기
function popup(obj, pageUserId, principalId) {
	if (pageUserId != principalId) {
		return;
	}
	$(obj).css("display", "flex");
}

function closePopup(obj) {
	$(obj).css("display", "none");
}


// (5) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
function modalInfo() {
	$(".modal-info").css("display", "none");
}

// (6) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달
function modalImage() {
	$(".modal-image").css("display", "none");
}

// (7) 구독자 정보 모달 닫기
function modalClose() {
	$(".modal-subscribe").css("display", "none");
	location.reload();
}






