var json_login_ui={
		retCode:101,
		msg:'请求成功',
		data:{
			res_type:'1001',
			ui:{
				view_id:'0',
				parent_id:'-1',
				view_type:'9999',
				view_name:'VgContentLayout',
				view_width:'1080',
				view_height:'1920',
				bg_normal_color:'#ffffffff',
				bg_focus_color:'#ffffffff',
				view_of:['-1','-1','-1','-1'],
				view_paddings:['0','0','0','0'],
				view_margins:['0','0','0','0'],
				refs:[],
				gravity:'center',
				orientation:'0',
				centers:['1','4','7'],
				action_type:'null',
				action:'null',
				actionLink:{},
				res_key:'null',
				childs:[{
					view_id:'1',
					parent_id:'0',
					view_type:'9999',
					view_name:'VgContentLayout',
					view_width:'1080',
					view_height:'156',
					bg_normal_color:'#ffffffff',
					bg_focus_color:'#ffffffff',
					view_of:['-1','-1','-1','-1'],
					view_paddings:['0','0','0','0'],
					view_margins:['0','470','0','0'],
					gravity:'',
					centers:[],
					orientation:'0',
					refs:[],
					action_type:'null',
					action:'null',
					actionLink:{},
					res_key:'null',
					childs:[{
						view_id:'2',
						parent_id:'1',
						view_type:'10001',
						view_name:'VgTextView',
						view_width:'256',
						view_height:'136',
						bg_normal_color:'#ffffffff',
						bg_focus_color:'#ffffffff',
						view_of:['-1','-1','-1','-1'],
						view_paddings:['10','10','10','10'],
						view_margins:['10','10','10','10'],
						gravity:'center',
						centers:[],
							orientation:'0',
						refs:[],
						action_type:'null',
						action:'null',
						actionLink:{},
						res_key:'null',
						text:'帐号:',
						input_type:'1',
						hint:'',
						text_size:'48',
						text_align:"2",
						text_color:'#ffc8c8c8',
						childs:[]
					},{
						view_id:'3',
						parent_id:'1',
						view_type:'10002',
						view_name:'VgTextField',
						view_width:'824',
						view_height:'136',
						bg_normal_color:'#ff00ff00',
						bg_focus_color:'#ffffcccc',
						view_of:['2','-1','-1','-1'],
						view_paddings:['30','30','30','30'],
						view_margins:['20','20','20','20'],
						gravity:'',
						centers:[],
							orientation:'0',
						refs:[],
						action_type:'',
						action:'null',
						actionLink:{},
						res_key:'username',
						text:'test',
						password:'0',
						text_align:"1",
						input_type:'1',
						hint:'请填写手机号',
						text_size:'48',
						text_color:'#ffc8c8c8',
						childs:[]
					}]
				},{
						view_id:'4',
						parent_id:'0',
						view_type:'9999',
						view_name:'VgContentLayout',
						view_width:'1080',
						view_height:'156',
						bg_normal_color:'#ffffffff',
						bg_focus_color:'#ffffffff',
						view_of:['-1','1','-1','-1'],
						view_paddings:['0','0','0','0'],
						view_margins:['0','10','0','0'],
						gravity:'',
						centers:[],
							orientation:'0',
						refs:[],
						action_type:'null',
						action:'null',
						actionLink:{},
						res_key:'null',
						childs:[{
								view_id:'5',
								parent_id:'4',
								view_type:'10001',
								view_name:'VgTextView',
								view_width:'256',
								view_height:'136',
								bg_normal_color:'#ffffffff',
								bg_focus_color:'#ffffffff',
								view_of:['-1','-1','-1','-1'],
								view_paddings:['10','10','10','10'],
								view_margins:['10','10','10','10'],
								gravity:'',
								centers:[],
									orientation:'0',
								refs:[],
								action_type:'null',
								action:'null',
								actionLink:{},
								res_key:'null',
								text:'密码:',
								input_type:'1',
								hint:'',
								text_size:'48',
								text_align:"2",
								text_color:'#ffc8c8c8',
								childs:[]
							},{
								view_id:'6',
								parent_id:'4',
								view_type:'10002',
								view_name:'VgTextField',
								view_width:'824',
								view_height:'136',
								bg_normal_color:'#ff00ff00',
								bg_focus_color:'#ffffcccc',
								view_of:['5','-1','-1','-1'],
								view_paddings:['30','30','30','30'],
								view_margins:['20','20','20','20'],
								gravity:'',
								centers:[],
									orientation:'0',
								refs:[],
								action_type:'null',
								action:'null',
								actionLink:{},
								res_key:'userpass',
								text:'123456',
								input_type:'2',
								password:'1',
								text_align:'1',
								hint:'请填写密码',
								text_size:'48',
								text_color:'#ffc8c8c8',
								childs:[]
							}]
					},{
						view_id:'7',
						parent_id:'0',
						view_type:'10003',
						view_name:'VgButton',
						view_width:'1080',
						view_height:'136',
						bg_normal_color:'#ffffcccc',
						bg_focus_color:'#ff0099cc',
						view_of:['-1','4','-1','-1'],
						view_paddings:['10','10','10','10'],
						view_margins:['20','48','20','20'],
						gravity:'',
						centers:[],
							orientation:'0',
						refs:[],
						action_type:'null',
						actionLink:{
							event_type:'1002',
							link:[{
								actionType:'10014',
								url:'http://192.168.16.198:8082/',
								ref_ui:['3','6'],
								params:[
									'device_id=12345678',
									'code=loginAction'
								]
							}]
						},
						action:'null',
						res_key:'',
						text:'登陆',
						text_size:'48',
						text_align:'0',
						text_color:'#ff000000',
						childs:[]
					},{
						view_id:'8',
						parent_id:'0',
						view_type:'9999',
						view_name:'VgContentLayout',
						view_width:'1080',
						view_height:'156',
						bg_normal_color:'#ffffffff',
						bg_focus_color:'#ffffffff',
						view_of:['-1','7','-1','-1'],
						view_paddings:['0','0','0','0'],
						view_margins:['0','10','0','0'],
						gravity:'',
						centers:[],
							orientation:'0',
						refs:[],
						action_type:'null',
						action:'null',
						actionLink:{},
						res_key:'null',
						childs:[{
								view_id:'9',
								parent_id:'8',
								view_type:'10001',
								view_name:'VgTextView',
								view_width:'256',
								view_height:'136',
								bg_normal_color:'#ffffffff',
								bg_focus_color:'#ffffffff',
								view_of:['-1','-1','-1','-1'],
								view_paddings:['10','10','10','10'],
								view_margins:['568','10','10','10'],
								gravity:'',
								centers:[],
									orientation:'0',
								refs:[],
								action_type:'null',
								action:'null',
								actionLink:{},
								res_key:'null',
								text:'找回密码',
								input_type:'1',
								hint:'',
								text_size:'48',
								text_align:"2",
								text_color:'#ff0000ff',
								childs:[]
							},{
								view_id:'10',
								parent_id:'8',
								view_type:'10001',
								view_name:'VgTextView',
								view_width:'256',
								view_height:'136',
								bg_normal_color:'#ffffffff',
								bg_focus_color:'#fffffff',
								view_of:['9','-1','-1','-1'],
								view_paddings:['30','30','30','30'],
								view_margins:['20','20','20','20'],
								gravity:'',
								centers:[],
									orientation:'0',
								refs:[],
								action_type:'null',
								action:'null',
								actionLink:{},
								res_key:'userpass',
								text:'注   册',
								input_type:'2',
								password:'1',
								text_align:'1',
								hint:'',
								text_size:'48',
								text_color:'#ff0000ff',
								childs:[]
							}]
					}],
			
		}
		
	}
};
function welcome(req,res,data){
	
	var string =JSON.stringify(json_login_ui);
	res.end(string);
}
exports.welcome=welcome;