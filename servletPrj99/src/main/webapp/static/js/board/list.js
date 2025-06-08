document.addEventListener("DOMContentLoaded", function () {
    const sortSelect = document.querySelector("#sortSelect");
    if (!sortSelect) {
        console.error("정렬 드롭다운을 찾을 수 없습니다.");
        return;
    }

    sortSelect.addEventListener("change", function () {
        const sort = this.value;

        fetch(`/board/listData?sort=${sort}`, {
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        })
            .then(response => response.json())
			.then(data => {
			    const tbody = document.querySelector("#boardTbody");
			    tbody.innerHTML = "";

			    // data.list 가 배열임을 확인해서 체크
			    if (!data.list || data.list.length === 0) {
			        tbody.innerHTML = `<tr><td colspan="5" style="text-align:center;">게시글이 없습니다.</td></tr>`;
			        return;
			    }

			    // data.list.forEach로 변경
			    data.list.forEach(vo => {
			        const row = `
			            <tr>
			                <td>${vo.no}</td>
			                <td>${vo.title}</td>
			                <td>${vo.content}</td>
			                <td>${vo.writerNo}</td>
			                <td>${vo.hit}</td>
			            </tr>
			        `;
			        tbody.insertAdjacentHTML("beforeend", row);
			    });
			})

            .catch(err => console.error("정렬 요청 실패", err));
    });
});
