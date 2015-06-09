/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function initialize(n, lt, lg) {

//alert('hiiiiiii');
    var name = n.split(',');
//    alert(name.length);
    var lat = new Array(20);
    lat = lt.split(',');
//    alert(lat);
    var lng = new Array(20);
    lng = lg.split(',');
//    alert(lng);


    var rtLabLatlng = new google.maps.LatLng(lat[0], lng[0]);

    var mapOptions = {
        zoom: 11,
        center: rtLabLatlng
    };

    var mapDiv = document.getElementById('map');
    var map = new google.maps.Map(mapDiv, mapOptions);

    for (i = 0; i < lat.length - 1; i++)
    {

        var LabLatlng = new google.maps.LatLng(lat[i], lng[i]);
        window["rtLabmarker" + i] = new google.maps.Marker({
            position: LabLatlng,
            map: map,
            title: name[i],
            clickable: true
        });

    }

    for (i = 0; i < lat.length - 1; i++)
    {
        createVariable(i);
    }

    function createVariable(iValue)
    {
        var sampleMarker = window["rtLabmarker" + iValue];
        google.maps.event.addDomListener(sampleMarker, 'click', function () {
            navigatePage(sampleMarker.title);
        });
    }

    google.maps.event.addDomListener(map, 'idle', function () {
        center = map.getCenter();
    });

    $(window).resize(function () {
        //          map.setCenter(map.getCenter());
        map.setCenter(rtLabLatlng);

    });
}

  function takeToNextPage()
    {
       window.location = "faces/enterpriseHome.xhtml";
    }

function navigatePage(pos) {
    alert(pos);
  
   
//    document.getElementById("testValue").setAttribute('value',pos);    
    sendFName([{"FName":pos}]);
   
//    document.getElementById('hideFacName:myinput').value = pos;  
//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FacName",pos);
//     takeToNextPage();
window.location = "enterpriseHome.xhtml";
     
    
}
google.maps.event.addDomListener(window, 'load', initialize);
 