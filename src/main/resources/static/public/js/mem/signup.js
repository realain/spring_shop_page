const signup=document.forms["signup"];
const checkId=document.getElementById("checkId");
const checkEmail=document.getElementById("checkEmail");
const checkPhone=document.getElementById("checkPhone");
//자동완성 + 웹표준화에 좋음

signup["id"].addEventListener("input",async(e)=>{
	let v=e.target.value;
	if(v.length>=4){
		let res=await fetch("/mem/ajax/findid/"+v);
		let json=await res.json();
		if(json){
			checkId.classList.remove("text-primary");
			checkId.classList.add("text-danger");
			checkId.innerText="존재하는 id";
		}else{
			checkId.classList.remove("text-danger");
			checkId.classList.add("text-primary");
			checkId.innerText="사용가능";
							
		}
	}else{
		checkId.classList.remove("text-primary");
		checkId.classList.add("text-danger");
		checkId.innerText="id의 길이는 4이상입니다.";
	}
	/*async 안쓰는 경우
	if(v.length>=4){
		///mem/ajax/findId/{id}
		fetch("/mem/ajax/findId/"+v)
		.then((resolve,reject)=>{return resolve.json()})
		.then((json)=>{
			if(json){
				checkId=innerText="존재하는 id";
			}else{
				checkId.innerText="사용가능";				
			}
		});
	}*/
})
signup["email"].addEventListener("blur",async(e)=>{
	let v=e.target.value;
	//정규 표현식
	let emailEx=/^[0-9a-zA-Z]*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[0-9a-zA-Z]{2,3}$/
	if(emailEx.test(v)){
		let res=await fetch("/mem/ajax/findemail/"+v);
		let json=await res.json();
		if(json){
			checkEmail.classList.remove("text-primary");
			checkEmail.classList.add("text-danger");
			checkEmail.innerText="존재하는 email"
		}else{
			checkEmail.classList.remove("text-danger");
			checkEmail.classList.add("text-primary");
			checkEmail.innerText="사용가능"
		}
	}else{
		checkEmail.classList.remove("text-primary");
		checkEmail.classList.add("text-danger");
		checkEmail.innerText="email형식이 맞지 않습니다"
	}
})
signup["phone"].addEventListener("blur",async(e)=>{
	let v=e.target.value;
	let phoneEx=/^01(0|1|6|9)-(\d{3,4})-(\d{4})$/;
	if(phoneEx.test(v)){
		let res=await fetch("/mem/ajax/findphone/"+v);
		let json=await res.json();
		if(json){
			checkPhone.classList.remove("text-primary");
			checkPhone.classList.add("text-danger");
			checkPhone.innerText="가입된 번호";
		}else{
			checkPhone.classList.remove("text-danger");
			checkPhone.classList.add("text-primary");
			checkPhone.innerText="사용가능한 번호"
		}
	}else{
		checkPhone.classList.remove("text-primary");
		checkPhone.classList.add("text-danger");
		checkPhone.innerText="번호의 형식을 맞추세요"
	}
})