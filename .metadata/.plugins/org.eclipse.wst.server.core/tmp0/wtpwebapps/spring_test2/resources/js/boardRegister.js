console.log("board Register in");
document.getElementById("trigger").addEventListener('click',()=>{
    document.getElementById("file").click();

})

//실행파일에 대한 정규표현식 지정
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else {
        return 1;
    }
}

document.addEventListener('change',(e)=>{
    if(e.target.id == 'file'){ // 파일에 변화가 생겼다면
        // files : input type = "file" element에 저장된 file의 정보를 가져오는 property
        const fileObj =document.getElementById('file').files;
        console.log(fileObj);
        //한번 disabled 달면 혼자 풀어질 수없으니 위에서 풀어줌
        document.getElementById("regBtn").disabled = false;

        // 등록된 file의 정보를 fileZone에 기록
        let div = document.getElementById("fileZone");
        div.innerHTML = '';

        //여러개의 등록파일이 모두 검증을 통과해야하기 때문에
        //isOk * 로 각각 파일 통과할떄 마다 연산을 실행 <=> 통과 여부를 확인
        let isOk = 1;
        let ul =`<ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            let validResult = fileValidation(file.name,file.size);
            isOk = validResult;
            console.log(isOk);
            ul+=`<li class="list-group-item">`;
            ul+=`<div class ="mb-3">`;
            ul+=`${validResult ? '<div class="fw-bold text-success">업로드 가능' : '<div class="fw-bold text-danger">업로드 불가능'}</div>`;
            ul+=`${file.name}`;
            ul+=`<span class="badge text-bg-${validResult ? "success" : "danger"}">${file.size}</span>`;
            ul+=`<li>`;
        }
        ul+=`</ul>`;
        div.innerHTML=ul;

        if(isOk == '0'){
            // 하나라도 파일이 검증을 통과하지 못하면 
            document.getElementById("regBtn").disabled = true;
        }
    }
})

/* <ul class="list-group">
  <li class="list-group-item">An item</li> */