function clickDescriptionSearch() {
    
}


function ShowDescriptionSearch() {
    var detial=$("#Detial").val();

    if(detial==""){
        $("#DescriptionSearch").css('display','none');
    }
    else{
        $("#DescriptionSearch").css('display','block');
    }
}

var timer = setInterval(function(){
    ShowDescriptionSearch();
},100);
