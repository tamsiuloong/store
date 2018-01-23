// 注册
Vue.component('my-userinfo', {
    template: '<div class="col-md-3" style="padding-top:20px">'+
    '<ol class="list-inline">'+
    '<li v-if="username === \'\'"><a href="login.htm">登录</a></li>'+
    '<li v-if="username === \'\'"><a href="register.htm">注册</a></li>'+
    '<span v-if="username != \'\'">欢迎您:{{username}}</span>'+
    '<a v-if="username != \'\'" href="#" @click="logout">退出</a>'+
    '<li><a href="cart.htm">购物车</a></li>'+
    '</ol>'+
    '</div>',
    data:function(){
        return {
            username:'',
            needLogin:'0'
        }
    },
    methods:{
        logout:function(){
            //ajax请求
            axios.get('user.do', {
                params: {
                    'method': 'logout'
                }
            }).then( (response)=> {
                if(response.data != undefined && response.data.ok){
                    this.username = '';
                }

            });
        }
    },
    created:function(){
        //ajax请求
        axios.get('user.do', {
            params: {
                'method': 'getUser'
            }
        }).then( (response)=> {
            const user = response.data.data;
            //alert(data);
            if(user != undefined && response.data.ok){
                this.username = user.username;
            }
            else{
                this.username='';
            }
        });
    }
})

