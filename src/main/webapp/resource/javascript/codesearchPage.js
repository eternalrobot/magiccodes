function ShowCodeSearch() {
    var input=$("#Input").val();
    var output=$("#Output").val();
    var detial=$("#Detial").val();

    if(input==""||output==""||detial==""){
        $("#CodeSearch").css('display','none');
    }
    else{
        $("#CodeSearch").css('display','block');
    }
}

var timer = setInterval(function(){
    ShowCodeSearch();
},100);

function clickCodeSearch() {
    $.ajax({
        url:"",
        data:{

        },
        success:function (data) {

        }
    })
}