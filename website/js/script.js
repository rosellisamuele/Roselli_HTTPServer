//Dichiarazione delle variabili
var contaTentativi=1;
var X;
var bgStyle;
var status;
/*Nel caso si tratti del primo tentativo, randomizzo
direttamente senza chiedere all'utente*/
if(contaTentativi===1) randN();
//Genera nuovo numero
function randN(){
    X = parseInt(Math.random()*100);
    console.log("Numero generato");
    
    document.getElementById("status").innerHTML= "Nuovo numero generato";
    bgStyle="white";
    document.getElementById("bd").style.backgroundColor = bgStyle;
    //console.log(X);
    contaTentativi = 1;
}

//Compara X (Num. generato) e Y (Num. inserito)
function compXY(){
    var Y = parseInt(document.getElementById("input").value);
    
    console.log(Y);
    if(Y > X){
        
        bgStyle="red";
        console.log("Il numero inserito e' maggiore. Ritenta");
        status = "Il numero inserito e' maggiore. Ritenta!";
        contaTentativi++;
        
    }else if(Y < X){
        bgStyle="orange";
        console.log("Il numero inserito e' minore. Ritenta");
        status = "Il numero inserito e' minore. Ritenta!";
        contaTentativi++;
        
    }else{
        bgStyle="green";
        console.log("Complimenti! Hai indovinato il numero in "+contaTentativi+" tentativi!");
        status = "Complimenti! Hai indovinato il numero ("+X+") in "+contaTentativi+" tentativi!";
    }
    
    document.body.style.backgroundColor = bgStyle;
    document.getElementById("status").innerHTML=status;
}