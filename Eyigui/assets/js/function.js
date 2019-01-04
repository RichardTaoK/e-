
/*
	兼容方式document.getElementsByClassName
	IE6-8不兼容
	解决问题：
	    classname[string]代表我们的类名
	    range[object]代表上级 即范围  但一定是对象控制子元素  
	    因为判断的时候返回的值是true或者false
	    true代表有那个函数那么返回那个值就可以了
	    false代表没有那个值  先获取全部的标签名，当
	    all[i]=classname就说明找到了我们所要的名字
*/


function getclass(classname,range){
	if(document.getElementsByClassName){
	//如果兼容的话就会返回true
		// alert("支持")
		return range.getElementsByClassName(classname);
	}else{
		// alert("不支持")
		var all=range.getElementsByTagName('*')
		       //获取全部的类名把不支持的浏览器获取到全部的classname
		var arr=[];
		for(var i=0;i<all.length;i++){
			if(checkclass(all[i].className,classname)){//如果all[i]里你要调用的属性和你传进去的参数相等
				arr.push(all[i])//把所有的东西都放在一个数组里
				// all[i].className==classname
			}
		}
		return arr;//将每个数都传到里面去
	}
}

/*当我们发现一个类可以定义多个类名的时候定义一个函数
用来检测是否包含我们需要的类名 返回值是true或者false这是为了方便上一个函数的调用
tagname="aa inner ewf ce"
aname="inner"
通过split将tagname分割开变成数组[aa,inner,ewf,ce]
如果tagname[i]==aname则返回真或者假*/
function checkclass(tagname,aname){
    var arr=tagname.split(" ");
	for(var i=0;i<arr.length;i++){
		if(arr[i]==aname){
			return true;
		}
	}
		return false;//因为return后面不执行那么不走if的话就会执行false
}

// 获取文本内容
// 设置文本内容
//text(obj)获取文本内容
//text(obj,"123112132")设置文本内容
function text(obj,val){
	//如果是空内容的话返回的是false也不对所以设置为undefined
	if(val==undefined){
		if(obj.textContent!=undefined){
			return obj.textContent;
		}else{
			return obj.innerText;
		}
	}else{
		if(obj.textContent!=undefined){
			obj.textContent=val;
		}else{
			obj.innerText=val;
		}
	}
}


//获取通用样式getStyle(box[0],attr)
// obj要获取的对象
// attr是属性值
// 注意属性要放在[]里

function getStyle(obj,attr){
	if(obj.currentStyle){
		return obj.currentStyle[attr];
	}else{
		return window.getComputedStyle(obj,null)[attr];
	}
}




/*
1.获取页面元素
	$("#box")   获取id
	$(".box")	获取classname
	$("div")	获取标签名
2.页面加载元素
	$(function(){});

*/
function $(selector,range){
	//使document变简单
	//如果传进来的是字符串直接返回
	//如果传进来的是函数，那么
	// 选择器
	if(typeof selector=="string"){
		// alert("获取");
		// 标签名用的是正则表达式最少一位
		range=range||document;
		
		if(/^[a-zA-Z][a-zA-Z1-6]{0,9}$/.test(selector)){
			return range.getElementsByTagName(selector);
		}
		if(/^<[a-zA-Z][a-zA-Z1-6]{0,9}>$/.test(selector)){
			return range.createElement(selector.slice(1,-1));
		}
	// charAt用来找摸个位置的值
		if(selector.charAt(0)=="#"){
			//selector="#box"要把第一个去掉
			return document.getElementById(selector.slice(1));
		}
		if(selector.charAt(0)=="."){
			return getclass(selector.substr(1),range);
		}
	}else if(typeof selector=="function"){
		// alert("页面加载") ;
		window.onload=selector;
		/*on(window,'onload',selector);
		return selector();*/
	}
}

// 我们发现会有一些换行来干扰我们的使用，我们把不需要的去掉
/*
"    12fcsdc   "=>"12fcsdc"
"        "=>""



*/
	function trim(str){
		return str.replace(/^\s+|\s+$/g,"");//把字符传两边的空白去掉
	}

/*获取子元素*/
	function getChild(obj){
		var childs=obj.childNodes;//先获取对象的子元素
		var newArr=[];//新建一个数组来盛放需要的元素
		for(var i=0;i<childs.length;i++){
			if(!(childs[i].nodeType==8||(childs[i].nodeType==3&&trim(childs[i].nodeValue)==""))){
					newArr.push(childs[i]);//等于8的说明是注释等于3的和为空的表示是是换行
			}
		}
		return newArr;
	}

/*
获取下一个兄弟节点元素
obj代表对象
因为下一个兄弟节点要把注释和换行除去
*/
	function getbrother(obj){
		var next=obj.nextSibling;
		if(!next){return false;}
		while(next.nodeType==8||(next.nodeType==3&&trim(next.nodeValue)=="")){
			next=next.nextSibling;//如果为空的话，next继续往下面走
			if(!next){return false;}//如果没有下一个的话，就报错
		}
		return next;
	}


/*获取父元素的第一个元素*/

	function getFirst(parent){
		/*var first=getChild(parent);
		return first[0];*/
		return getChild(parent)[0]
	}

/*获取父元素的最后一个元素*/
	function getLast(parent){
		var last=getChild(parent);
		return last[last.length-1];
	}

		
/*获取父元素的每一个元素*/

	function getIndex(parent,index){
		for(var i=0;i<parent.length;i++){
			parent[i].index=i;
			var index=parent[i],iondex;
			return getChild(parent)[index];
		}
	}


/*获取父对象的上一个兄弟节点*/
	function getUp(obj){
		var up=obj.previousSibling;
		if(!up){return false;}
		while(up.nodeType==8||(up.nodeType==3&&trim(up.nodeValue)=="")){
			up=up.previousSibling;//如果为空的话，next继续往下面走
			if(!up){return false;}//如果没有下一个的话，就报错
		}
		return up;
	}



/*插入到某个对象之前*/
	function insertBefore(obj1,obj2){
		var parent=obj2.parentNode;
		parent.insertBefore(obj1,obj2); 
	}


/*插入到某个对象之后*/
	function insertAfter(obj,upobj){
		var next=getbrother(upobj);
		var parent=upobj.parentNode;
		if(next){
			parent.insertBefore(obj,next);
		}else{
			parent.appendChild(obj);
		}
	}


/*处理一次处理多个事件*/
/*function on(obj,ev,callback){
	if(obj.addEventListener){
		obj.addEventListener(ev,callback);
	}else{
		//ie 6-8
		function ooofffl(){
			callback.call(obj);
		}
		obj.attachEvent('on'+ev,obj.ooofffl);
	}
}*/


/*清除那多个事件*/
/*function off(obj,ev,callback){
	if(obj.removeEventListener){
		obj.removeEventListener(ev,callback);
	}else{
		obj.detEvent('on'+ev,obj.ooofffl);
	}
}*/


/*处理一次处理多个事件*/
	function on(obj,ev,callback){
		if(obj.addEventListener){
			obj.addEventListener(ev,callback);
		}else{
			//ie 6-8
			
			obj.attachEvent('on'+ev,function(){
				callback.call(obj);
			});
		}
	}


/*清除那多个事件*/
	function off(obj,ev,callback){
		if(obj.removeEventListener){
			obj.removeEventListener(ev,callback);
		}else{
			obj.detEvent('on'+ev,callback);
		}
	}




/*鼠标滚轮事件*/
	function onmouseWheel(obj,upcallback,downcallback){
		if(obj.attachEvent){
			obj.attachEvent("onmousewheel",scrollFn); //IE、 opera
		}else if(obj.addEventListener){
			obj.addEventListener("mousewheel",scrollFn,false);
			//chrome,safari -webkit-
			obj.addEventListener("DOMMouseScroll",scrollFn,false);
			//firefox -moz-
		}
		function scrollFn(e){//事件处理程序
			var ev=e||window.event;//解决兼容

			//事件对象阻止浏览器默认行为
			if(ev.preventDefault){
				ev.preventDefault();//阻止默认浏览器动作（W3C）c/f
			}else{
				ev.returnValue=false;//ie
			}

			//c/e:120  f:-3
			//c/e:-120 f:3
			var dir=ev.wheelDelta||ev.detail;//兼容
			if(dir==120||dir==-3){
				upcallback.call(obj);
			}
			else if(dir==-120||dir==3){
				downcallback.call(obj);
			}
		}
	}


