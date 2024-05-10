/**
 * 
 */
console.log("modify in!~");
document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid;
        removeFileToServer(uuid).then(result =>{
            if(result == '1'){
                e.target.closest('li').remove();
            }
        })
        
    }
});

async function removeFileToServer(uuid){
    try {
        const url = "/board/file/"+uuid;
        const config = {
            method : "delete"
        }
        const resp = await fetch(url,config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById("logoutLink").addEventListener('click',()=>{
    document.getElementById("logoutForm").submit();
})