window.addEventListener('load',function (){
    let loginButtonSpan=document.getElementById("loginButtonSpan")



    if (sessionStorage.getItem("myJWT")==null){
        let loginButton=document.createElement('a')
        loginButton.setAttribute('position','relative')
        loginButton.setAttribute('left','750px')
        loginButton.setAttribute('bottom','140px')
        loginButton.setAttribute('href','KiesRole.html')
        loginButton.setAttribute('id','logInButton')
        //loginButton.setAttribute("className","types")
        loginButton.textContent='Inloggen'

        loginButtonSpan.append(loginButton)
        document.getElementById("logInButton").className = "types";

    }else {
        console.log("elseeee")
        let uitlogButton=document.createElement('a')
        uitlogButton.setAttribute('position','relative')
        uitlogButton.setAttribute('left','750px')
        uitlogButton.setAttribute('bottom','140px')
        uitlogButton.setAttribute('href','index.html')
        uitlogButton.setAttribute('id','uitlogButton')
        uitlogButton.setAttribute("className","types")
        uitlogButton.textContent='Uitloggen'
        uitlogButton.addEventListener('click',logOut)
        loginButtonSpan.append(uitlogButton)
        document.getElementById("uitlogButton").className = "types";

    }

    //document.getElementById("resultatenSpan").innerHTML=""

    getSortingValues("Burger")


})

function logOut(){
    sessionStorage.clear()
}






var clickedRecept=null

const radiobutton=document.getElementsByName("duratie")

document.getElementById('burgerCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('burgerCategorieSorteren').textContent);
})
document.getElementById('sushiCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('sushiCategorieSorteren').textContent);
})
document.getElementById('steakCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('steakCategorieSorteren').textContent);
})
document.getElementById('pastaCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('pastaCategorieSorteren').textContent);
})
document.getElementById('spareribsCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('spareribsCategorieSorteren').textContent);
})
document.getElementById('chickenCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('chickenCategorieSorteren').textContent);
})
document.getElementById('pizzaCategorieSorteren').addEventListener('click',ev=>{
    getSortingValues(document.getElementById('pizzaCategorieSorteren').textContent);
})




console.log('radiobutton '+radiobutton)

document.getElementById('testingInfoFunctie').addEventListener('click',function (){
    saveReceptNaam(document.getElementById('testingInfoFunctie').getAttribute('name'))
    window.location.assign("ReceptInformatie.html")
})



function saveReceptNaam(){
    window.sessionStorage.setItem("clickedRecept",clickedRecept)
    console.log('click')
}



function getSortingValues(type) {
    var resultatenSpan=document.getElementById('resultatenSpan')

    while (resultatenSpan.firstChild) {
        resultatenSpan.removeChild(resultatenSpan.firstChild);
    }
    //var resultatenSpan=document.getElementById('resultatenSpan')

    var ele = document.getElementsByName('kiesDuratie');

    
      
    for(let i = 0; i < ele.length; i++) {
        if(ele[i].checked){
            
            console.log(ele[i].value);
            console.log(type);
            var radiobuttonValue=ele[i].value;

        }
        // document.getElementById("result").innerHTML
        //         = "Gender: "+ele[i].value;
    }



    var fetchOptions = {
        method: "GET",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }


     fetch('restservices/home',fetchOptions)
     .then(res =>res.json())
     .then(data => {

         if (radiobuttonValue=="Any"){

             var resultFilterAny = data.filter( element => {


                 console.log("element "+element.naam+" "+element.duratie);
                 return element.categorie ==type})

             if (resultFilterAny.length<1){
                 resultatenSpan.append(document.createElement('h1').textContent="No Results")
             }
                else{
                 console.log('-1')

                 console.log('0')
                 for(let i = 0; i < resultFilterAny.length; i++) {
                     let nieuweResultaatContainer = document.createElement("div")
                     nieuweResultaatContainer.className = 'container';
                     console.log('1')
                     let nieuweResultaatContaineritem = document.createElement("div")
                     nieuweResultaatContaineritem.className = 'container-item';
                     console.log('2')
                     let nieuweResultaatContentSpan = document.createElement("span")
                     console.log('3')

                     let receptNaam = document.createElement('a');
                     receptNaam.href="ReceptInformatie.html"
                     receptNaam.setAttribute("href","ReceptInformatie.html")
                     receptNaam.setAttribute("margin","10px")

                     receptNaam.textContent=resultFilterAny[i].naam
                     receptNaam.classList.add('receptNaam');
                     console.log('3.1')


                     receptNaam.setAttribute("name",resultFilterAny[i].naam)

                     receptNaam.addEventListener('click',function (){

                         clickedRecept=receptNaam.textContent
                         saveReceptNaam()
                         console.log('cliii')
                     });


                     console.log('4')

                    console.log('likes '+resultFilterAny[i].aantalLikes)
                     console.log('dislikes '+resultFilterAny[i].aantalDisLikes)
                     let receptDuratie = document.createElement('p').textContent = '🕐Duration: ' + resultFilterAny[i].duratie;
                     let receptLikes=document.createElement('p').textContent="Likes: "+resultFilterAny[i].aantalLikes
                     let receptDisLikes=document.createElement('p').textContent="Dislikes: "+resultFilterAny[i].aantalDisLikes

                     // `<div className="container">
                     //     <div className="container-item"><img style="height: 150px;"
                     //                                          src="https://www.bergwelten.com/files/article/images/burger_0.jpg"
                     //                                          alt="burger foto"></div>
                     //     <span>
                     //
                     //         <a style="margin: 10px; text-decoration-color: black; text-decoration-line: none"
                     //            href="burger5min.html">Double cheeseBurger</a>
                     //    <p style="margin: 10px;">🕐Duration: 15 minuut</p>
                     //    </span>
                     // </div>`

                     let receptNaamContainer=document.createElement('div')
                     receptNaamContainer.append(receptNaam)

                     let receptLikesContainer=document.createElement('div')
                     receptLikesContainer.append(receptLikes)

                     let receptDisLikesContainer=document.createElement('div')
                     receptDisLikesContainer.append(receptDisLikes)

                     nieuweResultaatContentSpan.append(receptNaamContainer)
                     nieuweResultaatContentSpan.append(receptDuratie)
                     nieuweResultaatContentSpan.append(receptLikesContainer)
                     nieuweResultaatContentSpan.append(receptDisLikesContainer)

                     console.log('5')
                     console.log(resultFilterAny[i].imgSrc)

                     nieuweResultaatContaineritem.innerHTML="<img style='height: 150px;' src='"+resultFilterAny[i].imgSrc+"' alt='burger foto'>"
                     nieuweResultaatContainer.append(nieuweResultaatContaineritem)
                     nieuweResultaatContainer.append(nieuweResultaatContentSpan)
                     // nieuweResultaatContaineritem.appendChild(nieuweResultaatContentSpan)
                     // nieuweResultaatContainer.appendChild(nieuweResultaatContaineritem)
                     console.log('6')

                     resultatenSpan.appendChild(nieuweResultaatContainer)
                     console.log('7')
                 }
             }






         }else{
             var resultFilter = data.filter( element => {

                 console.log("element "+element.naam+" "+element.duratie);
                 return element.categorie ==type && element.duratie ==radiobuttonValue})

             if (resultFilter.length<1){
                 resultatenSpan.append(document.createElement('h1').textContent="No Results")
             }
            else{

                 for(let i = 0; i < resultFilter.length; i++) {

                     let nieuweResultaatContainer = document.createElement("div")
                     nieuweResultaatContainer.className = 'container';
                     console.log('1')
                     let nieuweResultaatContaineritem = document.createElement("div")
                     nieuweResultaatContaineritem.className = 'container-item';
                     console.log('2')
                     let nieuweResultaatContentSpan = document.createElement("span")
                     console.log('3')

                     let receptNaam = document.createElement('a');
                     receptNaam.href="ReceptInformatie.html"
                     receptNaam.setAttribute("href","ReceptInformatie.html")
                     receptNaam.setAttribute("margin","10px")

                     receptNaam.textContent=resultFilter[i].naam
                     receptNaam.classList.add('receptNaam');
                     console.log('3.1')


                     receptNaam.setAttribute("name",resultFilter[i].naam)

                     receptNaam.addEventListener('click',function (){

                         clickedRecept=receptNaam.textContent
                         saveReceptNaam()
                         console.log('cliii')
                     });


                     console.log('4')

                     console.log('likes '+resultFilter[i].aantalLikes)
                     console.log('dislikes '+resultFilter[i].aantalDisLikes)
                     let receptDuratie = document.createElement('p').textContent = '🕐Duration: ' + resultFilter[i].duratie;
                     let receptLikes=document.createElement('p').textContent="Likes: "+resultFilter[i].aantalLikes
                     let receptDisLikes=document.createElement('p').textContent="Dislikes: "+resultFilter[i].aantalDisLikes

                     // `<div className="container">
                     //     <div className="container-item"><img style="height: 150px;"
                     //                                          src="https://www.bergwelten.com/files/article/images/burger_0.jpg"
                     //                                          alt="burger foto"></div>
                     //     <span>
                     //
                     //         <a style="margin: 10px; text-decoration-color: black; text-decoration-line: none"
                     //            href="burger5min.html">Double cheeseBurger</a>
                     //    <p style="margin: 10px;">🕐Duration: 15 minuut</p>
                     //    </span>
                     // </div>`

                     let receptNaamContainer=document.createElement('div')
                     receptNaamContainer.append(receptNaam)

                     let receptLikesContainer=document.createElement('div')
                     receptLikesContainer.append(receptLikes)

                     let receptDisLikesContainer=document.createElement('div')
                     receptDisLikesContainer.append(receptDisLikes)

                     nieuweResultaatContentSpan.append(receptNaamContainer)
                     nieuweResultaatContentSpan.append(receptDuratie)
                     nieuweResultaatContentSpan.append(receptLikesContainer)
                     nieuweResultaatContentSpan.append(receptDisLikesContainer)

                     console.log('5')

                     nieuweResultaatContaineritem.innerHTML="<img style='height: 150px;' src='"+resultFilter[i].imgSrc+"' alt='burger foto'>"
                     nieuweResultaatContainer.append(nieuweResultaatContaineritem)
                     nieuweResultaatContainer.append(nieuweResultaatContentSpan)
                     // nieuweResultaatContaineritem.appendChild(nieuweResultaatContentSpan)
                     // nieuweResultaatContainer.appendChild(nieuweResultaatContaineritem)
                     console.log('6')

                     resultatenSpan.appendChild(nieuweResultaatContainer)
                     console.log('7')
                 }
             }




         }
         // console.log(data);




    }).catch(error=> console.log("Iets ging fout "+error));

}






document.getElementById('zoekButton').addEventListener('click',function (){


    let zoekText=document.getElementById('zoekOpdracht').value
    console.log("text "+zoekText)


    fetch("restservices/"+zoekText+"/zoekresultaat" ,{method: "POST"})
        .then(response=>response.json())
        .then(data=>{
            clickedRecept=data.naam
            console.log("data naam "+data.naam)
            saveReceptNaam()
            window.location.assign('ReceptInformatie.html')
        })
})



//bij het clicken van de naam van een recept wordt de naam van die recept toegevoegd aan de session storage om die later te gebruiken om de recept informatie te halen uit de server

