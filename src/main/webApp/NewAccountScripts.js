window.addEventListener('load',function (){

    document.getElementById("signUpButton").addEventListener('click',function (){
        console.log("signing up...")
        if (document.getElementById("name").value.length>0&&document.getElementById("username").value.length>0&&document.getElementById("passwoord").value.length>0&&document.getElementById("confirmPasswoord").value.length>0&&document.getElementById("gender").value.length>0&&document.getElementById("email").value.length>0){
            if (document.getElementById("passwoord").value===document.getElementById("confirmPasswoord").value){
                console.log("fetch")


                var formData = new FormData(document.getElementById("signUpForm"));
                var encData = new URLSearchParams(formData.entries());
                fetch("restservices/profile/newaccount" ,{method: "POST", body:encData})
                    .then(response=>{
                        if (response.status===200){
                            return response.json();
                        }else document.getElementById("signUpMelding").textContent="Username or email already exists!"
                    }).then(res=> {
                    document.getElementById("signUpMelding").textContent=res.result
                    let buttonDiv=document.getElementById("loginButtonDiv")
                    let goToLoginButton=document.createElement('a')
                    goToLoginButton.setAttribute("href","inloggenKlant.html")
                    goToLoginButton.setAttribute("class","types")
                    goToLoginButton.textContent="Log In"
                    buttonDiv.append(goToLoginButton)

                })
            }else document.getElementById("signUpMelding").textContent="Passwoord en Confirm passwoord zijn niet gelijk"

        }else document.getElementById("signUpMelding").textContent="Je hebt nog velden dat niet gevuld zijn"

    })


})