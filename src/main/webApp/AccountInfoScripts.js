var clickedRecept=null
function saveReceptNaam(){
    window.sessionStorage.setItem("clickedRecept",clickedRecept)

}


window.addEventListener('load',function (){
    console.log('loaded')


    var fetchOptions = {
        method: "GET",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }



    fetch('restservices/profile',fetchOptions)
        .then(response=>{
            if (response.status===200){
                return response.json()
            }else document.querySelector('main').innerHTML=`<h1>Restricted! Content is Alleen Beschikbaar Voor Ingelogde Klant</h1>`
                
        }

            )
        .then(data=>{
            console.log(data)
                let ul=document.getElementById('likedReceptsList')
            for (let i=0;i<data.likedRecepten.length;i++){
                let newDiv=document.createElement('div')
                let newLi=document.createElement('a')
                newLi.textContent=data.likedRecepten[i].naam
                newLi.setAttribute("href","ReceptInformatie.html")
                newLi.setAttribute("name",newLi.textContent)
                newLi.addEventListener('click',function (){
                    clickedRecept=newLi.textContent
                    saveReceptNaam()
                });

                newDiv.append(newLi)
                ul.append(newDiv)
            }
        })
})