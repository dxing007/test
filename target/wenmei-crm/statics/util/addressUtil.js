var addressMap = null;
function getAddressMap(){
	if(addressMap==null){
		//alert(1222);
		addressMap = {};
		for(var key in pdata) { 
			var item = pdata[key];
			addressMap[item.code]= item;
		}	
	} 
	return 	addressMap;
}
function getProvinceList(code){
		var retList = [
		{"code":"","names":"--\u8BF7\u9009\u62E9--","level":"0"}
		];
		for(var key in pdata) { 
			var item = pdata[key];
			 if (parseInt(item.level) == 0) {
				 retList.push(item);
	         }
		};
		return retList ;
}
	
function getCityList(provicneCode){
		var retList = [];
		for(var key in pdata) { 
			var item = pdata[key];
			provicneCode = provicneCode.substring(0,2);
			if (parseInt(item.level) == 1 && provicneCode == item.code.substring(0,2)) {
				retList.push(item);
            }
		};
		return retList;
		
}

function getCityName(code){
		var ret = {}; 
		for(var key in pdata) { 
			var item = pdata[key];
			if (item.code==code) {
				ret = item;
                return ret;
            } 
		};
}
function getCityShowName(code,sp){
	console.log(34343);
	if(!code){
		return '';
	}
    var paromdeCode = code.substring(0,2)+'0000';
	var cityValue ='';
	var provinceValue='';
	//console.log(paromdeCode+"___"+code);
	for(var key in pdata) { 
		var item = pdata[key];
		if(item.code==paromdeCode){
			provinceValue = item.names;
		}
		if (item.code==code) {
			ret = item;
			cityValue= ret.names;
			if(sp){
				return provinceValue +sp+cityValue;
			}else{
				return provinceValue +"</br>"+cityValue;
			}
			
		} 
	}
	return  provinceValue+'-'+cityValue;
}