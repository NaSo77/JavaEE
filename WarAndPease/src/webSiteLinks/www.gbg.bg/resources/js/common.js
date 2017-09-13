var d = document;
var catSel = 'cat1';
var timerId = 0;
var numArr = new Array();
var contArr = new Array();
var contSArr = new Array();
var aucArr = new Array();
for(var i=0; i<28; i++)
numArr[i] = i;
function $(id, f){
return eval('d.getElementById(id)' + (f? '.style' : ''));
}
function hideShow(id) {
if(d.getElementById(id))
d.getElementById(id).style.display =(d.getElementById(id).style.display=='')?'none':'';
}
function showdiv(id) {
if (document.getElementById) {
document.getElementById(id).style.display = 'block';
}
}
function hidediv(id) {
if (document.getElementById) {
document.getElementById(id).style.display = 'none';
}
}
function hideshowdiv(id) {
if (document.getElementById(id).style.display == 'none') {
document.getElementById(id).style.display = 'block';
}else{
document.getElementById(id).style.display = 'none';
}
}
function getSelLetter(letter) {
for(c = 1; c <= 16; c++){
document.getElementById('cat'+c).className = "";
}
for(i = 0; i <= 27; i++){
document.getElementById('l'+i).className = "";
}
document.getElementById('l'+letter).className = "lsel";
loadXml(letter);
}
function letterOver(letter) {
if(document.getElementById(letter).className != "lsel"){
document.getElementById(letter).className='lover';
}
}
function letterOut(letter) {
if(document.getElementById(letter).className != "lsel"){
document.getElementById(letter).className='';
}
}
function catOver(cat) {
if(document.getElementById(cat).className != "sel"){
document.getElementById(cat).className='catOver';
}
}
function catOut(cat) {
if(document.getElementById(cat).className != "sel"){
document.getElementById(cat).className='';
}
}
function inArray (arr, element) {
var len = arr.length;
for ( var x = 0 ; x <= len ; x++ ) {
if ( arr[x] == element ) return true;
}
return false;
}
function getDirsCont() {
letter =window.location.hash.substring(1);
numCats = 16;
var selTab2 = "cat" + getRondomDigit(numCats-1);
catSel = selTab2;
selectTab (selTab2, 0, 2);
document.getElementById(selTab2).className = "sel";
if(letter != '' && letter != 'a' && inArray(numArr, letter)){
for(c = 1; c <= 16; c++){
document.getElementById('cat'+c).className = "";
}
document.getElementById('l'+letter).className = "lsel";
loadXml(letter);
}
}
function selectCatTab(catId){
hidediv('dirsContXml');
showdiv('dirsCont');
for(i = 0; i <= 27; i++){
document.getElementById('l'+i).className = "";
}
selectTab (catId, selTab2, 2);
document.getElementById(catId).className = "sel";
}
function selectCatTabClick(dir){
var curr_cat = parseInt(catSel.substring(3));
var catId = 1;
if(dir == 'up'){
    catId = (curr_cat > 1) ? curr_cat - 1 : 16;
}else if(dir == 'down'){
   catId = (curr_cat < 16) ? curr_cat + 1 : 1;
}
catSel = 'cat'+catId;
if (document.getElementById('cat'+catId).className.indexOf('sel') == -1){
currDate = new Date();
im = document.getElementById('btnClkCnt');
src = 'http://a.abv.bg/www/delivery/lg.php?bannerid=249&campaignid=136&zoneid=63&cb=';
src += currDate.getTime();
im.src = src;
}
selectCatTab('cat'+catId);
}
function voteForLink(idOn, idOff, lid, vote) {
	document.getElementById(idOn).style.display = 'none';
	sendVoteRequest(lid, vote, idOff);
}
function addToFavorites(url, title) {
if (document.all)  // ie
window.external.AddFavorite(url, title)
else if (window.sidebar) // firefox
window.sidebar.addPanel(title, url, "");
}
function unsetZIndex(){
if(document.getElementById('topTabs'))
document.getElementById('topTabs').style.zIndex = 0;
}
function setZIndex(){
if(document.getElementById('topTabs'))
document.getElementById('topTabs').style.zIndex = 1000;
}
function promoClose(){
SetCookie('promoCookie', 'promo', (24*7*3600), '/', 'www.gbg.bg', '');
if (checkCoockie('www.gbg.bg') && GetCookie('promoCookie')){
hidediv('promo');
document.body.style.backgroundImage = 'url(/resources/im/bg.gif)';
}
}
function promo(){
if (checkCoockie('www.gbg.bg') && GetCookie('promoCookie')){
hidediv('promo');
document.body.style.backgroundImage = 'url(/resources/im/bg.gif)';
}else{
showdiv('promo');
document.body.style.backgroundImage = 'url(/resources/im/body_promo_bg.gif)';
}
}
function getWeatherCookie(){
if (checkCoockie('www.gbg.bg') && GetCookie('weatherCookieN')){
selCity = GetCookie('weatherCookieN');
}else{
selCity = 'sinoptik100727011'
}
if(selCity.indexOf('sinoptik') == -1) selCity = 'sinoptik100727011';
hidediv('sinoptikMore');
showdiv(selCity);
showdiv(selCity+'Detail');
}
function changeCity(id){
SetCookie('weatherCookieN', 'sinoptik'+id, (24*7*3600), '/', 'www.gbg.bg', '');
if (checkCoockie('www.gbg.bg') && GetCookie('weatherCookieN')){
selCity = GetCookie('weatherCookieN');
}
hidediv('sinoptikMore');
showdiv(selCity);
showdiv(selCity+'Detail');
}
function getWeatherCookieNew(){
if (checkCoockie('www.gbg.bg') && GetCookie('weatherCookieN')){
selCity = GetCookie('weatherCookieN');
}else{
selCity = 'sinoptik100727011'
}
if(selCity.indexOf('sinoptik') == -1) selCity = 'sinoptik100727011';
loadCity(selCity);
}
function changeCityNew(id){
SetCookie('weatherCookieN', 'sinoptik'+id, (24*7*3600), '/', 'www.gbg.bg', '');
if (checkCoockie('www.gbg.bg') && GetCookie('weatherCookieN')){
selCity = GetCookie('weatherCookieN');
}
loadCity('sinoptik'+id);
}
function loadCity(id){
hidediv('sinoptikMore');
showdiv('sinoptikCont');
showdiv('sinoptikDetail');
html = '<a href="http://a.abv.bg/www/delivery/ck.php?oaparams=2__bannerid=106__zoneid=63__oadest=http://sinoptik.bg/?lid='+contSArr[id+'Lid']+'" target="_blank">';
html += '<img class="sinoptik_img" src="'+contSArr[id+'Img']+'" width="107" height="75" border="0" alt="'+contSArr[id+'Desc']+'" title="'+contSArr[id+'Desc']+'" /></a>';
html += '<a href="http://a.abv.bg/www/delivery/ck.php?oaparams=2__bannerid=106__zoneid=63__oadest=http://sinoptik.bg/?lid='+contSArr[id+'Lid']+'" target="_blank">'+contSArr[id+'Name']+'</a>&nbsp;';
html += '<a style="cursor:pointer;" onclick="hidediv(\'sinoptikCont\');hidediv(\'sinoptikDetail\');showdiv(\'sinoptikMore\');">';
html += '<img src="/resources/im/choose_btn.gif" width="9" height="5" border="0" alt=""/></a><br /><div class="sinoptikTemp">'+contSArr[id+'Temp']+'</div>';
html += '<span class="sinoptikLike">Усеща се като<br/>'+contSArr[id+'Like']+'</span>';
d.getElementById('sinoptikCont').innerHTML = html;
d.getElementById('sinoptikDetail').innerHTML = '<a href="http://a.abv.bg/www/delivery/ck.php?oaparams=2__bannerid=106__zoneid=63__oadest=http://sinoptik.bg/?tid=20&oid=100&lid='+contSArr[id+'Lid']+'" target="_blank">Детайлна 10 дневна прогноза</a><br/>';
}
function getCurrencyCookie(){
if (checkCoockie('www.gbg.bg') && GetCookie('currencyCookieN')){
selCurrency = GetCookie('currencyCookieN');
}else{
  selCurrency = 'USD';
}
if(selCurrency.indexOf('USD')==-1 && selCurrency.indexOf('EUR')==-1 && selCurrency.indexOf('CHF')==-1 && selCurrency.indexOf('GBP')==-1 && selCurrency.indexOf('TRY')==-1 && selCurrency.indexOf('RON')==-1)
selCurrency = 'USD';
hidediv('USD'); hidediv('EUR'); hidediv('CHF'); hidediv('GBP'); hidediv('TRY'); hidediv('RON');
showdiv(selCurrency);
hidediv('vTypes');
}
function changeCurrency(name){
SetCookie('currencyCookieN', name, (24*7*3600), '/', 'www.gbg.bg', '');
if (checkCoockie('www.gbg.bg') && GetCookie('currencyCookieN')){
selCurrency = GetCookie('currencyCookieN');
}
hidediv('USD'); hidediv('EUR'); hidediv('CHF'); hidediv('GBP'); hidediv('TRY'); hidediv('RON');
showdiv(selCurrency);
hidediv('vTypes');
}
function changeVal(obj, priv, pub, capId){
obj.value = (obj.value == priv)?pub:priv;
obj.parentNode.className = (obj.parentNode.className == 'inEdit')?'inEdit pr':'inEdit';
$(capId).className = ($(capId).className == '')?'pr':'';
}
function selectTab (newTabId, oldTabId, selTabNum){
newTab = d.getElementById(newTabId);
newTab.className += " sel";
if (oldTabId) {
oldTab = d.getElementById(oldTabId);
oldTab.className = oldTab.className.substring(0,oldTab.className.length-3);
hideShow (oldTabId+'Cont');
hideShow (oldTabId+'glink');
}
hideShow (newTabId+'Cont');
hideShow (newTabId+'glink');
switch(selTabNum) {
case 1:
selTab1 = newTabId;
break;
case 2:
selTab2 = newTabId;
break;
case 3:
selTab3 = newTabId;
break;
case 4:
selTab4 = newTabId;
break;
case 5:
selTab5 = newTabId;
break;
case 6:
selTab6 = newTabId;
break;
default:
selTab = newTabId;
}
hideShow(oldTabId);
showdiv(newTabId);
}
function selectTabNew (newTabId, oldTabId, selTabNum){
newTab = d.getElementById(newTabId);
newTab.className += " sel";
if (oldTabId) {
oldTab = d.getElementById(oldTabId);
oldTab.className = oldTab.className.substring(0,oldTab.className.length-3);
hideShow (oldTabId+'Cont');
hideShow (oldTabId+'glink');
}
hideShow (newTabId+'Cont');
hideShow (newTabId+'glink');
switch(selTabNum) {
case 4:
selTab4 = newTabId;
break;
case 5:
selTab5 = newTabId;
break;
case 6:
selTab6 = newTabId;
break;
default:
selTab = newTabId;
}
}
function getIntFromId (selObj){
num = parseInt(selObj.substring((selObj.length - 1),selObj.length));
return num;
}
function loadCont(cId, tabId, tabNum, today){
selectTabNew(cId, tabId, tabNum);
html = '<a href="'+contArr[cId+'Link']+'" target="_blank"><img src="'+contArr[cId+'Url']+'" border="0" alt="'+contArr[cId+'Title']+'" title="'+contArr[cId+'Title']+'" /></a>';
html += '<a href="'+contArr[cId+'Link']+'" title="'+contArr[cId+'Title']+'" target="_blank">'+contArr[cId+'ShortTitle']+'</a><br />'+contArr[cId+'Desc']+'';
d.getElementById(cId.substring(0,2)+'Cont').innerHTML = html;
//var date=new Date();
//var day = date.getDate();
//var month = date.getMonth();
//var today = day+'.'+(month+1);
var dates=new Array("19.6","20.6","21.6","10.7","11.7","12.7","24.7","25.7","26.7","21.8","22.8","23.8","28.8","29.8","30.8",
"11.9","12.9","13.9","25.9","26.9","27.9","2.10","3.10","4.10","16.10","17.10","18.10","30.10","31.10","1.11");
if (cId == 'sp1' || cId == 'sp2' || cId == 'sp3' || cId == 'sp4'){
d.getElementById('sportni').style.backgroundImage = '';
}
if (cId == 'sp5' && inArray(dates, today)){
d.getElementById('sportni').style.backgroundImage = 'url(/resources/im/nescafe_bg.gif)';
d.getElementById('sportni').style.backgroundRepeat = 'no-repeat';
d.getElementById('sportni').style.backgroundPosition = 'top right';
}
}
/*function showAukro(aukroCat) {
    document.getElementById('auc47240').className = document.getElementById('auc47244').className =
    document.getElementById('auc47437').className = document.getElementById('auc47243').className =
    document.getElementById('auc50930').className = document.getElementById('auc47237').className = "aucCatBtn";
    document.getElementById(aukroCat).className += " aucCatBtnSel";
    var cat = aukroCat.substring(3);
    var html = '';
    for(var i = 0; i < 3; i++){
        html += '<div class="aucCatContBox"><div class="aucCatContItem">';
        html += '<a href="'+aucArr[cat]['auc'+i+'Link']+'" target="_blank">';
        html += '<img src="'+aucArr[cat]['auc'+i+'Photo']+'" onerror="this.onerror=null;this.src=\'/resources/im/missing.gif\';" alt="'+aucArr[cat]['auc'+i+'Desc']+'" title="'+aucArr[cat]['auc'+i+'Desc']+'" border="0"/></a><br/>';
        html += aucArr[cat]['auc'+i+'Price']+' лв.';
        html += '</div></div>';
    }
    document.getElementById('aucCatCont').innerHTML = html;
}*/
function randomAukro() {
    var selAukro = "auc" + (getRondomDigit(5)-1);
    showAukro(selAukro);
}
function showAukro(aukroCat) {
    for(var k = 0; k < 6; k++){
        document.getElementById('auc'+k).className = "aucCatBtn";
    }
    document.getElementById(aukroCat).className += " aucCatBtnSel";
    var cat = aukroCat.substring(3);
    var html = '';
    for(var i = 0; i < 3; i++){
        html += '<div class="aucCatContBox"><div class="aucCatContItem">';
        html += '<a href="'+aucArr[cat]['auc'+i+'Link']+'" target="_blank">';
        html += '<img src="'+aucArr[cat]['auc'+i+'Photo']+'" onerror="this.onerror=null;this.src=\'/resources/im/missing.gif\';" alt="'+aucArr[cat]['auc'+i+'Desc']+'" title="'+aucArr[cat]['auc'+i+'Desc']+'" border="0"/></a><br/>';
        html += aucArr[cat]['auc'+i+'Price']+' лв.';
        html += '</div></div>';
    }
    document.getElementById('aucCatCont').innerHTML = html;
}
function getElementsByClassName(classname) {
    var a = [];
    var re = new RegExp('b' + classname + 'b');
    var els = document.all?document.all:document.getElementsByTagName("*");
    for(var i=0,j=els.length; i<j; i++){
        if(els[i].className.indexOf(re))a.push(els[i]);
    }
    return a;
}
function getTabId(type, newsCount){  // type is 'prev' or 'next'
numId = getIntFromId(selTab5);
if (type == 'next') {
if (numId < newsCount) numId += 1;
else  numId = 1;
} else if (type == 'prev') {
if (numId > 1) numId -= 1;
else numId = newsCount;
} else numId = 1;
objId = 'vi' + numId;
return objId;
}
function getTabIdSv(type, svCount){  // type is 'prev' or 'next'
numId = getIntFromId(selTab6);
if (type == 'next') {
if (numId < svCount) numId += 1;
else  numId = 1;
} else if (type == 'prev') {
if (numId > 1) numId -= 1;
else numId = svCount;
} else numId = 1;
objId = 'sv' + numId;
return objId;
}
function getTabIdSp(type, spCount){  // type is 'prev' or 'next'
numId = getIntFromId(selTab4);
if (type == 'next') {
if (numId < spCount) numId += 1;
else  numId = 1;
} else if (type == 'prev') {
if (numId > 1) numId -= 1;
else numId = spCount;
} else numId = 1;
objId = 'sp' + numId;
return objId;
}
function getRondomDigit(num){
i = Math.round(num*Math.random())+1;
return i;
}
function chBtn(btn){
btn.className = (btn.className == 'sel')?'':'sel';
}
function htmlspecialchars(str) {
if (typeof(str) == "string") {
str = str.replace(/&/g, "&amp;"); /* must do &amp; first */
str = str.replace(/"/g, "&quot;");
str = str.replace(/'/g, "&#039;");
str = str.replace(/</g, "&lt;");
str = str.replace(/>/g, "&gt;");
}
return str;
}
function strstr(_s, _k)
{
var _idx = _s.indexOf(_k);
if (_idx >= 0)
{
  return _s.substr(_idx);
}
else
{
  return false;
}
}
function SetCookie(name,value,expires,path,domain,secure){
var today=new Date();
today.setTime(today.getTime());
if(expires) expires=expires*1000*60*60*24;
var expires_date = new Date(today.getTime()+(expires));
document.cookie=name+"="+escape(value)+((expires)?";expires="+expires_date.toGMTString():"")+((path)?";path="+path:"")+((domain)?";domain="+domain:"")+((secure)?";secure":"");
}
function GetCookie(name) {
var start=document.cookie.indexOf(name+"=");
var len=start+name.length+1;
if((!start)&&(name!=document.cookie.substring(0,name.length))) return null;
if(start==-1) return null;
var end=document.cookie.indexOf(";",len);
if(end==-1) end=document.cookie.length;
return unescape(document.cookie.substring(len,end));
}
function DeleteCookie(name,path,domain){
var exp=new Date();
exp.setTime(exp.getTime()-1);
if(GetCookie(name)) document.cookie=name+"="+((path)?";path="+path:"")+
((domain)?";domain="+domain:"" )+";expires="+exp.toGMTString();
}
function checkCoockie(domain){
if (document.cookie.length) return 1;
else return 0;
}
function isEmpty() {
v = document.catsrch.text_fld.value;
tVal = v.replace(/^\s+|\s+$/g, '');
//alert(tVal);
if (tVal.length == 0) {
return false;
}
document.catsrch.text_fld.value = tVal;
}
function chUrl(aObj, url){
if (aObj.className.indexOf('sel') != -1){
aObj.href = "/ukazatel/" + url;
return false;
} else aObj.href = "#a";
}
function hit(str){
var url="http://b.abv.bg/app/servlet/hitb?SID=2&PID=6666&banID="+str;
document.getElementById("countFirstClick").src = url;
}
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
document.getElementById('dirsContXml').innerHTML = html;
}
}
}
}
function pushDetails(id, vip) {
if(document.getElementById(id).className == "pusher_plus"){
hidediv('s'+id);
showdiv('sd'+id);
if(vip) showdiv('vipImage'+id);
document.getElementById(id).className = "pusher_minus";
}else{
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
function sendVoteRequest(id, vote, idOff){
	var req734 = null;
	if (window.XMLHttpRequest) {
	req734 = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	req734 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var url = "/link/vote/" + id + "/" + vote;
	req734.open("GET", url, true);
	req734.onreadystatechange = callback_sendvote( req734, idOff );
	req734.send(null);
}

function callback_sendvote(req, idOff) {
	return function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
			var root = req.responseXML.getElementsByTagName('root');
			document.getElementById(idOff).innerHTML = root[0].firstChild.nodeValue;
			document.getElementById(idOff).style.display = 'inline';
			}
		}
	}
}



function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function isUrlIndexed() {
	var url = trim(document.forms[0].url.value);
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

function getUrlIndexedInfo(isvip) {

 	isvip = typeof(isvip) != 'undefined' ? isvip : 0;
	var url = trim(document.forms[0].url.value);
	if(strstr(url, 'http://'))
	url = url.substring(7);
	getURLInfo(url, isvip);
}
function getURLInfo(url, isvip) {
	var link = "/link/geturlinfo/" + url;
	var req1 = null;
	if (window.XMLHttpRequest) {
		req1 = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		 req1 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req1.open("GET", link, true);
	req1.onreadystatechange = callback_urlinfo(req1, isvip);
	req1.send(null);
}
function callback_urlinfo(req, isvip){
	return function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				var name = '';
				var description = '';
				var keywords = '';
				var comments = '';
				if(req.responseXML != null){
				   name = (req.responseXML.getElementsByTagName("name")[0].firstChild != null) ?
				    req.responseXML.getElementsByTagName("name")[0].firstChild.nodeValue : '';
				   description = (req.responseXML.getElementsByTagName("description")[0].firstChild != null) ?
				    req.responseXML.getElementsByTagName("description")[0].firstChild.nodeValue : '';
				   keywords = (req.responseXML.getElementsByTagName("keywords")[0].firstChild != null) ?
				    req.responseXML.getElementsByTagName("keywords")[0].firstChild.nodeValue : '';
				   comments = (req.responseXML.getElementsByTagName("comments")[0].firstChild != null) ?
				    req.responseXML.getElementsByTagName("comments")[0].firstChild.nodeValue : '';

				   uri = (req.responseXML.getElementsByTagName("uri")[0].firstChild != null) ?
				    req.responseXML.getElementsByTagName("uri")[0].firstChild.nodeValue : '';

				    document.forms[0].url.value = uri;
				    document.forms[0].name.value = name;
				    document.forms[0].description.value = description;
				    document.forms[0].keywords.value = keywords;
				    document.forms[0].comments.value = comments;
					if(isvip == 0){
					    document.forms[0].name.disabled = true;
					    document.forms[0].description.disabled = true;
					    document.forms[0].keywords.disabled = true;
					    document.forms[0].comments.disabled = true;
					}
				    document.getElementById('urlmsg').innerHTML = '<span style="color:green; font-size: 11px;">Сайтът е индексиран в указателя на GBG.BG.</span>';
				    document.getElementById('namemsg').innerHTML = '';
				    document.getElementById('descriptionmsg').innerHTML = '';
				}
			}
		}
	}
}


var tabs = new Array('net','gyu','pix','video','nws','know');
function changeTab (tab) {
d.getElementById('schEn').className = tab.id;
for(var x in tabs){
d.getElementById(tabs[x]).className = tab.id != tabs[x]? '' : 'sel';
if(tab.id != tabs[x])
hidediv('mid'+tabs[x]);
else
showdiv('mid'+tabs[x]);
}
}
function _(o, _e, id){
return eval('o.getElementsByTagName("' + _e + '")[' + (id? id : 0) + ']');
}
function selCatTabImpl(id) {
selectCatTab(id);
timerId = 0;
}
function selCatTabDelay(id) {
timerId = setTimeout('selCatTabImpl(\''+id+'\')', 200);
}
function undoSelCatTab() {
if (timerId != 0)
clearTimeout(timerId);
}
