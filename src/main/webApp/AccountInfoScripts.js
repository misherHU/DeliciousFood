document.addEventListener('load',function (){
    console.log('loaded')
    var fetchOptions = {
        method: "GET",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }

    fetch('restservices/profile',fetchOptions)
        .then(response=>response.json())
        .then(data=>{
                let ul=document.getElementById('likedReceptsList')
            for (let i=0;i<data.likedRecepten.length;i++){
                let newLi=document.createElement('li')
                let text=document.createTextNode(data.likedRecepten[i])
                newLi.append(text)
                ul.append(newLi)
            }
        })
})