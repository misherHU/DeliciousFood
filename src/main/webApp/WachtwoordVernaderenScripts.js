document.getElementById("confirmButton").addEventListener('click',function (){
    if (document.getElementById("username").value.length>0&&document.getElementById("passwoord").value.length>0&&document.getElementById("confirmPasswoord").value.length>0&&document.getElementById("email").value.length>0){
        if (document.getElementById("passwoord").value===document.getElementById("confirmPasswoord").value){
            console.log("fetch")


            var formData = new FormData(document.getElementById("wachtWoordVeranderenForm"));
            var encData = new URLSearchParams(formData.entries());
            fetch("restservices/profile/wachtwoordveranderen" ,{method: "POST", body:encData})
                .then(response=>{
                    if (response.status===200){
                        return response.json();
                    }else document.getElementById("wachtWoordVeranderenMelding").textContent="Username en email klppenn niet met elkaar"
                }).then(res=> {
                document.getElementById("wachtWoordVeranderenMelding").textContent=res.result
                let buttonDiv=document.getElementById("loginButtonDiv")
                let goToLoginButton=document.createElement('a')
                goToLoginButton.setAttribute("href","KiesRole.html")
                goToLoginButton.setAttribute("class","types")
                goToLoginButton.textContent="Log In"
                buttonDiv.append(goToLoginButton)

            })
        }else document.getElementById("wachtWoordVeranderenMelding").textContent="Passwoord en Confirm passwoord zijn niet gelijk"

    }else document.getElementById("wachtWoordVeranderenMelding").textContent="Je hebt nog velden dat niet gevuld zijn"
})