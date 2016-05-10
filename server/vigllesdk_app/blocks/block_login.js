
function login(req,res,DATA){
	var json_action={
		retCode:101,
		data:{
			res_type:'1002',
			actionLink:{
				event_type:'1000',
				link:[{
					actionType:'10009',
					url:'http://192.168.16.198:8082/',
					params:[
						'device_id=12345678',
						'code=main'
					]
				}]
			}
		},
		msg:'登录成功'
	};
	var username=DATA.username;
	var password=DATA.userpass;
	if(username=='test'&&password=='123456'){
		res.end(JSON.stringify(json_action));
	}else{
		res.end(JSON.stringify({retCode:100,msg:'登录失败'}));
	}
}
exports.login=login;