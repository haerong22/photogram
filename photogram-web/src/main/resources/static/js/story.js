/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

// (1) 스토리 로드하기
let page = 0;

function storyLoad() {
	$.ajax({
		url: `/api/images?page=${page}`,
		dataType: "json"
	}).done(res => {
		console.log(res.data);
		res.data.content.forEach(image => {
			let storyItem = getStoryItem(image);
			$("#storyList").append(storyItem);
		});
	}).fail(err => {
		console.log(err);
	});
}
storyLoad();

function getStoryItem(image) {
	let item = `
		<div class="story-list__item">
			<div class="sl__item__header">
				<div>
					<img class="profile-image" src="/upload/${image.user.profileImageUrl}"
						onerror="this.src='/images/person.jpeg'"  alt=""/>
				</div>
				<div>${image.user.username}</div>
			</div>
		
			<div class="sl__item__img">
				<img src="/upload/${image.postImageUrl}"  alt=""/>
			</div>
		
			<div class="sl__item__contents">
				<div class="sl__item__contents__icon">
		
					<button>
	`;
					if(image.likeState) {
						item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
					} else {
						item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
					}
	item += `				
					</button>
				</div>
		
				<span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount}</b>likes</span>
		
				<div class="sl__item__contents__content">
					<p>${image.caption}</p>
				</div>
		
				<div id="storyCommentList-${image.id}">
		
					<div class="sl__item__contents__comment" id="storyCommentItem-1">
						<p>
							<b>Lovely :</b> 부럽습니다.
						</p>
		
						<button>
							<i class="fas fa-times"></i>
						</button>
		
					</div>
				
				</div>
		
				<div class="sl__item__input">
					<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
					<button type="button" onClick="addComment(${image.id})">게시</button>
				</div>
	
			</div>
		</div>
	`;
	return item
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	let checkNum = $(window).scrollTop() - ( $(document).height() - $(window).height() );
	if (checkNum < 1 && checkNum > -1) {
		page++;
		storyLoad();
	}
});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	if (likeIcon.hasClass("far")) {

		$.ajax({
			type: "POST",
			url: `/api/image/${imageId}/likes`,
			dataType: "json"
		}).done(res => {
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) + 1;
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(err => {
			console.log("fail", err);
		});

	} else {

		$.ajax({
			type: "DELETE",
			url: `/api/image/${imageId}/likes`,
			dataType: "json"
		}).done(res => {
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) - 1;
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(err => {
			console.log("fail", err);
		});

	}
}

// (4) 댓글쓰기
function addComment(imageId) {

	let commentInput = $(`#storyCommentInput-${imageId}`);
	let commentList = $(`#storyCommentList-${imageId}`);

	let data = {
		imageId: imageId,
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	$.ajax({
		type: "POST",
		url: "/api/comment",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(res => {
		console.log(res)
	}).fail(err => {
		console.log("fail => ", err);
	});

	let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-2""> 
			    <p>
			      <b>GilDong :</b>
			      댓글 샘플입니다.
			    </p>
			    <button><i class="fas fa-times"></i></button>
			  </div>
	`;
	commentList.prepend(content);
	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment() {

}







