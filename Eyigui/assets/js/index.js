$(function(){
	var wheel=$('.wheel')[0];
	var imgbox=$('.imgbox',wheel)[0];
	var divs=$('div',imgbox);
	var imgjs=$('img',imgbox);

	var list=$('.list',wheel)[0];
	var lis=$('li',list);
	lis[0].style.background='#b498c5';
	var iw=window.innerWidth|| document.documentElement.clientWidth|| document.body.clientWidth;

	for(var i=0;i<divs.length;i++){
		divs[i].style.width=iw+'px';
		imgjs[i].style.width=iw+'px';
	}
	imgbox.style.width=iw*divs.length+'px';

	var index=0;
	function move(){
		index++;
		if(index==divs.length){
			index=0;
		}
		for(var j=0;j<divs.length;j++){
			lis[j].style.background='';
			// lis[j].style.border='';
		}
		lis[index].style.background='#b498c5';
		animate(imgbox,{left:-iw},500,function(){
			imgbox.appendChild(getFirst(imgbox));
			imgbox.style.left=0;
		});
	}
	var t=setInterval(move,2000);
	wheel.onmouseover=function(){
		clearInterval(t);
	}
	wheel.onmouseout=function(){
		t=setInterval(move,2000);
	}

	


	var center=$('.center')[0];
	var ss=$('.s',center);
	var links=$('.linkk');
	for(var i=0;i<ss.length;i++){
		ss[i].index=i;
		ss[0].style.borderBottom='10px #87c8db solid';
		ss[i].onclick=function(){
			for(var j=0;j<ss.length;j++){
				ss[j].style='none';
			}
			this.style.borderBottom='4px #87c8db solid';
			this.style.color='#fff';
			this.style.fontWeight='bold';
			for(var j=0;j<links.length;j++){
				links[j].style.display='none';
			}
			links[this.index].style.display='block';
		}
	}



	var an=$('.an')[0];
	var c1=$('.c1',an)[0];



	// sousuo
	var center=$('.center')[0];
	var icon=$('.icon1',center)[0];
	var input=$('.input',center)[0];
	icon.onclick=function(){
		input.style.display='block';
	}
	icon.onmouseover=function(){
		input.style.display='none';
	}


	var an=$('.an')[0];
	an.onclick=function(){
		an.style.display='block';
	}


})


