

document.addEventListener('load',function (){
    console.log('loaded');
    let item=window.sessionStorage.getItem("clickedRecept")
    console.log('naget item')
    fetch("restservices/"+item)
        .then(response=>response.json())
        .then(data=>{
            console.log(1)
            let ingridientenList=document.getElementById("receptIngredientenList")
            for(let i=0;data.ingridienten.lenght;i++){
                let newLi=document.createElement('li')
                let text=document.createTextNode(data.ingridienten[i])
                newLi.append(text)
                ingridientenList.append(newLi)
            }

            let stappenList=document.getElementById("receptStappenList")
            for(let i=0;data.stappen.length;i++){
                let newLi=document.createElement('li')
                let text=document.createTextNode(data.stappen[i])
                newLi.append(text)
                stappenList.append(newLi)
            }


            document.getElementById("youtubeVideo").setAttribute("src",data.youtubeVideoSrc)




        })
})







document.getElementById("receptIngredienten")

document.getElementById("LikeRecept").addEventListener('click',function (){
    var fetchOptions = {
        method: "POST",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }
    fetch("restservices/receptinfo",fetchOptions)
        .then(response=>response.json())
        .then()





})




// window.location("index.html")