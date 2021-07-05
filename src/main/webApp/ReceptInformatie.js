

window.addEventListener('load',function (){

    console.log('loaded');
    let item=window.sessionStorage.getItem("clickedRecept")
    console.log(item)
    console.log('naget item')
    fetch("restservices/"+item)

        .then(response=>{

            if(response.status===200){
                return response.json()
            }else document.querySelector('main').innerHTML="No Recepts with that name"

        })
        .then(data=>{
            console.log(data.aantalLikes)
            console.log(1)
            console.log(data)
            console.log("ingredienten "+data.ingredienten[0])
            console.log("2")


            let stappenList=document.getElementById("receptStappenList")
            for(let i=0;i<data.stappen.length;i++){
                let newLi=document.createElement('li')
                let text=document.createTextNode(data.stappen[i])
                newLi.append(text)
                stappenList.append(newLi)
            }

            // console.log(data.youtubeVideoSrc)
            //
            //
             document.getElementById("youtubeVideo").setAttribute("src",data.youtubeVideoSrc)
            //
            console.log("fwwe")

            let ingredientenList=document.getElementById("receptIngredientenList")

            for(let i=0;i<data.ingredienten.length;i++){
                console.log("lus")
                let newLi=document.createElement('li')
                let text=document.createTextNode(data.ingredienten[i])
                newLi.append(text)
                ingredientenList.append(newLi)
            }
            console.log(" na fwwe")


            console.log("begin review loop")
            let reviewsDiv=document.getElementById("reviewsDiv")
            for(let r=0;r<data.reviews.length;r++){
                let nieuweResultaatContainer = document.createElement("div")
                nieuweResultaatContainer.className = 'container';
                console.log('1')
                let nieuweResultaatContaineritem = document.createElement("div")
                nieuweResultaatContaineritem.className = 'container-item';
                console.log('2')
                let nieuweResultaatContentSpan = document.createElement("span")
                console.log('3')
                let klantNaam = document.createElement('p');
                klantNaam.textContent=data.reviews[r].gebruikersNaam
                let reviewtekst = document.createElement('p')
                reviewtekst.textContent=data.reviews[r].tekst
                let reviewDatum = document.createElement('p').textContent = data.reviews[r].datum;


                nieuweResultaatContentSpan.append(klantNaam)
                nieuweResultaatContentSpan.append(reviewtekst)

                nieuweResultaatContentSpan.append(reviewDatum)

                nieuweResultaatContaineritem.append(nieuweResultaatContentSpan)
                nieuweResultaatContainer.append(nieuweResultaatContaineritem)

                reviewsDiv.append(nieuweResultaatContainer)
            }


            console.log(12)


        })
        .catch(error=>console.log(error))
    console.log("na then")
})







document.getElementById("receptIngredienten")

document.getElementById("LikeRecept").addEventListener('click',function (){
    console.log("liking recept....")
    var fetchOptions = {
        method: "POST",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }

    let item=window.sessionStorage.getItem("clickedRecept")
    fetch("restservices/"+item+"/like",fetchOptions)
        .then(response=>{
            if (response.status===200){
                console.log("status is 200")
                return response.json()
            }else if(response.status===403){

                alert("Je moet ingelogd zijn als klant om dit functie uit te voeren")
            }else console.log("status not 200")
        })
        .then(response=>{
            alert(response.result)

        })

})




document.getElementById("DislikeRecept").addEventListener('click',function (){
    var fetchOptions = {
        method: "POST",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }


    let item=window.sessionStorage.getItem("clickedRecept")
    fetch("restservices/"+item+"/dislike",fetchOptions)
        .then(response=>{
            if (response.status===200){
                console.log("status is 200")
                return response.json()
            }else if(response.status===403){

                alert("Je moet ingelogd zijn als klant om dit functie uit te voeren")
            }else console.log("status not 200")
            })
        .then(res=>{
            alert(res.result)
        })
})


document.getElementById("toonReviewForm").addEventListener('click',function (){
    let nieuwreviewDiv=document.getElementById("newReviewDiv")

    let reviewForm=document.createElement('form')
    reviewForm.setAttribute("id","reviewForm")

    let html=`<label for="opinie">Wat vindt je van deze recept?</label>
            <input type="text" id="opinie" name="opinie" required>
<!--            <div>-->
<!--            <a style="" href="" id="stuurReviewForm"  class="types" onclick="return false"> Review sturen</a>-->
<!--            </div>-->
            `

            reviewForm.innerHTML=html
    let buttonDiv=document.createElement('div')
    let stuurButton=document.createElement('a')
    stuurButton.setAttribute("href","index.html")
    stuurButton.setAttribute("class","types")
    stuurButton.setAttribute("onclick","return false")
    stuurButton.addEventListener('click',postReview)
    stuurButton.textContent="Stuur Review"
    buttonDiv.append(stuurButton)


            nieuwreviewDiv.append(reviewForm)
            nieuwreviewDiv.append(buttonDiv)

})



// document.getElementById("stuurReviewForm").addEventListener('click',function (){
//     console.log("clicked")
//
//
// })

function postReview(){
    if (document.getElementById("opinie").value.length>0){
        var formData = new FormData(document.getElementById("reviewForm"));
        var encData = new URLSearchParams(formData.entries());
        var fetchOptions = {
            method: "POST",
            headers: {
                'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
            },
            body: encData
        }
        let receptNaam=sessionStorage.getItem("clickedRecept")
        fetch('restservices/'+receptNaam+'/review',fetchOptions)
            .then(response=>{
                if (response.status===200){
                    console.log("status 200")
                    return response.json()
                }else if(response.status===403){

                    alert("Je moet ingelogd zijn als klant om dit functie uit te voeren")
                }else console.log("status not 200")
            })
            .then(res=>{
                alert(res.result)
                while (document.getElementById("newReviewDiv").firstChild) {
                    document.getElementById("newReviewDiv").removeChild(document.getElementById("newReviewDiv").firstChild);
                }
                console.log()

            })
    }else alert("Review form is leeg")


}


document.getElementById('zoekButton').addEventListener('click',function (){
    var formData = new FormData(document.getElementById("zoekOpdrachtForm2"));
    var encData = new URLSearchParams(formData.entries());

    let zoekText=document.getElementById('zoekOpdracht').value
    console.log("text "+zoekText)


    fetch("restservices/"+zoekText+"/zoekresultaat" ,{method: "POST", body:encData})
        .then(response=>response.json())
        .then(data=>{
            clickedRecept=data.naam
            console.log("data naam "+data.naam)
            saveReceptNaam()
            window.location.assign('ReceptInformatie.html')
        })
})


// window.location("index.html")