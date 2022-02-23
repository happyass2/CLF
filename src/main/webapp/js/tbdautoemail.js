(function(w){
	/*
        inputid:输入框id
        selectsuccess:选择值后的事件回调
        regpass:符合邮箱格式回调
        regerr:不符合邮箱格式回调
        tbdAutoEmailMap  :邮箱补全列表配置文件
        TbdAutoEmail.keydownevent  :利用键盘选取赋值成功 调用的额外回调 会返回操作输入框id
    */
	//自定义配置
	w.tbdAutoEmailMap=["qq.com","163.com","126.com","sina.com","hotmail.com","yahoo.com","sohu.com","yahoo.cn","gmail.com","tom.com"];
	//类
	w.TbdAutoEmail=function(opts){
		this.inputid=opts.inputid;
		this.selectsuccess=opts.selectsuccess;
		this.regpass=opts.regpass;
		this.regerr=opts.regerr;
		this.emailList=w.tbdAutoEmailMap  //邮箱补全列表
		this.eleinputid=document.getElementById(this.inputid);//输入框
		this.elebody=document.getElementsByTagName("body")[0];//body元素	
		this.elebox=document.createElement("div");//选择列表根元素
		this.elebox.id=this.inputid+"_layer";
		this.elebox.className="emaillayer";
		this.elebody.appendChild(this.elebox);//body元素 插入 选择列表根元素
		this.reg=/(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$)|(^\w+$)|(^\w+((-\w+)|(\.\w+))*$)|(^\w+((-\w+)|(\.\w+))*\@$)|(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+$)|(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*$)|(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.$)/;

		this.init=function(){
			this.addevent();
		};

		this.addevent=function(){
			var that=this;
			this.eleinputid.onkeyup=function(event){
				var ev=window.event || event;
				var target=ev.srcElement || ev.target;
				that.layer(this);
			};
			this.eleinputid.onfocus=function(){
				var alllist=document.getElementsByTagName("div");
				for(var i=0;i<alllist.length;i++){
					if(alllist[i].className=="emaillayer"){
						alllist[i].style.display="none";
					};
				};
				that.layer(this);
			};
			this.eleinputid.onclick=function(event){
				var ev=window.event || event;
				var target=ev.srcElement || ev.target;
				if(ev.stopPropagation){
					ev.stopPropagation();
				}else{
					ev.cancelBubble = true;
				};
			};
			this.elebox.onclick=function(event){
				var ev=window.event || event;
				var target=ev.srcElement || ev.target;
				that.eleinputid.value=target.innerHTML;
				that.elebox.style.display="none";
				if(that.selectsuccess){
					that.selectsuccess();
				};
				if(ev.stopPropagation){
					ev.stopPropagation();
				}else{
					ev.cancelBubble = true;
				};
			};
		};
		this.layer=function(self){
			//输入框x y 宽 高
			var ix=self.getBoundingClientRect().left;
			var iy=self.getBoundingClientRect().top;
			var iw=self.offsetWidth;
			var ih=self.offsetHeight;
			//输入框值位置宽度设置
			this.elebox.style.left=ix+"px";
			this.elebox.style.top=(iy+ih-1)+"px";
			this.elebox.style.width=iw+"px";
			//获取输入框值
			var dval=self.value.replace(/(^\s*)|(\s*$)/g, "");
			//获取@之前的值	
			if(dval==""){
				this.elebox.style.display="none";
				return false;
			};
			if(!this.reg.test(dval)){
				this.elebox.style.display="none";
				if(this.regerr){
					this.regerr();
				};
				return false;
			};
			if(this.regpass){
				this.regpass();
			};
			dval=this.realval(dval);
			//形成选择列表 插入到 选择列表根元素
			this.elebox.innerHTML="";
			for(var i=0;i<this.emailList.length;i++){
				var item=document.createElement("div");//选择列表根元素
				item.className="emaillayer_list";
				if(i==0){
					item.className="emaillayer_list active";
				};
				item.innerHTML=dval+"@"+this.emailList[i];
				this.elebox.appendChild(item);
			};
			//显示
			this.elebox.style.display="block";

		};
		this.hidelayer=function(){
			this.elebox.style.display="none";
		};
		this.realval=function(dval){
			var ri=dval.search("@");
			if(ri==-1){
				return dval;
			}else{
				return dval.substring(0,ri);
			};
		};



	};
	document.onclick=function(event){
		//页面点击 隐藏所有显示
		var alllist=document.getElementsByTagName("div");
		for(var i=0;i<alllist.length;i++){
			if(alllist[i].className=="emaillayer"){
				alllist[i].style.display="none";
			};
		};


	};
	w.TbdAutoEmail.keydownevent=null;
	document.onkeydown=function(event){
		//键盘赋值
		var ev=window.event || event;
		var keyc=ev.keyCode||ev.which||ev.charCode;
		var alllist=document.getElementsByTagName("div");
		var showautoemail=null;
		for(var i=0;i<alllist.length;i++){
			if(alllist[i].className=="emaillayer"){
				if(alllist[i].style.display=="block"){
					showautoemail=alllist[i];
				};
			};
		};
		if(showautoemail){//显示的弹窗根元素
			var showautoemailid=showautoemail.id;
			var showinput=document.getElementById(showautoemailid.substring(0,showautoemailid.lastIndexOf("_")));//显示的弹窗根元素 对应的input		

			var ai=0;//active位置
			var childlist=showautoemail.getElementsByTagName("div");
			for(var i=0;i<childlist.length;i++){
				if(childlist[i].className.search("active")!=-1){
					ai=i;
				};
			};
			childlist[ai].className="emaillayer_list";
			if(keyc==38){//上		
				showinput.blur();//阻止和输入框keyup冲突
				if(ai==0){
					childlist[childlist.length-1].className="emaillayer_list active";
					showinput.value=childlist[childlist.length-1].innerHTML;
				}else{
					childlist[ai-1].className="emaillayer_list active";
					showinput.value=childlist[ai-1].innerHTML;
				};
				if(TbdAutoEmail.keydownevent){
					TbdAutoEmail.keydownevent(showinput.id);
				};
				return false;//阻止页面滚动
			}else if(keyc==40){//下
				showinput.blur();//阻止和输入框keyup冲突
				if(ai==childlist.length-1){
					childlist[0].className="emaillayer_list active";
					showinput.value=childlist[0].innerHTML;
				}else{
					childlist[ai+1].className="emaillayer_list active";
					showinput.value=childlist[ai+1].innerHTML;
				};
				if(TbdAutoEmail.keydownevent){
					TbdAutoEmail.keydownevent(showinput.id);
				};
				return false;
			}else if(keyc==13){//回车
				showinput.blur();//阻止和输入框keyup冲突
				showinput.value=childlist[ai].innerHTML;
				showautoemail.style.display="none";
				if(TbdAutoEmail.keydownevent){
					TbdAutoEmail.keydownevent(showinput.id);
				};
			};
		};
	};
	w.TbdAutoEmail.setposition=function(){
		//位置社会资
		var alllist=document.getElementsByTagName("div");
		var showautoemail=null;
		for(var i=0;i<alllist.length;i++){
			if(alllist[i].className=="emaillayer"){
				if(alllist[i].style.display=="block"){
					showautoemail=alllist[i];
				};
			};
		};
		if(showautoemail){//显示的弹窗根元素
			var showautoemailid=showautoemail.id;
			var showinput=document.getElementById(showautoemailid.substring(0,showautoemailid.lastIndexOf("_")));//显示的弹窗根元素 对应的input
			//输入框x y 宽 高
			var ix=showinput.getBoundingClientRect().left;
			var iy=showinput.getBoundingClientRect().top;
			var iw=showinput.offsetWidth;
			var ih=showinput.offsetHeight;
			//输入框值位置宽度设置
			showautoemail.style.left=ix+"px";
			showautoemail.style.top=(iy+ih-1)+"px";
			showautoemail.style.width=iw+"px";
		};
	};
	window.onresize=function(){
		//窗口变化 重置位置
		w.TbdAutoEmail.setposition();
	};
	window.onscroll=function(){
		//滚动 重置位置
		w.TbdAutoEmail.setposition();
	};

})(window);
