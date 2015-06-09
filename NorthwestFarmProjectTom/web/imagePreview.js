/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function previewImage() {
    //var x = $('#uploadImage').val();
  
   //var arr = x.split("\\");
    
    //$('#impath').html(arr[2]);
    
    
        var oFReader = new FileReader();
        oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

        oFReader.onload = function (oFREvent) {
            document.getElementById("uploadPreview").src = oFREvent.target.result;
            $('#uploadPreview').show();
        };
    };
    
    
    $(window).load(hideImage);
    
    function hideImage(){
        
        $('#uploadPreview').hide();
        
        if($('#mediaFilelist').val() === null){
            $('#imagemedia').hide();
            
        }
        
    }
    
    function imageInsert(){
    document.getElementById("imagemedia").src = document.getElementById("mediaFilelist").value;
    $('#imagemedia').show();

}