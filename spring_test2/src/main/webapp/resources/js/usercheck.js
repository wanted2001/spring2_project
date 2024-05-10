/**
 * 
 */

console.log("안녕하");
document.getElementById("check").addEventListener('click', (e) => {
    const eVal = document.getElementById('e');
    let email = eVal.value;
    console.log(email);
    check(email).then(result => {
       if(result == '1'){
            alert("이미 있는 아이디 입니다.");
            eVal.value = "";
            return ;
        }else{
            alert("사용가능 한 아이디 입니다.");
            eVal.value = email;
            return;
        }
    });




});

async function check(email) {
    try {
        const url = "/user/check/"+email;
        const config = {
            method: 'GET'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }


}
