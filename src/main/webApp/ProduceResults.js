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




console.log(radiobutton)



function getSortingValues(type) {
    var ele = document.getElementsByName('duratie');

    
      
    for(let i = 0; i < ele.length; i++) {
        if(ele[i].checked){
            
            console.log(ele[i].value);
            console.log(type);
            var radiobuttonValue=ele[i].value;

        }
        // document.getElementById("result").innerHTML
        //         = "Gender: "+ele[i].value;
    }

    console.log(radiobuttonValue);



     fetch('/restservices/home')
     .then(res =>res.json())
     .then(data => {
        var resultFilter = data.filter( element => element.categorie ==type && element.duratie ==radiobuttonValue)
        console.log(resultFilter)

        var resultatenSpan=document.getElementById('resultatenSpan')
        for(let i = 0; i < data_filter.length; i++) {
            //console.log( data_filter[i])

            let nieuweResultaatContainer=document.createElement(div)
            nieuweResultaatContainer.className='container';

            let nieuweResultaatContaineritem=document.createElement(div)
            nieuweResultaatContaineritem.className='container-item';

            let nieuweResultaatContentSpan=document.createElement(span)
           

            
            let receptNaam=document.createElement('p').textContent= data_filter[i].naam;
            let receptDuratie=document.createElement('p').textContent='🕐Duration: '+radiobuttonValue;


            nieuweResultaatContentSpan.appendChild(receptNaam)
            nieuweResultaatContentSpan.appendChild(receptDuratie)

            nieuweResultaatContaineritem.appendChild(nieuweResultaatContentSpan)
            nieuweResultaatContainer.appendChild(nieuweResultaatContaineritem)



            resultatenSpan.appendChild(nieuweResultaatContainer)

        
    }


     })
         .catch(error=> console.log("Iets ging fout "+error));


}