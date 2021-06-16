// const newDiv = document.createElement("div");
// newDiv.style="container";

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

function saveReceptNaam(name){
    window.sessionStorage.setItem("clickedRecept",name)

}



function getSortingValues(type) {
    //resultatenSpan.removeChild(nieuweResultaatContainer)
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
         // console.log(data);
        var resultFilter = data.filter( element => {

            console.log("element "+element.naam+" "+element.duratie);
            return element.categorie ==type && element.duratie ==radiobuttonValue})


        console.log(resultFilter)
         console.log('-1')

        var resultatenSpan=document.getElementById('resultatenSpan')
         console.log('0')
        for(let i = 0; i < resultFilter.length; i++) {
            //console.log( data_filter[i])

            let nieuweResultaatContainer = document.createElement("div")
            nieuweResultaatContainer.className = 'container';
            console.log('1')
            let nieuweResultaatContaineritem = document.createElement("div")
            nieuweResultaatContaineritem.className = 'container-item';
            console.log('2')
            let nieuweResultaatContentSpan = document.createElement("span")
            console.log('3')

            let receptNaam = document.createElement('a');
            receptNaam.href="accountInfo.html"
            receptNaam.setAttribute("href","accountInfo.html")
            //receptNaam.className='receptNaamm';
            receptNaam.textContent=resultFilter[i].naam
            receptNaam.classList.add('receptNaam');
            console.log('3.1')


            receptNaam.setAttribute("name",resultFilter[i].naam)
            receptNaam.addEventListener('click',saveReceptNaam(receptNaam.getAttribute("name")));


            console.log('4')


            let receptDuratie = document.createElement('p').textContent = '🕐Duration: ' + radiobuttonValue;


            nieuweResultaatContentSpan.append(receptNaam)
            nieuweResultaatContentSpan.append(receptDuratie)
            console.log('5')

            nieuweResultaatContaineritem.appendChild(nieuweResultaatContentSpan)
            nieuweResultaatContainer.appendChild(nieuweResultaatContaineritem)
            console.log('6')

            resultatenSpan.appendChild(nieuweResultaatContainer)
            console.log('7')
        }

    }).catch(error=> console.log("Iets ging fout "+error));

}


document.getElementById('zoekButton').addEventListener('click',function (){
    var formData = new FormData(document.getElementById("zoekOpdrachtForm"));
    var encData = new URLSearchParams(formData.entries());

    let zoekText=document.getElementById('zoekOpdracht').value


    fetch("restservices/"+zoekText ,{method: "POST", body:encData})
        .then(response=>response.json())
        .then(data=>{
            saveReceptNaam(data.naam)
            window.location.assign('ReceptInformatie.html')
        })
})



//bij het clicken van de naam van een recept wordt de naam van die recept toegevoegd aan de session storage om die later te gebruiken om de recept informatie te halen uit de server

