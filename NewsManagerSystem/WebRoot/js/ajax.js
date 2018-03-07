//获取ajax核心对象
function getXhr(){
		 	var xhr = null;
		 	if (window.XMLHttpRequest){
		 		xhr = new XMLHttpRequest();
		 	}else{
		 		xhr = new ActiveXobject("Microsoft.XMLHttp");
		 	}
		 	return xhr;
		 }