




window.addEventListener('load',function (){
    var fetchOptions = {
        method: "GET",
        headers: {
            'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
        }
    }
fetch('restservices/admin',fetchOptions)

    .then(response=>{
        if (response.status===401){
            document.querySelector('main').innerHTML=`<h1>Restricted! Content is Alleen Beschikbaar Voor Beheerder</h1>`
            let buttonDiv=document.createElement('div')
            let stuurButton=document.createElement('a')
            stuurButton.setAttribute("href","KiesRole.html")
            stuurButton.setAttribute("class","types")

            //stuurButton.addEventListener('click',verwijderKlant)
            stuurButton.textContent="inloggen"
            buttonDiv.append(stuurButton)
            document.querySelector('main').append(buttonDiv)
        }
        console.log("response "+response)
        return response.json()
    })
    .catch(err=>{
        console.log("ina catch")
        console.log(err)
        console.log(response.error)

    })



})

document.getElementById("goToKlantDeleten").addEventListener('click',function (){
    let klantVerwijderenFormDiv=document.getElementById("klantVerwijderenFormDiv")

    if(klantVerwijderenFormDiv.children.length===0){
        let klantVerwijderenForm=document.createElement('form')
        klantVerwijderenForm.setAttribute("id","klantVerwijderenForm")

        let html=`<label for="klantGebruikersNaam">Klant gebruikers naam:</label>
            <input type="text" id="klantGebruikersNaam" name="klantGebruikersNaam" required>`

        klantVerwijderenForm.innerHTML=html

        let buttonDiv=document.createElement('div')
        let stuurButton=document.createElement('a')
        stuurButton.setAttribute("href","")
        stuurButton.setAttribute("class","types")
        stuurButton.setAttribute("onclick","return false")
        stuurButton.addEventListener('click',verwijderKlant)
        stuurButton.textContent="Confirm"
        buttonDiv.append(stuurButton)


        klantVerwijderenFormDiv.append(klantVerwijderenForm)
        klantVerwijderenFormDiv.append(buttonDiv)



    }



})




document.getElementById("goToReceptsDeleten").addEventListener('click',function (){



    let receptVerwijderenFormDiv=document.getElementById("receptVerwijderenFormDiv")
    console.log(receptVerwijderenFormDiv.children.length)
    if (receptVerwijderenFormDiv.children.length===0){

        let receptVerwijderenForm=document.createElement('form')
        receptVerwijderenForm.setAttribute("id","receptVerwijderenForm")


        let html=`<label for="receptNaam">Recept Naam:</label>
            <input type="text" id="receptNaam" name="receptNaam" required>`

        receptVerwijderenForm.innerHTML=html

        let buttonDiv=document.createElement('div')
        let stuurButton=document.createElement('a')
        stuurButton.setAttribute("href","")
        stuurButton.setAttribute("class","types")
        stuurButton.setAttribute("onclick","return false")
        stuurButton.addEventListener('click',verwijderRecept)
        stuurButton.textContent="Confirm"
        buttonDiv.append(stuurButton)


        receptVerwijderenFormDiv.append(receptVerwijderenForm)
        receptVerwijderenFormDiv.append(buttonDiv)

    }

    //const myElement = document.getElementById('foo');
    // console.log(receptVerwijderenFormDiv)
    // for (let i = 0; i < receptVerwijderenFormDiv.children.length; i++) {
    //     console.log(receptVerwijderenFormDiv.children[i].tagName);
    // }


})


document.getElementById("goToReceptsToevoegen").addEventListener('click',function (){



    let receptToevoegenFormDiv=document.getElementById("receptToevoegenFormDiv")
    console.log(receptToevoegenFormDiv.children.length)
    if (receptToevoegenFormDiv.children.length===0){

        let receptToevoegenForm=document.createElement('form')
        receptToevoegenForm.setAttribute("id","receptToevoegenForm")

        //String naam, String duratie, String categorie,String youtubeVideoSrc,String imgSrc,ArrayList<String> ingredienten,int aantalDisLikes,int aantalLikes,ArrayList<String> stappen,ArrayList<Review> reviews

        let html=`<label for="receptNaam">Recept Naam:</label>
            <input type="text" id="receptNaam" name="receptNaam" required>
            
            <label for="duratie">Recept Duratie:</label>
            <input type="text" id="duratie" name="duratie" required>
            
            <label for="categorie">Recept categorie:</label>
            <input type="text" id="categorie" name="categorie" required>
            
            <label for="youtubeVideoSrc">Recept Youtube Video Link:</label>
            <input type="text" id="youtubeVideoSrc" name="youtubeVideoSrc" required>
            
            <label for="imgSrc">image source:</label>
            <input type="text" id="imgSrc" name="imgSrc" required>
            
            <label for="ingredienten">ingredienten</label>
            <input type="text" id="ingredienten" name="ingredienten" required>
            
            <label for="stappen">stappen</label>
            <input type="text" id="stappen" name="stappen" required>
            
            `

        receptToevoegenForm.innerHTML=html

        let buttonDiv=document.createElement('div')
        let stuurButton=document.createElement('a')
        stuurButton.setAttribute("href","")
        stuurButton.setAttribute("class","types")
        stuurButton.setAttribute("onclick","return false")
        stuurButton.addEventListener('click',receptToevoegen)
        stuurButton.textContent="Confirm"
        buttonDiv.append(stuurButton)


        receptToevoegenFormDiv.append(receptToevoegenForm)
        receptToevoegenFormDiv.append(buttonDiv)

    }



})

document.getElementById("goToReceptsVeranderen").addEventListener('click',function () {
    let receptVeranderenFormDiv = document.getElementById("receptVeranderenFormDiv")
    console.log(receptVeranderenFormDiv.children.length)
    if (receptVeranderenFormDiv.children.length === 0) {

        let receptVeranderenForm = document.createElement('form')
        receptVeranderenForm.setAttribute("id", "receptVeranderenForm")

        //String naam, String duratie, String categorie,String youtubeVideoSrc,String imgSrc,ArrayList<String> ingredienten,int aantalDisLikes,int aantalLikes,ArrayList<String> stappen,ArrayList<Review> reviews
        let receptNaamHtml = `<label for="receptnaam">Recept Naam:</label>
            <input type="text" id="receptnaam" name="receptnaam" required> `
            




        let radioButtonsHtml=`
        <div>
            <input type="radio" name="kiesEigenschaap" value="naam"> Naam
            </div>
            <div>
            <input type="radio" name="kiesEigenschaap" value="duratie"> Duratie
            </div>
            <div>
            <input type="radio" name="kiesEigenschaap" value="categorie"> Categorie
            </div>
            <div>
            <input type="radio" name="kiesEigenschaap" value="youtubeVideoSrc"> Youtube Video Link
            </div>
            <div>
            <input type="radio" name="kiesEigenschaap" value="imgSrc"> Afbeelding Link/Path
            </div>
            <div>
            <input type="radio" name="kiesEigenschaap" value="ingredienten"> Ingredienten
            </div>
        
        `

        let veranderingsValue=`<div>
           Value <input type="text" name="veranderingsValue" id="veranderingsValue"> 
            </div>`

        receptVeranderenForm.innerHTML = receptNaamHtml
        document.getElementById("receptVeranderenRadioButtonsDiv").innerHTML=radioButtonsHtml
        document.getElementById("receptVeranderenValueDiv").innerHTML=veranderingsValue


        let buttonDiv = document.createElement('div')
        let stuurButton = document.createElement('a')
        stuurButton.setAttribute("href", "")
        stuurButton.setAttribute("class", "types")
        stuurButton.setAttribute("onclick", "return false")
        stuurButton.addEventListener('click', veranderRecept)
        stuurButton.textContent = "Confirm"
        buttonDiv.append(stuurButton)


        receptVeranderenFormDiv.append(receptVeranderenForm)
        document.getElementById("receptVeranderenValueDiv").append(buttonDiv)
    }


})



function verwijderKlant(){
    if (document.getElementById("klantGebruikersNaam").value.length>0){

        //document.getElementById("klantGebruikersNaam").value=""

        var formData = new FormData(document.getElementById("klantVerwijderenForm"));
        var encData = new URLSearchParams(formData.entries());
        var fetchOptions = {
            method: "POST",
            headers: {
                'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
            },
            body: encData
        }


        fetch('restservices/admin/deleteklant',fetchOptions)
            .then(response=>{
                if (response.status===200){
                    console.log("status 200")


                    return response.json()
                }else console.log("status not 200")
            })
            .then(res=>{
                console.log(res.result)
                document.getElementById("klantVerwijderenMelding").textContent=res.result

                document.getElementById("klantGebruikersNaam").value="";
            }).catch(err=>{
            document.getElementById("klantVerwijderenMelding").textContent="Geen klant gevonden met die naam"
        })
        console.log("verwijder")
    }else document.getElementById("klantVerwijderenMelding").textContent="Klant gebruikers naam veld is leeg."


}



function verwijderRecept(){
    if (document.getElementById("receptNaam").value.length>0){
        var formData = new FormData(document.getElementById("receptVerwijderenForm"));
        var encData = new URLSearchParams(formData.entries());
        var fetchOptions = {
            method: "POST",
            headers: {
                'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
            },
            body: encData
        }
        fetch('restservices/admin/deleterecept',fetchOptions)
            .then(response=>{
                if (response.status===200){
                    console.log("status 200")
                    return response.json()
                }else console.log("status not 200")
            })
            .then(res=>{
                console.log(res.result)
                document.getElementById("receptVerwijderenMelding").textContent=res.result
            })

    }else document.getElementById("receptVerwijderenMelding").textContent="Recept naam veld is leeg."

}







function receptToevoegen(){
    if (document.getElementById("receptNaam").value.length>0&&document.getElementById("duratie").value.length>0&&document.getElementById("youtubeVideoSrc").value.length>0&&document.getElementById("imgSrc").value.length>0&&document.getElementById("ingredienten").value.length>0&&document.getElementById("stappen").value.length>0){


        var formData = new FormData(document.getElementById("receptToevoegenForm"));
        var encData = new URLSearchParams(formData.entries());
        var fetchOptions = {
            method: "POST",
            headers: {
                'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
            },
            body: encData
        }


        fetch('restservices/admin/addrecept',fetchOptions)
            .then(response=>{
                if (response.status===200){
                    console.log("status 200")
                    return response.json()
                }else console.log("status not 200")
            })
            .then(res=>{
                console.log(res.result)
                document.getElementById("receptToevoegenMelding").textContent=res.result

            })
    }else document.getElementById("receptToevoegenMelding").textContent="Een of meer van u velden zijn leeg"


}


function veranderRecept(){
    if (document.getElementById("receptnaam").value.length>0){

        var ele = document.getElementsByName('kiesEigenschaap');



        for(let i = 0; i < ele.length; i++) {
            if(ele[i].checked){

                console.log(ele[i].value);

                var radiobuttonValue=ele[i].value;

            }

        }
        if (radiobuttonValue!=null){
            console.log("radioButoon "+radiobuttonValue)
            if (document.getElementById("veranderingsValue").value.length>0){

                const fetchOptions = {
                    method: "POST",
                    headers: {
                        'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")
                    }

                }


                fetch('restservices/admin/changerecept/'+document.getElementById("receptnaam").value+'/'+radiobuttonValue+'/'+document.getElementById("veranderingsValue").value,fetchOptions)
                    .then(response=>{
                        if (response.status===200){
                            console.log("status 200")
                            return response.json()
                        }else console.log("status not 200")
                    })
                    .then(res=>{
                        console.log(res.result)
                        document.getElementById("receptVeranderenMelding").textContent=res.result

                    })


            }else document.getElementById("receptVeranderenMelding").textContent="Je hebt geen value ingevuld"

        }else document.getElementById("receptVeranderenMelding").textContent="Je hebt geen eigenschaap geselecteerd"

    }else document.getElementById("receptVeranderenMelding").textContent="Recept Naam veld is leeg"





}


