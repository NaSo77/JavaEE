function loadXml(param){

    hidediv('dirsCont');
    showdiv('dirsContXml');
    document.getElementById('dirsContXml').innerHTML = '<div class="loading"><img src="/resources/im/loading.gif" width="40" height="40" border="0"/><br/><br/>Зареждане ...</div>';

	var req = null;
	if (window.XMLHttpRequest) {
	   req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	   req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var url = "category/xml/" + param;
    req.open("GET", url, true);
    req.onreadystatechange = callback( req );
    req.send(null);
    
}

function callback( req ){
	return function() {
		if (req.readyState == 4) {
		    if (req.status == 200) {
                html = '';
                dirshtml = '';
                name = '';
                short_name = '';
                path = '';
                letterCnt = 0;
                if(req.responseXML != null){
                    var xmlLength = req.responseXML.getElementsByTagName("name").length;
                    //if(xmlLength >16)
                    //    html += '<div class="dirsCat" style="border-right:1px solid #cccccc;">';
                    //else
                        html += '<div class="dirsCat" style="border-right:1px solid #cccccc;">';
                    for(var i = 0; i < xmlLength; i++){
                        name = req.responseXML.getElementsByTagName("name")[i].firstChild.nodeValue;
                        short_name = (req.responseXML.getElementsByTagName("short_name")[i].firstChild != null) ?
                            req.responseXML.getElementsByTagName("short_name")[i].firstChild.nodeValue : '';
                        path = (req.responseXML.getElementsByTagName("path")[i].firstChild != null) ?
                            req.responseXML.getElementsByTagName("path")[i].firstChild.nodeValue : '';

                        if(i <= 50){
                            if(short_name != '' && path != ''){
                                html += '<a href="/ukazatel/' + path + '"';
                                html += ' title="' + name + '" alt="' + name + '">';
                                html += short_name + '</a><br/>';
                            }else{
                                html += '<div class="letter">' + name + '</div>';
                                letterCnt++;
                            }
                        }

                        if(i == 16)
                            html += '</div><div class="dirsCat" style="border-right:1px solid #cccccc;">';
                        if(i == 33)
                            html += '</div><div class="dirsCatR">';
                    }
                    html += '</div>';

                    // popular catgories
                    var xmlPopularLength = req.responseXML.getElementsByTagName("namepopular").length;
                    for(var j = 0; j < xmlPopularLength; j++){
                        dirshtml += '<a href="/ukazatel/' + req.responseXML.getElementsByTagName("pathpopular")[j].firstChild.nodeValue + '">' + req.responseXML.getElementsByTagName("namepopular")[j].firstChild.nodeValue + '</a>';
                    }
                    // eof popular catgories
                }

                //document.getElementById('dirs').innerHTML = '<h1>Най-популярни</h1>' + dirshtml;
                document.getElementById('dirsContXml').innerHTML = html;
		    }
	    }
    }
}


function pushDetails(id, vip) {
	if(document.getElementById(id).className == "pusher_plus"){
       //showDetails(param, id);
       hidediv('s'+id);
       showdiv('sd'+id);
       if(vip) showdiv('vipImage'+id);
       document.getElementById(id).className = "pusher_minus";
    }else{
       //hideDetails(id);
       hidediv('sd'+id);
       showdiv('s'+id);
       if(vip) hidediv('vipImage'+id);
       document.getElementById(id).className = "pusher_plus";
    }
}

function showDetails(param, id){

	var req = null;
	if (window.XMLHttpRequest) {
	   req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	   req = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var url = "/category/details/" + param + "/" + id;
    req.open("GET", url, true);
    req.onreadystatechange = callback_res( req, id );
    req.send(null);

}
function callback_res( req, id ){
	return function() {
		if (req.readyState == 4) {
		    if (req.status == 200) {
                hidediv('s'+id);
                showdiv('sd'+id);
                document.getElementById('sd'+id).innerHTML = req.responseText;
		    }
	    }
    }
}

function hideDetails(id){
    hidediv('sd'+id);
    showdiv('s'+id);
}


function sendVoteRequest(id, vote){
	var req734 = null;
	if (window.XMLHttpRequest) {
	   req734 = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	   req734 = new ActiveXObject("Microsoft.XMLHTTP");
	}

	var url = "/link/vote/" + id + "/" + vote;
    req734.open("GET", url, true);
    req734.onreadystatechange = callback_sendvote( req734 );
    req734.send(null);
}

function callback_sendvote(req) {
	return function() {
		if (req.readyState == 4) {
		    if (req.status == 200) {
                // do nothing :)
		    }
	    }
    }
}

function trim(stringToTrim) {
  return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function isUrlIndexed() {
      var url = trim(document.forms[0].url.value).toLowerCase();
      while (url.indexOf("http://") === 0 || url.indexOf("https://") === 0) {
          if (url.indexOf("http://") === 0)
              url = url.substring(7);
            
          if (url.indexOf("https://") === 0)
              url = url.substring(8);
      }
      if(strstr(url, 'http://'))
          url = url.substring(7);
      checkURL(url);
}
function checkURL(url) {
      var link = "/link/urlcheck/" + url;
      var req1 = null;
      if (window.XMLHttpRequest) {
          req1 = new XMLHttpRequest();
      } else if (window.ActiveXObject) {
          req1 = new ActiveXObject("Microsoft.XMLHTTP");
      }
      req1.open("GET", link, true);
      req1.onreadystatechange = callback_url(req1);
      req1.send(null);
  }
  function callback_url(req){
      return function() {
          if (req.readyState == 4) {
              if (req.status == 200) {
                  var msg = req.responseText;
                  document.getElementById('urlmsg').innerHTML = msg;
              }
          }
      }
  }
