
function httpGet(){

	var http = require('http');
	var url= require('url');
	var querystring = require('querystring');

	/**
	 *===========================================
	 *         自定义模块 start
	 *===========================================
	 */
	var welcome_block=require('./blocks/block_welcome');
	var login_block=require('./blocks/block_login');
	var home_block=require('./blocks/block_home');
	var first_ui=require('./blocks/first_welcome_ui');
	var main_ui=require('./blocks/main_1');
	var panel=require('./blocks/panel');

	/**
	 *===========================================
	 *          自定义模块 end
	 *===========================================
	 */
	http.createServer(function (request,response){
		request.setEncoding("utf8");
		response.writeHead(200, {'Content-Type': 'text/html'});
		if(request.method=="POST"){
		   
			var body = '';	
			request.on('data', function (data) {//不停地接收数据
				body += data;
			//	console.log("Partial body: " + body);
			});
			request.on('end', function () {//当数据接收完毕时,会自动回调该方法,所以只要在此进行参数校验,并根据校验情况响应请求
				console.log("Body: " + body);
				var objectPostData = querystring.parse(body); 
				var da=JSON.stringify(objectPostData);
				console.log(da);
				var code=objectPostData.code;
				if(code=='welcome'){
					welcome_block.welcome(request,response,objectPostData);
				}else if(code=='home'){
					home_block.loadHome(request,response,objectPostData);
				}else if(code=='panel'){
					panel.panel(request,response,objectPostData);
				}else if(code=='login'){
					login_block.login(request,response,objectPostData);
				}else if(code='main'){
					main_ui.loadMain(request,response,objectPostData);
				}else{
					response.end('Permission is denied');
				}
				
			});
			
		}else{
			
			var params=url.parse(request.url,true).query;
			console.log(params);
			var code=params.code;
			if(code=='first'){
					first_ui.first_ui(request,response,params);
			}
			else if(code==null){
				var string =JSON.stringify(exports.loginUIJson);
					response.end(string);
			}else if(code=='login'){
				var username=params.username;
				var password=params.userpass;
				console.log(code);
				console.log(username);
				console.log(password);
				if(username=='test'&&password=='123456'){
					//response.end(JSON.stringify({retCode:101,data:'http://192.168.16.198:8082/',msg:'登录成功'}));
					login_block.login(request,response,params);
				}else{
					response.end(JSON.stringify({retCode:100,msg:'登录失败'}));
				}
			}else if(code=='panel'){
					panel.panel(request,response,params);
					
			}else if(code=='home'){
					home_block.loadHome(request,response,params);
			}else if(code=='welcome'){
				welcome_block.welcome(request,response,params);
			}else if(code='main'){
				main_ui.loadMain(request,response,params);
			}
		}
		
	}).listen(8082);
}
exports.httpGet=httpGet;
httpGet();


